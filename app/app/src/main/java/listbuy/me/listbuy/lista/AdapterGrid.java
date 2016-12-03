package listbuy.me.listbuy.lista;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import listbuy.me.listbuy.R;

/**
 * Created by Talitadossantoscastr on 30/11/2016.
 */

public class AdapterGrid extends RecyclerView.Adapter<AdapterGrid.ContactViewHolder> {


        public static class ContactViewHolder extends RecyclerView.ViewHolder {
            private TextView vTitulo;
            private ImageView imagem;

            public ContactViewHolder(View v) {
                super(v);
                vTitulo = (TextView)v.findViewById(R.id.textView7);
                imagem = (ImageView)v.findViewById(R.id.imageView3);
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
            return new ContactViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ContactViewHolder contactViewHolder, int position) {
            DadosFeeds ci = contactList.get(position);
            contactViewHolder.vTitulo.setText(ci.getNome());
            contactViewHolder.imagem.setImageResource(ci.getImagem());
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
                txtSuper = (TextView)v.findViewById(R.id.textView7);
                imagem = (ImageView)v.findViewById(R.id.imageView3);
            }
        }

}


