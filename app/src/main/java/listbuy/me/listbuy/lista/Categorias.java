package listbuy.me.listbuy.lista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import listbuy.me.listbuy.R;

public class Categorias extends AppCompatActivity {
    private  ListView lista;
    private ArrayList<CategoriasProdutos> categorias = new ArrayList<CategoriasProdutos>();
    private int[] id_imagem;
    private String[] nomes_categorias;
    private int cont;
    private String item_selcionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_categorias);
        lista = (ListView) findViewById(R.id.lstCategorias);
        id_imagem = new int[]{R.drawable.bebidas, R.drawable.carnes,
                R.drawable.frios, R.drawable.higiene,
                R.drawable.frutas, R.drawable.legumes,
                R.drawable.limpeza,R.drawable.padaria,R.drawable.definir};
        nomes_categorias = new String[]{"Bebidas","Carnes",
                                        "Frios","Higiene",
                                         "Frutas","Legumes",
                                            "Limpeza","Padaria","Outros"};
        cont = 0;
        for(String nome:nomes_categorias){
            categorias.add(new CategoriasProdutos(id_imagem[cont],nomes_categorias[cont]));
            cont++;
        }
        CategoriasAdapter listCateg = new CategoriasAdapter(Categorias.this, Categorias.this, categorias);
        lista.setAdapter(listCateg);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View v, int posicao, long id){
                //item_selcionado = parent.getItemAtPosition(posicao).
                Toast.makeText(Categorias.this,item_selcionado, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
