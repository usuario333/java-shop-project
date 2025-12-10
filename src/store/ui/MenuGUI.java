package store.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGUI extends JFrame {
    //Panels
    JPanel welcomePanel;
    JPanel middlePanel;
    JPanel middleMenuPanel;
    JPanel middleContentPanel;

    JPanel clientsPanel;
    JPanel productsPanel;
    JPanel ordersPanel;

    //Buttons
    JButton clientsButton;
    JButton productsButton;
    JButton ordersButton;

    public MenuGUI() {
        initWindow();
    }

    private void initWindow() {
        setTitle("Sistema Gestión de Pedidos para una Tienda");
        setSize(900, 600); //Setting the window size to 900 * 600px

        //Clicking the close button exits the program
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initPanels(); //Show and configure initial conditions for the Menu
        initActions(); //Configure actions for buttons

        setVisible(true); //Show the main window
    }

    private void initPanels() {
        // Init the content pane and layout type
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout()); //Delimit the window in 3 sections

        /* TOP SECTION */
        JPanel topPanel = new JPanel(new GridLayout(1, 3)); //1 row, 3 columns

        //Buttons
        clientsButton = new JButton("Clientes");
        productsButton = new JButton("Productos");
        ordersButton = new JButton("Órdenes");

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
        welcomePanel = new JPanel(new BorderLayout());

        //Welcome Label and Panel
        JLabel welcomeLabel = new JLabel("Bienvenido al Sistema Gestión" +
                " de Pedidos para una Tienda", JLabel.LEFT);

        //Add Welcome label to the panel in middle section
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);

        //Menu and Content Panels
        middlePanel = new JPanel(new GridLayout(0, 2)); //1 row, 2 columns
        middleMenuPanel = new JPanel(new GridLayout(0, 1));
        middleContentPanel = new JPanel(new BorderLayout());

        middleMenuPanel.add(new JPanel());

        //Clients panel
        clientsPanel = new JPanel();
        clientsPanel.setBorder(BorderFactory.createTitledBorder("Clientes"));
        clientsPanel.setBackground(Color.YELLOW);

        //Products panel
        productsPanel = new JPanel();
        productsPanel.setBorder(BorderFactory.createTitledBorder("Productos"));
        productsPanel.setBackground(Color.CYAN);

        //Orders panel
        ordersPanel = new JPanel();
        ordersPanel.setBorder(BorderFactory.createTitledBorder("Órdenes"));
        ordersPanel.setBackground(Color.GREEN);

        middleContentPanel.add(welcomePanel);

        //Add menu and content panels to the middle panel
        GridBagConstraints constraints = ConfigureConstraints.getConstrains();
        constraints.gridx = 1;
        constraints.weightx = 0.3;
        middlePanel.add(middleMenuPanel, constraints);

        constraints.gridx = 2;
        constraints.weightx = 0.7;
        middlePanel.add(middleContentPanel, constraints);

        //Add middle panel
        contentPane.add(middlePanel, BorderLayout.CENTER);

        /* BOTTOM SECTION */
        JPanel bottomPanel = new JPanel(new FlowLayout()); //Each component next to the another

        //Labels
        JLabel bottomLabel = new JLabel("Copyright @usuario33", JLabel.CENTER);
        bottomPanel.add(bottomLabel);

        //Add bottom panel
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
    }

    public void initActions() {
        clientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clients button");
                welcomePanel.setVisible(false);
                productsPanel.setVisible(false);
                ordersPanel.setVisible(false);
                middleContentPanel.remove(welcomePanel);
                middleContentPanel.remove(productsPanel);
                middleContentPanel.remove(ordersPanel);

                clientsPanel.setVisible(true);
                middleContentPanel.add(clientsPanel);
            }
        });

        productsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Products button");
                welcomePanel.setVisible(false);
                clientsPanel.setVisible(false);
                ordersPanel.setVisible(false);
                middleContentPanel.remove(welcomePanel);
                middleContentPanel.remove(clientsPanel);
                middleContentPanel.remove(ordersPanel);

                productsPanel.setVisible(true);
                middleContentPanel.add(productsPanel);
            }
        });

        ordersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Orders button");
                welcomePanel.setVisible(false);
                clientsPanel.setVisible(false);
                productsPanel.setVisible(false);
                middleContentPanel.remove(welcomePanel);
                middleContentPanel.remove(clientsPanel);
                middleContentPanel.remove(productsPanel);

                ordersPanel.setVisible(true);
                middleContentPanel.add(ordersPanel);
            }
        });
    }
}
