package listbuy.me.listbuy.lista;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import listbuy.me.listbuy.R;

public class DetalhesOfertas extends AppCompatActivity {
    private TextView nome;
    private TextView nome2;
    private ImageView imagem2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_detalhes_ofertas);
        imagem2 = (ImageView)findViewById(R.id.imageView4);
        nome = (TextView)findViewById(R.id.txtDetalhe1);
        nome2 = (TextView)findViewById(R.id.data);

        if(getIntent().hasExtra("nome")) {
            nome.setText("Nome Supermercado:" + getIntent().getStringExtra("nome"));
        }
            if (getIntent().hasExtra("img")) {


                Bitmap b = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("img"), 0, getIntent().getByteArrayExtra("img").length);
                imagem2.setImageBitmap(b);
            }
        }

    }

