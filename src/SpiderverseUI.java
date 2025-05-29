import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.LinkedList;

public class SpiderverseUI extends JFrame {
    private JPanel mainPanel;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JComboBox<String> cbPoder;
    private JComboBox<String> cbUniverso;
    private JComboBox<Integer> cbExperiencia;
    private JButton btnAgregar;
    private JButton btnBuscar;
    private JButton btnFiltrar;
    private JTable tablaHeroes;
    private JTextField txtBuscarCodigo;
    private JComboBox<String> cbFiltrarPoder;

    private SpiderverseHeroManager manager = new SpiderverseHeroManager();

    public SpiderverseUI() {
        setContentPane(mainPanel);
        setTitle("Registro de Héroes del Spiderverse");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        // ComboBox data
        cbPoder.setModel(new DefaultComboBoxModel<>(new String[]{
                "Sentido Arácnido", "Trepa Muros", "Fuerza Sobrehumana", "Agilidad Mejorada", "Tejido de Telaraña"
        }));

        cbFiltrarPoder.setModel(cbPoder.getModel());

        cbUniverso.setModel(new DefaultComboBoxModel<>(new String[]{
                "Tierra-616", "Tierra-1610", "Tierra-12041", "Tierra-90214", "Tierra-138"
        }));

        cbExperiencia.setModel(new DefaultComboBoxModel<>(new Integer[]{1, 2, 3, 4, 5}));

        actualizarTabla(manager.getHeroes());

        btnAgregar.addActionListener(e -> {
            try {
                int codigo = Integer.parseInt(txtCodigo.getText());
                String nombre = txtNombre.getText();
                String poder = (String) cbPoder.getSelectedItem();
                String universo = (String) cbUniverso.getSelectedItem();
                int experiencia = (Integer) cbExperiencia.getSelectedItem();

                SpiderverseHero heroe = new SpiderverseHero(codigo, nombre, poder, universo, experiencia);
                boolean exito = manager.agregarHeroe(heroe);

                if (exito) {
                    JOptionPane.showMessageDialog(this, "Héroe agregado con éxito");
                    actualizarTabla(manager.getHeroes());
                } else {
                    JOptionPane.showMessageDialog(this, "Error: Código ya registrado");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Código inválido");
            }
        });

        btnBuscar.addActionListener(e -> {
            try {
                int codigo = Integer.parseInt(txtBuscarCodigo.getText());
                SpiderverseHero h = manager.buscarPorCodigo(codigo);
                if (h != null) {
                    txtCodigo.setText(String.valueOf(h.getCodigo()));
                    txtNombre.setText(h.getNombre());
                    cbPoder.setSelectedItem(h.getPoderEspecial());
                    cbUniverso.setSelectedItem(h.getUniverso());
                    cbExperiencia.setSelectedItem(h.getNivelExperiencia());
                } else {
                    JOptionPane.showMessageDialog(this, "Héroe no encontrado");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Código inválido");
            }
        });

        btnFiltrar.addActionListener(e -> {
            String poder = (String) cbFiltrarPoder.getSelectedItem();
            LinkedList<SpiderverseHero> filtrados = manager.filtrarYOrdenar(poder);
            actualizarTabla(filtrados);
        });
    }

    private void actualizarTabla(LinkedList<SpiderverseHero> lista) {
        String[] columnas = {"Código", "Nombre", "Poder Especial", "Universo", "Nivel de Experiencia"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        for (SpiderverseHero h : lista) {
            modelo.addRow(new Object[]{
                    h.getCodigo(), h.getNombre(), h.getPoderEspecial(), h.getUniverso(), h.getNivelExperiencia()
            });
        }
        tablaHeroes.setModel(modelo);
    }

    public static void main(String[] args) {
        new SpiderverseUI();
    }
}
