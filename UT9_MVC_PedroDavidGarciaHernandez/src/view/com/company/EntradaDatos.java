package view.com.company;

import Connecion.ConectionBD;
import Controler.com.company.ControllerEntrada;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Statement;

public class EntradaDatos extends JDialog {
    private Statement stmt;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel LNif;
    private JTextField txtNif;
    private JLabel LNombre;
    private JTextField txtNombre;
    private JLabel LApellidos;
    private JLabel LCiudad;
    private JTextField txtCiudad;
    private JLabel LTipo;
    private JTextField txtTipo;
    private JLabel LFecha;
    private JTextField txtDia;
    private JTextField txtMes;
    private JTextField txtAnio;
    private JTextField txtApellido1;
    private JTextField txtApellido2;
    private JLabel LDireccion;
    private JTextField txtDireccion;
    private JTextField txtTlfno;
    private JLabel LTlfno;
    private JLabel LSexo;
    private JTextField txtSexo;

    public EntradaDatos() {
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

    private void onOK() {
        String nif,nombre,apell1,apell2,ciudad,direc,tlfno,fechaNac,sexo,tipo;
        String sql;

        try {
            nif = txtNif.getText();
            nombre = txtNombre.getText();
            apell1 = txtApellido1.getText();
            apell2 = txtApellido2.getText();
            ciudad = txtCiudad.getText();
            direc = txtDireccion.getText();
            tlfno = txtTlfno.getText();
            fechaNac = txtAnio.getText() + "-" + txtMes.getText() + "-" + txtDia.getText();
            sexo = txtSexo.getText();
            tipo = txtTipo.getText();

            sql = "insert into persona values(default,'"+ nif +"','"+ nombre +"','"+ apell1 +"','"+ apell2 +"','"+
                    ciudad +"','"+ direc +"','"+ tlfno +"','"+ fechaNac +"','"+ sexo +"','"+ tipo +"')";

            stmt = ConectionBD.getStmt();
            stmt.executeUpdate(sql);
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al introducir la nueva persona");

        }

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        EntradaDatos dialog = new EntradaDatos();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
