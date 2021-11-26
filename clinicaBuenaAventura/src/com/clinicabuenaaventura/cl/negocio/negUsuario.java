/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinicabuenaaventura.cl.negocio;

import com.clinicabuenaaventura.cl.entidades.Usuario;

/**
 *
 * @author Diego Gieminiani
 */
public class negUsuario {
    public boolean inicioSesion(String login, String pass)
    {
        Usuario usu = new Usuario();
        DAOUsuario datUsuario = new DAOUsuario();
        usu = datUsuario.buscaUsuario(login);
        if(usu==null)
        {
            return false;
        }
        else
        {
            if(usu.getPass().equals(pass))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
    
}
