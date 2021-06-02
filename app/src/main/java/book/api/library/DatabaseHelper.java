package book.api.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import book.api.library.BookDataModel;
import book.api.library.User;

import static android.content.ContentValues.TAG;

public class DatabaseHelper extends SQLiteOpenHelper {
    //database
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DatabaseRecords.db";

    //table #1
    public static final String TABLE_NAME = "BOOK_INFO";
    //columns
    public static final String COLUMN_BOOK_ID = "BOOK_ID";
    public static final String COLUMN_BOOK_TITLE = "BOOK_TITLE";
    public static final String COLUMN_BOOK_PUBLISHED_DATE = "PUBLISHED_DATE";

    // table #2
    public static final String TABLE_NAME_REGISTER = "TABLE_REGISTER";
    // columns
    public static final String COLUMN_USER_ID = "USER_ID";
    public static final  String COLUMN_USER_FULLNAME = "USER_FULLNAME";
    public static final String COLUMN_USER_USERNAME = "USER_USERNAME";
    public static final String COLUMN_USER_PASSWORD = "USER_PASSWORD";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // create table #1
     public static final String createTableStatement = "CREATE TABLE " + TABLE_NAME
                + "('" + COLUMN_BOOK_ID + "'INTEGER PRIMARY KEY AUTOINCREMENT," +
                "'" + COLUMN_BOOK_TITLE + "'VARCHAR(80) NOT NULL, " +
                "'" + COLUMN_BOOK_PUBLISHED_DATE + "'VARCHAR(50) NOT NULL)";

    // create table #2
    public static final String createRegisterTable = "CREATE TABLE " + TABLE_NAME_REGISTER
            + "('" + COLUMN_USER_ID + "' INTEGER PRIMARY KEY AUTOINCREMENT," +
            "'" + COLUMN_USER_FULLNAME + "' varchar(80) NOT NULL UNIQUE," +
            "'" + COLUMN_USER_USERNAME + "' varchar(50) NOT NULL UNIQUE," +
            "'" + COLUMN_USER_PASSWORD + "' varchar(50) NOT NULL)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableStatement);
        db.execSQL(createRegisterTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_REGISTER);
        onCreate(db);
    }

    //save the data from checkboxes in table #1
    public boolean saveDataToDB(String titleBook,String dataBook) {
        SQLiteDatabase db = null;
        ContentValues cv = null;

        long result = 0;
            try {
                db = this.getWritableDatabase();
                cv = new ContentValues();

                cv.put(COLUMN_BOOK_TITLE, titleBook);
                cv.put(COLUMN_BOOK_PUBLISHED_DATE,dataBook);
                //insert the record
                result = db.insert(TABLE_NAME, null, cv);

                // manually perform checkpoint
                //the wall file flush the content to the .db
                getReadableDatabase().rawQuery("PRAGMA wal_checkpoint ",null);

                Log.i(TAG,"addData: " + titleBook + " and "+ dataBook + " to " + TABLE_NAME);

            } catch (SQLException e) {

                Log.wtf("Error", e.getMessage());
            } finally {
                if (db != null) {
                    db.close();
                }
                if (cv != null) {
                    cv.clear();
                }
            }

        if(result == -1){
            return false;
        }else {
            return true;
        }

    }


    //return the data in the list view for table #1
    public List<BookDataModel> getAllBooks() {
        List<BookDataModel> returnList = new ArrayList<>();

        //get data from the db

        String queryString = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            //loop through the results and
            //create new item which have to
            //put in the list view
            do {
                //create object to assign the value
                BookDataModel bookDataModel = new BookDataModel();
                bookDataModel.setBookID(cursor.getInt(0));
                bookDataModel.setTitle(cursor.getString(1));
                bookDataModel.setPublishedDate(cursor.getString(2));
              //  bookDataModel.setAuthors(cursor.getString(3));

                //put the object in a list
                returnList.add(bookDataModel);
            } while (cursor.moveToNext());

        } else {
            //do not  do anything
            //empty  list
        }
        cursor.close();
        //return the full list
        return returnList;
    }

    //save the data for table #2
    public boolean registerUser(User user) {
        SQLiteDatabase db = null;
        try {
            db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_USER_FULLNAME, user.getFullName());
            cv.put(COLUMN_USER_USERNAME, user.getUsername());
            cv.put(COLUMN_USER_PASSWORD, user.getPassword());

            if (db.insert(TABLE_NAME_REGISTER, String.valueOf(new String[]{COLUMN_USER_FULLNAME,COLUMN_USER_USERNAME,COLUMN_USER_PASSWORD}), cv) != -1) {
                return true;
            }
        } catch (SQLException e) {
            Log.wtf("Error", e.getMessage());
        } finally {
            if (db != null)
                db.close();
        }
        return false;
    }

    public Cursor UserData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME_REGISTER,null);
        return c;
    }

    public boolean login(String username, String password) {
        try {
            SQLiteDatabase  db = getReadableDatabase();
            String sql = "SELECT * FROM " + TABLE_NAME_REGISTER
                    + " WHERE " + COLUMN_USER_USERNAME + " = '" + username + "'"
                    + " AND " + COLUMN_USER_PASSWORD + " = '" + password + "'";

            Cursor c  = db.rawQuery(sql, null);

            return c.moveToFirst();

        } catch (SQLException e) {
            Log.wtf("Error!", e.getMessage());
        }
        return false;
    }

    public boolean login_check(String username,String pass){
        try{
            SQLiteDatabase db = getReadableDatabase();
            String sql = "SELECT * FROM " + TABLE_NAME_REGISTER
                    + " WHERE " + COLUMN_USER_USERNAME + " = '" + username + "'"
                    + " AND " + COLUMN_USER_PASSWORD + " = '" + pass + "'";

            Cursor c = db.rawQuery(sql,null);

                Log.d(TAG,"User exits");
                return c.moveToFirst();

        }catch (SQLException e){
            Log.wtf("Error!",e.getMessage());
        }
        return false;
    }
    

}
