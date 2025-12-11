package store.ui.dialogs;

import store.models.Product;
import store.models.ProductCategory;
import store.services.ProductService;
import store.utils.SHA256Hashing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewProductDialog extends JDialog {
    //Dependences
    ProductService productService;

    public NewProductDialog(ProductService productService) {
        super(null, ModalityType.APPLICATION_MODAL);

        this.productService = productService;

        setTitle("Nuevo producto");

        /* Limits and Layout*/
        setBounds(200, 200, 400, 400);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Datos del producto"));
        panel.setLayout(new GridLayout(0, 1));

        panel.add(new JLabel("Referencia:"));
        JTextField referenceField = new JTextField(8);
        panel.add(referenceField);

        panel.add(new JLabel("Nombre:"));
        JTextField nameField = new JTextField(20);
        panel.add(nameField);

        panel.add(new JLabel("Precio:"));
        JTextField priceField = new JTextField(8);
        panel.add(priceField);

        panel.add(new JLabel("Categoría:"));
        JTextField categoryField = new JTextField(8);
        panel.add(categoryField);

        panel.add(new JLabel(""));

        JButton addButton = new JButton("Agregar");
        panel.add(addButton);

        add(panel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Realizar validaciones de tipos de dato y de valores
                // y agregar el nuevo producto al servicio
                String reference = referenceField.getText().toString();
                String name = nameField.getText().toString();
                double price = Double.parseDouble(priceField.getText().toString());
                String category = categoryField.getText().toString();

                if (reference.isEmpty()) {
                    // Ventana de diálogo con un mensaje de error
                    JOptionPane.showMessageDialog(
                            NewProductDialog.this,
                            "Debes escribir la referencia del producto",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                } else if (name.isEmpty()) {
                    // Ventana de diálogo con un mensaje de error
                    JOptionPane.showMessageDialog(
                            NewProductDialog.this,
                            "Debes escribir el nombre del producto",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                } else if (price <= 0) {
                    // Ventana de diálogo con un mensaje de error
                    JOptionPane.showMessageDialog(
                            NewProductDialog.this,
                            "El precio del producto debe ser mayor a 0",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                } else if (category.isEmpty()) {
                    // Ventana de diálogo con un mensaje de error
                    JOptionPane.showMessageDialog(
                            NewProductDialog.this,
                            "Debes escribir la categoría del producto",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                } else {
                    // Agregar el nuevo cliente al servicio
                    productService.addProduct(
                            new Product(
                                    SHA256Hashing.generateRandomHash(),
                                    reference,
                                    name,
                                    price,
                                    new ProductCategory(SHA256Hashing.generateRandomHash(), category)
                            )
                    );

                    // Cerrar el diálogo
                    dispose();
                }
            }
        });
    }
}