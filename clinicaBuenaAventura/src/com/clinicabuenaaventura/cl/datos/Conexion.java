/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinicabuenaaventura.cl.datos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego Gieminiani
 */
public class Conexion {

    public static Conexion instConn;
    private Connection conn;

    private Conexion() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "clinica", "123456");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized static Conexion InstanciaConn() {
        if (instConn == null) {
            instConn = new Conexion();
        }
        return instConn;
    }

    public Connection getConn() {
        return conn;
    }

    public void Cerrar() {
        instConn = null;
    }
}
