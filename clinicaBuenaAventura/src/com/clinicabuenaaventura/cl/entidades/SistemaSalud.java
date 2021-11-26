/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinicabuenaaventura.cl.entidades;

/**
 *
 * @author Diego Gieminiani
 */
public class SistemaSalud {
    
    private int id_sistema_salud;
    private String nombre_sistema_salud;

    public SistemaSalud() {
    }

    public SistemaSalud(int id_sistema_salud, String nombre_sistema_salud) {
        this.id_sistema_salud = id_sistema_salud;
        this.nombre_sistema_salud = nombre_sistema_salud;
    }

    public int getId_sistema_salud() {
        return id_sistema_salud;
    }

    public void setId_sistema_salud(int id_sistema_salud) {
        this.id_sistema_salud = id_sistema_salud;
    }

    public String getNombre_sistema_salud() {
        return nombre_sistema_salud;
    }

    public void setNombre_sistema_salud(String nombre_sistema_salud) {
        this.nombre_sistema_salud = nombre_sistema_salud;
    }

    @Override
    public String toString() {
        return "SistemaSalud{" + "id_sistema_salud=" + id_sistema_salud + ", nombre_sistema_salud=" + nombre_sistema_salud + '}';
    }
    
    
    
}
