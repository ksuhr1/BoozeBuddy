package com.example.katelynsuhr.boozebuddy;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

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

}
