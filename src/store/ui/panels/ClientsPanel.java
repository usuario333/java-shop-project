package store.ui.panels;

import store.models.Client;
import store.services.ClientService;
import store.ui.dialogs.NewClientDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientsPanel extends JPanel {
    //Dependences
    ClientService clientService;

    //Table
    DefaultTableModel tableModel;

    //Table Columns
    String[] tableColumns = {"Nombre", "Identification"};

    //Buttons
    JButton addRowClientButton;

    public ClientsPanel(ClientService clientService) {
        this.clientService = clientService;

        setBorder(BorderFactory.createTitledBorder("Clientes"));
        setBackground(Color.WHITE);

        initPanels();
        initActions();

        fillTable();
    }

    private void initPanels() {
        setLayout(new BorderLayout());

        // MIDDLE TABLE
        tableModel = new DefaultTableModel(tableColumns, 0);

        JTable mainTable = new JTable(tableModel);
        mainTable.setEnabled(false);

        JScrollPane scrollTable = new JScrollPane(mainTable);

        mainTable.setBackground(Color.YELLOW);
        add(scrollTable, BorderLayout.CENTER);

        // BOTTOM BUTTONS
        JPanel bottomPanel = new JPanel(new FlowLayout());
        addRowClientButton = new JButton("Agregar cliente");

        bottomPanel.add(addRowClientButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void fillTable() {
        tableModel.setRowCount(0);
        for (Client client : clientService.getClients().values()) {
            tableModel.addRow(new Object[]{
                    client.getFullName(),
                    client.getIdentification()
            });
        }
    }

    public void initActions() {
        addRowClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewClientDialog dialog = new NewClientDialog(clientService);
                dialog.setVisible(true);

                fillTable(); // Rellenar la tabla nuevamente
            }
        });
    }
}
