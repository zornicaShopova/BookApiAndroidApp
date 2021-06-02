package book.api.library;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;


public class ProfileActivity extends AppCompatActivity {
    ImageView image;
    EditText personNameTV, personUsernameTV, personPassTV;
    SQLiteDatabase sqLiteDatabase;
    DatabaseHelper db;
    Cursor c;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        image = findViewById(R.id.profilePic);

        personNameTV = findViewById(R.id.personName);
        personUsernameTV = findViewById(R.id.personUsername);
        personPassTV = findViewById(R.id.personPassword);

        Intent intent = getIntent();
        String loginUser = intent.getStringExtra("usernameInput");


        db = new DatabaseHelper(getApplicationContext());
        sqLiteDatabase = db.getReadableDatabase();
        c = db.UserData();

        if(c.moveToFirst()){
            String name,username,password;
            do{

                name = c.getString(1);
                username = c.getString(2);
                password = c.getString(3);



                    personNameTV.setText(name);
                    personUsernameTV.setText(username);
                    personPassTV.setText(password);

                if(username.equals(loginUser)){

                }


              // username == register username && password == register password
            }while(c.moveToNext());


        }

    }
}