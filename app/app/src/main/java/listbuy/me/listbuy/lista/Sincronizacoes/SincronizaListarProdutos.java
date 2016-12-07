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
import listbuy.me.listbuy.entities.Produtos;

public class SincronizaListarProdutos extends AsyncTask<String,String,String> {

    public interface Listener {

        public void onLoadedProdutos(List<Produtos> produtos);
    }

    private Listener mListener;


    public SincronizaListarProdutos(SincronizaListarProdutos.Listener mListener) {
        this.mListener = mListener;
    }



    @Override
    protected String doInBackground(String... n) {

        String api_url = "http://servidor.listbuy.me:81/list/productsByList/" + n[0] + "/";

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
                ArrayList<Produtos> produtos = new ArrayList<>();
                for (int i = 0; i < dados.length(); ++i) {
                    JSONObject dados_result = dados.getJSONObject(i);
                    Produtos produto = new Produtos();
                    produto.setDescricao(dados_result.getString("descricao"));
                    produto.setId_lista(dados_result.getLong("id_lista"));
                    produto.setAtivo(dados_result.getString("ativo"));
                    produto.setData_cadastro(dados_result.getString("data_ics"));
                    produto.setId_categoria(dados_result.getLong("id_categoria"));
                    produto.setId_produto(dados_result.getLong("id_produto"));
                    produto.setId_unidade_medida(dados_result.getLong("id_unidade_medida"));
                    produto.setNome(dados_result.getString("nome"));
                    produto.setQuantidade(dados_result.getLong("quantidade"));

                    produtos.add(produto);
                }

                // Implementa o retorno para a classe de Login
                if (mListener != null) {
                    mListener.onLoadedProdutos(produtos);
                }

            } else {
                if (mListener != null) {
                    mListener.onLoadedProdutos(null);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
            if (mListener != null) {
                mListener.onLoadedProdutos(null);
            }
        }


    }
}
