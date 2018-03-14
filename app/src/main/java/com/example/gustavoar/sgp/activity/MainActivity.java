package com.example.gustavoar.sgp.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.gustavoar.sgp.config.ConfiguracaoFirebase;
import com.example.gustavoar.sgp.fragment.ParoquiaFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.firebase.ui.auth.AuthUI;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.app.OnNavigationBlockedListener;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.example.gustavoar.sgp.R;

import java.util.Arrays;

public class MainActivity extends IntroActivity {

    private FirebaseAuth autenticacao;
//Listener para ver se o cara esta autenticado no firebase ou nao Cassio
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    public static final int RC_SIGN_IN = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_1)
                .build());

      /*  addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_2)
                .build());*/

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_3)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_4)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_cadastro_v2)
                .build());


        addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position ==3){
                    verificarUsuarioLogado();
                }
            }
            @Override public void onPageSelected(int position) {

               // Toast.makeText(getBaseContext(),position,Toast.LENGTH_SHORT).show();

                //Verifica se o slide esta na opcao do login e valida se o mesmo ja encontra-se logado ou nao
                if(position ==3){
                //    autenticacao.signOut();
                    verificarUsuarioLogado();
                }

            }
            @Override public void onPageScrollStateChanged(int state) {


            }
        });

        // Cria o listener do fireauth
        geraListenerFirebase();
        //Cria o Objeto fireauth
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //verificarUsuarioLogado();Retirado para tratar no listener

    }

    public void btEntrar(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void btCadastrar(View view) {
        startActivity(new Intent(this, CadastroActivity.class));
    }

    public void verificarUsuarioLogado() {

        //autenticacao.signOut();
       /* if (autenticacao.getCurrentUser() != null) {
            validarEmailLogin();
        }*/
     //Addiciona o listener ao objeto autenticação
     autenticacao.addAuthStateListener(mAuthStateListener);

    }

    public void abrirTelaPrincipal() {
        startActivity(new Intent(this, PrincipalActivity.class));
    }

    public void abrirTelaPrincipalAdmin() {
        startActivity(new Intent(this, PrincipalActivityAdmin.class));
    }

    public void validarEmailLogin(  FirebaseUser user){

       // String dominio = String.valueOf(autenticacao.getCurrentUser());
        //Inserido por cassio para pegar o email do usuario Logado
        String email = user.getEmail();
//        if (dominio.contains("@gmail.com")){
//            abrirTelaPrincipal();
//        }else if (dominio.contains("@hotmail.com")){
//            abrirTelaPrincipal();
//        }else{
//            abrirTelaPrincipalAdmin();

        if (email.contains("gmail")){
            //descomentar aqui quando estiver pronto a tela principal
            //abrirTelaPrincipalAdmin();
            Toast.makeText(getBaseContext(),"Abre a tela Principal Admin",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getBaseContext(),"Abre a Tela Principal",Toast.LENGTH_SHORT).show();
            abrirTelaPrincipal();
        }
    }

    private void geraListenerFirebase(){
        //User is signed out
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    //User is signed in
                    //onSignedInitialize(user.getDisplayName());

                    validarEmailLogin(user);
                } else {
                    //User is signed out
                    //onSignedOutCleanup();
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.EmailBuilder().build(),
                                            new AuthUI.IdpConfig.GoogleBuilder().build()))
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };
    }
    //Inserido por Cassio para iniciar o listener da autenticação
    @Override
    protected void onResume() {
        super.onResume();
        if(autenticacao != null){
            autenticacao.addAuthStateListener(mAuthStateListener);
        }

    }

    //Inserido por Cassio para pausar o listener da autenticação
    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthStateListener != null) {
            autenticacao.removeAuthStateListener(mAuthStateListener);
        }

    }

}
