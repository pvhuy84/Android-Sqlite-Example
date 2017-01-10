package com.example.hcd_fresher048.persistancedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager";
    public static final String TABLE_CONTACTS = "contacts";

    class ContactColumn implements BaseColumns {
        static final String KEY_NAME = "name";
        static final String KEY_PH_NO = "phone_number";
    }

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
            + ContactColumn._ID + " INTEGER PRIMARY KEY," + ContactColumn.KEY_NAME + " TEXT,"
            + ContactColumn.KEY_PH_NO + " TEXT" + ")";
    private static final String DROP_CONTACTS_TABLE = "DROP TABLE IF EXISTS " + TABLE_CONTACTS;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DROP_CONTACTS_TABLE);

        // Create tables again
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }


}
