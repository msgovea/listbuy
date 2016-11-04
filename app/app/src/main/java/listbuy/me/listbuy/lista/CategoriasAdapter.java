package listbuy.me.listbuy.lista;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

;import listbuy.me.listbuy.R;

/**
 * Created by Talitadossantoscastr on 16/10/2016.
 */

public class CategoriasAdapter extends BaseAdapter {
    Activity act;
    Context c;
    List<CategoriasProdutos> categorias;
    LayoutInflater inflater;

    public CategoriasAdapter(Activity act, Context c, List<CategoriasProdutos>categorias){
        this.act = act;
        this.c = c;
        this.categorias = categorias;
        this.inflater  = LayoutInflater.from(c);
    }
    @Override
    public int getCount() {
        return categorias.size();
    }

    @Override
    public Object getItem(int i) {
        return categorias.get(i);
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
            view = this.inflater.inflate(R.layout.card_view_categorias, viewGroup, false);
            holder.icone_Categoria = (ImageView)view.findViewById(R.id.person);
            holder.nome_categoria = (TextView)view.findViewById(R.id.nome_Categoria);
            holder.btnAdcCateg = (ImageButton)view.findViewById(R.id.imaaddCateg);
            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }
        CategoriasProdutos categProd = categorias.get(i);
        holder.icone_Categoria.setImageResource(categProd.getId_imagem());
        holder.nome_categoria.setText(categProd.getCategoria());

        holder.btnAdcCateg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String categoriasSel;
                categoriasSel = categorias.get(i).getCategoria();
                Intent intent = new Intent();
                intent.setClass(c,DadosProdutos.class);
                intent.putExtra("NOME_CATEGORIA",categoriasSel);
                act.startActivity(intent);

                //Toast.makeText(act,categoriasSel, Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
    private class ViewHolder {
        ImageView icone_Categoria;
        TextView nome_categoria;
        ImageButton btnAdcCateg;
    }
}
