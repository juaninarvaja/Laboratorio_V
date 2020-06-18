package com.example.aplicacionmunicipiodeolavarria;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TerminalAdapter extends RecyclerView.Adapter<TerminalViewHolder> {

    private List<Terminales> terminales;
    private MyOnItemClick listener;
    public TerminalAdapter(List<Terminales> terminales, MyOnItemClick listener ){
        this.terminales = terminales;
        this.listener = listener;
    }
    @Override
    public TerminalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_terminal,parent,false);
        TerminalViewHolder vh = new TerminalViewHolder(view,listener);
        Log.d("On create", "On create Persona");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TerminalViewHolder holder, int position) {
        Terminales e = this.terminales.get(position);
        holder.tvNombre.setText(e.getNombre());
        holder.tvLocalidad.setText(e.getLocalidad());
        holder.tvDireccion.setText(e.getCalle() +" "+ e.getAlturaCalle() + " " + e.getObservacion());
        holder.tvAltitudLongitud.setText(e.getLatitud() + " " +e.getLongitud());
        //holder.ivImage.setImageResource(p.getByteImg());
        //holder.ivImage.setImageBitmap(BitmapFactory.decodeByteArray(p.getByteImg(),0,p.getByteImg().length));
        holder.setPosition(position);
//        if(e.getByteImg() != null){
//            //Log.d("el nombre es","es"+p.getByteImg().length);
//            holder.ivImage.setImageBitmap(BitmapFactory.decodeByteArray(p.getByteImg(),0,p.getByteImg().length));
//        }

        Log.d("On bind", "On bind persona en posicion" + position);
    }

    @Override
    public int getItemCount() {
        return this.terminales.size();
    }
}
