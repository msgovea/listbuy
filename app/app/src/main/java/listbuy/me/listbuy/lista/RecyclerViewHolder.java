package listbuy.me.listbuy.lista;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import listbuy.me.listbuy.R;

/**
 * Created by Talitadossantoscastr on 03/12/2016.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    protected TextView title;
    protected ImageView imageview;

    public RecyclerViewHolder(View view) {
        super(view);

        this.title = (TextView)view.findViewById(R.id.textView7);
        this.imageview = (ImageView) view.findViewById(R.id.imageView3);
    }
}
