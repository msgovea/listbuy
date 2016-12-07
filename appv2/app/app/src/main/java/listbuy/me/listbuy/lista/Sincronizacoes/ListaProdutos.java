package listbuy.me.listbuy.lista.Sincronizacoes;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import listbuy.me.listbuy.LoginActivity;
import listbuy.me.listbuy.R;
import listbuy.me.listbuy.lista.AdicionarProdutos;
import listbuy.me.listbuy.lista.DbConn;
import listbuy.me.listbuy.lista.ListaAdapter;
import listbuy.me.listbuy.lista.Lista_inicial;
import listbuy.me.listbuy.lista.Produtos;

public class ListaProdutos extends AppCompatActivity implements View.OnClickListener  {

    private ListView lista;
    private EditText nome_lista;
    private Toolbar toolbar;
    private Button btn_cancel;
    public static ArrayList<Produtos> produtos = new ArrayList<Produtos>();
    private DbConn dbconn;
    private String nome_recebido ="";
    private String nome_categoria = "";
    private String nome_produto = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_lista_produtos);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Detalhes dos Produtos");

        SincronizaListarProdutos sync = new SincronizaListarProdutos();
        sync.execute();

        btn_cancel = (Button)findViewById(R.id.btnVoltar);
        btn_cancel.setOnClickListener(this);
        lista = (ListView) findViewById(R.id.lslistaprod);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Meus Produtos");
        dbconn = new DbConn(ListaProdutos.this);

        Intent it = getIntent();
        if(it.getStringExtra("NOME_ENVIADO")!= null) {
            nome_recebido = it.getStringExtra("NOME_ENVIADO");
            if (nome_recebido != "") {
                ListaAdapter listProd = new ListaAdapter(ListaProdutos.this, ListaProdutos.this, dbconn.selectProdId(dbconn.selectIdLista(nome_recebido)));
                lista.setAdapter(listProd);
                lista.deferNotifyDataSetChanged();
            }
        }
        if(it.getStringExtra("CATEGORIA") != null){
            nome_categoria = it.getStringExtra("CATEGORIA");
            if(nome_categoria != ""){
                ListaAdapter listProd = new ListaAdapter(ListaProdutos.this,ListaProdutos.this,produtos);
                lista.setAdapter(listProd);
                lista.deferNotifyDataSetChanged();
            }
        }
        if(it.getStringExtra("PRODUTO_ENVIADO") != null){
            nome_produto = it.getStringExtra("PRODUTO_ENVIADO");
            if(nome_produto != ""){
                ListaAdapter listProd = new ListaAdapter(ListaProdutos.this, ListaProdutos.this, dbconn.selectProdId(dbconn.selectIdLista(nome_recebido)));
                lista.setAdapter(listProd);
                lista.deferNotifyDataSetChanged();
            }
        }
    }
    public void onClick(View v) {

        if(v.getId()== R.id.btnVoltar){
            startActivity(new Intent(getApplicationContext(),Lista_inicial.class));
        }



    }
}
