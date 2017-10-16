package com.fanc.wheretoplay.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/7/13.
 */

public class RecordDatabaseHelper extends SQLiteOpenHelper {

    private static final String name = "search_record.db";
    private static final Integer version = 1;
    private final String TABLE = "record";
    private final String COLUMN_NAME = "_word";

    public RecordDatabaseHelper(Context context) {
        super(context, name, null, version);
    }

    /**
     * 删除数据库
     *
     * @param context
     * @return
     */
    public static boolean deleteDatabase(Context context) {
        return context.deleteDatabase(name);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE + "(" + COLUMN_NAME + " VARCHAR)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String word) {
        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.query(TABLE, new String[]{COLUMN_NAME}, null, null, null, null, null);
        if(c.moveToFirst()){
            do {
                if (TextUtils.equals(word, c.getString(c.getColumnIndex(COLUMN_NAME)))) {
                    db.delete(TABLE, COLUMN_NAME + "=?", new String[]{word});
                    break;
                }
            }while (c.moveToNext());
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, word);
        Log.i("aaa", "insert: " + db.insert(TABLE, null, values));
        db.close();
    }

    public List<String> query() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.query(TABLE, new String[]{COLUMN_NAME}, null, null, null, null, null);
        List<String> records = new ArrayList<>();
        if (c.moveToFirst()) {
            String name = null;
            do {
                name = c.getString(c.getColumnIndex(COLUMN_NAME));
                records.add(name);
            } while (c.moveToNext());
            c.close();
            db.close();
            Collections.reverse(records);
        }
        return records;
    }

    public void deleteDatabaseTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE from " + TABLE);
        db.close();
    }

}
