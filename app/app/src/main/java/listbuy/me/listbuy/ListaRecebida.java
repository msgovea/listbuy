package listbuy.me.listbuy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class ListaRecebida extends AppCompatActivity {

    EditText teste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_recebida);

        Bundle bundle = this.getIntent().getExtras();
        String id = bundle.getString("id");

        teste = (EditText) findViewById(R.id.testeLink);
        teste.setText(id);
    }
}
