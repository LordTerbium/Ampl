package main.Interface.Launcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class bottomBar extends JPanel implements ActionListener
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public bottomBar ()
    {

        Dimension preferredSize = new Dimension( 400, 80 );
        setPreferredSize( preferredSize );
        BorderLayout LAYOUT = new BorderLayout();
        setLayout( LAYOUT );

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout( new FlowLayout( FlowLayout.RIGHT, 40, 15 ) );
        btnPanel.setPreferredSize( new Dimension( 120, 40 ) );
        btnPanel.setVisible( true );

        JButton btnPlay = new JButton( "Play" );
        btnPlay.setMinimumSize( new Dimension( 50, 15 ) );
        btnPlay.setPreferredSize( new Dimension( 80, 30 ) );
        btnPlay.setVisible( true );
        btnPlay.addActionListener( this );
        btnPanel.add( btnPlay );

        JPanel usrPanel = new JPanel();
        usrPanel.setLayout( new FlowLayout( FlowLayout.CENTER ) );
        usrPanel.setPreferredSize( new Dimension( 80, 40 ) );
        usrPanel.setVisible( true );

        JComboBox usrCmb = new JComboBox();
        usrCmb.setMinimumSize( new Dimension( 50, 30 ) );
        usrCmb.setPreferredSize( new Dimension( 40, 30 ) );
        usrCmb.setVisible( true );
        usrPanel.add( usrCmb );

        add( btnPanel, BorderLayout.EAST );
        add( usrPanel, BorderLayout.WEST );

        setVisible( true );
    }

    @Override
    public void actionPerformed ( ActionEvent event )
    {

        new AccountConfiguration();
    }

}
