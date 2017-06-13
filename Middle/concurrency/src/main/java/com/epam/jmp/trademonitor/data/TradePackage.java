package com.epam.jmp.trademonitor.data;

import java.io.File;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

/*
 *  abstract class wrapper for data
 *  stores generic fields and read-write methods
 */

public abstract class TradePackage {
	protected CSVReader reader;
	protected CSVWriter writer;
	
	protected String shareName;
	protected String exDate;
	protected String recDate;
	protected int amountPerShare;
	
	protected String tradeID;
	protected int amount;
	
	public String getTradeID(){
		return tradeID;
	}
	
	public String getExDate() {
		return exDate;
	}

	public String getRecDate() {
		return recDate;
	}

	public int getAmount(){
		return amount;
	}

	public void setTradeID(String tradeID) {
		this.tradeID = tradeID;
	}

	public int getAmountPerShare() {
		return amountPerShare;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void readFromFile(File file) throws IOException{
		throw new IOException();
	}
	
	public void writeToFile(File path) throws IOException{
		throw new IOException();
	}
}
