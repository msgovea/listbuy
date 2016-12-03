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

    public class AdapterGrid extends RecyclerView.Adapter<AdapterGrid.ContactViewHolder> {
        public static class ContactViewHolder extends RecyclerView.ViewHolder {
            protected TextView vTitulo;
            protected ImageView imagem;

            public ContactViewHolder(View v) {
                super(v);
                vTitulo = (TextView) v.findViewById(R.id.textView7);
                imagem = (ImageView) v.findViewById(R.id.imageView3);
            }
        }

        private List<Feeds> contactList;
        private View v;

        public AdapterGrid(List<Feeds> contactList) {
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
            Feeds ci = contactList.get(position);
            contactViewHolder.vTitulo.setText(ci.getNome());
            //contactViewHolder.imagem.setInt(ci.getImagem());
        }

        @Override
        public int getItemCount() {
            return contactList.size();
        }

        public  class FeedsViewHolder extends RecyclerView.ViewHolder {
            private ImageView imagem;
            private TextView txtSuper;

            public FeedsViewHolder(View v) {

                super(v);
                txtSuper = (TextView) v.findViewById(R.id.textView7);
                imagem = (ImageView) v.findViewById(R.id.imageView3);
            }
        }


    }

    public class ContactViewHolder {
    }
}