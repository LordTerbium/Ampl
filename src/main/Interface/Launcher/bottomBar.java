package main.Interface.Launcher;

import javax.swing.*;
import java.awt.*;

public class bottomBar extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public bottomBar() {

        Dimension preferredSize = new Dimension(400, 80);
        setPreferredSize(preferredSize);
        BorderLayout LAYOUT = new BorderLayout();
        setLayout(LAYOUT);

        JPanel leftP = new JPanel();
        leftP.setLayout(new FlowLayout(FlowLayout.RIGHT, 40, 15));
        leftP.setPreferredSize(new Dimension(120, 40));
        leftP.setVisible(true);

        JButton btnPlay = new JButton("Play");
        btnPlay.setMinimumSize(new Dimension(50, 15));
        btnPlay.setPreferredSize(new Dimension(80, 30));
        btnPlay.setVisible(true);
        leftP.add(btnPlay);

        JPanel centerP = new JPanel();
        centerP.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerP.setPreferredSize(new Dimension(80, 40));
        centerP.setVisible(true);

        JTextField txtfUser = new JTextField(15);
        txtfUser.setMinimumSize(new Dimension(50, 30));
        txtfUser.setPreferredSize(new Dimension(40, 30));
        txtfUser.setVisible(true);
        centerP.add(txtfUser);

        add(leftP, BorderLayout.EAST);
        add(centerP, BorderLayout.CENTER);

        setVisible(true);
    }

}
