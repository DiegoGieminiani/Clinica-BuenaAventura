/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinicabuenaaventura.cl.entidades;

/**
 *
 * @author Diego Gieminiani
 */
public class Usuario {
    
    private int id_usuario;
    private String login;
    private String contrasena;

    public Usuario() {
    }

    public Usuario(int id_usuario, String login, String contrasena) {
        this.id_usuario = id_usuario;
        this.login = login;
        this.contrasena = contrasena;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id_usuario=" + id_usuario + ", login=" + login + ", contrasena=" + contrasena + '}';
    }
    
    
    
}
