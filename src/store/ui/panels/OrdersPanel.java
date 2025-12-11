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
import java.sql.Date;

import store.models.Order;
import store.services.OrderService;

public class OrdersPanel extends JPanel {
    //Dependences
    OrderService orderService;

    //Table
    DefaultTableModel tableModel;
    
    //Table Columns
    String[] columns = {"Fecha", "Cliente", "Estado", "Total"};

    public OrdersPanel(OrderService orderService) {
        this.orderService = orderService;

        setBorder(BorderFactory.createTitledBorder("Pedidos"));
        setBackground(Color.WHITE);

        initPanels();

        fillTable();
    }

    private void initPanels() {
        setLayout(new BorderLayout());

        // MIDDLE TABLE
        tableModel = new DefaultTableModel(columns, 0);

        JTable mainTable = new JTable(tableModel);
        mainTable.setEnabled(false);

        JScrollPane scrollTable = new JScrollPane(mainTable);

        mainTable.setBackground(Color.GREEN);
        add(scrollTable, BorderLayout.CENTER);

        // BOTTOM BUTTONS
        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton addRowButton = new JButton("Agregar pedido");
        bottomPanel.add(addRowButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void fillTable() {
        tableModel.setRowCount(0);
        for (Order order : orderService.getOrders().values()) {
            tableModel.addRow(new Object[]{
                new Date(order.getCreatedAt()).toString(),
                order.getClient().getFullName(),
                order.getCurrentStatus(),
                order.getTotal()
            });
        }
    }
}
