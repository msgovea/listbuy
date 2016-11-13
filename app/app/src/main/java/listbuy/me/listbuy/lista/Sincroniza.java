package listbuy.me.listbuy.lista;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import java.sql.Connection;
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



       try{
           URL url = new URL(api_url);
           urlConnection = (HttpURLConnection) url.openConnection();
           urlConnection.setDoOutput(true);
           urlConnection.setRequestProperty("Content-Type", "application/json");
           urlConnection.setRequestProperty("Accept-Encoding", "application/json");
           JSONObject jsonObject = new JSONObject();
           jsonObject.accumulate("email", login);
           jsonObject.accumulate("senha", senha);
           String json = jsonObject.toString();
           OutputStream outputStream = new BufferedOutputStream(urlConnection.getOutputStream());
           BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
           writer.write(json);
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
                 JSONObject resp = new JSONObject(response);
                 JSONObject object = new JSONObject(resp.getString("object"));
                 Log.i("nome", object.getString("nome"));
                 Log.i("email", object.getString("email"));
                 Log.i("tipo", object.getString("id_tipo_acesso"));
             }

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
