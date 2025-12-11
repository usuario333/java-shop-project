package store.ui;

import store.models.*;
import store.services.ClientService;
import store.services.OrderService;
import store.services.ProductService;
import store.ui.panels.ClientsPanel;
import store.ui.panels.OrdersPanel;
import store.ui.panels.ProductsPanel;
import store.ui.panels.WelcomePanel;
import store.utils.SHA256Hashing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

public class MenuGUI extends JFrame {
    //Container
    Container contentPane;

    //Buttons
    JButton clientsButton;
    JButton productsButton;
    JButton ordersButton;

    //Services
    ClientService clientService = new ClientService();
    ProductService productService = new ProductService();
    OrderService orderService = new OrderService();

    public MenuGUI() {
        initWindow();
    }

    private void initWindow() {
        setTitle("Sistema Gestión de Pedidos para una Tienda");
        setSize(900, 600); //Setting the window size to 900 * 600px
        setLocation(100, 100); //Setting the window position to (100,100)px

        //Clicking the close button exits the program
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initPanels(); //Show and configure initial conditions for the Menu
        initActions(); //Configure actions for buttons
        initDefaultData(); //Add default data to services

        setVisible(true); //Show the main window
    }

    private void initPanels() {
        // Init the content pane and layout type
        contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout()); //Delimit the window in 3 sections

        /* TOP SECTION */
        JPanel topPanel = new JPanel(new GridLayout(1, 3)); //1 row, 3 columns

        //Buttons
        clientsButton = new JButton("Clientes");
        productsButton = new JButton("Productos");
        ordersButton = new JButton("Pedidos");

        //Stylish for buttons
        clientsButton.setBackground(Color.YELLOW);
        productsButton.setBackground(Color.CYAN);
        ordersButton.setBackground(Color.GREEN);

        //Add buttons to the top panel
        topPanel.add(clientsButton);
        topPanel.add(productsButton);
        topPanel.add(ordersButton);

        //Add top panel
        contentPane.add(topPanel, BorderLayout.NORTH);

        /* MIDDLE SECTION */
        //Add middle panel
        contentPane.add(new WelcomePanel(), BorderLayout.CENTER);

        /* BOTTOM SECTION */
        JPanel bottomPanel = new JPanel(new FlowLayout()); //Each component next to the another

        //Labels
        JLabel bottomLabel = new JLabel("Copyright @usuario333 2025", JLabel.CENTER);
        bottomPanel.add(bottomLabel);

        //Add bottom panel
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
    }

    public void initActions() {
        clientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeCenterPanel();

                contentPane.add(new ClientsPanel(clientService), BorderLayout.CENTER);
                contentPane.revalidate();
            }
        });

        productsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeCenterPanel();

                contentPane.add(new ProductsPanel(productService), BorderLayout.CENTER);
                contentPane.revalidate();
            }
        });

        ordersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeCenterPanel();

                contentPane.add(new OrdersPanel(orderService, clientService, productService),
                        BorderLayout.CENTER);
                contentPane.revalidate();
            }
        });
    }

    private void removeCenterPanel() {
        BorderLayout layout = (BorderLayout) contentPane.getLayout();
        contentPane.remove(layout.getLayoutComponent(BorderLayout.CENTER));
    }

    /* SET DEFAULT DATA FOR SERVICES */
    public void initDefaultData() {
        //CLIENTS
        Client client1 = new Client(
                SHA256Hashing.generateRandomHash(),
                "Facundo Garcés",
                "123456789"
        );
        Client client2 = new Client(
                SHA256Hashing.generateRandomHash(),
                "Mariano Días",
                "659874789"
        );
        Client client3 = new Client(
                SHA256Hashing.generateRandomHash(),
                "Lucas Boyé",
                "42653586453"
        );
        clientService.addClient(client1);
        clientService.addClient(client2);
        clientService.addClient(client3);

        //PRODUCTS
        ProductCategory category1 = new ProductCategory(
                SHA256Hashing.generateRandomHash(),
                "Frutas"
        );

        Product product1 = new Product(
                SHA256Hashing.generateRandomHash(),
                "JSDHFITW",
                "Manzana",
                2.0,
                category1
        );

        Product product2 = new Product(
                SHA256Hashing.generateRandomHash(),
                "BVCVERYR",
                "Pera",
                3.0,
                category1
        );

        Product product3 = new Product(
                SHA256Hashing.generateRandomHash(),
                "OPFGHIRT",
                "Naranja",
                2.5,
                category1
        );

        Product product4 = new Product(
                SHA256Hashing.generateRandomHash(),
                "ASDKIUO",
                "Plátano",
                1.5,
                category1
        );

        productService.addProduct(product1);
        productService.addProduct(product2);
        productService.addProduct(product3);
        productService.addProduct(product4);

        //ORDERS
        ItemOrder itemOrder1 = new ItemOrder(
                SHA256Hashing.generateRandomHash(),
                product1,
                1.0,
                2
        );
        ItemOrder itemOrder2 = new ItemOrder(
                SHA256Hashing.generateRandomHash(),
                product2,
                3.0,
                5
        );
        ItemOrder itemOrder3 = new ItemOrder(
                SHA256Hashing.generateRandomHash(),
                product3,
                1.5,
                7
        );

        Order order1 = new Order(
                SHA256Hashing.generateRandomHash(),
                Instant.now().toEpochMilli(),
                client1
        );
        order1.addItem(itemOrder1);

        Order order2 = new Order(
                SHA256Hashing.generateRandomHash(),
                Instant.now().toEpochMilli(),
                client2
        );
        order2.addItem(itemOrder2);
        order2.addItem(itemOrder3);

        orderService.addOrder(order1);
        orderService.addOrder(order2);
    }
}
