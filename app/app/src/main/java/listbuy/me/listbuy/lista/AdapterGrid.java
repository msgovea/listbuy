package listbuy.me.listbuy.lista;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import listbuy.me.listbuy.R;
import listbuy.me.listbuy.SharingScreen;

/**
 * Created by Talitadossantoscastr on 30/11/2016.
 */

public class AdapterGrid extends RecyclerView.Adapter<AdapterGrid.ContactViewHolder> {


        public static class ContactViewHolder extends RecyclerView.ViewHolder  {
            private TextView vTitulo;
            private ImageView imagem;
            private View view;
            private Activity activity;
            private Context context;

            public ContactViewHolder(View v, Context c, Activity act) {
                super(v);
                vTitulo = (TextView)v.findViewById(R.id.txtNoticia);
                imagem = (ImageView)v.findViewById(R.id.imageView3);
                view = v;
                this.activity = act;
                this.context = c;
            }
        }

        private List<DadosFeeds> contactList;
        private View v;

        public AdapterGrid(List<DadosFeeds> contactList) {
            this.contactList = contactList;
        }

        @Override
        public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_feeds, parent, false);
            v = itemView;
            return new ContactViewHolder(itemView,context,act);
        }

        @Override
        public void onBindViewHolder(ContactViewHolder contactViewHolder, int position) {
            final DadosFeeds ci = contactList.get(position);
            contactViewHolder.vTitulo.setText(ci.getNome());
            contactViewHolder.imagem.setImageResource(ci.getImagem());
            contactViewHolder.view.setOnClickListener( new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent = new Intent();
                    act.startActivity(intent);
                    startActivity(new Intent(this, DetalhesOfertas.class));
                }
            });

        }

        @Override
        public int getItemCount() {
            return contactList.size();
        }

        public static class FeedViewHolder extends RecyclerView.ViewHolder {
            private ImageView imagem;
            private TextView txtSuper;

            public FeedViewHolder(View v) {

                super(v);
                txtSuper = (TextView)v.findViewById(R.id.txtNoticia);
                imagem = (ImageView)v.findViewById(R.id.imageView3);
            }
        }

}


