package listbuy.me.listbuy.lista;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import listbuy.me.listbuy.R;

/**
 * Created by Talitadossantoscastr on 03/12/2016.
 */

public class RecyclerView_Adapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private ArrayList<DadosFeeds> arrayList;
    private Context context;

    public RecyclerView_Adapter(Context context, ArrayList<DadosFeeds> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);

    }
    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        final DadosFeeds model = arrayList.get(position);

        //RecyclerViewHolder mainHolder = (RecyclerViewHolder) holder;// holder
        RecyclerViewHolder mainHolder = holder;

        Bitmap image = BitmapFactory.decodeResource(context.getResources(),
                model.getImagem());// This will convert drawbale image into
        // bitmap

        // setting title
        mainHolder.title.setText(model.getNome());

        mainHolder.imageview.setImageBitmap(image);
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        // This method will inflate the custom layout and return as viewholder
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());

        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(R.layout.card_view_feeds, viewGroup, false);
        RecyclerViewHolder listHolder = new RecyclerViewHolder(mainGroup);
        return listHolder;

    }
}
