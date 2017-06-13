package com.epam.jmp.trademonitor.data;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import com.opencsv.CSVReader;

public class IncomingPackage extends TradePackage {
	private String sellerAccount;
	private String buyerAccount;
	private String tradeDate;
	private String settledDate;
	
	@Override
	public void readFromFile(File file) throws IOException{
		
		//Get info from filename
		StringTokenizer tokenizer = new StringTokenizer(file.getName(), "<>_.");
		this.shareName = tokenizer.nextToken();
		this.exDate = tokenizer.nextToken();
		this.recDate = tokenizer.nextToken();
		this.amountPerShare = Integer.valueOf(tokenizer.nextToken());
		
		//Get info from file
		reader = new CSVReader(new FileReader(file));
		String[] fields;
		fields = reader.readNext();
		
		//TODO Check format
		this.tradeID = fields[0];
		this.sellerAccount = fields[1];
		this.buyerAccount = fields[2];
		this.amount = Integer.valueOf(fields[3]);
		this.tradeDate = fields[4];
		this.settledDate = fields[5];
		
		reader.close();
		
	}

	public String getSellerAccount() {
		return sellerAccount;
	}

	public String getBuyerAccount() {
		return buyerAccount;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public String getSettledDate() {
		return settledDate;
	}
	
	
	
}
