package listbuy.me.listbuy.lista.Sincronizacoes;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import listbuy.me.listbuy.entities.Listas;

public class SINCRONIZAGET extends AsyncTask<String,String,String> {

    public interface Listener {

        public void onLoaded(List<Listas> listas);
    }

    private Listener mListener;


    public SINCRONIZAGET(SINCRONIZAGET.Listener mListener) {
        this.mListener = mListener;
    }



    @Override
    protected String doInBackground(String... n) {

        String api_url = "http://servidor.listbuy.me:81/list/user/" + 1 + "/";

        String response = "";

        HttpURLConnection urlConnection;
        String requestBody;

        try {

            URL url = new URL(api_url);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            InputStream inputStream;
            // get stream
            if (urlConnection.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
                inputStream = urlConnection.getInputStream();
            } else {
                inputStream = urlConnection.getErrorStream();
            }
            // parse stream
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                response += temp;
            }
            return response;


        } catch (IOException e) {
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
                JSONArray dados = api_result.getJSONArray("object");
                ArrayList<Listas> listas = new ArrayList<>();
                for (int i = 0; i < dados.length(); ++i) {
                    JSONObject dados_result = dados.getJSONObject(i);
                    Listas lista = new Listas();

                    lista.setId_lista(dados_result.getLong("id_lista"));
                    lista.setTipo_lista(dados_result.getString("tipo_lista"));
                    lista.setTitulo(dados_result.getString("titulo"));
                    lista.setId_consumidor(dados_result.getLong("id_consumidor"));
                    lista.setAtiva(dados_result.getString("ativa"));
                    lista.setData_ics("2016-11-08"/*dados_result.getString("data_ics")*/);
                    lista.setData_alt("2016-11-08"/*dados_result.getString("data_alt")*/);
                    listas.add(lista);
                }

                // Implementa o retorno para a classe de Login
                if (mListener != null) {
                    mListener.onLoaded(listas);
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
