        package com.example.projekt;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

        public class MainActivity extends AppCompatActivity {
    private ListView lista;
    private EditText poleTekstowe;
    private Button przyciskDodaj;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> listaElementow = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


            });
        lista = findViewById(R.id.lista);
        poleTekstowe = findViewById(R.id.poleTekstowe);
        przyciskDodaj = findViewById(R.id.przyciskDodaj);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaElementow);
        przyciskDodaj.setOnClickListener(v -> {
        });
    }
}