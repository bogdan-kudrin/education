package com.company;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * User: BKudrin
 * Date: 28.04.2015
 * Time: 17:21
 */
public class CustomWriter extends Writer {

    private static final CustomWriter INSTANCE = new CustomWriter();




    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {

    }

    @Override
    public void flush() throws IOException {

    }

    @Override
    public void close() throws IOException {

    }
}
