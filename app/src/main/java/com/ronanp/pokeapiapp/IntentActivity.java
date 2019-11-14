package com.ronanp.pokeapiapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URL;
import java.util.jar.Attributes;

import static com.ronanp.pokeapiapp.MainActivity.Name;

public class IntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_row);

        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.Name);
        String url = intent.getStringExtra(MainActivity.URL);

        TextView nameTextView = findViewById(R.id.poke_name);
        TextView urlTextView = findViewById(R.id.poke_url);

        nameTextView.setText("Name: " + name);
        urlTextView.setText("URL: " + url);
    }
}