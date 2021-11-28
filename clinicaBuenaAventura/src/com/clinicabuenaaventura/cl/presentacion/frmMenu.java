/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinicabuenaaventura.cl.presentacion;

import com.clinicabuenaaventura.cl.entidades.*;
import com.clinicabuenaaventura.cl.negocio.*;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nicol
 */
public class frmMenu extends javax.swing.JFrame {

    private HashMap<String, Integer> mapEspecialidades = new HashMap<>();
    private HashMap<String, Integer> mapSistemaSalud = new HashMap<>();

    /**
     * Creates new form frmMenu
     */
    public frmMenu() {
        initComponents();
        llenarTablaEsp();
        llenarComboEsp();
        nextIdEsp();
        llenarTablaAte();
        llenarComboSisSal();
        nextIdAte();
    }

    // Metodos para el pane especialidades //
    private void llenarTablaEsp() {
        NegEspecialidad nEspecialidad = new NegEspecialidad();
        ArrayList<Especialidad> listaEspecialidad = nEspecialidad.listarTodo();
        //objetos necesarios para llenar la tabla
        DefaultTableModel modelo = (DefaultTableModel) tblEspecialidades.getModel();
        Object[] fila = new Object[2];
        for (Especialidad esp : listaEspecialidad) {
            fila[0] = esp.getId_especialidad();
            fila[1] = esp.getNombre_especialidad();

            modelo.addRow(fila);
        }

        tblEspecialidades.setModel(modelo);
    }

    private void nextIdEsp() {
        NegEspecialidad nEsp = new NegEspecialidad();
        txtIdEsp.setText(String.valueOf(nEsp.nextId()));
    }

    private void refrescaTablaEsp() {
        DefaultTableModel dm = (DefaultTableModel) tblEspecialidades.getModel();
        dm.getDataVector().removeAllElements();
        llenarTablaEsp();
    }

    private void limpiarDatosEsp() {
        txtNombreEsp.setText("");
        txtIdEsp.setText("");
        cboEspecialidad.setSelectedIndex(0);
        nextIdEsp();
        txtNombre.requestFocus();

    }

    private void agregarEsp() {

        int id = Integer.parseInt(txtIdEsp.getText());
        String nombre = txtNombreEsp.getText();

        Especialidad esp = new Especialidad();
        esp.setId_especialidad(id);
        esp.setNombre_especialidad(nombre);

        NegEspecialidad nEspecialidad = new NegEspecialidad();
        if (nEspecialidad.agregar(esp)) {
            JOptionPane.showMessageDialog(this, "Especialidad creada");
            refrescaTablaEsp();
            limpiarDatosEsp();
        } else {
            JOptionPane.showMessageDialog(this, "Especialidad NO creada");
            limpiarDatosEsp();
        }

    }

    private void modificarEsp() {

        int id = Integer.parseInt(txtIdEsp.getText());
        String nombre = txtNombreEsp.getText();

        Especialidad esp = new Especialidad();
        esp.setId_especialidad(id);
        esp.setNombre_especialidad(nombre);

        NegEspecialidad nEspecialidad = new NegEspecialidad();
        if (nEspecialidad.modificar(esp)) {
            JOptionPane.showMessageDialog(this, "Especialidad Modificada");
            refrescaTablaEsp();
            limpiarDatosEsp();
        } else {
            JOptionPane.showMessageDialog(this, "Especialidad NO Modificada");
            limpiarDatosEsp();
        }

    }

    private void eliminarEsp() {

        int id = Integer.parseInt(txtIdEsp.getText());

        Especialidad esp = new Especialidad();
        esp.setId_especialidad(id);

        NegEspecialidad nEspecialidad = new NegEspecialidad();
        if (nEspecialidad.eliminar(esp)) {
            JOptionPane.showMessageDialog(this, "Especialidad eliminada");
            refrescaTablaEsp();
            limpiarDatosEsp();
        } else {
            JOptionPane.showMessageDialog(this, "Especialidad NO eliminada");
            limpiarDatosEsp();
        }
    }

