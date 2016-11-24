package br.puccamp.listbuy.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Rest {

//
//        try {
//            String result = http.sendGet("http://listbuy.me/");
//        }catch (Exception e) {
//            System.err.println(e.getMessage());
//        }


    public static String sendGet(String url) throws Exception {

        try {
            URL obj = new URL(url);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } catch (Exception e) {
            throw new Exception("Erro ao enviar e-mail");
        }

    }



}