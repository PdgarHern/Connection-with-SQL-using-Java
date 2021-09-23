package view.com.company;

import Connecion.ConectionBD;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Statement;

public class ActualizarDatos extends JDialog {
    private Statement stmt;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtNombre;
    private JLabel LNombre;
    private JTextField txtNif;
    private JLabel LNif;
    private JTextField txtApellido1;
    private JTextField txtApellido2;
    private JLabel LApellidos;
    private JLabel LCiudad;
    private JTextField txtCiudad;
    private JLabel LDireccion;
    private JTextField txtDireccion;
    private JTextField txtTlfno;
    private JTextField txtDia;
    private JTextField txtMes;
    private JTextField txtAnio;
    private JLabel LSexo;
    private JLabel LTipo;
    private JTextField txtSexo;
    private JTextField txtTipo;

    public ActualizarDatos() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {onOK();}
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {onCancel();}
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    public void onOK() {
        String nif,nombre,apell1,apell2,ciudad,direccion,tlfno,fechaNac,sexo,tipo;
        String sql;

        try {
            nif = txtNif.getText();
            nombre = txtNombre.getText();
            apell1 = txtApellido1.getText();
            apell2 = txtApellido2.getText();
            ciudad = txtCiudad.getText();
            direccion = txtDireccion.getText();
            tlfno = txtTlfno.getText();
            fechaNac = txtAnio.getText() + "-" + txtMes.getText() + "-" + txtDia.getText();
            sexo = txtSexo.getText();
            tipo = txtTipo.getText();

            sql = "update persona set nombre = '"+ nombre +"',"+
                    "apellido1 = '"+ apell1 +"',"+
                    "apellido2 = '"+ apell2 +"',"+
                    "ciudad = '"+ ciudad +"',"+
                    "direccion = '"+ direccion +"',"+
                    "telefono = '"+ tlfno +"',"+
                    "fecha_nacimiento = '"+ fechaNac +"',"+
                    "sexo = '"+ sexo +"',"+
                    "tipo = '"+ tipo +"' where nif = '"+ nif +"'";

            stmt = ConectionBD.getStmt();
            stmt.executeUpdate(sql);
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al modificar la persona");
        }

    }

    public void onCancel() {
        dispose();
    }

    public void setTxtNombre(String nombre) {
        this.txtNombre.setText(nombre);
    }

    public void setTxtNif(String nif) {
        this.txtNif.setText(nif);
    }

    public void setTxtApellido1(String apellido1) {
        this.txtApellido1.setText(apellido1);
    }

    public void setTxtApellido2(String apellido2) {
        this.txtApellido2.setText(apellido2);
    }

    public void setTxtCiudad(String ciudad) {
        this.txtCiudad.setText(ciudad);
    }

    public void setTxtDireccion(String direccion) {
        this.txtDireccion.setText(direccion);
    }

    public void setTxtTlfno(String tlfno) {
        this.txtTlfno.setText(tlfno);
    }

    public void setTxtDia(String dia) {
        this.txtDia.setText(dia);
    }

    public void setTxtMes(String mes) {
        this.txtMes.setText(mes);
    }

    public void setTxtAnio(String anio) {
        this.txtAnio.setText(anio);
    }

    public void setTxtSexo(String sexo) {
        this.txtSexo.setText(sexo);
    }

    public void setTxtTipo(String tipo) {
        this.txtTipo.setText(tipo);
    }



}
