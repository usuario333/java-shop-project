package store.ui.panels;

import store.models.Product;
import store.services.ProductService;
import store.ui.dialogs.NewProductDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductsPanel extends JPanel {
    //Dependences
    ProductService productService;

    //Table
    DefaultTableModel tableModel;

    //Table Columns
    String[] columns = {"Referencia", "Nombre", "Precio", "Categoría"};

    //Buttons
    JButton addRowProductButton;

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
        addRowProductButton = new JButton("Agregar producto");

        bottomPanel.add(addRowProductButton);

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
        addRowProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewProductDialog dialog = new NewProductDialog(productService);
                dialog.setVisible(true);

                fillTable(); // Rellenar la tabla nuevamente
            }
        });
    }
}
