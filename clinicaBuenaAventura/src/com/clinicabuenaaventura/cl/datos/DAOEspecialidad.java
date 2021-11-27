/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinicabuenaaventura.cl.datos;


import com.clinicabuenaaventura.cl.entidades.Especialidad;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego Gieminiani
 */
public class DAOEspecialidad {
    
    private final String selectAll = "select id_especialidad,nombre_especialidad from especialidad";
    private final String selectNextId = "select max(id_especialidad)+1 nextid from especialidad";
    private final String insert = "insert into especialidad(id_especialidad,nombre_especialidad) values (?,?)";
    private final String update = "update especialidad set nombre_especialidad=? where id_especialidad=?";
    private final String delete = "delete from especialidad where id_especialidad = ?";
    private static Conexion objConn = Conexion.InstanciaConn();
    private ResultSet rs;
    
    public ArrayList<Especialidad> listarTodo() {
        try {
            ArrayList<Especialidad> listaEsp = new ArrayList<Especialidad>();
            PreparedStatement ps = objConn.getConn().prepareStatement(selectAll);
            rs = ps.executeQuery();

            while (rs.next()) {
                Especialidad esp = new Especialidad();
                esp.setId_especialidad(rs.getInt("id_especialidad"));
                esp.setNombre_especialidad(rs.getString("nombre_especialidad"));
                listaEsp.add(esp);

            }
            return listaEsp;

        } catch (SQLException ex) {
            Logger.getLogger(DAOAtencion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int nextId() {
        try {
            int nId = 0;
            PreparedStatement ps = objConn.getConn().prepareStatement(selectNextId);
            rs = ps.executeQuery();
            if (rs.next()) {
                nId = rs.getInt("nextid");
            }
            return nId;
        } catch (SQLException ex) {
            Logger.getLogger(DAOEspecialidad.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public boolean agregar(Especialidad esp) {
        try {
            PreparedStatement ps = objConn.getConn().prepareStatement(insert);
            ps.setInt(1, esp.getId_especialidad());
            ps.setString(2, esp.getNombre_especialidad());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOEspecialidad.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean modificar(Especialidad esp) {
        try {
            PreparedStatement ps = objConn.getConn().prepareStatement(update);
            ps.setString(1, esp.getNombre_especialidad());
            ps.setInt(2, esp.getId_especialidad());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOEspecialidad.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean eliminar(Especialidad esp) {
        try {
            PreparedStatement ps = objConn.getConn().prepareStatement(delete);
            ps.setInt(1, esp.getId_especialidad());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOEspecialidad.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
