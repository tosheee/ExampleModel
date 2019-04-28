package com.examplemodel.examplemodel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DictionaryDB extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DictionaryDB";
    private static final String DATABASE_TABLE = "DictionaryTable";

    public static final String KEY_ROWID = "_id";
    public static final String KEY_EN_WORD = "en_word";
    public static final String KEY_BG_WORD = "bg_word";


    public DictionaryDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + DATABASE_TABLE + "("
                + KEY_ROWID + " INTEGER PRIMARY KEY," + KEY_EN_WORD + " TEXT,"
                + KEY_BG_WORD + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

        // Create tables again
        onCreate(db);
    }

    void addWords(Words words) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EN_WORD, words.getEn_word()); // Contact Name
        values.put(KEY_BG_WORD, words.getBg_word()); // Contact Phone

        // Inserting Row
        db.insert(DATABASE_TABLE, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    public List<Words> getAllWords() {
        List<Words> wordsList = new ArrayList<Words>();
        String selectQuery = "SELECT  * FROM " + DATABASE_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Words words = new Words();
                words.setId(Integer.parseInt(cursor.getString(0)));
                words.setEn_word(cursor.getString(1));
                words.setBg_word(cursor.getString(2));
                wordsList.add(words);
            } while (cursor.moveToNext());
        }

        return wordsList;
    }

    public List<Words> getAllWordsNew() {
        //List<Words> wordsList = new ArrayList<Words>();
        String [] columns = new String [] {KEY_ROWID, KEY_EN_WORD, KEY_BG_WORD};
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query(DATABASE_TABLE, columns, null, null, null, null, null);
        int iRowID = c.getColumnIndex(KEY_ROWID);
        Log.i("NAME", "Name " + c.getColumnName(1));
        int iEnWord =  c.getColumnIndex(KEY_EN_WORD);
        //int iBgWord =  c.getColumnIndex(KEY_BG_WORD);
        //Log.i("ROW", "Row " + iBgWord);
        //Map<String, List<String>> expandableListDetail = new TreeMap<String, List<String>>();

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            Log.i("NAME", "Name" + c.getString(iEnWord));
       }

        c.close();
        return null;
    }

/*
    public void getAndInsertDataFromFireBase(){

        final DatabaseReference senderDb = FirebaseDatabase.getInstance().getReference().child("Items");

        senderDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    //Log.i("Testing", "DATA: "+ childDataSnapshot.child("bg_word").getValue().toString());
                    createEntry(childDataSnapshot.child("en_word").getValue().toString(), childDataSnapshot.child("bg_word").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }
*/

}
