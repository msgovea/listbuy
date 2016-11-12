package listbuy.me.listbuy.lista;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import listbuy.me.listbuy.R;

/**
 * Created by Talitadossantoscastr on 12/11/2016.
 */

public class Sincroniza extends AsyncTask<String,String,String> {
    private String login;
    private String senha;
    @Override
    protected String doInBackground(String... n) {
        String api_url = "http://servidor.listbuy.me:81/login";
        login = n[0];
        senha = n[1];

        HttpURLConnection urlConnection;
        String requestBody;
        Uri.Builder builder = new Uri.Builder();
        Map<String, String> params = new HashMap<>();

        params.put("email", login);
        params.put("senha", senha);

        Iterator entries = params.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            builder.appendQueryParameter(entry.getKey().toString(), entry.getValue().toString());
            entries.remove();
        }
        requestBody = builder.build().getEncodedQuery();

       try{
           URL url = new URL(api_url);
           urlConnection = (HttpURLConnection) url.openConnection();
           urlConnection.setDoOutput(true);
           urlConnection.setRequestProperty("Content-Type", "application/json");
           OutputStream outputStream = new BufferedOutputStream(urlConnection.getOutputStream());
           BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
           writer.write(requestBody);
           writer.flush();
           writer.close();
           outputStream.close();

           InputStream inputStream;
           // get stream
           if (urlConnection.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
               inputStream = urlConnection.getInputStream();
           } else {
               inputStream = urlConnection.getErrorStream();
           }
           // parse stream
           BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
           String temp, response = "";
             while ((temp = bufferedReader.readLine()) != null) {
               response += temp;
                 Log.i("teste_api", response);
           }

       } catch (IOException e) {
            e.printStackTrace();
       }
        return null;
    }
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

    }
}
