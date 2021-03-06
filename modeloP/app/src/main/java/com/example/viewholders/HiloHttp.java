package com.example.viewholders;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class HiloHttp extends Thread {
    Handler handler;
    String url;
    boolean texto = true;
    int pos;
    String peticion;
    Uri.Builder parametros;

    public HiloHttp(Handler handler,String peticion,String url,Boolean texto, int i){
        this.handler = handler;
        this.url = url;
        this.texto = texto;
        this.pos = i;
        this.peticion =peticion;
    }
    public HiloHttp(Handler handler,String peticion,String url,Boolean texto,Uri.Builder parametros){
        this.handler = handler;
        this.url = url;
        this.texto = texto;
        this.peticion =peticion;
        this.parametros = parametros;
    }
    @Override
    public void run(){
        HttpManager manager = new HttpManager();

        try {
            //Thread.sleep(3000);
            if(texto && this.peticion == "GET"){
                //Log.d("estoy en","run");
                String respuesta = manager.consultarPersona(this.url);
                //Log.d("estoy en","respuesta del mensaje" + respuesta);
                Message message = new Message();
                message.arg1=MainActivity.TEXTO;
                //message.obj = respuesta;
                List<Persona> auxPersonas = new ArrayList<>();
                JSONArray array = new JSONArray(respuesta.toString());
                for(int i = 0; i<array.length(); i++){
                    JSONObject object = array.getJSONObject(i);
                    auxPersonas.add(new Persona(object.getString("nombre"),
                            object.getString("apellido"),object.getString("telefono"),
                            object.getString("img")));
                }
                message.obj = auxPersonas;
                this.handler.sendMessage(message);
            }
            else if(this.peticion =="GET"){
                Log.d("entra al hiloHttp","entro para trabajar en una imagen");
                byte[] imagen= manager.consultarImg(this.url);
                Message message = new Message();
                message.obj = imagen;
                message.arg1=MainActivity.IMAGEN;
                message.arg2 = pos;

                this.handler.sendMessage(message);
            }

            else if(this.peticion == "POST"){
                //Log.d("POST", "entro a HILOHTTP opcion post");
                String strMessage = manager.subirPersona(parametros,this.url);
              //  Log.d("el mensaje", "dentro del hilo es" + strMessage);
                Message message = new Message();
                message.obj = strMessage;
                this.handler.sendMessage(message);

            }


        } catch (MalformedURLException | JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
