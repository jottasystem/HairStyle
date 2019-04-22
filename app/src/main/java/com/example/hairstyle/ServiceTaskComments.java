package com.example.hairstyle;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

public class ServiceTaskComments extends AsyncTask<Void, Void,String> {

    private Context httpContext; // Contexto
    ProgressDialog progressDialog; // Mensagem de carregando
    public String resultApi = "" ;
    public String linkRequestApi= ""; //Link para consumir serviço

    public String name;
    public String comments;
    public String rate;

    public String message;
    public String backMessage;



    public ServiceTaskComments(Context httpContext, String linkRequestApi, String name, String comments, String rate, String message, String backMessage) {
        this.httpContext = httpContext;
        this.linkRequestApi = linkRequestApi;

        this.name = name;
        this.comments = comments;
        this.rate = rate;
        this.backMessage = backMessage;

    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();

        progressDialog = ProgressDialog.show(httpContext,message,"aguarde...");
    }


    @Override
    protected String doInBackground(Void ... params){


        String result = null;
        String wsURL = linkRequestApi;
        URL url = null;

        try {

            if(name == null) name = "Usuario não encontrado";

            url = new URL(wsURL);
            //Paramentros de integracao
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            JSONObject parametroPost = new JSONObject();
            parametroPost.put("name",name);
            System.out.println("postandoo name"+name);
            parametroPost.put("coment",comments);
            System.out.println("postandoo comments "+comments);

            parametroPost.put("rate",Integer.parseInt(rate));
            System.out.println("postandoo rate "+rate);


            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);


            OutputStream os = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            writer.write(getPostDataString(parametroPost));
            writer.flush();
            writer.close();
            os.close();

            int responseCode = urlConnection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){

                BufferedReader in =  new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));


                StringBuffer sb = new StringBuffer("");
                String linea = "";
                while ((linea =  in.readLine()) != null){
                    sb.append(linea);
                    break;
                }
                in.close();
                result = sb.toString();
            }
            else{

                result = new String("Error: "+ responseCode);

            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }


    @Override
    protected void onPostExecute(String s){
        super.onPostExecute(s);
        progressDialog.dismiss();
        resultApi = s;
        Toast.makeText(httpContext, backMessage,Toast.LENGTH_LONG).show(); //Notificacao de result
    }



    //                      Functions
    public String getPostDataString(JSONObject params) throws  Exception{

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys() ;

        while(itr.hasNext()){
            String key = itr.next();
            Object value = params.get(key);

            if (first){
                first = false;
            }else{
                result.append("&");
            }

            result.append(URLEncoder.encode(key,"UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(),"UTF-8"));

        }

        return result.toString();

    }



}
