package com.example.viewholders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity  implements MyOnItemClick, Handler.Callback, LifecycleObserver {
        List<Persona> personas;
    public static final int TEXTO = 1;
    public static final int IMAGEN = 2;
    //public static
    public Handler handler = new Handler(this);
    public static PersonaAdapter adap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        personas = new ArrayList<>();

         //recibe una clase q implemente callback
        HiloHttp miHIlo = new HiloHttp(handler,"GET","http://192.168.0.18:3000/personas",true,0);
        miHIlo.start(); //no run
        //HiloHttp miHiloImagen = new HiloHttp(handler,"http://192.168.0.12:3000/personas",false);
        //miHiloImagen.start();

        //PersonaAdapter pAdapter = new PersonaAdapter(personas,this);
        adap = new PersonaAdapter(personas,this);
        RecyclerView rvPersona = super.findViewById(R.id.rvPersonas);
        rvPersona.setAdapter(adap);
        rvPersona.setLayoutManager(new LinearLayoutManager(this)); //uno abajo del otro
        /*
        puedo hacerlo de otras formas
        LinearLayout linearLayouut;
        linearLayout.setOrientation(REcyclerView.HORIZONTAL);
         o
         GridLayoutManager glm = new GridLAyoutManager(this,3) //genero con 3 columnas
         */

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("estoy en", "onResume: entro al onresume");

        HiloHttp miHIlo = new HiloHttp(handler,"GET","http://192.168.0.18:3000/personas",true,0);
        miHIlo.start();
        //Carga Activity.

    }


    @Override
    public boolean onCreateOptionsMenu(Menu m) {
         super.getMenuInflater().inflate(R.menu.menu, m);
         return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.alta) {
            Log.d("Llego a menu", "Se hizo click en el alta");
            Intent intent = new Intent(this, FormularioAlta.class);
            //p.edad = 23;
            //p.nombre = "Juan";
            super.startActivity(intent); //arranca la activity
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(int postion) {
    Log.d("llamando a", "llamando a " + postion);
    Log.d("llamando a", "llamando a "  + personas.get(postion).getApellido());
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {
        //recorre y ve si hay mensajes para procesar
        Log.d("Mensaje","Llego el mansjae" + message.obj);
        if(message.arg1 ==TEXTO){
            personas.clear();
            personas.addAll((List<Persona>) message.obj);
            adap.notifyDataSetChanged();
            for(int i = 0; i<personas.size();i++){
                HiloHttp miHiloImagen = new HiloHttp(handler,"GET",personas.get(i).getImagen(),false,i);
                miHiloImagen.start();
            }

           // message.arg2
            //TextView tv  = super.findViewById(R.id.tvPersonas);
            //tv.setText(message.obj.toString());
        }
        //esto en el onbind
        else if(message.arg1 ==IMAGEN){
            //Log.d("mensaje", "handleMessage: entro como q hay una imagen");
            byte[] aux = (byte[]) message.obj;
           // Log.d("el mensaje q llega es", "es" + aux);
            personas.get(message.arg2).setByteImg((byte[]) message.obj);
            adap.notifyItemChanged(message.arg2);
           // Log.d("y esa imagen la clase","es" + personas.get(message.arg2).getByteImg());
           // ImageView img = super.findViewById(R.id.img);
           // byte[] imagen = (byte[]) message.obj;
            //img.setImageBitmap(BitmapFactory.decodeByteArray(imagen,0,imagen.length));
        }
        return false;
    }
}
