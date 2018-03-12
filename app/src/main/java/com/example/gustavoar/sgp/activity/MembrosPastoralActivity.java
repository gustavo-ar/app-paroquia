package com.example.gustavoar.sgp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.gustavoar.sgp.R;
import com.example.gustavoar.sgp.util.HMAux;

import java.util.ArrayList;

public class MembrosPastoralActivity extends AppCompatActivity {

    private Context context;

    private ListView lv_membros_pastoral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membros_pastoral);

        inicializarVariavel();
        inicializarAcao();
    }

    private void inicializarVariavel() {
        context = getBaseContext();

        lv_membros_pastoral = (ListView) findViewById(R.id.lv_membros_pastoral);

        String[] De = {HMAux.TEXTO_02, HMAux.TEXTO_01};
        int[] Para = {R.id.celula_iv_avatar, R.id.celula_tv_opcoes};

        lv_membros_pastoral.setAdapter(
                new SimpleAdapter(
                        context,
                        gerarOpcoes(),
                        R.layout.celula,
                        De,
                        Para
                )
        );
    }

    String opcoes_pastoral[] = {
            "Pastoral da Acolhida",
            "Pastoral do Batismo",
            "Pastoral da Catequese",
            "Pastoral da Criança",
            "Pastoral do Dizimo",
            "Pastoral da Familia",
            "Pastoral da Juventude",
            "Pastoral da Liturgia",
            "Pastoral da Rua",
            "Pastoral da Saúde"
    };

    int[] icones = {
            R.drawable.emconstrucao,
            R.drawable.pastoraldobatismo,
            R.drawable.pastoraldacatequese,
            R.drawable.emconstrucao,
            R.drawable.pastoraldodizimo,
            R.drawable.pastoraldafamilia,
            R.drawable.pastoraldajuventude,
            R.drawable.pastoraldaliturgia,
            R.drawable.emconstrucao,
            R.drawable.pastoraldasaude
    };

    private ArrayList<HMAux> gerarOpcoes() {
        ArrayList<HMAux> opcoes = new ArrayList<>();
        //
        for (int i = 0; i < opcoes_pastoral.length; i++) {
            HMAux hmAux = new HMAux();
            hmAux.put(HMAux.TEXTO_01, opcoes_pastoral[i]);
            hmAux.put(HMAux.TEXTO_02, String.valueOf(icones[i]));

            opcoes.add(hmAux);
        }
        //
        return opcoes;
    }

    private void inicializarAcao() {

        lv_membros_pastoral.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int item = (int) parent.getItemIdAtPosition(position);

                switch (item) {

                    case 0:
//                        Intent intent = new Intent(context, Teste.class);
//                        startActivity(intent);

                        break;
                }
            }
        });
    }
}

