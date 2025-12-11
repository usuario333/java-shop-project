package store.ui.dialogs;

import store.services.OrderService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewOrderDialog extends JDialog {
    //Dependences
    OrderService orderService;

    public NewOrderDialog(OrderService orderService) {
        super(null, ModalityType.APPLICATION_MODAL);

        this.orderService = orderService;

        setTitle("Nuevo pedido");

        /* Limits and Layout*/
        setBounds(200, 200, 400, 200);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Datos del pedido"));
        panel.setLayout(new GridLayout(0, 1));

        panel.add(new JLabel("Cliente:"));
        JTextField nameField = new JTextField(20);
        panel.add(nameField);

        panel.add(new JLabel(""));

        JButton addButton = new JButton("Agregar");
        panel.add(addButton);

        add(panel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Realizar validaciones de tipos de dato y de valores
                // y agregar la nueva orden al servicio
                String fullName = nameField.getText().toString();

                if (fullName.isEmpty()) {
                    // Ventana de diálogo con un mensaje de error
                    JOptionPane.showMessageDialog(
                            NewOrderDialog.this,
                            "Debes escribir el nombre del cliente",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                } else {
                    // Agregar la nueva orden al servicio
                    /*orderService.addOrder(
                            new Order(
                                    SHA256Hashing.generateRandomHash(),
                                    fullName,
                                    identification
                            )
                    );*/

                    // Cerrar el diálogo
                    dispose();
                }
            }
        });
    }
}
