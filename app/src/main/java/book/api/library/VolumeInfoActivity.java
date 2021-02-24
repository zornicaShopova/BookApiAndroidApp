package book.api.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class VolumeInfoActivity extends AppCompatActivity {
    TextView totalBooks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_info);
        totalBooks = findViewById(R.id.totalsBooks);

        BookDataService bookDataService = new BookDataService(VolumeInfoActivity.this);

        Intent intent = getIntent();
        String received_input  = intent.getStringExtra("search_input");

        bookDataService.getTotalItems(received_input, new BookDataService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(VolumeInfoActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onResponse(String totalItems) {
                totalBooks.setText(totalItems);
                //Toast.makeText(VolumeInfoActivity.this, "Total items are: " + totalItems, Toast.LENGTH_SHORT).show();
            }
        });
    }
}