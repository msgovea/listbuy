package listbuy.me.listbuy.lista;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import listbuy.me.listbuy.MenuLateral;
import listbuy.me.listbuy.R;
import listbuy.me.listbuy.entities.Listas;
import listbuy.me.listbuy.lista.Sincronizacoes.SincronizaListarListas;
import listbuy.me.listbuy.lista.Sincronizacoes.SincronizaListarProdutos;
import listbuy.me.listbuy.lista.Sincronizacoes.SincronizaLogin;

public class Lista_inicial extends AppCompatActivity implements View.OnClickListener, SincronizaListarListas.Listener {
    private FloatingActionButton fab;
    private FloatingActionButton fabSimples;
    private FloatingActionButton fabEvento;
    private Animation gEsq;
    private Animation gDir;
    private Animation abrir;
    private Animation fechar;
    private ListView listas;
    private String nome_lista;
    private Integer tpLista = 0;
    private boolean flagAber = false;
    public static ArrayList<ListasCriadas> listaCriada = new ArrayList<ListasCriadas>();
    private DbConn dbconn;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.act_lista_inicial);

        //TODO: ESTE COMANDO ADICIONA O MENU SUPERIOR COM O BOTAO VOLTAR E O TITULO 'X' EH NECESSARIO AppCompatActivity COMO EXTEND
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Minhas Listas");

        fab = (FloatingActionButton) findViewById(R.id.fb_plus2);
        fabSimples = (FloatingActionButton) findViewById(R.id.fb_simples);
        fabEvento = (FloatingActionButton) findViewById(R.id.fb_evento);
        abrir = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fechar = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fb_close);
        gEsq = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        gDir = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_ant);
        listas = (ListView) findViewById(R.id.lscListas);
        fab.setOnClickListener(this);
        fabSimples.setOnClickListener(this);
        fabEvento.setOnClickListener(this);

        dbconn = new DbConn(Lista_inicial.this);

        SincronizaListarListas sinc = new SincronizaListarListas(this);
        sinc.execute();


    }

    public void onClick(View v) {

        // valida se o botão + foi clicado
        if (flagAber) {
            fabSimples.startAnimation(fechar);
            fabEvento.startAnimation(fechar);
            fab.startAnimation(gDir);
            fabSimples.setClickable(false);
            fabEvento.setClickable(false);
            flagAber = false;

        } else {

            fabSimples.startAnimation(abrir);
            fabEvento.startAnimation(abrir);
            fab.startAnimation(gEsq);
            fabSimples.setClickable(true);
            fabEvento.setClickable(true);
            flagAber = true;
        }

        Bundle bundle = new Bundle();
        Intent intent = new Intent(this, AdicionarProdutos.class);

        if (v.getId() == R.id.fb_evento) {
            bundle.putString("tipoLista", "E");
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if (v.getId() == R.id.fb_simples) {
            bundle.putString("tipoLista", "P");
            intent.putExtras(bundle);
            startActivity(intent);
        }


    }

    @Override
    public void onLoaded(List<Listas> listas) {


        ListaCriadaAdapter listCriad = new ListaCriadaAdapter(Lista_inicial.this, Lista_inicial.this, listas);
        this.listas.setAdapter(listCriad);
        this.listas.deferNotifyDataSetChanged();

        /*startActivity(new Intent(this, MenuLateral.class));
        SharedPreferences.Editor editor = getSharedPreferences("INFORMACOES_LOGIN_AUTOMATICO", MODE_PRIVATE).edit();

        editor.putString("nome", "teste");
        editor.commit();
        finishActivity(1);-*/

    }
}
