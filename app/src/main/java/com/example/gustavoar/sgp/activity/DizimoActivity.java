package com.example.gustavoar.sgp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.gustavoar.sgp.R;

/**
 * Created by GUSTAVOAR on 05/03/2018.
 */

public class DizimoActivity extends AppCompatActivity {

    private Context context;

    // Spinner Estado Civil
    private Spinner sp_estado_civil;
    String[] estado_civil = new String[]{"<Selecione a Opção>", "Solteiro", "Casado Civil", "Casado Igreja e Civil", "Divorciado"};
    ArrayAdapter<String> adapter_estado_civil;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dizimo);

        inicializarVariavel();
        inicializarAcao();

    }

    private void inicializarVariavel() {

        context = getBaseContext();
        sp_estado_civil = (Spinner) findViewById(R.id.sp_estado_civil);

        //Adaptador String
        adapter_estado_civil = new ArrayAdapter<String>
                (context, android.R.layout.simple_spinner_item, estado_civil
                );

        adapter_estado_civil.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp_estado_civil.setAdapter(adapter_estado_civil);
    }

    private void inicializarAcao() {
    }
}