    // Metodos para el pane atenciones //
    private void llenarTablaAte() {
        NegAtencion nAtencion = new NegAtencion();
        ArrayList<Atencion> listaAtencion = nAtencion.listarTodo();
        //objetos necesarios para llenar la tabla
        DefaultTableModel modelo = (DefaultTableModel) tblAtenciones.getModel();
        Object[] fila = new Object[7];
        for (Atencion ate : listaAtencion) {
            fila[0] = ate.getId_atencion();
            fila[1] = ate.getRut_paciente();
            fila[2] = ate.getNombre_paciente();
            fila[3] = ate.getEdad_paciente();
            fila[4] = ate.getFecha_nacimiento_paciente();
            fila[5] = ate.getId_especialidad();
            fila[6] = ate.getId_sistema_salud();            

            modelo.addRow(fila);
        }

        tblAtenciones.setModel(modelo);
    }

    private void llenarComboEsp() {
        NegEspecialidad nEspecialidad = new NegEspecialidad();
        ArrayList<Especialidad> listaEspecialidad = nEspecialidad.listarTodo();
        for (Especialidad esp : listaEspecialidad) {
            mapEspecialidades.put(esp.getNombre_especialidad(), esp.getId_especialidad());
            cboEspecialidad.addItem(esp.getNombre_especialidad());
        }

    }

    private void llenarComboSisSal() {
        NegSistemaSalud nSistemaSalud = new NegSistemaSalud();
        ArrayList<SistemaSalud> listaSistemaSalud = nSistemaSalud.listarTodo();
        for (SistemaSalud sisSal : listaSistemaSalud) {
            mapSistemaSalud.put(sisSal.getNombre_sistema_salud(), sisSal.getId_sistema_salud());
            cboSistemaSalud.addItem(sisSal.getNombre_sistema_salud());
        }

    }

    private void nextIdAte() {
        NegAtencion nAte = new NegAtencion();
        txtIdAte.setText(String.valueOf(nAte.nextId()));
    }

    private void refrescaTablaAte() {
        DefaultTableModel dm = (DefaultTableModel) tblAtenciones.getModel();
        dm.getDataVector().removeAllElements();
        llenarTablaAte();
    }

    private void limpiarDatosAte() {
        txtRut.setText("");
        txtNombre.setText("");
        txtIdAte.setText("");
        txtEdad.setText("");
        txtFechaNacimiento.setText("");
        cboEspecialidad.setSelectedIndex(0);
        cboSistemaSalud.setSelectedIndex(0);
        nextIdAte();
        txtNombre.requestFocus();

    }

