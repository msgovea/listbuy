package listbuy.me.listbuy;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.appindexing.AndroidAppUri;
import com.google.android.gms.appinvite.AppInvite;
import com.google.android.gms.appinvite.AppInviteInvitationResult;
import com.google.android.gms.appinvite.AppInviteReferral;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;

import java.util.List;

/**
 * Created by Aline on 11/11/2016.
 */

public class SharingList extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean instalado = appInstalledOrNot("listbuy.me.listbuy");
        if (instalado) {
            Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("listbuy.me.listbuy");
            startActivity(LaunchIntent);

            System.out.println("Aplicativo ListBuy está instalado!");
        } else {
            System.out.println("ListBuy não está instalado!");
        }
    }

    private boolean appInstalledOrNot(String uri){
        PackageManager pacote = getPackageManager();
        boolean appInstalado;
        try{
            pacote.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            appInstalado = true;
        }
        catch (PackageManager.NameNotFoundException e){
            appInstalado = false;
        }
        return appInstalado;
    }



    protected void onNewIntent(Intent intent){
        String action = intent.getAction();
        Uri link = intent.getData();
        if(Intent.ACTION_VIEW.equals(action) && link != null){
            String IdLista = link.getLastPathSegment();
            intent.putExtra("IdLista", IdLista);
            setContentView(R.layout.act_lista_inicial);
        }
    }

}
