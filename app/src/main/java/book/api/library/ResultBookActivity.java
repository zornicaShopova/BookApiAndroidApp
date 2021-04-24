package book.api.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ResultBookActivity extends AppCompatActivity {
    //list view where the data is displayed
    ListView listView_bookInfo;

    //List<BookDataModel> listBooks = new ArrayList<BookDataModel>();
    ArrayAdapter<BookDataModel> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_information);

        //list view implementation
        listView_bookInfo = findViewById(R.id.list_view_bookInfo);

        //take the input value of the searchInput field
        Intent intent = getIntent();
        String receivedStringInput = intent.getStringExtra("search_input");

        //constructor for taking the context
        BookDataService bookDataService = new BookDataService(ResultBookActivity.this);

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
                listView_bookInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        BookDataModel bookDataModel = (BookDataModel) parent.getItemAtPosition(position);
                        Toast.makeText(getApplicationContext(),"clicked on row " + bookDataModel.getTitle(),Toast.LENGTH_LONG).show();
                    }
                });

                //Toast.makeText(ResultBookActivity.this,"Success!",Toast.LENGTH_LONG).show();

            }
        });

        //click event ot the list view with books Element

        listView_bookInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(ResultBookActivity.this, "Click" + adapter.getItem(position), Toast.LENGTH_LONG).show();
            }
        });



    }
}