    private void agregarAte() {

        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        int id = Integer.parseInt(txtIdAte.getText());
        String rut = txtRut.getText();
        String nombre = txtNombre.getText();
        int edad = Integer.parseInt(txtEdad.getText());
        Date fecha_nacimiento_paciente = null;
        try {
            fecha_nacimiento_paciente = formato.parse(txtFechaNacimiento.getText());
        } catch (ParseException ex) {
            Logger.getLogger(frmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        int id_especialidad = mapEspecialidades.get(cboEspecialidad.getSelectedItem().toString());
        int id_sistema_salud = mapSistemaSalud.get(cboSistemaSalud.getSelectedItem().toString());

        Atencion ate = new Atencion();
        ate.setId_atencion(id);
        ate.setRut_paciente(rut);
        ate.setNombre_paciente(nombre);
        ate.setEdad_paciente(edad);
        ate.setFecha_nacimiento_paciente(fecha_nacimiento_paciente);
        ate.setId_especialidad(id_especialidad);
        ate.setId_sistema_salud(id_sistema_salud);

        NegAtencion nAtencion = new NegAtencion();
        if (nAtencion.agregar(ate)) {
            JOptionPane.showMessageDialog(this, "Atencion creada");
            refrescaTablaAte();
            limpiarDatosAte();
        } else {
            JOptionPane.showMessageDialog(this, "Atencion NO creada");
            limpiarDatosAte();
        }

    }

    private void modificarAte() {

        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        int id = Integer.parseInt(txtIdAte.getText());
        String rut = txtRut.getText();
        String nombre = txtNombre.getText();
        int edad = Integer.parseInt(txtEdad.getText());
        Date fecha_nacimiento_paciente = null;
        try {
            fecha_nacimiento_paciente = formato.parse(txtFechaNacimiento.getText());
        } catch (ParseException ex) {
            Logger.getLogger(frmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        int id_especialidad = mapEspecialidades.get(cboEspecialidad.getSelectedItem().toString());
        int id_sistema_salud = mapSistemaSalud.get(cboSistemaSalud.getSelectedItem().toString());

        Atencion ate = new Atencion();
        ate.setId_atencion(id);
        ate.setRut_paciente(rut);
        ate.setNombre_paciente(nombre);
        ate.setEdad_paciente(edad);
        ate.setFecha_nacimiento_paciente(fecha_nacimiento_paciente);
        ate.setId_especialidad(id_especialidad);
        ate.setId_sistema_salud(id_sistema_salud);

        NegAtencion nAtencion = new NegAtencion();
        if (nAtencion.modificar(ate)) {
            JOptionPane.showMessageDialog(this, "Atencion modificada");
            refrescaTablaAte();
            limpiarDatosAte();
        } else {
            JOptionPane.showMessageDialog(this, "Atencion NO modificada");
            limpiarDatosAte();
        }

    }

    private void eliminarAte() {

        int id = Integer.parseInt(txtIdAte.getText());

        Atencion ate = new Atencion();
        ate.setId_atencion(id);

        NegAtencion nAtencion = new NegAtencion();
        if (nAtencion.eliminar(ate)) {
            JOptionPane.showMessageDialog(this, "Atencion eliminada");
            refrescaTablaAte();
            limpiarDatosAte();
        } else {
            JOptionPane.showMessageDialog(this, "Atencion NO eliminada");
            limpiarDatosAte();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tpEspAte = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAtenciones = new javax.swing.JTable();
        btnAgregarAte = new javax.swing.JButton();
        btnModificarAte = new javax.swing.JButton();
        btnEliminarAte = new javax.swing.JButton();
        lblRut = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblEdad = new javax.swing.JLabel();
        lblFechaNacimiento = new javax.swing.JLabel();
        lblEspecialidad = new javax.swing.JLabel();
        lblSitemaSalud = new javax.swing.JLabel();
        txtRut = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtEdad = new javax.swing.JTextField();
        txtFechaNacimiento = new javax.swing.JTextField();
        cboEspecialidad = new javax.swing.JComboBox<>();
        cboSistemaSalud = new javax.swing.JComboBox<>();
        txtIdAte = new javax.swing.JTextField();
        lblIdAte = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEspecialidades = new javax.swing.JTable();
        btnAgregarEsp = new javax.swing.JButton();
        btnModificarEsp = new javax.swing.JButton();
        btnEliminarEsp = new javax.swing.JButton();
        txtNombreEsp = new javax.swing.JTextField();
        lblNombreEsp = new javax.swing.JLabel();
        lblIdEsp = new javax.swing.JLabel();
        txtIdEsp = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("MENÚ PRINCIPAL");

        tblAtenciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Rut", "Nombre", "Edad", "Fecha de nacimiento", "Id Especialidad", "Id Sistema de salud"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblAtenciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAtencionesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblAtenciones);
        if (tblAtenciones.getColumnModel().getColumnCount() > 0) {
            tblAtenciones.getColumnModel().getColumn(0).setMinWidth(60);
            tblAtenciones.getColumnModel().getColumn(0).setPreferredWidth(60);
            tblAtenciones.getColumnModel().getColumn(0).setMaxWidth(60);
        }

        btnAgregarAte.setText("Agregar");
        btnAgregarAte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarAteMouseClicked(evt);
            }
        });

        btnModificarAte.setText("Modificar");
        btnModificarAte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModificarAteMouseClicked(evt);
            }
        });

