/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinicabuenaaventura.cl.negocio;

import com.clinicabuenaaventura.cl.entidades.Especialidad;
import com.clinicabuenaaventura.cl.datos.DAOEspecialidad;
import java.util.ArrayList;

/**
 *
 * @author nicol
 */
public class NegEspecialidad {

    private DAOEspecialidad datoEsp = new DAOEspecialidad();

    public ArrayList<Especialidad> listarTodo() {

        ArrayList<Especialidad> listaEsp = datoEsp.listarTodo();
        return listaEsp;
    }

    public int nextId() {
        return datoEsp.nextId();
    }

    public boolean agregar(Especialidad esp) {
        if (datoEsp.agregar(esp)) {
            return true;
        } else {
            return false;
        }

    }

    public boolean modificar(Especialidad esp) {
        if (datoEsp.modificar(esp)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean eliminar(Especialidad esp) {
        if (datoEsp.eliminar(esp)) {
            return true;
        } else {
            return false;
        }

    }
}
