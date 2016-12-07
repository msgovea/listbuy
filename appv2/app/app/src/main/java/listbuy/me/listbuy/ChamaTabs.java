package listbuy.me.listbuy;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import listbuy.me.listbuy.lista.TabLayoutAdapter;

public class ChamaTabs extends AppCompatActivity {

    private ViewPager view;
    private TabLayout tab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chama_tabs);
        ViewPager view = (ViewPager) findViewById(R.id.view_pager);
        TabLayout tab = (TabLayout) findViewById(R.id.tablayout);

        view.setAdapter(new TabLayoutAdapter(getSupportFragmentManager()));
        tab.setupWithViewPager(view);




    }
}
