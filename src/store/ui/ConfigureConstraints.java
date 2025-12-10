package store.ui;

import java.awt.*;

public class ConfigureConstraints {

    public static GridBagConstraints getConstrains() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;

        return c;
    }
}
