package com.example.welp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class UserDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "User.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "user";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE_NUMBER = "phone_number";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_PHONE_NUMBER + " TEXT, " +
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT)";

    public UserDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required for now
    }

    public long insertUser(String name, String phoneNumber, String email, String password) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PHONE_NUMBER, phoneNumber);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);

        return db.insert(TABLE_NAME, null, values);
    }

    public Cursor getUserById(long id) {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_PHONE_NUMBER,
                COLUMN_EMAIL,
                COLUMN_PASSWORD
        };

        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };

        return db.query(
                TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
    }
}