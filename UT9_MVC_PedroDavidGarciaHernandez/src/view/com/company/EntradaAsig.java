package view.com.company;

import Connecion.ConectionBD;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Statement;

public class EntradaAsig extends JDialog {
    private Statement stmt;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel LNombre;
    private JTextField txtNombre;
    private JLabel LCreditos;
    private JTextField txtCreditos;
    private JLabel LTipo;
    private JTextField txtTipo;
    private JLabel LCurso;
    private JTextField txtCurso;
    private JLabel LCuatrimestre;
    private JTextField txtCuatrimestre;
    private JLabel LIdProfesor;
    private JTextField txtIdProfesor;
    private JLabel LIdGrado;
    private JTextField txtIdGrado;

    public EntradaAsig() {
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
        String nombreA,creditos,tipoA,curso,cuatrimestre,id_profesor,id_grado;
        String sql;

        try {
            nombreA = txtNombre.getText();
            creditos = txtCreditos.getText();
            tipoA = txtTipo.getText();
            curso = txtCurso.getText();
            cuatrimestre = txtCuatrimestre.getText();
            id_profesor = txtIdProfesor.getText();
            id_grado = txtIdGrado.getText();

            sql = "insert into asignatura values(default,'"+ nombreA +"',"+ creditos +",'"+ tipoA +"',"+ curso +","+
                    cuatrimestre +","+ id_profesor +","+ id_grado +")";

            stmt = ConectionBD.getStmt();
            stmt.executeUpdate(sql);
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al introducir la nueva asignatura");
            System.out.println(e.getMessage());

        }

    }

    public void onCancel() {
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
