package com.epam.jmp.softwareprinciples.datasource;

import com.epam.jmp.softwareprinciples.model.ClientCard;
import com.epam.jmp.softwareprinciples.model.ClientData;

/**
 * Created by User on 3/8/2017.
 */
public class DataBaseDataSource implements DataSource{
    public DataBaseDataSource(String url) {

    }

    @Override
    public ClientData getCard() {
        // some cool stuff with jdbc
        ClientCard clientCard = new ClientCard();
        clientCard.setAddress("");
        clientCard.setName("");
        return clientCard;
    }

    @Override
    public void saveCard(ClientData card) {
        // some cool stuff with jdbc
    }
}
