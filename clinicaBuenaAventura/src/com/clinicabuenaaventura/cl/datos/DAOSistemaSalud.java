/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinicabuenaaventura.cl.datos;

import com.clinicabuenaaventura.cl.entidades.SistemaSalud;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego Gieminiani
 */
public class DAOSistemaSalud {

    private final String selectAll = "select * from sistema_salud";
    private static Conexion objConn = Conexion.InstanciaConn();
    ResultSet rs;

    public ArrayList<SistemaSalud> listarTodo() {
        try {
            ArrayList<SistemaSalud> listaSissal = new ArrayList<SistemaSalud>();
            PreparedStatement ps = objConn.getConn().prepareStatement(selectAll);
            rs = ps.executeQuery();
            while (rs.next()) {
                SistemaSalud sissal = new SistemaSalud();
                sissal.setId_sistema_salud(rs.getInt("id_sistema_salud"));
                sissal.setNombre_sistema_salud(rs.getString("nombre_sistema_salud"));
                listaSissal.add(sissal);
            }
            return listaSissal;
        } catch (SQLException ex) {
            Logger.getLogger(DAOSistemaSalud.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
