package store.ui.panels;

import store.models.Order;
import store.services.ClientService;
import store.services.OrderService;
import store.services.ProductService;
import store.ui.dialogs.NewOrderDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class OrdersPanel extends JPanel {
    //Dependences
    OrderService orderService;
    ClientService clientService;
    ProductService productService;

    //Table
    DefaultTableModel tableModel;

    //Table Columns
    String[] columns = {"Fecha", "Cliente", "Estado", "Total"};

    //Buttons
    JButton addRowOrderButton;

    public OrdersPanel(OrderService orderService,
                       ClientService clientService,
                       ProductService productService) {
        this.orderService = orderService;
        this.clientService = clientService;
        this.productService = productService;

        setBorder(BorderFactory.createTitledBorder("Pedidos"));
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

        mainTable.setBackground(Color.GREEN);
        add(scrollTable, BorderLayout.CENTER);

        // BOTTOM BUTTONS
        JPanel bottomPanel = new JPanel(new FlowLayout());
        addRowOrderButton = new JButton("Agregar pedido");

        bottomPanel.add(addRowOrderButton);

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

    public void initActions() {
        addRowOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewOrderDialog dialog = new NewOrderDialog(orderService);
                dialog.setVisible(true);

                fillTable(); // Rellenar la tabla nuevamente
            }
        });
    }
}
