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
import listbuy.me.listbuy.entities.ProdutoCadastrado;
import listbuy.me.listbuy.entities.Produtos;

/**
 * Created by Talitadossantoscastr on 05/12/2016.
 */

public class SincronizaCriarProdutos extends AsyncTask<Object, String, String> {

    public interface Listener {

        public void onLoaded(Produtos p);
    }

    private Long id_produto;
    private String nome;
    private String descricao;
    private Long id_categoria;
    private String ativo;
    private String data_cadastro;
    private Long id_lista;
    private Long quantidade;
    private Long id_unidade_medida;
    private Long id_consumidor; // iserir no construtr depois
    private Listener mListener;

    public SincronizaCriarProdutos(Listener mListener) {
        this.mListener = mListener;

    }

    @Override
    protected String doInBackground(Object... n) {

        String api_url = "http://servidor.listbuy.me:81/product/insert/";
        nome = (String) n[0];
        descricao = (String) n[1];
        id_categoria = (Long) n[2];
        ativo = (String) n[3];
        id_lista = (Long) n[4];
        quantidade = (Long) n[5];
        id_unidade_medida = (Long) n[6];
        id_consumidor = (Long) n[7];

        HttpURLConnection urlConnection;
        String requestBody;

        try {
            URL url = new URL(api_url);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept-Encoding", "application/json");
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("nome", nome);
            jsonObject.accumulate("descricao", descricao);
            jsonObject.accumulate("id_categoria", id_categoria);
            jsonObject.accumulate("ativo", ativo);
            jsonObject.accumulate("id_lista", id_lista);
            jsonObject.accumulate("quantidade", quantidade);
            jsonObject.accumulate("id_unidade_medida", id_unidade_medida);
            jsonObject.accumulate("id_consumidor", id_consumidor);

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

                Produtos pd = new Produtos();

                pd.setNome(dados_result.getString("nome"));
                pd.setDescricao(dados_result.getString("descricao"));
                pd.setId_categoria(dados_result.getLong("id_categoria"));
                pd.setId_lista(dados_result.getLong("id_lista"));
                pd.setAtivo(dados_result.getString("ativo"));
                pd.setQuantidade(dados_result.getLong("quantidade"));
                pd.setId_unidade_medida(dados_result.getLong("id_unidade_medida")); // entender pq unidad de medida é 1
                //lista.setData_alt(dados_result.getString("id_consumidor"));


                if (mListener != null) {
                    mListener.onLoaded(pd);
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
