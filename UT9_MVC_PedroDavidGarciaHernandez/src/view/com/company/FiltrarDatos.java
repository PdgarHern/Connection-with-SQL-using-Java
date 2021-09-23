package view.com.company;

import Connecion.ConectionBD;
import model.com.company.ModelEntrada;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;

public class FiltrarDatos extends JDialog {
    private Statement stmt;
    private JPanel contentPane;
    private JButton btnAceptar;
    private JButton btnVerTodos;
    private JButton btnCancelar;
    private JLabel LNif;
    private JTextField txtNif;
    private JLabel LNombre;
    private JLabel LApellidos;
    private JLabel LCiudad;
    private JLabel LDireccion;
    private JLabel LTlfno;
    private JLabel LFecha;
    private JLabel LSexo;
    private JLabel LTipo;
    private JComboBox comboFecha;
    private JTextField txtDia;
    private JTextField txtMes;
    private JTextField txtAnio;
    private JTextField txtApellido1;
    private JTextField txtApellido2;
    private JTextField txtCiudad;
    private JTextField txtDireccion;
    private JTextField txtTlfno;
    private JTextField txtSexo;
    private JTextField txtTipo;
    private JTextField txtNombre;
    private JComboBox cboCamposOrdenacion;
    private JRadioButton radioASC;
    private JRadioButton radioDESC;
    private JPanel panelOrdenacion;

    private JTable table1;
    private DefaultTableModel modelo = new DefaultTableModel();

    public FiltrarDatos(JTable t) {
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

    public void onAccept() {
        String nif,nombre,apell1,apell2,ciudad,direccion,tlfno,fechaNac,sexo,tipo;
        String cboFecha;
        String sql = "select * from persona ";
        int numCond = 0;

        String[] titulos = {"NIF", "Nombre", "Apellido1", "Apellido2", "Ciudad", "Dirección", "Teléfono", "Fecha Nacimiento", "Sexo", "Tipo"};
        DefaultTableModel m = new DefaultTableModel(null,titulos);

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
            cboFecha = (String) comboFecha.getSelectedItem();

            if (!nif.equals("")) {
                numCond++;
                sql += " where nif like '%"+ nif +"%' ";

            }
            if (!nombre.equals("")) {
                numCond++;

                if (numCond == 1) {
                    sql += " where nombre like '%"+ nombre +"%' ";

                } else {
                    sql += " and nombre like '%"+ nombre +"%' ";

                }

            }
            if (!apell1.equals("")) {
                numCond++;

                if (numCond == 1) {
                    sql += " where apellido1 like '%"+ apell1 +"%' ";

                } else {
                    sql += " and apellido1 like '%"+ apell1 +"%' ";

                }

            }
            if (!apell2.equals("")) {
                numCond++;

                if (numCond == 1) {
                    sql += " where apellido2 like '%"+ apell2 +"%' ";

                } else {
                    sql += " and apellido2 like '%"+ apell2 +"%' ";

                }

            }
            if (!ciudad.equals("")) {
                numCond++;

                if (numCond == 1) {
                    sql += " where ciudad like '%"+ ciudad +"%' ";

                } else {
                    sql += " and ciudad like '%"+ ciudad +"%' ";

                }

            }
            if (!direccion.equals("")) {
                numCond++;

                if (numCond == 1) {
                    sql += " where direccion like '%"+ direccion +"%' ";

                } else {
                    sql += " and direccion like '%"+ direccion +"%' ";

                }

            }
            if (!tlfno.equals("")) {
                numCond++;

                if (numCond == 1) {
                    sql += " where telefono like '%"+ tlfno +"%' ";

                } else {
                    sql += " and telefono like '%"+ tlfno +"%' ";

                }

            }
            if (!fechaNac.equals("--")) {
                numCond++;

                if (numCond == 1) {
                    sql += " where fecha_nacimiento "+ cboFecha + "'" + fechaNac +"' ";

                } else {
                    sql += " and fecha_nacimiento "+ cboFecha + "'" + fechaNac +"' ";

                }

            }
            if (!sexo.equals("")) {
                numCond++;

                if (numCond == 1) {
                    sql += " where sexo like '%"+ sexo +"%' ";

                } else {
                    sql += " and sexo like '%"+ sexo +"%' ";

                }

            }
            if (!tipo.equals("")) {
                numCond++;

                if (numCond == 1) {
                    sql += " where tipo like '%"+ tipo +"%' ";

                } else {
                    sql += " and tipo like '%"+ tipo +"%' ";

                }

            }

            String campoOrden = (String) cboCamposOrdenacion.getSelectedItem();
            if (!campoOrden.equals("(Orden de registro)")) {
                if (campoOrden.equals("Apellidos")) {
                    if (radioASC.isSelected()) {
                        sql += "order by apellido1 asc, apellido2 asc";

                    } else {
                        sql += "order by apellido1 desc, apellido2 desc";

                    }

                } else if (campoOrden.equals("Dirección")) {
                    if (radioASC.isSelected()) {
                        sql += "order by direccion asc";

                    } else {
                        sql += "order by direccion desc";

                    }

                } else if (campoOrden.equals("Teléfono")) {
                    if (radioASC.isSelected()) {
                        sql += "order by telefono asc";

                    } else {
                        sql += "order by telefono desc";

                    }

                } else if (campoOrden.equals("Fecha de nacimiento")) {
                    if (radioASC.isSelected()) {
                        sql += "order by fecha_nacimiento asc";

                    } else {
                        sql += "order by fecha_nacimiento desc";

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

            String[] fila = new String[10];

            while (rs.next()) {
                fila[0] = rs.getString("nif");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("apellido1");
                fila[3] = rs.getString("apellido2");
                fila[4] = rs.getString("ciudad");
                fila[5] = rs.getString("direccion");
                fila[6] = rs.getString("telefono");
                fila[7] = rs.getString("fecha_nacimiento");
                fila[8] = rs.getString("sexo");
                fila[9] = rs.getString("tipo");
                m.addRow(fila);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al filtrar la tabla persona");
        }
        table1.setModel(m);
        dispose();

    }

    public void onCancel() {
        dispose();

    }

    public void onViewAll() {
        ModelEntrada entrada = new ModelEntrada();
        table1.setModel(entrada.CargaDatos(modelo));
        dispose();

    }

}
