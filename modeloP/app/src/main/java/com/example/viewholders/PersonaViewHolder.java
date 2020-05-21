package com.example.viewholders;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

public class PersonaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public View view;

    TextView tvApellido;
    TextView tvNombre;
    TextView tvTelefono;
    ImageView ivImage;

    MyOnItemClick listener;
    int position;


    public PersonaViewHolder(@NonNull View itemView, MyOnItemClick listener)  {
        super(itemView);
        this.view = itemView;
        this.tvApellido = view.findViewById(R.id.tvApellido);
        this.tvNombre = view.findViewById(R.id.tvNombre);
        this.tvTelefono = view.findViewById(R.id.tvTelefono);
        this.ivImage = view.findViewById(R.id.ivImage);

        itemView.setOnClickListener(this);
        this.listener = listener;

    }
    public void setPosition(int position){
        this.position = position;
    }
    @Override
    public void onClick(View view) {
        //Log.d("llamando a", "ENTRO A ONclICK EN pERSONA vIEW HOLDER");
        listener.onItemClick(position);
    }
}
