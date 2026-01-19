package com.example.projekt;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
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
    private Spinner spinner;
    private TextView podsumowanie;
    private ArrayAdapter<Item> adapter;
    private ArrayList<Item> listaElementow = new ArrayList<>();

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
        spinner = findViewById(R.id.spinner);
        podsumowanie = findViewById(R.id.podsumowanie);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                this, R.array.priorytet, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        listaElementow.add(new Item("wyjście do kina", (byte) 2));
        listaElementow.add(new Item("wyjście do sklepu", (byte) 1));
        listaElementow.add(new Item("wyjście gdzieś", (byte) 2));

        adapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, listaElementow) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv = (TextView) super.getView(position, convertView, parent);
                Item item = getItem(position);
                if (item != null) {
                    tv.setText(item.toString());
                    if (Boolean.TRUE.equals(item.getCzyWykonane())) {
                        tv.setPaintFlags(tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        tv.setTextColor(Color.GRAY);
                    } else {
                        tv.setPaintFlags(tv.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                        switch (item.getPriorytet()) {
                            case 1:
                                tv.setTextColor(0xFFFF0000);
                                break;
                            case 2:
                                tv.setTextColor(0xFFFF8800);
                                break;
                            default:
                                tv.setTextColor(0xFF000000);
                                break;
                        }
                    }
                }
                return tv;
            }
        };
        lista.setAdapter(adapter);
        aktualizujPodsumowanie();

        przyciskDodaj.setOnClickListener(v -> {
            String tekst = poleTekstowe.getText().toString().trim();
            if (!tekst.isEmpty()) {
                byte pri = Byte.parseByte((String) spinner.getSelectedItem());
                listaElementow.add(new Item(tekst, pri));
                adapter.notifyDataSetChanged();
                poleTekstowe.setText("");
                aktualizujPodsumowanie();
            }
        });

        lista.setOnItemClickListener((parent, view, position, id) -> {
            Item it = listaElementow.get(position);
            it.setCzyWykonane(!it.getCzyWykonane());
            adapter.notifyDataSetChanged();
            aktualizujPodsumowanie();
        });

        lista.setOnItemLongClickListener((parent, view, position, id) -> {
            listaElementow.remove(position);
            adapter.notifyDataSetChanged();
            aktualizujPodsumowanie();
            return true;
        });
    }

    private void aktualizujPodsumowanie() {
        int pozostale = 0;
        for (Item it : listaElementow) {
            if (!Boolean.TRUE.equals(it.getCzyWykonane())) {
                pozostale++;
            }
        }
        podsumowanie.setText("Do zrobienia: " + pozostale);
    }
}