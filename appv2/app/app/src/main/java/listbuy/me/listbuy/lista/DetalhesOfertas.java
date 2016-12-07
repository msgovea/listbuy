package listbuy.me.listbuy.lista;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import listbuy.me.listbuy.MenuLateral;
import listbuy.me.listbuy.R;

public class DetalhesOfertas extends AppCompatActivity {

    private TextView nome;
    private TextView nome2;
    private ImageView imagem2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_detalhes_ofertas);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Detalhes dos Feeds");

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, Feeds.class));
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
