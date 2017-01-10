package com.example.hcd_fresher048.persistancedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.example.hcd_fresher048.persistancedemo.DatabaseHandler.ContactColumn.KEY_NAME;
import static com.example.hcd_fresher048.persistancedemo.DatabaseHandler.ContactColumn.KEY_PH_NO;
import static com.example.hcd_fresher048.persistancedemo.DatabaseHandler.TABLE_CONTACTS;

public class ContactDAO {

    Context context;
    DatabaseHandler databaseHandler;

    public ContactDAO(Context context) {
        this.context = context;
        databaseHandler = new DatabaseHandler(context);
    }

    public void addContact(Contact contact) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); // Contact Name
        values.put(KEY_PH_NO, contact.getPhoneNumber()); // Contact Phone Number

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }

    public Contact getContact(int id) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { DatabaseHandler.ContactColumn._ID,
                        KEY_NAME, KEY_PH_NO }, DatabaseHandler.ContactColumn._ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return contact;
    }

    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    public int updateContact(Contact contact) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        // updating row
        return db.update(TABLE_CONTACTS, values, DatabaseHandler.ContactColumn._ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }

    public void deleteContact(Contact contact) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        db.delete(TABLE_CONTACTS, DatabaseHandler.ContactColumn._ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }
}
