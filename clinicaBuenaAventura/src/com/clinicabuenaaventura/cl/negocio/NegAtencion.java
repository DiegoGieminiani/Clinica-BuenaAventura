/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinicabuenaaventura.cl.negocio;

import com.clinicabuenaaventura.cl.datos.DAOAtencion;
import com.clinicabuenaaventura.cl.entidades.Atencion;
import java.util.ArrayList;

/**
 *
 * @author Diego Gieminiani
 */
public class NegAtencion {

    private DAOAtencion datoAte = new DAOAtencion();

    public ArrayList<Atencion> listarTodo() {
        ArrayList<Atencion> listaAte = datoAte.listarTodo();
        return listaAte;
    }

    public int nextId() {
        return datoAte.nextId();
    }

    public boolean agregar(Atencion ate) {
        if (datoAte.agregar(ate)) {
            return true;
        } else {
            return false;
        }

    }

    public boolean modificar(Atencion ate) {
        if (datoAte.modificar(ate)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean eliminar(Atencion ate) {
        if (datoAte.eliminar(ate)) {
            return true;
        } else {
            return false;
        }

    }

}
