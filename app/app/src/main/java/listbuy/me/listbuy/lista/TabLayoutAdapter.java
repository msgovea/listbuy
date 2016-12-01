package listbuy.me.listbuy.lista;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Talitadossantoscastr on 20/11/2016.
 */

public class TabLayoutAdapter extends FragmentPagerAdapter {

    private final int PAGE_CONT = 2;
    private String title_Tabs[] = new String[]{ "Minhas", "Compartilhadas"};

    public TabLayoutAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new TabListasCompartilhadas();
            case 1:
                return new TabListasMinhas();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return PAGE_CONT;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return title_Tabs[position];
            case 1:
                return title_Tabs[position];
            default:
                return null;
        }
    }
}
