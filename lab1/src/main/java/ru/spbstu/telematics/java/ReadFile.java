package ru.spbstu.telematics.java;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class ReadFile
{
    public static void main( String[] args )
    {
        System.out.println( "Hello_World!"  );
    }

    static String read(String path) throws IOException
    {
        FileReader reader=new FileReader(path);
        String out="";
        int c;
        while((c= reader.read())!=-1)
        {
            out+=(char)c;
        }
        return out;
    }

}
