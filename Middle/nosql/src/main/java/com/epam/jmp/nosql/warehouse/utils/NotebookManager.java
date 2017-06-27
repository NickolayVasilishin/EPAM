package com.epam.jmp.nosql.warehouse.utils;

import com.epam.jmp.nosql.warehouse.model.SimpleNotebook;

/**
 * Created by Nikolay_Vasilishin on 6/27/2017.
 */
public interface NotebookManager {
    SimpleNotebook getNotebook(String user);
}
