package main.Service;

import java.util.Arrays;

public class Version
{

    public static String toString ( int[] v )
    {
        String s = Arrays.toString( v );
        s = s.substring( 1, s.length() - 1 );
        s = s.replace( ",", "." );
        return s;
    }
}
