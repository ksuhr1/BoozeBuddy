package com.example.katelynsuhr.boozebuddy;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by katelynsuhr on 11/29/17.
 */

public final class BoozeUtil {

    public static void writeFile(Context context, String data, String name) {
        try {
            FileOutputStream writer = new FileOutputStream(new File(context.getFilesDir(), name), true);
            writer.write(data.getBytes());
            writer.close();
        } catch(IOException ie) {
            ie.printStackTrace();
        }
    }


    public static String readFile(Context context, String name) {
        StringBuilder sb = new StringBuilder();
        try {
            FileReader reader = new FileReader(new File(context.getFilesDir(), name));
            BufferedReader br = new BufferedReader(reader);

            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return sb.toString();
    }


    public static boolean isExist(Context context, String name) {
        return new File(context.getFilesDir(), name).exists();
    }
    public static boolean removeDrink(Context context, String name, String itemId) {

        StringBuilder sb = new StringBuilder();
        try {
            FileReader reader = new FileReader(new File(context.getFilesDir(), name));
            BufferedReader br = new BufferedReader(reader);

            String line = br.readLine();
            Log.i("before:", line);
            while (line != null && !line.contains(itemId)) {
                sb.append(line + "\n");
                line = br.readLine();
                Log.i("after:", line);
            }
        } catch (IOException ie) {
            ie.printStackTrace();
            return false;
        }

        writeFile(context, sb.toString(), name);
        return true;
    }

}
