package com.assignment;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileUtils {
    public static void writeTextFile(String text, String filename){
        try {
            FileOutputStream outputStream = new FileOutputStream(filename);
            OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            BufferedWriter bf = new BufferedWriter(writer);

            bf.write(text);

            bf.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String readTextFile(String filename){
        StringBuilder retStr = new StringBuilder();
        try {
            FileInputStream inputStream = new FileInputStream(filename);
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(reader);

            String line;
            while ((line = br.readLine()) != null){
                retStr.append(line);
            }
             reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retStr.toString();
    }

    public static void saveObject(Object obj, String filename) {

        try {
            FileOutputStream outStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);

            objectOutStream.writeObject(obj);


            objectOutStream.close();
            outStream.close();
            System.out.println("Saved succesfully");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static Object loadObject(String filename) {

        Object retObj = null;

        try {
            ObjectInputStream obj = new ObjectInputStream(new FileInputStream(filename));

            retObj = obj.readObject();

            obj.close();

        } catch (FileNotFoundException e) {
            System.out.println("No savefile found.");
        } catch (InvalidClassException e) {
            System.out.println("Invalid class, save file unusable.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found!");

        }

        return retObj;

    }
}
