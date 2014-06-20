package main.Interface.Log;

import main.Ampl;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class ConsoleHandler extends Handler {

    public ConsoleHandler() {

        super();
    }

    @Override
    public void publish(LogRecord record) {

        String s = getFormatter().format(record);
        try {
            Ampl.logConsole.write(s);
        } catch (NullPointerException e) {
            System.out.println("There was an error: See the log for further information!!");
        }


    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub

    }

    @Override
    public void close() throws SecurityException {
        // TODO Auto-generated method stub

    }
}
