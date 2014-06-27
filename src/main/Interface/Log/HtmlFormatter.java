package main.Interface.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class HtmlFormatter extends Formatter
{

    public String format ( LogRecord rec )
    {

        StringBuilder buf = new StringBuilder( 1000 );

        buf.append( "<tr valign=top>" ); buf.append( "<td>" ); buf.append( "<font color=\"white\">" + calcDate( rec.getMillis() ) + "</font>" ); buf.append( "&nbsp &nbsp</td>" ); buf.append( "<td>" );

        if ( rec.getLevel().intValue() >= Level.WARNING.intValue() )
        {
            buf.append( "<b><font color=\"red\">" ); buf.append( rec.getLevel() ); buf.append( "</font></b>&nbsp &nbsp &nbsp" );
        } else
        {
            buf.append( "<b><font color=\"white\">" ); buf.append( rec.getLevel() ); buf.append( "</font></b> &nbsp &nbsp &nbsp" ); if ( rec.getLevel().intValue() == Level.FINE.intValue() )
        {
            buf.append( "<b><font color=\"white\">" + rec.getLoggerName() + "</font></b> &nbsp &nbsp &nbsp" );
            }
        } buf.append( "</td>" ); buf.append( "<td><font color=\"white\">" ); buf.append( rec.getMessage() ); buf.append( "</font></td>" ); buf.append( "</tr>" );
        return buf.toString();

    }

    private String calcDate ( long millisecs )
    {

        SimpleDateFormat date_format = new SimpleDateFormat( "[dd/MM/yyyy HH:mm]" ); Date resultdate = new Date( millisecs ); return date_format.format( resultdate );
    }
}
