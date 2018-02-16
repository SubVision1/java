package ru.spbstu.telematics.java;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReadFileTest {

    @Test
    public void Test(){
        String fileName="testFile.txt";
        String in="testFile Entry";
        String out="";

        try
        {
            FileWriter writer = new FileWriter(fileName, false);
            writer.write(in);
            writer.flush();
            writer.close();

            out= ReadFile.read(fileName);
            File file=new File(fileName);
            file.delete();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
            System.out.println("\nFile writing failed");
        }
        if(in.equals(out)) System.out.println("\nTest OK");
        else System.out.println("\nTest Failed");
    }

}