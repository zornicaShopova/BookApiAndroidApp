package book.api.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ResultBookActivity extends AppCompatActivity {
    //list view where the data is displayed
    ListView listView_bookInfo;
    ImageView addBookButton;
    //List<BookDataModel> listBooks = new ArrayList<BookDataModel>();
    ArrayAdapter<BookDataModel> adapter;
    CheckBox checkBox;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_information);

        //constructor for taking the context
        BookDataService bookDataService = new BookDataService(ResultBookActivity.this);
        //take the input value of the searchInput field
        Intent intent = getIntent();
        String receivedStringInput = intent.getStringExtra("search_input");

        //addBookToList button
        addBookButton = findViewById(R.id.addBookButton);
        checkBox = findViewById(R.id.checkBox);

        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(ResultBookActivity.this, "Save in favourite", Toast.LENGTH_SHORT).show();
                // ?? how to take book and data fields
//                if (checkBox.isChecked()) {
//                    // db.saveDataToDB(book,data);
//
//                }
            }

        });

        //list view implementation
        listView_bookInfo = findViewById(R.id.list_view_bookInfo);

        bookDataService.getVolumeInfo(receivedStringInput, new BookDataService.VolumeInfoResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(ResultBookActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(List<BookDataModel> bookDataModels) {
                //get the list values
                //DatabaseHelper db = new DatabaseHelper(ResultBookActivity.this);
                // listBooks = db.getAllBooks();

                //put the values it the adapter view
                adapter = new CustomAdapter(ResultBookActivity.this, bookDataModels);
                //show the adapter in the ListView
                listView_bookInfo.setAdapter(adapter);
            }
        });
    }
}