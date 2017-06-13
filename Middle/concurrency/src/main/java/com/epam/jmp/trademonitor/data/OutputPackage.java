package com.epam.jmp.trademonitor.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

import com.opencsv.CSVWriter;

public class OutputPackage extends TradePackage {
	private final static String PREFIX = "CAMMIS_";
	
	private UUID claimID;
	private String receiveAccount;
	private String entitledAccount;
	
	public OutputPackage(IncomingPackage incoming) {
		exDate = incoming.getExDate();
		recDate = incoming.getRecDate();
		amountPerShare = incoming.getAmountPerShare();
		
		claimID = UUID.randomUUID();
		tradeID = incoming.getTradeID();
		receiveAccount = incoming.getBuyerAccount();
		entitledAccount = incoming.getSellerAccount();
		amount = incoming.getAmount() * incoming.getAmountPerShare();
	}
	
	
	@Override
	public void writeToFile(File path) throws IOException {
		String filename;
		filename = OutputPackage.PREFIX + exDate + "_" + recDate + "_" + amountPerShare + ".csv";
		File file = new File(path, filename);
		writer = new CSVWriter(new FileWriter(file));
		String[] line = {
				claimID.toString().substring(0, 10),
				tradeID,
				receiveAccount,
				entitledAccount,
				"" + amount
		}; 
		writer.writeNext(line);
		writer.close();
	}
	
	
	
}
