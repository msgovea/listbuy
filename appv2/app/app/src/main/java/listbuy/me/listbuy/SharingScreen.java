package listbuy.me.listbuy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;


public class SharingScreen extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharing);

        iv = (ImageView) findViewById(R.id.imageShare);
        iv.setImageResource(R.drawable.ic_share);

        Button btShareList = (Button) findViewById(R.id.bt_share_list);
        Button btShareOffer = (Button) findViewById(R.id.bt_share_offer);

        btShareList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareList();
            }

        });
        btShareOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareOffer();
            }

        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Compartilhamento");
    }

    private void shareList() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String id = "123";
        intent.putExtra(Intent.EXTRA_TEXT, "https://g65q8.app.goo.gl/?link=http://listbuy.com/idLista/"+id+"&apn=listbuy.me.listbuy");
        startActivity(intent);
    }

    private void shareOffer() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, 5678);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 5678 && resultCode == RESULT_OK){
            //imagem veio da camera
            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");

            //Comprime o bitmap em um stream e depois converte em um ByteArray
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imagem.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] bitMapData = stream.toByteArray();

            //Insere o ByteArray na Intent e iniciamos Compartilhamento de Oferta com os dados extras da intent
            Intent intent = new Intent(SharingScreen.this, SharingOffer.class);
            intent.putExtra("imagem", bitMapData);
            startActivity(intent);
        }
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