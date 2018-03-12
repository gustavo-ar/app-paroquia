package com.example.gustavoar.sgp.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.gustavoar.sgp.R;
import com.example.gustavoar.sgp.config.ConfiguracaoFirebase;
import com.example.gustavoar.sgp.fragment.ParoquiaFragment;
import com.example.gustavoar.sgp.helper.Base64Custom;
import com.example.gustavoar.sgp.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class PrincipalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

//    private MaterialCalendarView calendarView;

//    Context context;
//    private ListView lv_opcoes_paroquia;

    //apagar essas quatro linhas
//    private TextView textoSaudacao, textoSaldo;
//    private Double despesaTotal = 0.0;
//    private Double receitaTotal = 0.0;
//    private Double resumoUsuario = 0.0;

    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
    private DatabaseReference usuarioRef;
    private ValueEventListener valueEventListenerUsuario;

    //private RecyclerView recyclerView;

    //apagar essas duas linhas
//    private AdapterMovimentacao adapterMovimentacao;
//    private List<Movimentacao> movimentacoes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("SGP");
        setSupportActionBar(toolbar);

        //Carrega tela principal
        ParoquiaFragment paroquiaFragment = new ParoquiaFragment();
        FragmentTransaction fragment = getSupportFragmentManager().beginTransaction();
        fragment.replace(R.id.frameContainer, paroquiaFragment);
        fragment.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


//        textoSaldo = findViewById(R.id.textSaldo);
//        textoSaudacao = findViewById(R.id.textSaudacao);
//        calendarView = findViewById(R.id.calendarView);
//        recyclerView = findViewById(R.id.recyclerMovimentos);
//        configuraCalendarView();
//
//        //Configurar adapter
//        adapterMovimentacao = new AdapterMovimentacao(movimentacoes,this);
//
//        //Configurar RecyclerView
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager( layoutManager );
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter( adapterMovimentacao );

    //        inicializarVariavel();
//        inicializarAcao();
//    }
//
//    private void inicializarVariavel() {
//
//        context = getBaseContext();
//
//        lv_opcoes_paroquia = (ListView) findViewById(R.id.lv_paroquia);
//
//        String[] De = {HMAux.TEXTO_02, HMAux.TEXTO_01};
//        int[] Para = {R.id.celula_iv_avatar, R.id.celula_tv_opcoes};
//
//        lv_opcoes_paroquia.setAdapter(
//                new SimpleAdapter(
//                        context,
//                        gerarOpcoes(),
//                        R.layout.celula,
//                        De,
//                        Para
//                )
//        );
//    }
//
//    String opcoes_paroquia[] = {
//            "História",
//            "Membros da Pastoral",
//            "Localização",
//            "Eventos",
//            "Contatos"
//
//    };
//
//    int[] icones = {
//            R.drawable.sobre,
//            R.drawable.rezando,
//            R.drawable.localizacao,
//            R.drawable.calendario,
//            R.drawable.telefone
//
//    };
//
//    private ArrayList<HMAux> gerarOpcoes() {
//        ArrayList<HMAux> opcoes = new ArrayList<>();
//        //
//        for (int i = 0; i < opcoes_paroquia.length; i++) {
//            HMAux hmAux = new HMAux();
//            hmAux.put(HMAux.TEXTO_01, opcoes_paroquia[i]);
//            hmAux.put(HMAux.TEXTO_02, String.valueOf(icones[i]));
//
//            opcoes.add(hmAux);
//        }
//        //
//        return opcoes;
//
//    }
//
//    private void inicializarAcao() {
//
//        lv_opcoes_paroquia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                int item = (int) parent.getItemIdAtPosition(position);
//
//                switch (item) {
//
//                    case 0:
//                        Intent intent = new Intent(context, HistoriaActivity.class);
//                        startActivity(intent);
//
//                        break;
//                    case 1:
//                        Intent membrosintent = new Intent(context, MembrosPastoralActivity.class);
//                        startActivity(membrosintent);
//
//                        break;
//                    case 2:
//                        Intent localizacaointent = new Intent(context, LocalizacaoActivity.class);
//                        startActivity(localizacaointent);
//
//                        break;
//                    case 3:
//                        Intent eventosintent = new Intent(context, EventosActivity.class);
//                        startActivity(eventosintent);
//
//                        break;
//                    case 4:
//                        Intent contatosintent = new Intent(context, ContatosActivity.class);
//                        startActivity(contatosintent);
//
//                        break;
//                }
//            }
//        });
//    }
//
    @Override
    protected void onStart() {
        super.onStart();
        recuperarResumo();
    }

    public void recuperarResumo() {

        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        usuarioRef = firebaseRef.child("usuarios").child(idUsuario);

        valueEventListenerUsuario = usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Usuario usuario = dataSnapshot.getValue(Usuario.class);
//
//                despesaTotal = usuario.getDespesaTotal();
//                receitaTotal = usuario.getReceitaTotal();
//                resumoUsuario = receitaTotal - despesaTotal;
//
//                DecimalFormat decimalFormat = new DecimalFormat("0.##");
//                String resultadoFormatado = decimalFormat.format( resumoUsuario );
//
//                textoSaudacao.setText("Olá, " + usuario.getNome() );
//                textoSaldo.setText( "R$ " + resultadoFormatado );

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


//    public void adicionarDespesa(View view){
////        startActivity(new Intent(this, DespesasActivity.class));
//    }
//
//    public void adicionarReceita(View view){
////        startActivity(new Intent(this, ReceitasActivity.class));
//    }

//    public void configuraCalendarView(){
//
//        CharSequence meses[] = {"Janeiro","Fevereiro", "Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
//        calendarView.setTitleMonths( meses );
//
//        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
//            @Override
//            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
//
//            }
//        });
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuSair:
                autenticacao.signOut();
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        usuarioRef.removeEventListener(valueEventListenerUsuario);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_dizimo) {

            startActivity( new Intent(this, DizimoActivity.class ) );

        } else if (id == R.id.nav_casamento) {

            startActivity( new Intent(this, CasamentoActivity.class ) );

        } else if (id == R.id.nav_batizado) {



        } else if (id == R.id.nav_catequese) {



        } else if (id == R.id.nav_documentos) {



        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    public void enviarEmail() {
//        Intent email = new Intent(Intent.ACTION_SEND);
//        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"gestaoparoquial@gmail.com"});
//        email.putExtra(Intent.EXTRA_SUBJECT, "Contato pelo App");
//        email.putExtra(Intent.EXTRA_TEXT, "Mensagem automática");
//
//        //configurar apps para e-mail
//        email.setType("message/rfc822");
//        //email.setType("application/pdf");
//        //email.setType("image/png");
//
//        startActivity(Intent.createChooser(email, "Escolha o App de e-mail:"));
//    }
}
