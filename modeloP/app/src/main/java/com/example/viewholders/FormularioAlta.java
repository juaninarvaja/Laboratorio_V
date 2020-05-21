package com.example.viewholders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class FormularioAlta extends AppCompatActivity implements View.OnClickListener,Handler.Callback {
    Handler hand = new Handler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta_pantalla);
        ActionBar toolbar = getSupportActionBar();
        //habilitas el boton back a la izquierda
        toolbar.setDisplayHomeAsUpEnabled(true);
        //
        // String edad = (String) super.getIntent().getExtras().get("nombre");
        Button button = (Button)findViewById(R.id.botonGuardar);
        button.setOnClickListener(this);
        //TextView tv = super.findViewById(R.id.tvNombre);
       // tv.setText(MainActivity.p.nombre + " " +MainActivity.p.edad);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu m){
        super.getMenuInflater().inflate(R.menu.menu, m);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home){
            Intent i = new Intent(this,MainActivity.class);
            super.finish();
            //tengo q cerrar el activity y vuelve al home pq nunca se cerro
        }
        return true;
    }

    public Persona cargarPersona(){
        EditText etNombre =  findViewById(R.id.etNombre);
        String nombre = etNombre.getText().toString();
        EditText etApellido =  findViewById(R.id.etApellido);
        String apellido = etApellido.getText().toString();
        EditText etNumero =  findViewById(R.id.etNumero);
        String numero =  etNumero.getText().toString();
        EditText etFoto =  findViewById(R.id.etFoto);
        String foto = etFoto.getText().toString();
        Persona aux = new Persona(nombre,apellido,numero,foto);
        return aux;
    }

    public Uri.Builder personaToUriBuilder(Persona p){
        Uri.Builder params = new Uri.Builder();
        params.appendQueryParameter("nombre",p.getNombre());
        params.appendQueryParameter("apellido",p.getApellido());
        params.appendQueryParameter("telefono",p.getNumero());
        params.appendQueryParameter("img",p.getImagen());
        return params;
    }

    @Override
    public void onClick(View view) {
        Persona persona =  cargarPersona();
        Uri.Builder parametros = personaToUriBuilder(persona);
        HashMap<String, String> params;
        HiloHttp miHIlo = new HiloHttp(hand,"POST","http://192.168.0.18:3000/nuevaPersona",true,parametros);
        miHIlo.start(); //no run
        Log.d("clickeo en el alta", "onClick: hizo click");
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {


        Log.d("el mensaje es", message.obj.toString());
        if(message.obj != null){
            Log.d("entro","paso el if");
            Log.d("valor adap","dentro del message del alta es" + MainActivity.adap.toString());
            //MainActivity.adap.notifyDataSetChanged();
        }
        return false;
    }
}
