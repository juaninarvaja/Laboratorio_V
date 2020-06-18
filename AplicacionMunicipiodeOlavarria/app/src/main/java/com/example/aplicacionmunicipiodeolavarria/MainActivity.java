package com.example.aplicacionmunicipiodeolavarria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleObserver;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements MyOnItemClick, Handler.Callback, LifecycleObserver {

    List<Terminales> terminales;
    public static final int TEXTO = 1;
    public static final int IMAGEN = 2;
    //public static
    public Handler handler = new Handler(this);
    public static TerminalAdapter adap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        terminales = new ArrayList<>();

        //recibe una clase q implemente callback
        HiloHttp miHIlo = new HiloHttp(handler,"GET","https://api.datosabiertos.olavarria.gov.ar/api/v2/datastreams/TERMI-SUBE-DEL-PARTI-DE/data.xml/?auth_key=DZg5bsjkdO3RutJv95NiJgTKhBdxiC5Bq75MXjaU",true,0);

        //HiloHttp miHIlo = new HiloHttp(handler,"GET","https://api.datosabiertos.olavarria.gov.ar/api/v2/datastreams/EMPRE-HABIL-2019/data.xml/?auth_key=edd4b1b997754f90c7373547e4a88d5ca9b8b2b9",true,0);

        miHIlo.start(); //no run
        //HiloHttp miHiloImagen = new HiloHttp(handler,"http://192.168.0.12:3000/personas",false);
        //miHiloImagen.start();


        adap = new TerminalAdapter(terminales,this);
        RecyclerView rvPersona = super.findViewById(R.id.rvTerminales);
        rvPersona.setAdapter(adap);
        rvPersona.setLayoutManager(new LinearLayoutManager(this)); //uno abajo del otro

    }
    @Override
    public boolean handleMessage(@NonNull Message message) {
        //recorre y ve si hay mensajes para procesar
        Log.d("Mensaje","Llego el mansjae" + message.obj);
        if(message.arg1 ==TEXTO){
            terminales.clear();
            terminales.addAll((List<Terminales>) message.obj);
            adap.notifyDataSetChanged();
//            for(int i = 0; i<terminales.size();i++){
//                HiloHttp miHiloImagen = new HiloHttp(handler,"GET",personas.get(i).getImagen(),false,i);
//                miHiloImagen.start();
//            }

            // message.arg2
            //TextView tv  = super.findViewById(R.id.tvPersonas);
            //tv.setText(message.obj.toString());
        }
        return false;
    }

    @Override
    public void onItemClick(int postion) {
        Log.d("llamando a", "llamando a " + postion);
       // Log.d("llamando a", "llamando a "  + personas.get(postion).getApellido());
    }

}
