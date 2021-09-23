package view.com.company;

import Connecion.ConectionBD;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Statement;

public class ActualizarAsig extends JDialog {
    private Statement stmt;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel LId;
    private JTextField txtId;
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

    public ActualizarAsig() {
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
        String idA,nombreA,creditos,tipoA,curso,cuatrimestre,id_profesor,id_grado;
        String sql;

        try {
            idA = txtId.getText();
            nombreA = txtNombre.getText();
            creditos = txtCreditos.getText();
            tipoA = txtTipo.getText();
            curso = txtCurso.getText();
            cuatrimestre = txtCuatrimestre.getText();
            id_profesor = txtIdProfesor.getText();
            id_grado = txtIdGrado.getText();

            sql = "update asignatura set nombre = '"+ nombreA +"',"+
                    "creditos = "+ creditos +","+
                    "tipo = '"+ tipoA +"',"+
                    "curso = "+ curso +","+
                    "cuatrimestre = "+ cuatrimestre +","+
                    "id_profesor = "+ id_profesor +","+
                    "id_grado = "+ id_grado +" where id = "+ idA;

            stmt = ConectionBD.getStmt();
            stmt.executeUpdate(sql);
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al modificar la asignatura");
        }

    }

    public void onCancel() {
        dispose();
    }

    public void setTxtId(String idA) {
        this.txtId.setText(idA);
    }

    public void setTxtNombre(String nombre) {this.txtNombre.setText(nombre);}

    public void setTxtCreditos(String creditos) {
        this.txtCreditos.setText(creditos);
    }

    public void setTxtTipo(String tipo) {
        this.txtTipo.setText(tipo);
    }

    public void setTxtCurso(String curso) {
        this.txtCurso.setText(curso);
    }

    public void setTxtCuatrimestre(String cuatrimestre) {
        this.txtCuatrimestre.setText(cuatrimestre);
    }

    public void setTxtIdProfesor(String idProfesor) {
        this.txtIdProfesor.setText(idProfesor);
    }

    public void setTxtIdGrado(String idGrado) {
        this.txtIdGrado.setText(idGrado);
    }

}
