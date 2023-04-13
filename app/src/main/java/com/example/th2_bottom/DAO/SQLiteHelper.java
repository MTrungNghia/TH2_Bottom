package com.example.th2_bottom.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.th2_bottom.model.CongViec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CongViecs.db";
    private static int DATABASE_VERSION = 1;
    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE CongViecs("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, content TEXT, date TEXT, status TEXT," +
                "together INTEGER)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public List<CongViec> getAll() {
        List<CongViec> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "date DESC";
        Cursor rs = st.query("CongViecs", null, null, null, null, null, order);
        while (rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String content = rs.getString(2);
            String date = rs.getString(3);
            String status = rs.getString(4);
            int together = rs.getInt(5);
            list.add(new CongViec(id, name, content, date, status, together));
        }
        return list;
    }
    public long addItem(CongViec i) {
        ContentValues values = new ContentValues();
        values.put("name", i.getName());
        values.put("content", i.getContent());
        values.put("date", i.getDate());
        values.put("status", i.getStatus());
        values.put("together", i.getTogether());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("CongViecs", null, values);
    }
    //    Lay các item theo date:
    public List<CongViec> getByDate(String date) {
        List<CongViec> list = new ArrayList<>();
        String whereClause = "date like ?";
        String[] whereArgs = {date};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("CongViecs", null, whereClause, whereArgs, null,null, null);
        while (rs!=null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String content = rs.getString(2);
            String status = rs.getString(4);
            int together = rs.getInt(5);
            list.add(new CongViec(id, name, content, date, status, together));
        }
        return list;
    }
    public List<CongViec> searchByTitle(String key) {
        List<CongViec> list = new ArrayList<>();
//        String whereClause = "name like ?";
//        String[] whereArgs = {"%"+key+"%"};
        String whereClause = "name LIKE ? OR content LIKE ?";
        String[] whereArgs = {"%"+key+"%", "%"+key+"%"};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("CongViecs", null, whereClause, whereArgs, null,null, null);
        while (rs!=null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String content = rs.getString(2);
            String date = rs.getString(3);
            String status = rs.getString(4);
            int together = rs.getInt(5);
            list.add(new CongViec(id, name, content, date, status, together));
        }
        return list;
    }

    public String countByStatus() {
//        Map<String, Integer> countMap = new HashMap<>();
        String result = "";
        String query = "SELECT status, COUNT(*) FROM CongViecs GROUP BY status ORDER BY COUNT(*) DESC";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor != null && cursor.moveToNext()) {
            String status = cursor.getString(0);
            int count = cursor.getInt(1);
//            countMap.put(status, count);
            result += status + " : " + String.valueOf(count) + "\n";
        }
//        StringBuilder sb = new StringBuilder();
//        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
//            sb.append(entry.getKey() + " : " + entry.getValue() + "\n");
//        }
//        result = sb.toString();
        return result;
    }

    public List<CongViec> searchByCategory(String category) {
        List<CongViec> list = new ArrayList<>();
        String whereClause = "status like ?";
        String[] whereArgs = {category};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("CongViecs", null, whereClause, whereArgs, null,null, null);
        while (rs!=null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String content = rs.getString(2);
            String date = rs.getString(3);
            String status = rs.getString(4);
            int together = rs.getInt(5);
            list.add(new CongViec(id, name, content, date, status, together));
        }
        return list;
    }
    //    Update:
    public int update(CongViec i) {
        ContentValues values = new ContentValues();
        values.put("name", i.getName());
        values.put("content", i.getContent());
        values.put("date", i.getDate());
        values.put("status", i.getStatus());
        values.put("together", i.getTogether());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClase = "id = ?";
        String[] whereArgs = {Integer.toString(i.getId())};
        return sqLiteDatabase.update("CongViecs", values, whereClase, whereArgs);
    }

    //    Delete:
    public int delete(int id) {
        String whereClase = "id = ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("CongViecs", whereClase, whereArgs);

    }
}
