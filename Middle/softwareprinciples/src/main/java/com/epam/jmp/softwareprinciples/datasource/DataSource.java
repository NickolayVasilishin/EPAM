package com.epam.jmp.softwareprinciples.datasource;

import com.epam.jmp.softwareprinciples.model.ClientData;

/**
 * Created by User on 3/8/2017.
 */
public interface DataSource {
    public ClientData getCard();
    public void saveCard(ClientData card);
}
