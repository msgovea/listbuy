package listbuy.me.listbuy.lista;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import listbuy.me.listbuy.MenuLateral;
import listbuy.me.listbuy.R;
import listbuy.me.listbuy.lista.Sincronizacoes.SincronizaListarListas;
import listbuy.me.listbuy.lista.Sincronizacoes.SincronizaListarProdutos;

public class AdicionarProdutos extends AppCompatActivity implements View.OnClickListener {

    private ListView lista;
    private EditText nome_lista;
    private Toolbar toolbar;
    private Button btn_salvar;
    public static ArrayList<Produtos> produtos = new ArrayList<Produtos>();
    private DbConn dbconn;
    private String nome_recebido ="";
    private String nome_categoria = "";
    private String nome_produto = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_adicionar_produtos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Adicionar Prodtuos");

        SincronizaListarProdutos sync = new SincronizaListarProdutos();
        sync.execute();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        btn_salvar = (Button)findViewById(R.id.btnSal);
        btn_salvar.setOnClickListener(this);

        lista = (ListView) findViewById(R.id.lstlist);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        dbconn = new DbConn(AdicionarProdutos.this);

        Intent it = getIntent();
        if(it.getStringExtra("NOME_ENVIADO")!= null) {
            nome_recebido = it.getStringExtra("NOME_ENVIADO");
            if (nome_recebido != "") {
                ListaAdapter listProd = new ListaAdapter(AdicionarProdutos.this, AdicionarProdutos.this, dbconn.selectProdId(dbconn.selectIdLista(nome_recebido)));
                lista.setAdapter(listProd);
                lista.deferNotifyDataSetChanged();
            }
        }
        if(it.getStringExtra("CATEGORIA") != null){
            nome_categoria = it.getStringExtra("CATEGORIA");
            if(nome_categoria != ""){
                ListaAdapter listProd = new ListaAdapter(AdicionarProdutos.this,AdicionarProdutos.this,produtos);
                lista.setAdapter(listProd);
                lista.deferNotifyDataSetChanged();
            }
        }
        if(it.getStringExtra("PRODUTO_ENVIADO") != null){
            nome_produto = it.getStringExtra("PRODUTO_ENVIADO");
            if(nome_produto != ""){
                ListaAdapter listProd = new ListaAdapter(AdicionarProdutos.this, AdicionarProdutos.this, dbconn.selectProdId(dbconn.selectIdLista(nome_recebido)));
                lista.setAdapter(listProd);
                lista.deferNotifyDataSetChanged();
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
        if(v.getId() == R.id.btnSal){

                    nome_lista = (EditText) findViewById(R.id.edtListas);
                    String nome = nome_lista.getText().toString();
                    dbconn = new DbConn(AdicionarProdutos.this);
                    dbconn.insereNovaLista(produtos, nome);
                    startActivity(new Intent(this, Lista_inicial.class));
                    produtos.clear();

        }
    }
    /*protected class Sincroniza extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... n) {
            String api_url = R.string.url_listar_listas + "1/";
            try{
                URL url = new URL(api_url);
                URLConnection urlc = url.openConnection();
                BufferedReader bfr = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
                String line = bfr.readLine();
                Log.i("teste_api",line);
                JSONArray api_response = new JSONArray(line);

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

        }
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, Lista_inicial.class));
        finishActivity(0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:break;
        }
        return true;
    }

}
