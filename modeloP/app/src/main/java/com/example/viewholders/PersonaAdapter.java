//package com.example.clase2;
package com.example.viewholders;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaViewHolder> {

    private List<Persona> personas;
    private MyOnItemClick listener;
    public PersonaAdapter(List<Persona> personas, MyOnItemClick listener ){
        this.personas = personas;
        this.listener = listener;
    }
    @Override
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_persona,parent,false);
        PersonaViewHolder vh = new PersonaViewHolder(view,listener);
        Log.d("On create", "On create Persona");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaViewHolder holder, int position) {
        Persona p = this.personas.get(position);
         holder.tvApellido.setText(p.getApellido());
         holder.tvNombre.setText(p.getNombre());
         holder.tvTelefono.setText(p.getNumero());
         //holder.ivImage.setImageResource(p.getByteImg());
         //holder.ivImage.setImageBitmap(BitmapFactory.decodeByteArray(p.getByteImg(),0,p.getByteImg().length));
         holder.setPosition(position);
         if(p.getByteImg() != null){
             //Log.d("el nombre es","es"+p.getByteImg().length);
             holder.ivImage.setImageBitmap(BitmapFactory.decodeByteArray(p.getByteImg(),0,p.getByteImg().length));
         }

         Log.d("On bind", "On bind persona en posicion" + position);
    }

    @Override
    public int getItemCount() {
        return this.personas.size();
    }
}
