package book.api.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    Button searchB, showItemsB;
    EditText searchET;
    ListView lv_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchB = findViewById(R.id.searchButton);
        searchET = findViewById(R.id.searchEditText);
        showItemsB = findViewById(R.id.showItemsButton);
        lv_items = findViewById(R.id.lv_items);

        showItemsB.setOnClickListener(v -> {
            //variable for  input data
            String searchInput = searchET.getText().toString();

            Intent intent = new Intent(MainActivity.this, VolumeInfoActivity.class);
            //take the  string from input field
            intent.putExtra("search_input", searchInput);
            startActivity(intent);

            searchET.setText("");
        });

        searchB.setOnClickListener(v -> {
            String searchInput = searchET.getText().toString();
            Intent intent = new Intent(MainActivity.this, BookInformation.class);

            intent.putExtra("search_input",searchInput);
            startActivity(intent);

            searchET.setText("");
        });
    }
}