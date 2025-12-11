package store.ui.dialogs;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import store.models.Client;
import store.services.ClientService;
import store.utils.SHA256Hashing;

public class NewClientDialog extends JDialog {
    //Dependences
    ClientService clientService;

    public NewClientDialog(ClientService clientService) {
        super(null, ModalityType.APPLICATION_MODAL);

        this.clientService = clientService;

        setTitle("Nuevo cliente");

        /* Limits and Layout*/
        setBounds(200,200,400,200);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Nuevo Cliente"));
        panel.setLayout(new GridLayout(0,1));

        panel.add(new JLabel("Nombre:"));
        JTextField nameField = new JTextField(20);
        panel.add(nameField);

        panel.add(new JLabel("Identificación:"));
        JTextField identificationField = new JTextField(8);
        panel.add(identificationField);

        panel.add(new JLabel(""));

        JButton addButton = new JButton("Agregar");
        panel.add(addButton);

        add(panel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Realizar validaciones de tipos de dato y de valores
                // y agregar el nuevo cliente al servicio
                String fullName = nameField.getText().toString();
                String identification = identificationField.getText().toString();

                if (fullName.isEmpty()) {
                    // Ventana de diálogo con un mensaje de error
                    JOptionPane.showMessageDialog(
                            NewClientDialog.this,
                            "Debes escribir el nombre del cliente",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                } else {
                    if (identification.isEmpty()) {
                        // Ventana de diálogo con un mensaje de error
                        JOptionPane.showMessageDialog(
                                NewClientDialog.this,
                                "Debes escribir el número de identificación del cliente",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    } else {
                        // Agregar el nuevo cliente al servicio
                        clientService.addClient(
                            new Client(
                                SHA256Hashing.generateRandomHash(), 
                                fullName, 
                                identification
                            )
                        );

                        // Cerrar el diálogo
                        dispose();
                    }
                }
            }
        });
    }
}
