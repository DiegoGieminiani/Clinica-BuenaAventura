/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinicabuenaaventura.cl.negocio;

import com.clinicabuenaaventura.cl.datos.DAOSistemaSalud;
import com.clinicabuenaaventura.cl.entidades.SistemaSalud;
import java.util.*;

/**
 *
 * @author Diego Gieminiani
 */
public class NegSistemaSalud {

    public ArrayList<SistemaSalud> listarTodo() {
        DAOSistemaSalud datoSissal = new DAOSistemaSalud();
        ArrayList<SistemaSalud> listaSissal = datoSissal.listarTodo();
        return listaSissal;
    }

}
