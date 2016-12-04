package listbuy.me.listbuy.lista;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import listbuy.me.listbuy.R;

/**
 * Created by Talitadossantoscastr on 20/11/2016.
 */

public class TabListasCompartilhadas  extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_listas_comp, container, false);
        return view;
    }
}
