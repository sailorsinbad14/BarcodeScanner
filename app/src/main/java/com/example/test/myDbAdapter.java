package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

public class myDbAdapter {
    myDbHelper myhelper;
    public myDbAdapter(Context context){
        myhelper = new myDbHelper(context);
    }

    public long insertData(String sku){
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.SKU, sku);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
        return id;
    }

    public String getData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.SKU};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);

        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String skus =cursor.getString(cursor.getColumnIndex(myDbHelper.SKU));

            buffer.append(cid+ "   " + skus + " \n");
        }
        return buffer.toString();
    }

//    public  int delete(String uname)
//    {
//        SQLiteDatabase db = myhelper.getWritableDatabase();
//        String[] whereArgs ={uname};
//
//        int count =db.delete(myDbHelper.TABLE_NAME ,myDbHelper.SKU+" = ?",whereArgs);
//        return  count;
//    }
//
//    public int updateName(String oldName , String newName)
//    {
//        SQLiteDatabase db = myhelper.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(myDbHelper.SKU,newName);
//        String[] whereArgs= {oldName};
//        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.SKU+" = ?",whereArgs );
//        return count;
//    }

    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "myTable";   // Table Name
        private static final int DATABASE_Version = 1;   // Database Version
        private static final String UID="_id";     // Column I (Primary Key)
        private static final String SKU = "SKU";    //Column II
       // private static final String MyPASSWORD= "Password";    // Column III
       private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
               " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+SKU+" VARCHAR(255) "+ ");";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Message.message(context,""+e);
            }
        }
    }
}
