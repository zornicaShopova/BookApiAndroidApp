package book.api.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText fullNameET,
            usernameET,
            passwordET,
            configPassET;
    Button registrationBtn;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);

        fullNameET = findViewById(R.id.fullnameEditText);
        usernameET = findViewById(R.id.usernameEditText);
        passwordET = findViewById(R.id.passwordEditText);
        configPassET = findViewById(R.id.confPassEditText);
        registrationBtn = findViewById(R.id.registrationButton);


        registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v.getId() == R.id.registrationButton) {
                    //check if the field  is empty
                    if (fullNameET.getText().length() > 0
                            && usernameET.getText().length() > 0
                            && passwordET.getText().length() > 0
                            && passwordET.getText().toString().equals(configPassET.getText().toString())) {

                        String fullNameInput = fullNameET.getText().toString();
                        String usernameInput = usernameET.getText().toString();
                        String passwordInput = passwordET.getText().toString();

                        User user = new User();
                        user.setFullName(fullNameInput);
                        user.setUsername(usernameInput);
                        user.setPassword(passwordInput);

                        //call method to save the data
                        if (!db.registerUser(user)) {
                            Toast.makeText(RegisterActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                            return;
                        }

                    } else {
                        Toast.makeText(RegisterActivity.this, "Please provide all the necessary information!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(RegisterActivity.this, "Registration Success!", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(RegisterActivity.this, RegisterConfirmActivity.class);
                startActivity(intent);

            }
        });
    }
}