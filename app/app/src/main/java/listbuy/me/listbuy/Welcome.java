package listbuy.me.listbuy;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import listbuy.me.listbuy.entities.Consumidor;
import listbuy.me.listbuy.lista.AdicionarProdutos;
import listbuy.me.listbuy.lista.DbConn;


public class Welcome extends AppCompatActivity {

    private Button log_out;
    private Usuario usu;
    private DbConn dbconn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Meu Perfil");

        dbconn = new DbConn(Welcome.this);

        log_out = (Button) findViewById(R.id.button);
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                dbconn.deleteConsumidor();
            }
        });

        dbconn = new DbConn(this);
        Consumidor consumidor = dbconn.selectConsumidor();

        TextView nome = (TextView) findViewById(R.id.txtnome);
        TextView email = (TextView) findViewById(R.id.txtemail);
        TextView senha = (TextView) findViewById(R.id.txtsenha);

        nome.setText(consumidor.getNome());
        email.setText(consumidor.getEmail());
        senha.setText("*********");
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
