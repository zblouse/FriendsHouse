package com.zblouse.friendshouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * sqLite Database Helper for the House database
 */
public class HouseDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "house_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "houses";
    //Keys used for the columns in the database
    private static final String ID_KEY = "id";
    private static final String HOUSE_NAME_KEY = "house_name";
    private static final String HOUSE_DESCRIPTION_KEY = "house_description";
    private static final String HOUSE_LATITUDE_KEY = "house_latitude";
    private static final String HOUSE_LONGITUDE_KEY = "house_longitude";

    public HouseDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creates the database
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + "(" +
                ID_KEY + " INTEGER PRIMARY KEY," +
                HOUSE_NAME_KEY + " TEXT," +
                HOUSE_DESCRIPTION_KEY + " TEXT," +
                HOUSE_LATITUDE_KEY + " REAL," +
                HOUSE_LONGITUDE_KEY + " REAL)";

        sqLiteDatabase.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * Adds the House to the database
     * @param house
     */
    public void addHouseToDatabase(House house){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(HOUSE_NAME_KEY, house.getName());
        contentValues.put(HOUSE_DESCRIPTION_KEY, house.getDescription());
        contentValues.put(HOUSE_LATITUDE_KEY, house.getLatitude());
        contentValues.put(HOUSE_LONGITUDE_KEY, house.getLongitude());
        database.insert(TABLE_NAME,null,contentValues);

        database.close();
    }

    /**
     * Returns all houses in the database, used to display all houses in the RecyclerView in the ListViewFragment
     * @return
     */
    public List<House> getAllHouses(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor houseCursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<House> houses = new ArrayList<>();
        if(houseCursor.moveToFirst()){
            do {
                House house = new House(houseCursor.getInt(0),houseCursor.getString(1), houseCursor.getString(2), houseCursor.getDouble(3), houseCursor.getDouble(4));
                houses.add(house);
            } while(houseCursor.moveToNext());
        }
        houseCursor.close();
        database.close();
        return houses;
    }
}
