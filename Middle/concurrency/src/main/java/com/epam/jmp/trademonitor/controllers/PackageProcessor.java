package com.epam.jmp.trademonitor.controllers;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import com.epam.jmp.trademonitor.data.DividentPackage;
import com.epam.jmp.trademonitor.data.IncomingPackage;
import com.epam.jmp.trademonitor.data.OutputPackage;

public class PackageProcessor implements Observer, Runnable{
	private final static int THREAD_COUNT = 10;
	public final static String DEFAULT_OUTPUT_PATH = "/output";
	private PackageQueue packageQueue;
	private final ExecutorService workerPool;
	private boolean packagesInQueue; 
	private int count;
	private boolean logging;
	private Logger logger;
	private File path;
	
	//Initializes pool with 10 reusable threads
	//packages are writing themselves so path is delegating by thread worker to each package constructor
	public PackageProcessor(String pathOut){
		workerPool = Executors.newFixedThreadPool(PackageProcessor.THREAD_COUNT);
		packagesInQueue = false;
		path = new File(PackageExplorer.dir.getAbsoluteFile(), pathOut);
		
	}
	
	//links processor with queue
	public void setPackageQueue(PackageQueue queue){
		packageQueue = queue;
	}

	//notifies when packages are available to be processed
	@Override
	public void update(Observable arg0, Object arg1) {
		count += (Integer) arg1;
		packagesInQueue = true;
		log("Updated observers, count incremented by " + arg1);
	}
	
	//processor routine
	//checks available packages and gives them to threads
	//sleeps in other way
	@Override
	public void run() {
		log("Started worker pool");
		while(true){
			if(hasWork()){
				while(count-- != 0){
					//TODO
					log("Taking package");
					workerPool.execute(new Worker(packageQueue.getPackage()));
				}
				packagesInQueue = false;
			}
			else
				try {
					log("No work, sleeping");
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		} 
	}
	
	
	
	private boolean hasWork(){
		return packagesInQueue;
	}
	
	//single thread
	protected class Worker implements Runnable{
		private IncomingPackage incoming;

		Worker(IncomingPackage incoming){
			this.incoming = incoming;
		}
		
		//checks dates and decides which package to create
		//packages use self-writing use incoming package fields and path
		private void process() throws ParseException {
			if (incoming != null) {

				// sd before ex, sd before rec
				boolean[] state = new boolean[2];
				log("Compare dates");
				if (compareDates(incoming.getSettledDate(),
						incoming.getExDate()) <= 0)
					state[0] = true;
				if (compareDates(incoming.getSettledDate(),
						incoming.getRecDate()) <= 0)
					state[1] = true;
				log("Writing processed data into file");
				try {
					if (state[0])
						new DividentPackage(incoming, true).writeToFile(path);
					if (!state[0] && state[1])
						new DividentPackage(incoming, false).writeToFile(path);
					;
					if (!state[1])
						new OutputPackage(incoming).writeToFile(path);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		private int compareDates(String date1, String date2) throws ParseException{
			SimpleDateFormat format = new SimpleDateFormat("ddmmyyyy");
			return format.parse(date1).compareTo(format.parse(date2));
		}
		
		@Override
		public void run() {
			try {
				process();
			} catch (ParseException e) {
				e.printStackTrace();
			}		
		}
		
	}

	public void setLogging() {
		logging = true;
		logger = Logger.getLogger(getClass().toString());
	}
	
	private void log(String message){
		if(logging)
			logger.info(message);
	}

}
