package com.epam.jmp.trademonitor.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import com.epam.jmp.trademonitor.data.IncomingPackage;

public class PackageExplorer {
	
	PackageQueue queue;
	
	//Defaults
	public final static String DEFAULT_INCOMING_PATH = "input/";
	public final static String DEFAULT_OLD_PATH = "old/";
	public final static long DEFAULT_REFRESH_RATE = 100L;
	
	//From properties
	private File pathIncoming;
	private File pathOld;
	private long refreshRate;
	private int limit = 500; //TODO remove hardcode
	
	public static File dir = new File(".");
	private List<File> files;
	private long lastModified;
	private boolean logging = false;
	private Logger logger;
	
	public PackageExplorer(PackageQueue queue, String pathIn, String pathOld, long rate) {
		this.queue = queue;
		pathIncoming = new File(dir, pathIn);
		this.pathOld = new File(dir, pathOld);
		refreshRate = rate;
	}
	
	//Loads files from incoming directory and stores them in linked list
	//stops when reaches limit
	private void loadFiles(){
		int count = 0;
		files = new LinkedList<>();	
		for(File each:pathIncoming.listFiles()){
			if(each != null && each.isFile())
				files.add(each);
			if(count++ == limit)
				break;
		}
	}
	
	//Checks if directory was modified (by somebody else)
	private boolean modified(){
		long lastMod = pathIncoming.lastModified();
		if(lastModified == lastMod)
			return false;
		else {
			lastModified = lastMod;
			return true;
		}
	}
	
	//Correctly (i guess) moves files to directory with old files
	private void moveFile(File file){
		Path movefrom = FileSystems.getDefault().getPath(file.getAbsoluteFile().toString());
        Path target = FileSystems.getDefault().getPath(new File(pathOld.getAbsoluteFile(), file.getName()).toString());
        
        try {
			Files.move(movefrom, target, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//checks if directory is modified, then processes files into
	//packages and moves processed files to /old
	public void inspectIncoming() throws InterruptedException{
		List<IncomingPackage> packages = new LinkedList<>();
		
		while(true){
			log("Searching files.");
			if(modified()){
				log("New files!");
				loadFiles();
				for(File each:files){
					IncomingPackage p = new IncomingPackage();
					try {
						p.readFromFile(each);
						logBlack("read file " + each.getAbsolutePath());
						packages.add(p);
					} catch (IOException e) {
						e.printStackTrace();
						continue;
					}
						moveFile(each);
				}
				modified();
				queue.addPackages(packages);
			} else
				Thread.sleep(refreshRate);
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
	
	//just for contrast message
	private void logBlack(String message){
		if(logging)
			System.out.println(message);
	}

}
