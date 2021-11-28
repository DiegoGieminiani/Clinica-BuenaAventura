/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinicabuenaaventura.cl.negocio;

import com.clinicabuenaaventura.cl.datos.DAOUsuario;
import com.clinicabuenaaventura.cl.entidades.Usuario;

/**
 *
 * @author Diego Gieminiani
 */
public class NegUsuario {

    public boolean inicioSesion(String login, String contrasena) {
        Usuario usu = new Usuario();
        DAOUsuario datoUsuario = new DAOUsuario();
        usu = datoUsuario.buscaUsuario(login);
        if (usu == null) {
            return false;
        } else {
            if (usu.getContrasena().equals(contrasena)) {
                return true;
            } else {
                return false;
            }
        }
    }

}
