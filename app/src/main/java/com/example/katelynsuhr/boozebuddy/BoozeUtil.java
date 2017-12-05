package com.example.katelynsuhr.boozebuddy;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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
        //serialize the List
//        try (
//               // OutputStream file = new FileOutputStream("quarks.ser");
//               OutputStream file = new File(new File(context.getFilesDir(), name), true);
//
//                OutputStream buffer = new BufferedOutputStream(file);
//                ObjectOutput output = new ObjectOutputStream(buffer);
//        ) {
//            output.writeObject(quarks);
//        } catch (IOException ex) {
//            fLogger.log(Level.SEVERE, "Cannot perform output.", ex);
//        }
//
//        //deserialize the quarks.ser file
//        try (
//                InputStream file = new FileInputStream("quarks.ser");
//                InputStream buffer = new BufferedInputStream(file);
//                ObjectInput input = new ObjectInputStream(buffer);
//        ) {
//            //deserialize the List
//            List<String> recoveredQuarks = (List<String>) input.readObject();
//            //display its data
//            for (String quark : recoveredQuarks) {
//                System.out.println("Recovered Quark: " + quark);
//            }
//        } catch (ClassNotFoundException ex) {
//            fLogger.log(Level.SEVERE, "Cannot perform input. Class not found.", ex);
//        } catch (IOException ex) {
//            fLogger.log(Level.SEVERE, "Cannot perform input.", ex);
//        }
 //   }

}

    // PRIVATE

//    private static final Logger fLogger =
//            Logger.getLogger(ExerciseSerializableNew.class.getPackage().getName())
//            ;

   // }

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
