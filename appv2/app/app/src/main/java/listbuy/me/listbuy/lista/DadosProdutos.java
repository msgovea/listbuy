package listbuy.me.listbuy.lista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import listbuy.me.listbuy.LoginActivity;
import listbuy.me.listbuy.MenuLateral;
import listbuy.me.listbuy.R;

public class DadosProdutos extends AppCompatActivity implements View.OnClickListener {

    private EditText extDescricao;
    private EditText extQtd;
    private EditText extUm;
    private Button btnOk;
    private Button btnCancel;
    private Spinner smp;
    private TextView txtCategoria;
    private TextView txtUnidadeMedida;
    private String nome_categ;
    private String[] nomes_UM;
    private ArrayAdapter<String> adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_dados_produtos);
        extDescricao = (EditText) findViewById(R.id.extProduto);
        extQtd = (EditText) findViewById(R.id.edtQtd);
        //extUm = (EditText) findViewById(R.id.edtUm);
        smp = (Spinner)findViewById(R.id.spUm);
        txtCategoria= (TextView)findViewById(R.id.textView);
        //txtUnidadeMedida = (TextView)findViewById(R.id.txtunid);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Detalhes do Produto");

        btnOk = (Button)findViewById(R.id.btnOk);
        btnCancel = (Button)findViewById(R.id.btnCancel);

        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        Intent it = getIntent();
        if(it.getStringExtra("NOME_CATEGORIA")!= null) {
            nome_categ = it.getStringExtra("NOME_CATEGORIA");
            if (nome_categ != "") {
                txtCategoria.setText(nome_categ);
            }
        }

        nomes_UM = new String[]{"....","Unidades","KG","Litros"};
        adp = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nomes_UM);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        smp.setAdapter(adp);


    }
    public void onClick(View v) {

        if (v.getId() == R.id.btnOk) {
            String categoria = smp.getSelectedItem().toString();
            if(Validacoes.isNullOrEmpty(extDescricao.getText().toString())){
                extDescricao.setError("Descrição Produto é Obrigatório!");

            }
            if(Validacoes.isNumeric(extQtd.getText().toString())){
                extQtd.setError("Digite um valor valido!");
            }
            else{
                //Toast.makeText(this,categoria, Toast.LENGTH_SHORT).show();
            Intent it = new Intent();
            int qtd_convert= Integer.parseInt(extQtd.getText().toString());
            AdicionarProdutos.produtos.add(new Produtos(extDescricao.getText().toString(),qtd_convert,smp.getSelectedItem().toString()));
            it.setClass(this,AdicionarProdutos.class);
            it.putExtra("CATEGORIA",categoria);
            startActivity(it);
            }

            if(v.getId() == R.id.btnCancel) {
                startActivity(new Intent(getApplicationContext(), AdicionarProdutos.class));
            }
        }
    }
    public void onLoaded(listbuy.me.listbuy.entities.Produtos prod) {
        startActivity(new Intent(this, AdicionarProdutos.class));
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, Categorias.class));
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
