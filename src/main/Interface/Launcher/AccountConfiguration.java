package main.Interface.Launcher;

import main.Ampl;
import main.Interface.Log.LogWrapper;
import main.Service.Settings.Player.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static main.Ampl.getSettings;

/**
 * This class is a proper JOptionsPane Dialog to change settings about an account. Created by Max on 21.06.2014.
 */
public class AccountConfiguration extends JFrame implements ActionListener, ListSelectionListener {

    private final JList<String> usrName;
    private final JButton btnDel;
    private LogWrapper logger = LogWrapper.getLogger(AccountConfiguration.class);
    private DefaultListModel<String> listModel = new DefaultListModel<String>();

    public AccountConfiguration() {

        logger.debug("Opening account settings.");

        setLocationRelativeTo(Ampl.getLauncherFrame());
        setTitle("Accounts-manager");
        Dimension MIN_SIZE = new Dimension(400, 500);
        setMinimumSize(MIN_SIZE);
        setMaximumSize(getSettings().getInfoLauncher().getSize());
        BorderLayout LAYOUT = new BorderLayout();
        setLayout(LAYOUT);

        logger.debug("Properties set.<br> Adding panes.");


        refreshListModel();
        usrName = new JList<String>(listModel);
        usrName.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        usrName.setLayoutOrientation(JList.VERTICAL);
        usrName.setVisibleRowCount(- 1);
        JScrollPane scrollPane = new JScrollPane(usrName, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(250, 80));

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.PAGE_AXIS));
        ImageIcon icon = new ImageIcon(getClass()
                .getResource("/resources/tango-icon-theme-0.8.90/tango-icon-theme-0.8.90/32x32/actions/list-add.png"));
        JButton btnAdd = new JButton(icon);
        btnAdd.addActionListener(this);
        btnAdd.setActionCommand("add");
        ImageIcon icon1 = new ImageIcon(getClass().getResource(
                "/resources/tango-icon-theme-0.8.90/tango-icon-theme-0.8.90/32x32/actions/list-remove.png"));
        btnDel = new JButton(icon1);
        btnDel.addActionListener(this);
        btnDel.setActionCommand("del");
        btnDel.setEnabled(false);
        btnPanel.add(btnAdd);
        btnPanel.add(btnDel);

        JPanel dtPanel = new JPanel();
        dtPanel.setLayout(new BoxLayout(dtPanel, BoxLayout.PAGE_AXIS));
        dtPanel.setBackground(new Color(117, 117, 117));
        JPanel usrNPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        usrNPanel.setOpaque(false);
        JLabel usrNLbl = new JLabel("User name:");
        JTextField usrNTxt = new JTextField();
        usrNTxt.setColumns(15);
        usrNPanel.add(usrNLbl);
        usrNPanel.add(usrNTxt);
        JPanel pwPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        pwPanel.setOpaque(false);
        JLabel pwLbl = new JLabel("Password:");
        JTextField pwTxt = new JTextField();
        pwTxt.setColumns(15);
        pwPanel.add(pwLbl);
        pwPanel.add(pwTxt);
        dtPanel.add(usrNPanel);
        dtPanel.add(pwPanel);

        add(scrollPane, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.WEST);
        add(dtPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    /**
     * The method refreshes the shown user list. If no data was found it will load it from the file. If the file is
     * empty it will show anything.
     */
    private void refreshListModel() {

        List<User> userList = getSettings().getAccounts().getUserList();
        if (! userList.isEmpty()) {

            logger.debug("Refreshing user data.");
            for (User user : userList) {
                listModel.addElement(user.getName());
            }
        } else {
            logger.debug("Loading data ...");
            getSettings().getAccounts().load();
            if (! userList.isEmpty()) {

                logger.debug("Refreshing user data.");
                for (User user : userList) {
                    listModel.addElement(user.getName());
                }
            } else {
                logger.debug("No data ...");
            }
        }
    }

    /**
     * The method gets called whenever one of the buttons in the frame gets hit. If you created a new profile it will
     * start an authentication request. Otherwise it will logout the selected profile and delete it from the list and
     * the file.
     *
     * @param e is the event registered by the EDT.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("add")) {
            //TODO implement the authentication of the provided data.
            refreshListModel();
        } else if (e.getActionCommand().equals("del")) {
            //TODO implement the logout of the selected list entry.
            int index = usrName.getSelectedIndex();
            usrName.remove(index);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

        ListSelectionModel listSelectionModel = (ListSelectionModel) e.getSource();
        if (! listSelectionModel.isSelectionEmpty() && ! e.getValueIsAdjusting()) {
            btnDel.setEnabled(true);
        } else {
            btnDel.setEnabled(false);
        }
    }
}
