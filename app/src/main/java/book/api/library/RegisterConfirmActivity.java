package book.api.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterConfirmActivity extends AppCompatActivity {
    Button logB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_confirm);
        logB  =  findViewById(R.id.logButton);

        logB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( RegisterConfirmActivity.this,LoginActivity.class);  // Register confirm activity
                startActivity(intent);
            }
        });
    }
}