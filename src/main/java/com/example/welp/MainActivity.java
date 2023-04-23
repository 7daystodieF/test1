package com.example.welp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // ...

    public void onLoginClick(View view) {
        EditText emailInput = (EditText) findViewById(R.id.editTextEmail);
        EditText passwordInput = findViewById(R.id.editTextPassword);

        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        // Check if email and password are in the database
        if (checkCredentials(email, password)) {
            Intent intent = new Intent(com.example.welp.MainActivity.this, ActivityFinal.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkCredentials(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(com.example.welp.MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        String[] projection = {
                UsersContract.User.COLUMN_NAME_EMAIL,
                UsersContract.User.COLUMN_NAME_PASSWORD
        };

        String selection = UsersContract.User.COLUMN_NAME_EMAIL + " = ? AND " +
                UsersContract.User.COLUMN_NAME_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};

        Cursor cursor = getContentResolver().query(
                UsersContract.User.CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                null
        );

        return cursor != null && cursor.getCount() > 0;
    }


    public void onSignUpClick(View view) {
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}