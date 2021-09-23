package view.com.company;

import Connecion.ConectionBD;
import model.com.company.ModelAsignaturas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

public class FiltrarAsig extends JDialog {
    private Statement stmt;
    private JPanel contentPane;
    private JButton btnAceptar;
    private JButton btnVerTodos;
    private JButton btnCancelar;
    private JComboBox cboCamposOrdenacion;
    private JPanel panelOrdenacion;
    private JRadioButton radioASC;
    private JRadioButton radioDESC;
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

    private JTable table1;
    private DefaultTableModel modelo = new DefaultTableModel();

    public FiltrarAsig(JTable t) {
        this.table1 = t;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnAceptar);

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {onCancel();}
        });

        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {onAccept();}
        });

        btnVerTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {onViewAll();}
        });

    }

    public void onCancel() {
        dispose();
    }

    public void onAccept() {
        String idA,nombreA,creditos,tipoA,curso,cuatrimestre,id_profesor,id_grado;
        String sql = "select * from asignatura ";
        int numCond = 0;

        String[] titulos = { "id","Nombre", "creditos", "Tipo", "Curso", "Cuatrimestre", "Id Profesor", "Id Grado"};
        DefaultTableModel m = new DefaultTableModel(null, titulos);

        try {
            idA = txtId.getText();
            nombreA = txtNombre.getText();
            creditos = txtCreditos.getText();
            tipoA = txtTipo.getText();
            curso = txtCurso.getText();
            cuatrimestre = txtCuatrimestre.getText();
            id_profesor = txtIdProfesor.getText();
            id_grado = txtIdGrado.getText();

            if (!idA.equals("")) {
                numCond++;
                sql += " where id = "+ idA;

            }
            if (!nombreA.equals("")) {
                numCond++;

                if (numCond == 1) {
                    sql += " where nombre like '%"+ nombreA +"%' ";

                } else {
                    sql += " and nombre like '%"+ nombreA +"%' ";

                }

            }
            if (!creditos.equals("")) {
                numCond++;

                if (numCond == 1) {
                    sql += " where creditos = "+ creditos;

                } else {
                    sql += " and creditos = "+ creditos;

                }

            }
            if (!tipoA.equals("")) {
                numCond++;

                if (numCond == 1) {
                    sql += " where tipo like '%"+ tipoA +"%' ";

                } else {
                    sql += " and tipo like '%"+ tipoA +"%' ";

                }

            }
            if (!curso.equals("")) {
                numCond++;

                if (numCond == 1) {
                    sql += " where curso = "+ curso;

                } else {
                    sql += " and curso = "+ curso;

                }

            }
            if (!cuatrimestre.equals("")) {
                numCond++;

                if (numCond == 1) {
                    sql += " where cuatrimestre = "+ cuatrimestre;

                } else {
                    sql += " and cuatrimestre = "+ cuatrimestre;

                }

            }
            if (!id_profesor.equals("")) {
                numCond++;

                if (numCond == 1) {
                    sql += " where id_profesor = "+ id_profesor;

                } else {
                    sql += " and id_profesor = "+ id_profesor;

                }

            }
            if (!id_grado.equals("")) {
                numCond++;

                if (numCond == 1) {
                    sql += " where id_grado = "+ id_grado;

                } else {
                    sql += " and id_grado = "+ id_grado;

                }

            }

            String campoOrden = (String) cboCamposOrdenacion.getSelectedItem();
            if (!campoOrden.equals("(Orden de registro)")) {
                if (campoOrden.equals("Créditos")) {
                    if (radioASC.isSelected()) {
                        sql += "order by creditos asc";

                    } else {
                        sql += "order by creditos desc";

                    }

                } else if (campoOrden.equals("Id Profesor")) {
                    if (radioASC.isSelected()) {
                        sql += "order by id_profesor asc";

                    } else {
                        sql += "order by id_profesor desc";

                    }

                } else if (campoOrden.equals("Id Grado")) {
                    if (radioASC.isSelected()) {
                        sql += "order by id_grado asc";

                    } else {
                        sql += "order by id_grado desc";

                    }

                } else {
                    if (radioASC.isSelected()) {
                        sql += "order by "+ campoOrden +" asc";

                    } else {
                        sql += "order by "+ campoOrden +" desc";

                    }

                }

            }

            stmt = ConectionBD.getStmt();
            ResultSet rs = stmt.executeQuery(sql);

            String[] fila = new String[9];

            while (rs.next()) {
                fila[0] = rs.getString("id");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("creditos");
                fila[3] = rs.getString("tipo");
                fila[4] = rs.getString("curso");
                fila[5] = rs.getString("cuatrimestre");
                fila[6] = rs.getString("id_profesor");
                fila[7] = rs.getString("id_grado");
                m.addRow(fila);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al filtrar la tabla asignatura");
            System.out.println(e.getMessage());
        }
        table1.setModel(m);
        dispose();

    }

    public void onViewAll() {
        ModelAsignaturas entrada = new ModelAsignaturas();
        table1.setModel(entrada.CargaDatos(modelo));
        dispose();

    }

}
