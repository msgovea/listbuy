package listbuy.me.listbuy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class SharingOffer extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharing_offer);

        // Realiza a leitura das informações enviadas pela Activity anterior e a armazenamos em um Bundle
        Bundle bundle = this.getIntent().getExtras();

        // Realiza a conversão do ByteArray para um Bitmap
        byte[] imagemByteArray = bundle.getByteArray("imagem");
        Bitmap imagem = BitmapFactory.decodeByteArray(imagemByteArray, 0, imagemByteArray.length);

        // Instancia e insere a imagem na ImageView
        imageView = (ImageView) findViewById(R.id.capturedImage);
        imageView.setImageBitmap(imagem);


        Button btShare = ( Button ) findViewById( R.id.bt_share );
        Button btCancel = ( Button ) findViewById( R.id.bt_cancel );

        btShare.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                share();
            }
        } );

        btCancel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                cancel();
            }
        } );
    }

    private void share() {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("android.resource://")
                // defina o seu package aqui
                .append("pucc.listbuy")
                //drawable a ser capturado, repare que esse é o drawable definido no src do ImageView
                .append(imageView);

        //captura a uri do drawable que está no Imageview
        Uri uriImage = Uri.parse(strBuilder.toString());
        // cria a intent e define a ação
        Intent intent = new Intent(Intent.ACTION_SEND);
        // tipo de conteúdo da intent
        intent.setType("image/*");
        // stream a ser compartilhado
        intent.putExtra(Intent.EXTRA_STREAM, uriImage);

        startActivity(intent);
    }

    private void cancel(){
        finishActivity(1);
    }
}
