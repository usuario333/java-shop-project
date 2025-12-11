package store.ui.panels;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import store.models.Product;
import store.services.ProductService;
import store.ui.dialogs.NewProductDialog;

public class ProductsPanel extends JPanel {
    //Dependences
    ProductService productService;

    //Table
    DefaultTableModel tableModel;
    
    //Table Columns
    String[] columns = {"Referencia", "Nombre", "Precio", "Categoría"};

    //Buttons
    JButton addRowButton;

    public ProductsPanel(ProductService productService) {
        this.productService = productService;

        setBorder(BorderFactory.createTitledBorder("Productos"));
        setBackground(Color.WHITE);

        initPanels();
        initActions();

        fillTable();
    }

    private void initPanels() {
        setLayout(new BorderLayout());

        // MIDDLE TABLE
        tableModel = new DefaultTableModel(columns, 0);

        JTable mainTable = new JTable(tableModel);
        mainTable.setEnabled(false);

        JScrollPane scrollTable = new JScrollPane(mainTable);

        mainTable.setBackground(Color.CYAN);
        add(scrollTable, BorderLayout.CENTER);

        // BOTTOM BUTTONS
        JPanel bottomPanel = new JPanel(new FlowLayout());
        addRowButton = new JButton("Agregar producto");
        bottomPanel.add(addRowButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void fillTable() {
        tableModel.setRowCount(0);
        for (Product product : productService.getProducts().values()) {
            tableModel.addRow(new Object[]{
                product.getReference(),
                product.getName(),
                product.getPrice(),
                product.getCategory().getName()
            });
        }
    }

    public void initActions() {
        addRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewProductDialog(productService).setVisible(true);
                // Refrescar la tabla
                fillTable(); // Rellenar la tabla nuevamente
            }
        });
    }
}
