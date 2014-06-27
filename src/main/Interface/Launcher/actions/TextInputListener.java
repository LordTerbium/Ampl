package main.Interface.Launcher.actions;

import main.Interface.Launcher.LauncherFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Max on 27.06.2014.
 */
public class TextInputListener implements ActionListener
{

    @Override
    public void actionPerformed ( ActionEvent e )
    {
        if(e.getActionCommand().equals( "user" )){
            JTextField user =(JTextField) e.getSource();
            if(!user.getText().isEmpty()){
                LauncherFrame.instance().getBottomBar().getAccountConfiguration().getBtnAdd().setEnabled( true );
            }
        }
    }
}
