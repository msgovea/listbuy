package listbuy.me.listbuy.lista.Sincronizacoes;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Talitadossantoscastr on 13/11/2016.
 */

public class SincronizaListarProdutos extends AsyncTask<String,String,String> {
    @Override
    protected String doInBackground(String... n) {
        String api_url = "http://servidor.listbuy.me:81/list/productsByList/1/";
        try{
            URL url = new URL(api_url);
            URLConnection urlc = url.openConnection();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
            String line = bfr.readLine();
            Log.i("teste_api",line);
            JSONArray api_response = new JSONArray(line);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

    }
}
