package book.api.library.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import book.api.library.R;
import book.api.library.ResultBookActivity;
import book.api.library.VolumeInfoActivity;

public class MainActivity extends AppCompatActivity {
    Button showBooksBtn, showTotalBooksBtn;
    EditText searchET;
    ListView lv_items;

    //when the method is commented the back button
    //stop working and you can't go back
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showBooksBtn = findViewById(R.id.showBooksButton);
        searchET = findViewById(R.id.searchEditText);
        showTotalBooksBtn = findViewById(R.id.totalBooksButton);
        lv_items = findViewById(R.id.lv_items);

        //show the  total books  from the search
        showTotalBooksBtn.setOnClickListener(v -> {
            //variable for  input data
            String searchInput = searchET.getText().toString();

            Intent intent = new Intent(MainActivity.this, VolumeInfoActivity.class);
            //take the  string from input field
            intent.putExtra("search_input", searchInput);
            startActivity(intent);

            searchET.setText("");
        });

        //show the list of the searched book
        showBooksBtn.setOnClickListener(v -> {

            String searchInput = searchET.getText().toString();
            Intent intent = new Intent(MainActivity.this, ResultBookActivity.class);

            intent.putExtra("search_input",searchInput);
            startActivity(intent);

            searchET.setText("");
        });
    }
}