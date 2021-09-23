package Controler.com.company;

import Connecion.ConectionBD;
import model.com.company.ModelAsignaturas;
import model.com.company.ModelEntrada;
import view.com.company.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Statement;

public class ControllerAsignaturas implements ActionListener, WindowListener {
    private Statement stmt;
    private final ViewAsignaturas frentrada = new ViewAsignaturas();
    private DefaultTableModel m = null;


    // Constructor lanza cada uno de los procedimientos de la aplicación
    public ControllerAsignaturas() {
        IniciarVentana();
        IniciarEventos();
        PrepararBaseDatos();
    }

    public void IniciarVentana() {
        frentrada.setVisible(true);
    }

    public void IniciarEventos() {
        frentrada.getBtnEliminar().addActionListener(this::actionPerformed);
        frentrada.getBtnModificar().addActionListener(this::actionPerformed);
        frentrada.getBtnNuevo().addActionListener(this::actionPerformed);
        frentrada.getPersonasButton().addActionListener(this::actionPerformed);
        frentrada.getBtnFiltrar().addActionListener(this::actionPerformed);
        frentrada.addWindowListener(this);

    }

    public void PrepararBaseDatos() {
        ModelAsignaturas entrada = new ModelAsignaturas();
        frentrada.getTable1().setModel(entrada.CargaDatos(m));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String entrada = e.getActionCommand();

        switch (entrada) {
            case "Eliminar asignatura":
                int filaSelecAsignatura;
                String id;
                int resp;

                try {
                    filaSelecAsignatura = frentrada.getTable1().getSelectedRow();

                    if (filaSelecAsignatura == -1) {
                        JOptionPane.showMessageDialog(null,"Debes seleccionar la asignatura a borrar");

                    } else {
                        resp = JOptionPane.showConfirmDialog(null,"¿Desea eliminar la asignatura seleccionado?",
                                "Eliminar",JOptionPane.YES_NO_OPTION);

                        if (resp == JOptionPane.YES_OPTION) {
                            m = (DefaultTableModel) frentrada.getTable1().getModel();
                            id = (String) m.getValueAt(filaSelecAsignatura,0);

                            stmt = ConectionBD.getStmt();

                            stmt.executeUpdate("delete from asignatura where id = " + id);
                            PrepararBaseDatos();

                        }

                    }

                } catch (Exception x) {
                    JOptionPane.showMessageDialog(null,"Error al eliminar la asignatura");
                }

                break;

            case "Modificar asignatura":
                int filSecAsig;
                String idA,nombreA,creditos,tipoA,curso,cuatrimestre,id_profesor,id_grado;

                filSecAsig = frentrada.getTable1().getSelectedRow();

                if (filSecAsig == -1) {
                    JOptionPane.showMessageDialog(null,"Debes seleccionar la asignatura a modificar");

                } else {
                    ActualizarAsig actualizarAsig = new ActualizarAsig();

                    m = (DefaultTableModel) frentrada.getTable1().getModel();

                    idA = (String) m.getValueAt(filSecAsig,0);
                    nombreA = (String) m.getValueAt(filSecAsig,1);
                    creditos = (String) m.getValueAt(filSecAsig,2);
                    tipoA = (String) m.getValueAt(filSecAsig,3);
                    curso = (String) m.getValueAt(filSecAsig,4);
                    cuatrimestre = (String) m.getValueAt(filSecAsig,5);
                    id_profesor = (String) m.getValueAt(filSecAsig,6);
                    id_grado = (String) m.getValueAt(filSecAsig,7);

                    actualizarAsig.setTxtId(idA);
                    actualizarAsig.setTxtNombre(nombreA);
                    actualizarAsig.setTxtCreditos(creditos);
                    actualizarAsig.setTxtTipo(tipoA);
                    actualizarAsig.setTxtCurso(curso);
                    actualizarAsig.setTxtCuatrimestre(cuatrimestre);
                    actualizarAsig.setTxtIdProfesor(id_profesor);
                    actualizarAsig.setTxtIdGrado(id_grado);

                    actualizarAsig.setBounds(700,300,400,400);
                    actualizarAsig.setVisible(true);
                    PrepararBaseDatos();

                }

                break;

            case "Añadir asignatura":
                EntradaAsig entradaAsig = new EntradaAsig();
                entradaAsig.setBounds(700,300,400,400);
                entradaAsig.setVisible(true);
                PrepararBaseDatos();
                break;

            case "Filtrar asignaturas":
                FiltrarAsig filtrarAsig = new FiltrarAsig(frentrada.getTable1());
                filtrarAsig.setBounds(700,300,450,400);
                filtrarAsig.setVisible(true);
                break;

            case "Personas":
                frentrada.dispose();
                new ControllerEntrada();
                break;
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("ha salido del programa");
        frentrada.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ConectionBD.CloseConn();
    }

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}

}
