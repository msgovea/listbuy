package listbuy.me.listbuy.lista;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import listbuy.me.listbuy.R;

/**
 * Created by Talitadossantoscastr on 02/10/2016.
 */

public class ListaAdapter extends BaseAdapter{
    private DbConn dbconn;
    Activity act;
    Context c;
    List<Produtos> produtos;
    LayoutInflater inflater;


    public ListaAdapter(Activity act, Context c, List<Produtos>produtos){
        this.act = act;
        this.c = c;
        this.produtos = produtos;
        this.inflater  = LayoutInflater.from(c);
    }

   @Override

    public int getCount() {
        return produtos.size();
    }

    @Override
    public Object getItem(int i) {
        return produtos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if(view == null){
            holder = new ViewHolder();
            view = this.inflater.inflate(R.layout.card_view_produtos, viewGroup, false);
            holder.nome_prod = (TextView)view.findViewById(R.id.nome_prod);
            holder.qtd = (TextView)view.findViewById(R.id.qtd);
            holder.btn_excl = (ImageButton)view.findViewById(R.id.btn_deletar);
            holder.un_med = (TextView)view.findViewById(R.id.txtUnid);
            view.setTag(holder);

        }
        else{
            holder = (ViewHolder) view.getTag();
        }
        Produtos prod = produtos.get(i);
        holder.nome_prod.setText(prod.getDescricao());
        holder.qtd.setText(prod.getQtd()+"");
        holder.un_med.setText(prod.getUnMed());

        holder.btn_excl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String nome_produto;
                nome_produto = produtos.get(i).getDescricao();
                Intent intent = new Intent();
                intent.setClass(c,AdicionarProdutos.class);
                intent.putExtra("PRODUTO_ENVIADO",nome_produto);
                //dbconn.deleteProdNome(dbconn.selectIdProd(nome_produto));
                //act.startActivity(intent);
                //Toast.makeText(act,id, Toast.LENGTH_SHORT).show();

            }

        });
        return view;
    }

    private class ViewHolder {
        TextView nome_prod;
        TextView qtd;
        TextView un_med;
        ImageButton btn_excl;

    }

}
