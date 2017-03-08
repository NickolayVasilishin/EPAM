package com.epam.jmp.softwareprinciples.factory;

import com.epam.jmp.softwareprinciples.datasource.DataBaseDataSource;
import com.epam.jmp.softwareprinciples.datasource.DataSource;
import com.epam.jmp.softwareprinciples.datasource.FileSystemDataSource;

import java.io.File;

/**
 * Created by User on 3/8/2017.
 */
public class SimpleDataSourceFactory implements DataSourceFactory {
    @Override
    public DataSource getDataSource(String url) {
        File file = new File(url);
        if (file.exists()) {
            System.out.println("Opening file.");
            return FileSystemDataSource.open(file);
        } else {
            System.out.println("Opening connection to DB.");
            return new DataBaseDataSource(url);
        }
    }

    public static void main(String[] args) {
        new SimpleDataSourceFactory().getDataSource("jdbc:derby://ser.ver:8080");
    }
}
