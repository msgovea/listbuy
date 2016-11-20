package listbuy.me.listbuy;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by filip on 10/21/2015.
 */
public class Welcome extends AppCompatActivity {

    private Button log_out;
    private Usuario usu;
 //jjjjjjj
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Meu Perfil");

        log_out = (Button) findViewById(R.id.button);
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSharedPreferences("INFORMACOES_LOGIN_AUTOMATICO", MODE_PRIVATE).edit().clear().commit();
                //editor.clear().commit();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
        SharedPreferences prefs = getSharedPreferences("INFORMACOES_LOGIN_AUTOMATICO",0);

        usu = new Usuario(prefs.getString("nome",null),prefs.getString("login",null),prefs.getString("senha",null));

        TextView nome = (TextView) findViewById(R.id.txtnome);
        TextView email = (TextView) findViewById(R.id.txtemail);
        TextView senha = (TextView) findViewById(R.id.txtsenha);

        nome.setText(usu.getNome());
        email.setText(usu.getEmail());
        senha.setText(usu.getSenha());
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences("INFORMACOES_LOGIN_AUTOMATICO", MODE_PRIVATE);
        String login= prefs.getString("login", null);

        if (login== null) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
