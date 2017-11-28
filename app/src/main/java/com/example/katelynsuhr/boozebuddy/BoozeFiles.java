package com.example.katelynsuhr.boozebuddy;

import android.content.Context;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.CharacterCodingException;

/**
 * Created by kennedybagnol on 11/27/17.
 */

public class BoozeFiles {
        private File file;
        private File path;
        private String category;
        private int date;


        BoozeFiles(String name, String category, Context context) {
            this.category = category;
            this.path = context.getFilesDir();
            this.file = new File(path, name);
        }

        BoozeFiles(int date, String category, Context context){
            this.category = category;
            this.path = context.getFilesDir();
            this.file = new File(path, Integer.toString(date));
            this.date = date;
        }

    void writeFile(BoozeFiles file, String data) {
        try {
            FileOutputStream writer = new FileOutputStream(file.file, true);
            writer.write(data.getBytes());
            writer.close();
        } catch(IOException ie) {
            ie.printStackTrace();
        }

    }

    void writeDrink(BoozeFiles file, String drink, String calories, String nutrients) {
        try {
            FileOutputStream writer = new FileOutputStream(file.file, true);
            writer.write((drink + "/").getBytes());
            writer.write((calories + "/").getBytes());
            writer.write((nutrients + "/").getBytes());
            writer.close();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    String readDrink(BoozeFiles file){
        int length = (int) file.file.length();
        byte[] bytes = new byte[length];
        try {
            FileInputStream in = new FileInputStream(file.file);
            in.read(bytes);
            in.close();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        String contents = new String(bytes);
        String drink = "";
        String someString = "/";
        char slash = someString.charAt(0);
        int track = 0;
        for (int i = 0; i < contents.length(); i++){
            char c = contents.charAt(i);
            if(c == slash){
                track++;
            }
            if(track%3 == 0){
                drink = drink + Character.toString(c);
            }
        }
        return drink;
    }

    String readCalories(BoozeFiles file){
        int length = (int) file.file.length();
        byte[] bytes = new byte[length];
        try {
            FileInputStream in = new FileInputStream(file.file);
            in.read(bytes);
            in.close();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        String contents = new String(bytes);
        String calories = "";
        String someString = "/";
        char slash = someString.charAt(0);
        int track = 0;
        for (int i = 0; i < contents.length(); i++){
            char c = contents.charAt(i);
            if(c == slash){
                track++;
            }
            if(track%3 == 1){
                calories = calories + Character.toString(c);
            }
        }
        return calories;
    }

    String readNutrients(BoozeFiles file){
        int length = (int) file.file.length();
        byte[] bytes = new byte[length];
        try {
            FileInputStream in = new FileInputStream(file.file);
            in.read(bytes);
            in.close();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        String contents = new String(bytes);
        String nutrients = "";
        String someString = "/";
        char slash = someString.charAt(0);
        int track = 0;
        for (int i = 0; i < contents.length(); i++){
            char c = contents.charAt(i);
            if(c == slash){
                track++;
            }
            if(track%3 == 2){
                nutrients = nutrients + Character.toString(c);
            }
        }
        return nutrients;
    }

    void deleteFile(BoozeFiles file){
        file.file.delete();
        file.path.delete();
    }

    String readFile(BoozeFiles file) {
        int length = (int) file.file.length();
        byte[] bytes = new byte[length];
        try {
            FileInputStream in = new FileInputStream(file.file);
            in.read(bytes);
            in.close();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        String contents = new String(bytes);
        return contents;
    }




}
