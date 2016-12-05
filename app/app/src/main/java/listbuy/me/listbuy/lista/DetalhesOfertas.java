package listbuy.me.listbuy.lista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import listbuy.me.listbuy.R;

public class DetalhesOfertas extends AppCompatActivity {
    private String imagem;
    private ImageView imagem2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_detalhes_ofertas);
        imagem2 = (ImageView)findViewById(R.id.imageView4);
        int i = R.drawable.arena;
        Intent it = getIntent();
        Toast.makeText(DetalhesOfertas.this,it.getStringExtra("IMAGEM"), Toast.LENGTH_SHORT).show();
        imagem2.setImageResource(i);
        if(it.getStringExtra("IMAGEM")!= null) {
            imagem = it.getStringExtra("IMAGEM");
            //Toast.makeText(,ci.getImagem(), Toast.LENGTH_SHORT).show();
            /*if (nome_categ != "") {
                txtCategoria.setText(nome_categ);
            }*/
        }
    }
}
