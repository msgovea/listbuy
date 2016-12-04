package listbuy.me.listbuy.lista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import listbuy.me.listbuy.R;
import listbuy.me.listbuy.entities.Produtos;

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
            AdicionarProdutos.produtos.add(new Produtos());
            it.setClass(this,AdicionarProdutos.class);
            it.putExtra("CATEGORIA",categoria);
            startActivity(it);
            }
        }
    }
}
