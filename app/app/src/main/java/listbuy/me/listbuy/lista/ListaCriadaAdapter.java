package listbuy.me.listbuy.lista;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import listbuy.me.listbuy.R;
import listbuy.me.listbuy.entities.Listas;


public class ListaCriadaAdapter extends BaseAdapter  {
    Activity act;
    Context c;
    List<Listas> listas;
    LayoutInflater inflater;
    public static final int CONSTANTE_TELA_1 = 1;
    private DbConn dbconn;

    public ListaCriadaAdapter(Activity act, Context c, List<Listas> listas){
        this.act = act;
        this.c = c;
        this.listas = listas;
        this.inflater  = LayoutInflater.from(c);
    }
    @Override
    public int getCount() {
        try {
            return listas.size();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Object getItem(int i) {
        return listas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ListaCriadaAdapter.ViewHolder holder;

        if(view == null){
            holder = new ListaCriadaAdapter.ViewHolder();
            view = this.inflater.inflate(R.layout.card_view_listas, viewGroup, false);
            holder.nome_lista = (TextView)view.findViewById(R.id.nome_lista);
            holder.data_lista = (TextView)view.findViewById(R.id.data_lista);
            holder.btn_alt = (ImageButton) view.findViewById(R.id.imageAlt);
            holder.btn_exluir = (ImageButton) view.findViewById(R.id.imExcluir);
            view.setTag(holder);

        }
        else{
            holder = (ListaCriadaAdapter.ViewHolder) view.getTag();
        }

        final Listas lista = listas.get(i);
        holder.nome_lista.setText(lista.getTitulo());
        holder.data_lista.setText(lista.getData_alt());

        holder.btn_exluir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //TODO: CHAMAR API MANDANDO CODIGO DA LISTA A SER DELETADA listas.get(i).getId_lista();
                act.startActivity(new Intent(c, Lista_inicial.class));
            }
        });
        holder.btn_alt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Long id_lista = listas.get(i).getId_lista();
                //Toast.makeText(act,nome, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(c,AdicionarProdutos.class);
                intent.putExtra("ID_LISTA",id_lista);
                act.startActivity(intent);
            }
        });
        return view;
    }
    public void onActivityResult(int codTela, int resultado, Intent intent){
        if(codTela == CONSTANTE_TELA_1){
            Bundle params = intent.getExtras();
            if(params != null){
                String msg = params.getString("msg");
            }
        }
    }
    private class ViewHolder {
        TextView nome_lista;
        TextView data_lista;
        ImageButton btn_exluir;
        ImageButton btn_alt;

    }
}
