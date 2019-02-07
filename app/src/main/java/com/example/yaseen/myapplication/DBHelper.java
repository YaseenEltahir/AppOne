package com.example.yaseen.myapplication;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    /*public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    public static final String CONTACTS_COLUMN_STREET = "street";
    public static final String CONTACTS_COLUMN_CITY = "place";
    public static final String CONTACTS_COLUMN_PHONE = "phone";
*/
    public static final String ESSAYS_TABLE_NAME = "essays";
    public static final String ESSAYS_COLUMN_ID = "essay_id";
    public static final String ESSAYS_COLUMN_ESSAY_TITLE = "essay_name";
    public static final String ESSAYS_COLUMN_FILE_NAME = "file_name";
    public static final String ESSAYS_COLUMN_ESSAY_BODY = "file_content";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table contacts " +
                        "(id integer primary key, name text,phone text,email text, street text,place text)"
        );
        db.execSQL(
                "create table "+ESSAYS_TABLE_NAME +
                        " ("+ESSAYS_COLUMN_ID+" integer primary key,"+ ESSAYS_COLUMN_ESSAY_TITLE +" text, "+ESSAYS_COLUMN_FILE_NAME+" text,"+ ESSAYS_COLUMN_ESSAY_BODY +" text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        db.execSQL("DROP TABLE IF EXISTS "+ESSAYS_TABLE_NAME);

        onCreate(db);
    }

    /*public boolean insertContact(String name, String phone, String email, String street, String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.insert("contacts", null, contentValues);
        return true;
    }*/

    //     "(essay_id integer primary key, file_name text,file_content text)"
    public boolean insertEssay(String essay_name,String file_name, String file_content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ESSAYS_COLUMN_ESSAY_TITLE, essay_name);
        contentValues.put(ESSAYS_COLUMN_FILE_NAME, file_name);
        contentValues.put(ESSAYS_COLUMN_ESSAY_BODY, file_content);
        db.insert(ESSAYS_TABLE_NAME, null, contentValues);
        return true;

    }

   /* public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from contacts where id=" + id + "", null);
        return res;
    }*/

    public Cursor getEssayData(int essay_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from essays where essay_id=" + essay_id + "", null);
        return res;
    }

    /*public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }*/

    public int essaysNumberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, ESSAYS_TABLE_NAME);
        return numRows;
    }
    public boolean isDataBaseEmpty() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, ESSAYS_TABLE_NAME);
        return (numRows==0)?true:false;
    }

    public boolean updateEssay(Integer essay_id, String file_name, String file_content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ESSAYS_COLUMN_ID, essay_id);
        contentValues.put(ESSAYS_COLUMN_FILE_NAME, file_name);
        contentValues.put(ESSAYS_COLUMN_ESSAY_BODY, file_content);
        db.update(ESSAYS_COLUMN_ESSAY_TITLE, contentValues, ESSAYS_COLUMN_ID+" = ? ", new String[]{Integer.toString(essay_id)});

        return true;
    }

    /*public boolean updateContact(Integer id, String name, String phone, String email, String street, String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update("contacts", contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }*/

    /*public Integer deleteContact(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[]{Integer.toString(id)});
    }*/

    public Integer deleteEssay(Integer essay_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(ESSAYS_TABLE_NAME,
                ESSAYS_COLUMN_ID+" = ? ",
                new String[]{Integer.toString(essay_id)});
    }

    /*public ArrayList<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from contacts", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }*/
    public ArrayList<String> getAllEssays() {
        ArrayList<String> array_list = new ArrayList<>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+ESSAYS_TABLE_NAME, null);
//        Cursor res = db.rawQuery("select * from "+ESSAYS_TABLE_NAME+" where "+ ESSAYS_COLUMN_ESSAY_BODY +" like '%يس%'", null);
//        Cursor res = db.rawQuery("select * from "+ESSAYS_TABLE_NAME, null);
//        SELECT column1, column2, ...
//        FROM table_name
//        WHERE columnN LIKE pattern;
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(ESSAYS_COLUMN_ESSAY_TITLE)));
            res.moveToNext();
        }
        return array_list;
    }
    public ArrayList<String> getLikeEssays(String ... word) {
        ArrayList<String> array_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery="select * from "+ESSAYS_TABLE_NAME+" where ";
        for(String s:word )
            sqlQuery+=ESSAYS_COLUMN_ESSAY_BODY+" like '%"+s+"%' OR ";
        sqlQuery=sqlQuery.substring(0,sqlQuery.length()-4);
        Cursor res = db.rawQuery(sqlQuery, null);
//        Cursor res = db.rawQuery("select * from "+ESSAYS_TABLE_NAME+" where "+ ESSAYS_COLUMN_ESSAY_BODY +" like '%"+word[0]+"%'", null);
//        Cursor res = db.rawQuery("select * from "+ESSAYS_TABLE_NAME+" where "+ ESSAYS_COLUMN_ESSAY_BODY +" like '%يس%'", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(ESSAYS_COLUMN_ESSAY_TITLE)));
            res.moveToNext();
        }
        return array_list;
    }
}
