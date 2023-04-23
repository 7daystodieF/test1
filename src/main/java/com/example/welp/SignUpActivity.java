package com.example.welp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void onSignUpClick(View view) {
        EditText nameInput = findViewById(R.id.editTextName);
        EditText emailInput = findViewById(R.id.editTextEmail);
        EditText passwordInput = findViewById(R.id.editTextPassword);

        String name = nameInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if the email already exists in the database
        String[] projection = {
                UsersContract.User.COLUMN_NAME_EMAIL
        };
        String selection = UsersContract.User.COLUMN_NAME_EMAIL + " = ?";
        String[] selectionArgs = {email};

        if (getContentResolver().query(
                UsersContract.User.CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                null
        ).getCount() > 0) {
            Toast.makeText(this, "Email already exists", Toast.LENGTH_SHORT).show();
            return;
        }

        // Add the user to the database
        ContentValues values = new ContentValues();

        values.put(UsersContract.User.COLUMN_NAME_EMAIL, email);
        values.put(UsersContract.User.COLUMN_NAME_PASSWORD, password);

        Uri uri = getContentResolver().insert(UsersContract.User.CONTENT_URI, values);

        if (uri != null) {
            Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Error registering user", Toast.LENGTH_SHORT).show();
        }
    }
}