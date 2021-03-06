package listbuy.me.listbuy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import listbuy.me.listbuy.lista.CategoriasProdutos;
import listbuy.me.listbuy.lista.DadosFeeds;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import listbuy.me.listbuy.lista.AdapterGrid;
import listbuy.me.listbuy.lista.DetalhesOfertas;

public class Feeds extends AppCompatActivity {
    private RecyclerView rec;
    private ImageView imagem;
    private String[] nomes_categorias;
    private ArrayList<DadosFeeds> teste = new ArrayList<DadosFeeds>();
    private int cont;
    private int[] id_imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_feeds);
        rec = (RecyclerView) findViewById(R.id.recycle);
        imagem = (ImageView) findViewById(R.id.imageView3);
        getSupportActionBar().setTitle("TELA DE FEEDS");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Meu Feed");


        rec.setHasFixedSize(true);
        StaggeredGridLayoutManager llm = new StaggeredGridLayoutManager(2, 1);
        //Para fazer a grid. O parametro numero 2 é o tanto de colunas que irá ter
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        //Setar a orientação do layout
        rec.setLayoutManager(llm);
        //aplicar ao recycleview

        id_imagem = new int[]{R.drawable.carrefor, R.drawable.covabra,
                R.drawable.arena, R.drawable.walmart,
                R.drawable.covabra, R.drawable.dia,
                R.drawable.oferta, R.drawable.padaria, R.drawable.definir};

        nomes_categorias = new String[]{"Carrefour", "Covabra",
                "Arena", "Good Bom",
                "Pague Menos", "Walmart",
                "BIG", "Poupar", "Dia"};
        cont = 0;
        /*for (String nome : nomes_categorias) {
            teste.add(new DadosFeeds(id_imagem[cont], nomes_categorias[cont]));
            cont++;
        }*/

        teste.add(new DadosFeeds(R.drawable.carrefor, "Carrefour"));
        teste.add(new DadosFeeds(R.drawable.covabra, "Covabra"));
        teste.add(new DadosFeeds(R.drawable.arena, "Arena"));
        teste.add(new DadosFeeds(R.drawable.walmart, "Walmart"));
        teste.add(new DadosFeeds(R.drawable.covabra, "Pague Menos"));
        teste.add(new DadosFeeds(R.drawable.dia, "Dia"));
        teste.add(new DadosFeeds(R.drawable.big, "BIG"));
        teste.add(new DadosFeeds(R.drawable.arena, "Poupar"));
        teste.add(new DadosFeeds(R.drawable.good, "Good Bom"));

        rec.setAdapter(new AdapterGrid(teste,this));

        rec.setAdapter(new AdapterGrid(teste,this));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MenuLateral.class));
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
