package com.example.viewholders;

import java.util.Arrays;

public class Persona {
    private String nombre;
    private String apellido;
    private String numero;
    private String imagen;
    private byte[] byteImg;

    public Persona() {
    }

    public Persona(String nombre, String apellido, String numero,String imagen) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numero = numero;
        this.imagen = imagen;
    }

    public String getNumero() {
        return this.numero;
    }

    public byte[] getByteImg() {
        return this.byteImg;
    }

    public void setByteImg(byte[] byteImg) {
        this.byteImg = byteImg;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getImagen() {
        return this.imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }



    public String getNombre() {
        return this.nombre;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", numero='" + numero + '\'' +
                ", imagen='" + imagen + '\'';
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
