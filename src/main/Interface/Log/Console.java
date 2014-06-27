package main.Interface.Log;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Console extends JFrame
{

    private final static LogWrapper   logger           = LogWrapper.getLogger( Console.class );
    private static final long         serialVersionUID = 1L;
    private static       Console      _instance        = new Console();
    private final        Dimension    WIN_SIZE         = new Dimension( 600, 400 );
    private final        Dimension    MIN_SIZE         = new Dimension( 600, 400 );
    private final        BorderLayout LAYOUT           = new BorderLayout();
    private final        Color        COLOR            = new Color( 40, 45, 50 );
    private JScrollPane   scrollPane;
    private JEditorPane   console;
    private HTMLEditorKit kit;
    private HTMLDocument  doc;
    private JPopupMenu    contextMenu;
    private JMenuItem     copy, clear;

    private Console ()
    {

        // Changes the Look&Feel for the console
        UIManager.put( "control", COLOR ); UIManager.put( "text", Color.WHITE ); UIManager.put( "nimbusBase", Color.BLACK ); UIManager.put( "nimbusFocus", COLOR ); UIManager.put( "nimbusBorder", COLOR ); UIManager.put( "nimbusLightBackground", COLOR ); UIManager.put( "info", COLOR );

        setLocation( new Point( 400, 400 ) ); setSize( WIN_SIZE ); setTitle( "Ampl Console" ); setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        // setIconImage(Utils.getImage("/resources/Icon.png"));
        setLayout( LAYOUT ); setMinimumSize( MIN_SIZE ); setPreferredSize( WIN_SIZE );

        console = new JEditorPane( "text/html", "" )
        {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean getScrollableTracksViewportWidth ()
            {

                return true;
            }
        }; DefaultCaret defaultCaret = ( DefaultCaret ) console.getCaret(); defaultCaret.setUpdatePolicy( DefaultCaret.ALWAYS_UPDATE );

        kit = new HTMLEditorKit(); doc = new HTMLDocument(); console.setBackground( new Color( 53, 54, 53 ) ); console.setEditable( false ); console.setSelectionColor( Color.YELLOW ); console.setEditorKit( kit ); console.setDocument( doc );

        initContextMenu();

        scrollPane = new JScrollPane( console, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED ); add( scrollPane, BorderLayout.CENTER );

    }

    private void initContextMenu ()
    {

        contextMenu = new JPopupMenu();

        copy = new JMenuItem( "Copy to Clipboard" ); copy.addActionListener( new ActionListener()
    {
        public void actionPerformed ( ActionEvent e )
        {

            StringSelection select = new StringSelection( console.getSelectedText() ); Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); clipboard.setContents( select, null );
        }
    } );

        clear = new JMenuItem( "Clear console" ); clear.addActionListener( new ActionListener()
    {
        public void actionPerformed ( ActionEvent e )
        {

            console.setText( null ); logger.info( "<font color=\"green\">Cleared Console</font>" );
        }
    } ); contextMenu.add( copy ); contextMenu.add( clear );

        console.addMouseListener( new MouseAdapter()
        {
            public void mousePressed ( MouseEvent e )
            {

                if ( e.getButton() == MouseEvent.BUTTON3 )
                {
                    if ( console.getSelectedText() == null )
                    {
                        contextMenu.getComponent( 0 ).setEnabled( false ); contextMenu.show( console, e.getX(), e.getY() );
                    } else
                    {
                        contextMenu.show( console, e.getX(), e.getY() );
                    }
                }
            }
        } );
    }

    public static Console instance ()
    {
        return _instance;
    }

    public synchronized void write ( String text )
    {

        try
        {
            kit.insertHTML( doc, doc.getLength(), text, 0, 0, null );
        } catch ( BadLocationException e )
        {
            logger.fatal( "Can't write the text. <br>" + e.getLocalizedMessage() );
        } catch ( IOException e )
        {
            logger.fatal( "Can't write the text. <br>" + e.getLocalizedMessage() );
        }

    }
}