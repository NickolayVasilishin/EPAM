package com.epam.jmp.softwareprinciples.datasource;

import com.epam.jmp.softwareprinciples.factory.DataSourceFactory;
import com.epam.jmp.softwareprinciples.factory.SimpleDataSourceFactory;
import com.epam.jmp.softwareprinciples.model.ClientCard;
import com.epam.jmp.softwareprinciples.model.ClientData;
import org.junit.Test;
import java.io.File;

public class ConsumerTest {

    @Test
    public void testFactory() {
        DataSourceFactory factory = new SimpleDataSourceFactory();
        DataSource dataSource = factory.getDataSource("src/test/resources/card.txt");
        ClientData clientData = dataSource.getCard();

        org.junit.Assert.assertEquals(clientData, new ClientCard("Fillip J.,D.C.", ","));
    }


}