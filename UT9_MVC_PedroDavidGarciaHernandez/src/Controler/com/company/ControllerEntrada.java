package Controler.com.company;

import Connecion.ConectionBD;
import model.com.company.ModelAsignaturas;
import model.com.company.ModelEntrada;
import view.com.company.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.Statement;

public class ControllerEntrada implements ActionListener, WindowListener {

    private Statement stmt;
    private final ViewEntrada frentrada = new ViewEntrada();
    private DefaultTableModel m = null;


    // Constructor lanza cada uno de los procedimientos de la aplicación
    public ControllerEntrada() {
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
        frentrada.getCargarButton().addActionListener(this::actionPerformed);
        frentrada.getBtnFiltrar().addActionListener(this::actionPerformed);
        frentrada.addWindowListener(this);

    }

    public void PrepararBaseDatos() {
        ModelEntrada entrada = new ModelEntrada();
        frentrada.getTable1().setModel(entrada.CargaDatos(m));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String entrada = e.getActionCommand();

        switch (entrada) {
            case "Eliminar persona":
                int filaSelec;
                String nif;
                int resp;

                try {
                    filaSelec = frentrada.getTable1().getSelectedRow();

                    if (filaSelec == -1) {
                        JOptionPane.showMessageDialog(null,"Debes seleccionar la persona a borrar");

                    } else {
                        resp = JOptionPane.showConfirmDialog(null,"¿Desea eliminar la persona seleccionado?",
                                "Eliminar",JOptionPane.YES_NO_OPTION);

                        if (resp == JOptionPane.YES_OPTION) {
                            m = (DefaultTableModel) frentrada.getTable1().getModel();
                            nif = (String) m.getValueAt(filaSelec,0);

                            stmt = ConectionBD.getStmt();

                            stmt.executeUpdate("delete from persona where nif = '" + nif + "'");
                            PrepararBaseDatos();

                        }

                    }

                } catch (Exception x) {
                    JOptionPane.showMessageDialog(null,"Error al eliminar la persona");
                }

                break;

            case "Modificar persona":
                int filSelec;
                String dni,nombre,apell1,apell2,ciudad,direc,tlfno,fechaNac,sexo,tipo;

                filSelec = frentrada.getTable1().getSelectedRow();

                if (filSelec == -1) {
                    JOptionPane.showMessageDialog(null,"Debes seleccionar la persona a modificar");

                } else {
                    ActualizarDatos actualizarDatos = new ActualizarDatos();

                    m = (DefaultTableModel) frentrada.getTable1().getModel();

                    dni = (String) m.getValueAt(filSelec,0);
                    nombre = (String) m.getValueAt(filSelec,1);
                    apell1 = (String) m.getValueAt(filSelec,2);
                    apell2 = (String) m.getValueAt(filSelec,3);
                    ciudad = (String) m.getValueAt(filSelec,4);
                    direc = (String) m.getValueAt(filSelec,5);
                    tlfno = (String) m.getValueAt(filSelec,6);
                    fechaNac = (String) m.getValueAt(filSelec,7);
                    sexo = (String) m.getValueAt(filSelec,8);
                    tipo = (String) m.getValueAt(filSelec,9);

                    actualizarDatos.setTxtNif(dni);
                    actualizarDatos.setTxtNombre(nombre);
                    actualizarDatos.setTxtApellido1(apell1);
                    actualizarDatos.setTxtApellido2(apell2);
                    actualizarDatos.setTxtCiudad(ciudad);
                    actualizarDatos.setTxtDireccion(direc);
                    actualizarDatos.setTxtTlfno(tlfno);
                    actualizarDatos.setTxtAnio(fechaNac.substring(0,4));
                    actualizarDatos.setTxtMes(fechaNac.substring(5,7));
                    actualizarDatos.setTxtDia(fechaNac.substring(8,10));
                    actualizarDatos.setTxtSexo(sexo);
                    actualizarDatos.setTxtTipo(tipo);

                    actualizarDatos.setBounds(700,300,400,400);
                    actualizarDatos.setVisible(true);
                    PrepararBaseDatos();

                }
                break;

            case "Añadir persona":
                EntradaDatos entradaDatos = new EntradaDatos();
                entradaDatos.setBounds(700,300,400,400);
                entradaDatos.setVisible(true);
                PrepararBaseDatos();
                break;

            case "Filtrar personas":
                FiltrarDatos filtrarDatos = new FiltrarDatos(frentrada.getTable1());
                filtrarDatos.setBounds(700,300,450,400);
                filtrarDatos.setVisible(true);
                break;

            case "Asignaturas":
                frentrada.dispose();
                new ControllerAsignaturas();
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
