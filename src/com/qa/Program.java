package com.qa;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Program {
    static String[] devs = {"Abe", "Anwar", "Ben", "Chloe", "Ciara", "Danielle", "Dinaka", "Fatima", "Jema", "Joe", "Kyle", "Sarah", "Zita" };

    public static void main(String[] args) {
        try {
            simpleFileRead();
//            simpleFileWrite();
//            bufferedFileWrite();
        } catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void simpleFileRead() throws IOException {
        FileInputStream fin = new FileInputStream(new File("Brian.txt"));

        byte[] bArray = new byte[256];
        int tot = 0, numRead;
        for (; (numRead = fin.read(bArray)) != -1; tot += numRead) {
            System.out.print(new String(bArray, 0, numRead));}
        System.out.println("\nPrinted a total of " + tot + " bytes");

    }
    public static void simpleFileWrite() throws IOException {
        List<String> developers = new ArrayList<String>();
        developers.addAll(Arrays.asList(devs));

        byte[] b;
        FileOutputStream fout = null;

        try{
            fout =new FileOutputStream("Cohort1.txt");
            for (String name : developers) {
                name += "\n";
                b = name.getBytes();
                fout.write(b);
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
            }
        finally{
            fout.close();
            System.out.println("Success...Cohort 1 names saved");
        }
    }
    public static void bufferedFileWrite() throws IOException {

        List<String> developers = new ArrayList<String>();
        developers.addAll(Arrays.asList(devs));

        byte[] b;
        FileOutputStream fout = null;
        OutputStreamWriter osr = null;
        BufferedWriter bfr = null;

        try{
            fout =new FileOutputStream("Cohort1_Buffered.txt");
            osr = new OutputStreamWriter(fout);
            bfr = new BufferedWriter(osr);

            for (String name : developers) {
                bfr.write(name + "\n");
            }

            // LAMBDA DEMOS
            //1
            // Consumer to display a name
//            Consumer<String> display = name -> System.out.println(name);
//            developers.forEach(display);

            //2
            // Consumer to display a name in uppercase
            // to demo that we can have multiple lines
            Consumer<String> displayUpper = name -> {
                name = name.toUpperCase();
                System.out.println(name);
            };
            developers.forEach(displayUpper);

            //3
            //shorthand inferred interface type
//            developers.forEach(n -> System.out.println(n));
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        finally{
            bfr.flush();
            osr.close();
            fout.close();
            System.out.println("Success...Cohort 1 Buffered File Saved");
        }
    }




}

