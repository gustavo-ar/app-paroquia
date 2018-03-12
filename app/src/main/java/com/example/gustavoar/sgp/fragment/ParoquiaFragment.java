package com.example.gustavoar.sgp.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.gustavoar.sgp.R;
import com.example.gustavoar.sgp.activity.ContatosActivity;
import com.example.gustavoar.sgp.activity.EventosActivity;
import com.example.gustavoar.sgp.activity.HistoriaActivity;
import com.example.gustavoar.sgp.activity.LocalizacaoActivity;
import com.example.gustavoar.sgp.activity.MembrosPastoralActivity;
import com.example.gustavoar.sgp.config.ConfiguracaoFirebase;
import com.example.gustavoar.sgp.util.HMAux;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ParoquiaFragment extends Fragment {

    Context context;
    private ListView lv_opcoes_paroquia;

    public ParoquiaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_paroquia, container, false);

        inicializarVariavel(view);
        inicializarAcao();

        return view;
    }

    private void inicializarVariavel(View view) {

        context = getActivity();

        lv_opcoes_paroquia = (ListView) view.findViewById(R.id.lv_paroquia);

        String[] De = {HMAux.TEXTO_02, HMAux.TEXTO_01};
        int[] Para = {R.id.celula_iv_avatar, R.id.celula_tv_opcoes};

        lv_opcoes_paroquia.setAdapter(
                new SimpleAdapter(
                        context,
                        gerarOpcoes(),
                        R.layout.celula,
                        De,
                        Para
                )
        );

    }

    String opcoes_paroquia[] = {
            "História",
            "Membros da Pastoral",
            "Localização",
            "Eventos",
            "Contatos"

    };

    int[] icones = {
            R.drawable.sobre,
            R.drawable.rezando,
            R.drawable.localizacao,
            R.drawable.calendario,
            R.drawable.telefone

    };

    private ArrayList<HMAux> gerarOpcoes() {
        ArrayList<HMAux> opcoes = new ArrayList<>();
        //
        for (int i = 0; i < opcoes_paroquia.length; i++) {
            HMAux hmAux = new HMAux();
            hmAux.put(HMAux.TEXTO_01, opcoes_paroquia[i]);
            hmAux.put(HMAux.TEXTO_02, String.valueOf(icones[i]));

            opcoes.add(hmAux);
        }
        //
        return opcoes;

    }

    private void inicializarAcao() {

        lv_opcoes_paroquia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int item = (int) parent.getItemIdAtPosition(position);

                switch (item) {

                    case 0:
                        Intent intent = new Intent(context, HistoriaActivity.class);
                        startActivity(intent);

                        break;
                    case 1:
                        Intent membrosintent = new Intent(context, MembrosPastoralActivity.class);
                        startActivity(membrosintent);

                        break;
                    case 2:
                        Intent localizacaointent = new Intent(context, LocalizacaoActivity.class);
                        startActivity(localizacaointent);

                        break;
                    case 3:
                        Intent eventosintent = new Intent(context, EventosActivity.class);
                        startActivity(eventosintent);

                        break;
                    case 4:
                        Intent contatosintent = new Intent(context, ContatosActivity.class);
                        startActivity(contatosintent);

                        break;
                }
            }
        });
    }
}


