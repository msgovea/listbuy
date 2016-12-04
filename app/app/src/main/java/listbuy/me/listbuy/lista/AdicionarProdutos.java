package listbuy.me.listbuy.lista;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import listbuy.me.listbuy.R;
import listbuy.me.listbuy.entities.Consumidor;
import listbuy.me.listbuy.entities.Listas;
import listbuy.me.listbuy.entities.Produtos;
import listbuy.me.listbuy.lista.Sincronizacoes.SincronizaCriarLista;
import listbuy.me.listbuy.lista.Sincronizacoes.SincronizaListarListas;
import listbuy.me.listbuy.lista.Sincronizacoes.SincronizaListarProdutos;

public class AdicionarProdutos extends AppCompatActivity implements View.OnClickListener, SincronizaCriarLista.Listener, SincronizaListarProdutos.Listener {

    private ListView lista;
    private EditText nome_lista;
    private Toolbar toolbar;
    private Button btn_salvar;
    public static ArrayList<Produtos> produtos = new ArrayList<Produtos>();
    private DbConn dbconn;
    private Long id_lista;
    private String nome_categoria = "";
    private String nome_produto = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_adicionar_produtos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*SincronizaListarProdutos sync = new SincronizaListarProdutos();
        sync.execute();*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        btn_salvar = (Button) findViewById(R.id.btnSal);
        btn_salvar.setOnClickListener(this);

        lista = (ListView) findViewById(R.id.lstlist);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        dbconn = new DbConn(AdicionarProdutos.this);

        Intent it = getIntent();
        if (it.getLongExtra("ID_LISTA", 0) != 0) {
            id_lista = it.getLongExtra("ID_LISTA", 0);
            if (id_lista != null) {

                SincronizaListarProdutos sync = new SincronizaListarProdutos(this);
                sync.execute();


            }
        }
    }

    public void onClick(View v) {
        Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        if (v.getId() == R.id.fab) {
            //startActivity(new Intent(this, DadosProdutos.class));
            startActivity(new Intent(this, Categorias.class));
        }
        if (v.getId() == R.id.btnSal) {

            nome_lista = (EditText) findViewById(R.id.edtListas);
            String nome = nome_lista.getText().toString();

            Bundle bundle = new Bundle();

            dbconn = new DbConn(AdicionarProdutos.this);
            Consumidor consumidor = dbconn.selectConsumidor();

            SincronizaCriarLista sinc = new SincronizaCriarLista(this);
            sinc.execute(getIntent().getExtras().getString("tipoLista"),
                    nome,
                    consumidor.getId_consumidor(),
                    "Y");
        }
    }

    @Override
    public void onLoaded(Listas lista) {
        startActivity(new Intent(this, Lista_inicial.class));
    }

    @Override
    public void onLoadedProdutos(List<Produtos> produtos) {
        // TODO: getProductsList(API)
        ListaAdapter listProd = new ListaAdapter(AdicionarProdutos.this, AdicionarProdutos.this, produtos);
        lista.setAdapter(listProd);
        lista.deferNotifyDataSetChanged();
    }
}
