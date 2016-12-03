package listbuy.me.listbuy.lista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import listbuy.me.listbuy.R;

/**
 * Created by Talitadossantoscastr on 03/12/2016.
 */

public class ActFeeds extends AppCompatActivity {

    private RecyclerView rec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_feeds);
        rec = (RecyclerView) findViewById(R.id.recycle);


        rec.setHasFixedSize(true);
        StaggeredGridLayoutManager llm = new StaggeredGridLayoutManager(2, 1);
        //Para fazer a grid. O parametro numero 2 é o tanto de colunas que irá ter
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        //Setar a orientação do layout
        rec.setLayoutManager(llm);
        //aplicar ao recycleview
    }
}
