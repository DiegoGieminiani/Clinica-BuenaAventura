/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinicabuenaaventura.cl.entidades;

import java.util.Date;

/**
 *
 * @author Diego Gieminiani
 */
public class Atencion {

    private int id_atencion;
    private String rut_paciente;
    private String nombre_paciente;
    private int edad_paciente;
    private Date fecha_nacimiento_paciente;
    private int id_especialidad;
    private int id_sistema_salud;

    public Atencion() {
    }

    public int getId_atencion() {
        return id_atencion;
    }

    public void setId_atencion(int id_atencion) {
        this.id_atencion = id_atencion;
    }

    public String getRut_paciente() {
        return rut_paciente;
    }

    public void setRut_paciente(String rut_paciente) {
        this.rut_paciente = rut_paciente;
    }

    public String getNombre_paciente() {
        return nombre_paciente;
    }

    public void setNombre_paciente(String nombre_paciente) {
        this.nombre_paciente = nombre_paciente;
    }

    public int getEdad_paciente() {
        return edad_paciente;
    }

    public void setEdad_paciente(int edad_paciente) {
        this.edad_paciente = edad_paciente;
    }

    public Date getFecha_nacimiento_paciente() {
        return fecha_nacimiento_paciente;
    }

    public void setFecha_nacimiento_paciente(Date fecha_nacimiento_paciente) {
        this.fecha_nacimiento_paciente = fecha_nacimiento_paciente;
    }

    public int getId_especialidad() {
        return id_especialidad;
    }

    public void setId_especialidad(int id_especialidad) {
        this.id_especialidad = id_especialidad;
    }

    public int getId_sistema_salud() {
        return id_sistema_salud;
    }

    public void setId_sistema_salud(int id_sistema_salud) {
        this.id_sistema_salud = id_sistema_salud;
    }

    @Override
    public String toString() {
        return "Atencion{" + "id_atencion=" + id_atencion + ", rut_paciente=" + rut_paciente + ", nombre_paciente=" + nombre_paciente + ", edad_paciente=" + edad_paciente + ", fecha_nacimiento_paciente=" + fecha_nacimiento_paciente + ", id_especialidad=" + id_especialidad + ", id_sistema_salud=" + id_sistema_salud + '}';
    }

}
