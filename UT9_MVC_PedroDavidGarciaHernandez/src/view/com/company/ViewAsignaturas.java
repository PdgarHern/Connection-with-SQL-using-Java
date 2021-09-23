package view.com.company;

import javax.swing.*;

public class ViewAsignaturas extends JFrame {
    private JButton btnEliminar;
    private JButton btnModificar;
    private JPanel PanelEntrada;
    private JButton btnNuevo;
    private JTable table1;
    private JButton cargarButton;
    private JButton personasButton;
    private JPanel panelAcciones;
    private JButton btnFiltrar;


    public ViewAsignaturas() {

        super("Asignaturas");
        setContentPane(PanelEntrada);
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        setSize(ancho, alto);

    }

    public JPanel getPanelEntrada() {
        return PanelEntrada;
    }

    public void setPanelEntrada(JPanel panelEntrada) {
        PanelEntrada = panelEntrada;
    }

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

    public JButton getCargarButton() {
        return cargarButton;
    }

    public void setCargarButton(JButton cargarButton) {
        this.cargarButton = cargarButton;
    }

    public JButton getPersonasButton() {
        return personasButton;
    }

    public void setPersonasButton(JButton personasButton) {
        this.personasButton = personasButton;
    }

    public JButton getBtnEliminar() {return btnEliminar;}

    public void setBtnEliminar(JButton btnEliminar) {this.btnEliminar = btnEliminar;}

    public JPanel getPanelAcciones() {return panelAcciones;}

    public void setPanelAcciones(JPanel panelAcciones) {this.panelAcciones = panelAcciones;}

    public JButton getBtnNuevo() {return btnNuevo;}

    public void setBtnNuevo(JButton btnNuevo) {this.btnNuevo = btnNuevo;}

    public JButton getBtnModificar() {return btnModificar;}

    public void setBtnModificar(JButton btnModificar) {this.btnModificar = btnModificar;}

    public JButton getBtnFiltrar() {return btnFiltrar;}

    public void setBtnFiltrar(JButton btnFiltrar) {this.btnFiltrar = btnFiltrar;}

}
