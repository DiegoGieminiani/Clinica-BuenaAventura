/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinicabuenaaventura.cl.datos;

import com.clinicabuenaaventura.cl.entidades.Atencion;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego Gieminiani
 */
public class DAOAtencion {

    private final String selectAll = "select id_atencion,rut_paciente,nombre_paciente,edad_paciente,fecha_nacimiento_paciente,id_especialidad,id_sistema_salud from atencion";
    private final String selectNextId = "select max(id_atencion)+1 nextid from atencion";
    private final String insert = "insert into atencion(id_atencion,rut_paciente,nombre_paciente,edad_paciente,fecha_nacimiento_paciente,id_especialidad,id_sistema_salud) values (?,?,?,?,?,?,?)";
    private final String update = "update atencion set id_atencion=?,rut_paciente=?,nombre_paciente=?,edad_paciente=?,fecha_nacimiento_paciente=?,id_especialidad=?,id_sistema_salud where id_atencion=?";
    private final String delete = "delete from atencion where id_atencion = ?";
    private static Conexion objConn = Conexion.InstanciaConn();
    private ResultSet rs;

    public ArrayList<Atencion> listarTodo() {
        try {
            ArrayList<Atencion> listaAte = new ArrayList<Atencion>();
            PreparedStatement ps = objConn.getConn().prepareStatement(selectAll);
            rs = ps.executeQuery();

            while (rs.next()) {
                Atencion ate = new Atencion();
                ate.setId_atencion(rs.getInt("id_atencion"));
                ate.setNombre_paciente(rs.getString("nombre_paciente"));
                ate.setRut_paciente(rs.getString("rut_paciente"));
                ate.setEdad_paciente(rs.getInt("edad_paciente"));
                ate.setFecha_nacimiento_paciente(rs.getDate("fecha_nacimiento_paciente"));
                ate.setId_especialidad(rs.getInt("id_especialidad"));
                ate.setId_sistema_salud(rs.getInt("id_sistema_salud"));
                listaAte.add(ate);

            }
            return listaAte;

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
            Logger.getLogger(DAOAtencion.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public boolean agregar(Atencion ate) {
        try {
            java.sql.Date fecha_nacimiento_paciente = new java.sql.Date(ate.getFecha_nacimiento_paciente().getTime());
            PreparedStatement ps = objConn.getConn().prepareStatement(insert);
            ps.setInt(1, ate.getId_atencion());
            ps.setString(2, ate.getRut_paciente());
            ps.setString(3, ate.getNombre_paciente());
            ps.setInt(4, ate.getEdad_paciente());
            ps.setDate(5, fecha_nacimiento_paciente);
            ps.setInt(6, ate.getId_especialidad());
            ps.setInt(7, ate.getId_sistema_salud());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOAtencion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean modificar(Atencion ate) {
        try {
            java.sql.Date fecha_nacimiento_paciente = new java.sql.Date(ate.getFecha_nacimiento_paciente().getTime());
            PreparedStatement ps = objConn.getConn().prepareStatement(update);
            ps.setInt(1, ate.getId_atencion());
            ps.setString(2, ate.getRut_paciente());
            ps.setString(3, ate.getNombre_paciente());
            ps.setInt(4, ate.getEdad_paciente());
            ps.setDate(5, fecha_nacimiento_paciente);
            ps.setInt(6, ate.getId_especialidad());
            ps.setInt(7, ate.getId_sistema_salud());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOAtencion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean eliminar(Atencion ate) {
        try {
            PreparedStatement ps = objConn.getConn().prepareStatement(delete);
            ps.setInt(1, ate.getId_atencion());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOAtencion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
