        package com.example.projekt;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
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
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lista = findViewById(R.id.lista);
        poleTekstowe = findViewById(R.id.poleTekstowe);
        przyciskDodaj = findViewById(R.id.przyciskDodaj);

        listaElementow.add("wyjscie do kina");
        listaElementow.add("wyjscie do sklepu");
        listaElementow.add("wyjscie do gdzies");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaElementow);
        lista.setAdapter(adapter);

        przyciskDodaj.setOnClickListener(v -> {
            String tekst = poleTekstowe.getText().toString();
            if (!tekst.isEmpty()) {
                listaElementow.add(tekst);
                adapter.notifyDataSetChanged();
                poleTekstowe.setText("");
            }
        });
        // TODO: na 5 nacisniecie elementu listy i przekreslenie usumenice lub itp
        lista.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        view.setBackgroundColor(Color.GRAY);
                        TextView textView = (TextView) view;
                        if(textView.getPaintFlags() == Paint.STRIKE_THRU_TEXT_FLAG){
                            textView.setPaintFlags(0);
                        }else {
                            textView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                        }
                    }
                }
        );
        // TODO: na 6 elementy listy sa obiektami
        // od 1 do 100 dodawanie do listy
    }
}
