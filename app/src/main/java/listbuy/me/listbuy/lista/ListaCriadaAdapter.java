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

/**
 * Created by Talitadossantoscastr on 02/10/2016.
 */

public class ListaCriadaAdapter extends BaseAdapter  {
    Activity act;
    Context c;
    List<ListasCriadas> listasCriadas;
    LayoutInflater inflater;
    public static final int CONSTANTE_TELA_1 = 1;
    private DbConn dbconn;

    public ListaCriadaAdapter(Activity act, Context c, List<ListasCriadas>listasCriadas){
        this.act = act;
        this.c = c;
        this.listasCriadas = listasCriadas;
        this.inflater  = LayoutInflater.from(c);
    }
    @Override
    public int getCount() {
        return listasCriadas.size();
    }

    @Override
    public Object getItem(int i) {
        return listasCriadas.get(i);
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

        final ListasCriadas listacriada = listasCriadas.get(i);
        holder.nome_lista.setText(listacriada.getNomeLista());
        holder.data_lista.setText(listacriada.getDataCriacao());

        holder.btn_exluir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String nomeRemov;
                nomeRemov = listasCriadas.get(i).getNomeLista();
                Intent intent = new Intent();
                intent.setClass(c,Lista_inicial.class);
                intent.putExtra("NOME_DEL",nomeRemov);
                act.startActivity(intent);
            }
        });
        holder.btn_alt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String nome;
                nome = listasCriadas.get(i).getNomeLista();
                //Toast.makeText(act,nome, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(c,AdicionarProdutos.class);
                intent.putExtra("NOME_ENVIADO",nome);
                act.startActivity(intent);
            }
        });
        return view;
    }
    public void onActivityResult(int codTela,int resultado,Intent intent){
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
