/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinicabuenaaventura.cl.datos;

import com.clinicabuenaaventura.cl.entidades.Usuario;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego Gieminiani
 */
public class DAOUsuario {
    
    private final String selectUsuario = "select * from usuario where login = ?";
    private static Conexion objConn = Conexion.InstanciaConn();
    private ResultSet rs;
    
    public Usuario buscaUsuario(String login)
    {
        try {
            Usuario usu = new Usuario();
            PreparedStatement ps = objConn.getConn().prepareStatement(selectUsuario);
            ps.setString(1, login);
            rs = ps.executeQuery();
            if(rs.next())
            {
                usu.setId_usuario(rs.getInt("id_usuario"));
                usu.setLogin(rs.getString("login"));
                usu.setContrasena(rs.getString("contrasena"));
                return usu;
            }
            else
            {
                return null;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
