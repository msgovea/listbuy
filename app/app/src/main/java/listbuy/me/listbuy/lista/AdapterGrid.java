package listbuy.me.listbuy.lista;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
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


            public ContactViewHolder(View v) {
                super(v);
                vTitulo = (TextView)v.findViewById(R.id.txtNoticia);
                imagem = (ImageView)v.findViewById(R.id.imageView3);
                view = v;

            }
        }

        private List<DadosFeeds> contactList;
        private View v;
        private Activity act;

        public AdapterGrid(List<DadosFeeds> contactList, Activity act) {
            this.contactList = contactList;
            this.act = act;
        }

        @Override
        public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_feeds, parent, false);
            v = itemView;
            return new ContactViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final ContactViewHolder contactViewHolder, int position) {
            final DadosFeeds ci = contactList.get(position);
            contactViewHolder.vTitulo.setText(ci.getNome());
            contactViewHolder.imagem.setBackgroundResource(ci.getImagem());
            //contactViewHolder.imagem.setImageResource(ci.getImagem());



            contactViewHolder.view.setOnClickListener( new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    ByteArrayOutputStream bs = new ByteArrayOutputStream();
                    contactViewHolder.imagem.buildDrawingCache();
                    Bitmap bmap = contactViewHolder.imagem.getDrawingCache();
                    bmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
                    Intent intent = new Intent();
                    intent.setClass(act,DetalhesOfertas.class);
                    intent.putExtra("nome",ci.getNome());
                    intent.putExtra("img", bs.toByteArray());
                    act.startActivity(intent);
                    //Toast.makeText(act,ci.getImagem(), Toast.LENGTH_SHORT).show();

                  //v.startActivity(new Intent(this, DetalhesOfertas.class));
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


