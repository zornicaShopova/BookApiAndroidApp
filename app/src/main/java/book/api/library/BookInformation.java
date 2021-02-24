package book.api.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BookInformation extends AppCompatActivity {
    ListView listView_bookInfo;
    ArrayList<BookDataModel> bookDataModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_information);

        //field for  list view
        listView_bookInfo = findViewById(R.id.list_view_bookInfo);

        Intent intent = getIntent();
        String receivedData = intent.getStringExtra("search_input");

        BookDataService bookDataService = new BookDataService(BookInformation.this);

        bookDataService.getVolumeInfo(receivedData, new BookDataService.VolumeInfoResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(BookInformation.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(List<BookDataModel> bookDataModel) {
                //put the entire list into the  list view  control

                CustomAdapter customAdapter = new CustomAdapter(BookInformation.this,bookDataModel);
                listView_bookInfo.setAdapter(customAdapter);

                Toast.makeText(BookInformation.this, "Success!", Toast.LENGTH_SHORT).show();
                //  ArrayAdapter arrayAdapter = new ArrayAdapter(BookInformation.this, android.R.layout.simple_dropdown_item_1line, bookDataModel);
            }
        });

//        listView_bookInfo.setOnClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                BookReportModel text = bookReportModel.get(position);
//                Toast.makeText(BookInformation.this, (BookReportModel) text, Toast.LENGTH_LONG).show();
//            }
//        });

    }
}