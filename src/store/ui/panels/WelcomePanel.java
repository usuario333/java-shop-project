package store.ui.panels;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomePanel extends JPanel {
    public WelcomePanel() {
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Bienvenido al Sistema Gestión" +
                " de Pedidos para una Tienda", JLabel.CENTER);
        add(welcomeLabel, BorderLayout.CENTER);
    }
}
