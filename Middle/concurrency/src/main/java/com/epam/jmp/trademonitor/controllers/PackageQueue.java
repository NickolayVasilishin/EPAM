package com.epam.jmp.trademonitor.controllers;

import java.util.List;
import java.util.Observable;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

import com.epam.jmp.trademonitor.data.IncomingPackage;



public class PackageQueue extends Observable{
	private Queue<IncomingPackage> packages;
	private boolean logging = false;
	private Logger logger;
	
	public PackageQueue(PackageProcessor processor) {
		packages = new ConcurrentLinkedQueue<>();
		addObserver(processor);
	}
	
	public void addPackages(List<IncomingPackage> packages){
		this.packages.addAll(packages);
		setChanged();
		notifyObservers(packages.size());
		log("Packages added, observers notified");
	}
	
	public IncomingPackage getPackage(){
		return packages.poll();
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
