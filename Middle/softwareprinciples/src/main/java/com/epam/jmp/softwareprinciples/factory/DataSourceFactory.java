package com.epam.jmp.softwareprinciples.factory;

import com.epam.jmp.softwareprinciples.datasource.DataSource;

/**
 * Created by User on 3/8/2017.
 */
public interface DataSourceFactory {
    public DataSource getDataSource(String url);
}
