package com.epam.jmp.trademonitor.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

import com.opencsv.CSVWriter;

public class DividentPackage extends TradePackage {
	private final static String PREFIX = "DIV_";
	
	private UUID dividendID;
	private String receiveAccount;
	private CSVWriter writer;
	
	public DividentPackage(IncomingPackage incoming, boolean toSeller) {
		exDate = incoming.getExDate();
		recDate = incoming.getRecDate();
		amountPerShare = incoming.getAmountPerShare();
		
		dividendID = UUID.randomUUID();
		tradeID = incoming.getTradeID();
		if(toSeller)
			receiveAccount = incoming.getSellerAccount();
		else
			receiveAccount = incoming.getBuyerAccount();
		amount = incoming.getAmount() * incoming.getAmountPerShare();
	}
	
	
	
	@Override
	public void writeToFile(File path) throws IOException{
		String filename;
		filename = DividentPackage.PREFIX + exDate + "_" + recDate + "_" + amountPerShare + ".csv";
		File file = new File(path.getAbsoluteFile(), filename);
		writer = new CSVWriter(new FileWriter(file));
		String[] line = {
				dividendID.toString(),
				tradeID,
				receiveAccount,
				"" + amount
		};		
		writer.writeNext(line);
		writer.close();
	}
	
	
	
	
	
}
