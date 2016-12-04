package listbuy.me.listbuy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import listbuy.me.listbuy.lista.CategoriasProdutos;
import listbuy.me.listbuy.lista.DadosFeeds;

import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import listbuy.me.listbuy.lista.AdapterGrid;

public class Feeds extends AppCompatActivity {
    private RecyclerView rec;
    private String[] nomes_categorias;
    private ArrayList<DadosFeeds> teste = new ArrayList<DadosFeeds>();
    private int cont;
    private int[] id_imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_feeds);
        rec = (RecyclerView)findViewById(R.id.recycle);
        getSupportActionBar().setTitle("TELA DE FEEDS");


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
                R.drawable.oferta,R.drawable.padaria,R.drawable.definir};

        nomes_categorias = new String[]{"Bebidas","Carnes",
                "Frios","Higiene",
                "Frutas","Legumes",
                "Limpeza","Padaria","Outros"};
        cont = 0;
        for(String nome:nomes_categorias){
            teste.add(new DadosFeeds(id_imagem[cont],nomes_categorias[cont]));
            cont++;
        }

        rec.setAdapter(new AdapterGrid(teste));
    }
}