// * This code was generated with love by Window Builder VS Code extension. * //

import javax.swing.*;
import java.awt.*;
import Unicorn.UnicornLookAndFeel;

public class GUI {
  public static void main(String[] args) {

    try {
      UIManager.setLookAndFeel(new UnicornLookAndFeel());
    } catch (Exception e) {
      e.printStackTrace();
    }

    JFrame frame = new JFrame("Window builder!");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(602, 400);

    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();

    // Fill the layout with placeholders
    for (int row = 0; row < 21; row++) {
      for (int col = 0; col < 15; col++) {
        constraints.gridx = col;
        constraints.gridy = row;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;

        Component filler = Box.createRigidArea(new Dimension(0, 0));
        panel.add(filler, constraints);
      }
    }

    constraints.weightx = 0;
    constraints.weighty = 0;
    constraints.fill = GridBagConstraints.NONE;
    constraints.anchor = GridBagConstraints.NORTHWEST;

    JButton button_0 = new JButton("Button");
    button_0.setPreferredSize(new Dimension(55, 27));

    constraints.gridx = -2;
    constraints.gridy = 10;
    constraints.gridwidth = 2;
    constraints.gridheight = 2;
    panel.add(button_0, constraints);

    frame.add(panel);
    frame.setVisible(true);
  }
}
