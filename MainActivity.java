package com.example.hcd_fresher048.persistancedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
        File f = new File(folder.getPath(), "output.dat");
        try (
                FileOutputStream fos = openFileOutput("test.dat", MODE_PRIVATE);
//                new FileOutputStream(f);
                PrintWriter pw = new PrintWriter(fos);
        ) {
            pw.println("Hello");
            pw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        ContactDAO dao = new ContactDAO(this);
//        dao.addContact(new Contact(1, "An", "0123455"));
//        Contact contact = dao.getContact(1);
//        Log.d(TAG, "onCreate: " + contact);
    }

}
