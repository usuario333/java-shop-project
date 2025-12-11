package store.ui.panels;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import store.models.Client;
import store.services.ClientService;
import store.ui.dialogs.NewClientDialog;

public class ClientsPanel extends JPanel {
    //Dependences
    ClientService clientService;

    //Table
    DefaultTableModel tableModel;
    
    //Table Columns
    String[] tableColumns = {"Nombre", "Identification"};

    //Buttons
    JButton addRowButton;
    
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
        addRowButton = new JButton("Agregar cliente");

        bottomPanel.add(addRowButton);
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
        addRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewClientDialog(clientService).setVisible(true);
                // Refrescar la tabla
                fillTable(); // Rellenar la tabla nuevamente
            }
        });
    }
}
