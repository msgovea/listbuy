package listbuy.me.listbuy.lista.Sincronizacoes;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

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
import java.util.List;

import listbuy.me.listbuy.LoginActivity;
import listbuy.me.listbuy.R;
import listbuy.me.listbuy.Welcome;
import listbuy.me.listbuy.lista.DbConn;
import listbuy.me.listbuy.lista.Lista_inicial;


public class SincronizaLogin extends AsyncTask<String, String, String> {

    public interface Listener {

        public void onLoaded(String string);
    }

    private String login;
    private String senha;
    public String status = "nao";
    private Listener mListener;
    private DbConn dbconn;

    public SincronizaLogin(Listener mListener) {
        this.mListener = mListener;

    }

    @Override
    protected String doInBackground(String... n) {

        String api_url = "http://servidor.listbuy.me:81/login";
        login = n[0];
        senha = n[1];

        HttpURLConnection urlConnection;
        String requestBody;

        try {
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
                //Log.i("teste_api", response);
                //JSONObject resp = new JSONObject(response);
                //JSONObject object = new JSONObject(resp.getString("object"));
                //Log.i("nome", object.getString("nome"));
                //Log.i("email", object.getString("email"));
                //Log.i("tipo", object.getString("id_tipo_acesso"));
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
        dbconn = new DbConn(LoginActivity.context);
        status = "sim";
        try {
            JSONObject api_result = new JSONObject(result);

            String message = api_result.getString("message");
            if (message.equalsIgnoreCase("success")) {
                String dados = api_result.getString("object");
                JSONObject dados_result = new JSONObject(dados);
                Long id_consumidor = dados_result.getLong("id_consumidor");
                String nome = dados_result.getString("nome");
                String email = dados_result.getString("email");
                String senha = dados_result.getString("senha");
                String tipo_acesso = dados_result.getString("id_tipo_acesso");
                String key_acesso = dados_result.getString("key_acesso");

                //Salvar no Sqlite para nao perder os dados da pessoa logada no  app
                dbconn.insertConsumidor(id_consumidor,nome,email,senha,tipo_acesso);

                // Implementa o retorno para a classe de Login
                if (mListener != null) {
                    mListener.onLoaded("true");
                }
                //LoginActivity.context.startActivity(new Intent(LoginActivity.context, Lista_inicial.class));


            } else if (message.equalsIgnoreCase("ativacao")) {
                LoginActivity.mProgressView.setVisibility(View.INVISIBLE);
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.context);
                builder.setTitle("Ative sua conta");
                builder.setMessage("É necessário ativar sua conta com o link enviado em seu e-mail");
                builder.setPositiveButton("Fechar", null);
                builder.setCancelable(false);
                builder.show();
                if (mListener != null) {
                    mListener.onLoaded("false");
                }
            } else {
                LoginActivity.mProgressView.setVisibility(View.INVISIBLE);
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.context);
                builder.setTitle("");
                builder.setMessage("Login/Senha incorreto");
                builder.setPositiveButton("Fechar", null);
                builder.setCancelable(false);
                builder.show();
                if (mListener != null) {
                    mListener.onLoaded("false");
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
            LoginActivity.mProgressView.setVisibility(View.INVISIBLE);
            LoginActivity.mProgressView.setVisibility(View.INVISIBLE);
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.context);
            builder.setTitle("Erro");
            builder.setMessage("Erro ao Carregar Dados");
            builder.setPositiveButton("Fechar", null);
            builder.setCancelable(false);
            builder.show();
            if (mListener != null) {
                mListener.onLoaded("false");
            }
        }


    }


}
