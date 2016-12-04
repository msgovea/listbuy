package listbuy.me.listbuy.lista.Sincronizacoes;


import android.os.AsyncTask;

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

import listbuy.me.listbuy.entities.Listas;
import listbuy.me.listbuy.lista.DbConn;


public class SincronizaCriarLista extends AsyncTask<Object, String, String> {

    public interface Listener {

        public void onLoaded(Listas lista);
    }

    private String tipo_lista;
    private String titulo;
    private Long id_consumidor;
    private String ativa;
    private Listener mListener;

    public SincronizaCriarLista(Listener mListener) {
        this.mListener = mListener;

    }

    @Override
    protected String doInBackground(Object... n) {

        String api_url = "http://servidor.listbuy.me:81/list/insert/";
        tipo_lista = (String) n[0];
        titulo = (String) n[1];
        id_consumidor = (Long)n[2];
        ativa = (String) n[3];

        HttpURLConnection urlConnection;
        String requestBody;

        try {
            URL url = new URL(api_url);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept-Encoding", "application/json");
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("tipo_lista", tipo_lista);
            jsonObject.accumulate("titulo", titulo);
            jsonObject.accumulate("id_consumidor", id_consumidor);
            jsonObject.accumulate("ativa", ativa);
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
            }
            return response;


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        try {
            JSONObject api_result = new JSONObject(result);

            String message = api_result.getString("message");
            if (message.equalsIgnoreCase("success")) {
                String dados = api_result.getString("object");
                JSONObject dados_result = new JSONObject(dados);

                Listas lista = new Listas();

                lista.setId_lista(dados_result.getLong("id_lista"));
                lista.setTipo_lista(dados_result.getString("tipo_lista"));
                lista.setTitulo(dados_result.getString("titulo"));
                lista.setId_consumidor(dados_result.getLong("id_consumidor"));
                lista.setAtiva(dados_result.getString("ativa"));
                lista.setData_ics(dados_result.getString("data_ics"));
                lista.setData_alt(dados_result.getString("data_alt"));

                // Implementa o retorno para a classe de Login
                if (mListener != null) {
                    mListener.onLoaded(lista);
                }


            } else {

                if (mListener != null) {
                    mListener.onLoaded(null);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
            if (mListener != null) {
                mListener.onLoaded(null);
            }
        }


    }


}
