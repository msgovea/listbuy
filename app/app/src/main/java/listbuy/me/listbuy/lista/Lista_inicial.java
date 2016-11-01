package listbuy.me.listbuy.lista;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import listbuy.me.listbuy.R;

public class Lista_inicial extends AppCompatActivity implements View.OnClickListener {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_lista_inicial);

        fab = (FloatingActionButton)findViewById(R.id.fb_plus2);
        fabSimples = (FloatingActionButton)findViewById(R.id.fb_simples);
        fabEvento = (FloatingActionButton)findViewById(R.id.fb_evento);
        abrir = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fechar = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fb_close);
        gEsq = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        gDir = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_ant);
        listas = (ListView)findViewById(R.id.lscListas);
        fab.setOnClickListener(this);
        fabSimples.setOnClickListener(this);
        fabEvento.setOnClickListener(this);

        dbconn = new DbConn(Lista_inicial.this);

        Intent it = getIntent();
        String nome_del = "";
        if(it.getStringExtra("NOME_DEL")!= null) {
            nome_del = it.getStringExtra("NOME_DEL");
            if (nome_del != "") {

                dbconn.deleteListaprodIdLista(dbconn.selectIdLista(nome_del));
                dbconn.deleteListaId(dbconn.selectIdLista(nome_del));
            }
        }
        ListaCriadaAdapter listCriad = new ListaCriadaAdapter(Lista_inicial.this,Lista_inicial.this,dbconn.selectListasCriadas());
        listas.setAdapter(listCriad);
        listas.deferNotifyDataSetChanged();

    }
    public void onClick(View v){

        // valida se o botão + foi clicado
        if(flagAber){
            fabSimples.startAnimation(fechar);
            fabEvento.startAnimation(fechar);
            fab.startAnimation(gDir);
            fabSimples.setClickable(false);
            fabEvento.setClickable(false);
            flagAber = false;

        }
        else{

            fabSimples.startAnimation(abrir);
            fabEvento.startAnimation(abrir);
            fab.startAnimation(gEsq);
            fabSimples.setClickable(true);
            fabEvento.setClickable(true);
            flagAber = true;
        }

        if (v.getId() == R.id.fb_evento){
            startActivity(new Intent(this,AdicionarProdutos.class));
            Toast.makeText(getApplicationContext(), "lista evento", Toast.LENGTH_SHORT).show();
            tpLista = 1;
        }
        if(v.getId() == R.id.fb_simples){
            startActivity(new Intent(this,AdicionarProdutos.class));
            Toast.makeText(getApplicationContext(), "lista simples", Toast.LENGTH_SHORT).show();
            tpLista = 2;
        }

    }
}
