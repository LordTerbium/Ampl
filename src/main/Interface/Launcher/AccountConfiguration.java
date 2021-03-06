package main.Interface.Launcher;

import main.Interface.Launcher.actions.TextInputListener;
import main.Interface.Log.LogWrapper;
import main.Service.Authentication.Authentication;
import main.Service.Settings.InfoLauncher;
import main.Service.Settings.Player.Accounts;
import main.Service.Settings.Player.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * This class is a proper JOptionsPane Dialog to change settings about an account. Created by Max on 21.06.2014.
 */
public class AccountConfiguration extends JFrame implements ActionListener, ListSelectionListener
{

    private final JList<String>  usrName;
    private final JButton        btnDel;
    private final JTextField     usrNTxt;
    private final JPasswordField pwTxt;
    private final JButton        btnAdd;
    private LogWrapper               logger    = LogWrapper.getLogger( AccountConfiguration.class );
    private DefaultListModel<String> listModel = new DefaultListModel<String>();

    public AccountConfiguration ()
    {

        logger.debug( "Opening account settings." );

        setLocationRelativeTo( LauncherFrame.instance() );
        setTitle( "Accounts-manager" );
        Dimension MIN_SIZE = new Dimension( 400, 500 );
        setMinimumSize( MIN_SIZE );
        setMaximumSize( InfoLauncher.instance().getSize() );
        BorderLayout LAYOUT = new BorderLayout();
        setLayout( LAYOUT );

        logger.debug( "Properties set.<br> Adding panes." );

        usrName = new JList<String>( refreshListModel() );
        usrName.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        usrName.addListSelectionListener( this );
        usrName.setLayoutOrientation( JList.VERTICAL );
        usrName.setVisibleRowCount( - 1 );
        JScrollPane scrollPane = new JScrollPane( usrName, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scrollPane.setPreferredSize( new Dimension( 250, 80 ) );

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout( new BoxLayout( btnPanel, BoxLayout.PAGE_AXIS ) );

        ImageIcon icon = new ImageIcon( getClass().getResource( "/resources/tango-icon-theme-0.8.90/tango-icon-theme-0.8.90/32x32/actions/list-add.png" ) );
        btnAdd = new JButton( icon );
        btnAdd.addActionListener( this );
        btnAdd.setEnabled( false );

        btnAdd.setActionCommand( "add" );
        ImageIcon icon1 = new ImageIcon( getClass().getResource( "/resources/tango-icon-theme-0.8.90/tango-icon-theme-0.8.90/32x32/actions/list-remove.png" ) );
        btnDel = new JButton( icon1 );
        btnDel.addActionListener( this );
        btnDel.setActionCommand( "del" );
        btnDel.setEnabled( false );

        btnPanel.add( btnAdd );
        btnPanel.add( btnDel );

        JPanel dtPanel = new JPanel();
        dtPanel.setLayout( new BoxLayout( dtPanel, BoxLayout.PAGE_AXIS ) );
        dtPanel.setBackground( new Color( 117, 117, 117 ) );

        JPanel usrNPanel = new JPanel( new FlowLayout( FlowLayout.CENTER, 20, 20 ) );
        usrNPanel.setOpaque( false );

        JLabel usrNLbl = new JLabel( "User name:" );
        usrNTxt = new JTextField();
        usrNTxt.setColumns( 15 );
        usrNTxt.addActionListener( new TextInputListener() );
        usrNTxt.setActionCommand( "user" );

        usrNPanel.add( usrNLbl );
        usrNPanel.add( usrNTxt );

        JPanel pwPanel = new JPanel( new FlowLayout( FlowLayout.CENTER, 20, 20 ) );
        pwPanel.setOpaque( false );

        JLabel pwLbl = new JLabel( "Password:" );
        pwTxt = new JPasswordField();
        pwTxt.setEchoChar( '*' );
        pwTxt.setColumns( 15 );

        pwPanel.add( pwLbl );
        pwPanel.add( pwTxt );

        dtPanel.add( usrNPanel );
        dtPanel.add( pwPanel );

        add( scrollPane, BorderLayout.CENTER );
        add( btnPanel, BorderLayout.WEST );
        add( dtPanel, BorderLayout.SOUTH );

        setVisible( true );
    }

    /**
     * The method refreshes the shown user list. If no data was found it will load it from the file. If the file is empty it will show anything.
     */
    private DefaultListModel <String> refreshListModel ()
    {

        List<User> userList = Accounts.instance().getUserList();
        if ( ! userList.isEmpty() )
        {

            logger.debug( "Refreshing user data." );
            for ( User user : userList )
            {
                listModel.addElement( user.getName() );
            }
        } else
        {
            logger.debug( "Loading data ..." );
            Accounts.instance().load();
            userList = Accounts.instance().getUserList();
            if ( ! userList.isEmpty() )
            {
                logger.debug( "Refreshing user data." );
                for ( User user : userList )
                {
                    listModel.addElement( user.getName() );
                }
            } else
            {
                logger.debug( "No data ..." );
                listModel.clear();
            }
        }

        return listModel;
    }

    /**
     * The method gets called whenever one of the buttons in the frame gets hit. If you created a new profile it will start an authentication request. Otherwise it will logout the selected profile and delete it from the list and the file.
     *
     * @param e is the event registered by the EDT.
     */
    @Override
    public void actionPerformed ( ActionEvent e )
    {

        if ( e.getActionCommand().equals( "add" ) )
        {
            //TODO implement the authentication of the provided data.
            Authentication authentication = new Authentication();
            if ( authentication.login( usrNTxt.getText(), pwTxt.getPassword() ) )
            {

                usrName.setModel( refreshListModel() );
                usrNTxt.setText( "" );
                pwTxt.setText( "" );
                btnAdd.setEnabled( false );
            }
        } else if ( e.getActionCommand().equals( "del" ) )
        {
            //TODO implement the logout of the selected list entry.
            Accounts.instance().removeUser( usrName.getSelectedValue() );
            usrName.setModel( refreshListModel() );
            usrName.validate();
            btnDel.setEnabled( false );
        }
    }

    @Override
    public void valueChanged ( ListSelectionEvent e )
    {

        if ( e.getFirstIndex() != - 1 )
        {
            btnDel.setEnabled( true );
        }
    }

    /**
     * The method returns the JList component of the configuration panel.
     * @return a JList object.
     */
    public JList<String> getUsrName ()
    {
        return usrName;
    }

    /**
     * The method returns the delete JButton of the configuration panel.
     * @return a JButton object.
     */
    public JButton getBtnDel ()
    {
        return btnDel;
    }

    /**
     * The method returns the text-field component for the username.
     * @return a JTextField object.
     */
    public JTextField getUsrNTxt ()
    {
        return usrNTxt;
    }

    /**
     * The method returns the password field component.
     * @return a JPasswordField object.
     */
    public JPasswordField getPwTxt ()
    {
        return pwTxt;
    }

    /**
     * The method returns the add button of the configuration panel.
     * @return a JButton object.
     */
    public JButton getBtnAdd ()
    {
        return btnAdd;
    }
}