        btnEliminarAte.setText("Eliminar");
        btnEliminarAte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarAteMouseClicked(evt);
            }
        });

        lblRut.setText("Rut");

        lblNombre.setText("Nombre");

        lblEdad.setText("Edad");

        lblFechaNacimiento.setText("Fecha de nacimiento");

        lblEspecialidad.setText("Especialidad");

        lblSitemaSalud.setText("Sistema de salud");

        txtRut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRutActionPerformed(evt);
            }
        });

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        txtFechaNacimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaNacimientoActionPerformed(evt);
            }
        });

        cboEspecialidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        cboEspecialidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEspecialidadActionPerformed(evt);
            }
        });

        cboSistemaSalud.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { }));

        txtIdAte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdAteActionPerformed(evt);
            }
        });

        lblIdAte.setText("Id Atencion");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1243, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAgregarAte)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificarAte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminarAte))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre)
                            .addComponent(lblRut)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblIdAte, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblEdad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtRut, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                            .addComponent(txtEdad)
                            .addComponent(txtIdAte))
                        .addGap(112, 112, 112)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFechaNacimiento)
                            .addComponent(lblSitemaSalud)
                            .addComponent(lblEspecialidad))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFechaNacimiento)
                            .addComponent(cboEspecialidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboSistemaSalud, 0, 110, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRut)
                    .addComponent(lblFechaNacimiento)
                    .addComponent(txtRut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(lblEspecialidad)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEdad)
                    .addComponent(lblSitemaSalud)
                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSistemaSalud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdAte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdAte))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarAte)
                    .addComponent(btnAgregarAte)
                    .addComponent(btnModificarAte))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tpEspAte.addTab("Atenciones", jPanel2);

        tblEspecialidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblEspecialidades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEspecialidadesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEspecialidades);
        if (tblEspecialidades.getColumnModel().getColumnCount() > 0) {
            tblEspecialidades.getColumnModel().getColumn(0).setMinWidth(60);
            tblEspecialidades.getColumnModel().getColumn(0).setPreferredWidth(60);
            tblEspecialidades.getColumnModel().getColumn(0).setMaxWidth(60);
        }

        btnAgregarEsp.setText("Agregar");
        btnAgregarEsp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarEspMouseClicked(evt);
            }
        });

        btnModificarEsp.setText("Modificar");
        btnModificarEsp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModificarEspMouseClicked(evt);
            }
        });

        btnEliminarEsp.setText("Eliminar");
        btnEliminarEsp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarEspMouseClicked(evt);
            }
        });

        txtNombreEsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreEspActionPerformed(evt);
            }
        });

        lblNombreEsp.setText("Nombre Especialidad:");

        lblIdEsp.setText("Id Especialidad:");

        txtIdEsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdEspActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1243, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAgregarEsp)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificarEsp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminarEsp))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombreEsp)
                            .addComponent(lblIdEsp))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreEsp, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdEsp, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdEsp)
                    .addComponent(txtIdEsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreEsp)
                    .addComponent(txtNombreEsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarEsp)
                    .addComponent(btnEliminarEsp)
                    .addComponent(btnAgregarEsp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tpEspAte.addTab("Especialidades", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tpEspAte)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(601, 601, 601)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tpEspAte)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreEspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreEspActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreEspActionPerformed

    private void txtRutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRutActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtFechaNacimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaNacimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaNacimientoActionPerformed

    private void cboEspecialidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEspecialidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboEspecialidadActionPerformed

    private void btnAgregarEspMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarEspMouseClicked
        // TODO add your handling code here:
        agregarEsp();
    }//GEN-LAST:event_btnAgregarEspMouseClicked

    private void txtIdEspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdEspActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdEspActionPerformed

    private void txtIdAteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdAteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdAteActionPerformed

    private void tblEspecialidadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEspecialidadesMouseClicked
        // TODO add your handling code here:
        int fila = tblEspecialidades.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe selecionar una especialidad");
        } else {
            String id = (String) tblEspecialidades.getValueAt(fila, 0).toString();
            String nombre = (String) tblEspecialidades.getValueAt(fila, 1);
            txtIdEsp.setText(id);
            txtNombreEsp.setText(nombre);
        }

    }//GEN-LAST:event_tblEspecialidadesMouseClicked

    private void btnModificarEspMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarEspMouseClicked
        // TODO add your handling code here:
        modificarEsp();
    }//GEN-LAST:event_btnModificarEspMouseClicked

    private void btnEliminarEspMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarEspMouseClicked
        // TODO add your handling code here:
        eliminarEsp();
    }//GEN-LAST:event_btnEliminarEspMouseClicked

    private void btnModificarAteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarAteMouseClicked
        // TODO add your handling code here:
        modificarAte();
    }//GEN-LAST:event_btnModificarAteMouseClicked

    private void btnEliminarAteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarAteMouseClicked
        // TODO add your handling code here:
        eliminarAte();
    }//GEN-LAST:event_btnEliminarAteMouseClicked

    private void btnAgregarAteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarAteMouseClicked
        // TODO add your handling code here:
        agregarAte();
    }//GEN-LAST:event_btnAgregarAteMouseClicked

    private void tblAtencionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAtencionesMouseClicked
        // TODO add your handling code here:
        int fila = tblAtenciones.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe selecionar un registro de atencion");
        } else {
            String id = (String) tblAtenciones.getValueAt(fila, 0).toString();
            String rut = (String) tblAtenciones.getValueAt(fila, 1);
            String nombre = (String) tblAtenciones.getValueAt(fila, 2);
            String edad = (String) tblAtenciones.getValueAt(fila, 3).toString();
            String fecha_nacimiento = (String) tblAtenciones.getValueAt(fila, 4).toString();
            String id_especialidad  = (String) tblAtenciones.getValueAt(fila, 5).toString();
            String id_sistema_salud = (String) tblAtenciones.getValueAt(fila, 6).toString();
            
            
            txtIdAte.setText(id);
            txtRut.setText(rut);
            txtNombre.setText(nombre);
            txtEdad.setText(edad);
            txtFechaNacimiento.setText(fecha_nacimiento);
            cboEspecialidad.setSelectedItem(id_especialidad);
            cboSistemaSalud.setSelectedItem(id_sistema_salud);

        }
    }//GEN-LAST:event_tblAtencionesMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarAte;
    private javax.swing.JButton btnAgregarEsp;
    private javax.swing.JButton btnEliminarAte;
    private javax.swing.JButton btnEliminarEsp;
    private javax.swing.JButton btnModificarAte;
    private javax.swing.JButton btnModificarEsp;
    private javax.swing.JComboBox<String> cboEspecialidad;
    private javax.swing.JComboBox<String> cboSistemaSalud;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblEspecialidad;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblIdAte;
    private javax.swing.JLabel lblIdEsp;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombreEsp;
    private javax.swing.JLabel lblRut;
    private javax.swing.JLabel lblSitemaSalud;
    private javax.swing.JTable tblAtenciones;
    private javax.swing.JTable tblEspecialidades;
    private javax.swing.JTabbedPane tpEspAte;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtFechaNacimiento;
    private javax.swing.JTextField txtIdAte;
    private javax.swing.JTextField txtIdEsp;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreEsp;
    private javax.swing.JTextField txtRut;
    // End of variables declaration//GEN-END:variables
}
