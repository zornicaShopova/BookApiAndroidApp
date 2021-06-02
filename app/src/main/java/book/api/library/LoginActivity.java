package book.api.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import book.api.library.main.MainActivity;

public class LoginActivity extends AppCompatActivity {

    EditText passET, usernameET;
    Button logB;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameET = findViewById(R.id.usernameEditT);
        passET = findViewById(R.id.passEditT);
        logB = findViewById(R.id.logInTheSystemButton);

        db = new DatabaseHelper(this);

        logB.setOnClickListener(v -> {
            if (v.getId() == R.id.logInTheSystemButton) {
                String usernameInput = usernameET.getText().toString();
                String passwordInput = passET.getText().toString();

                    if (db.login(usernameInput, passwordInput)) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("usernameInput",usernameInput);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "THE PASSWORD OR USERNAME ARE INCORRECT!", Toast.LENGTH_SHORT).show();
                    }

            } else {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


    }
}