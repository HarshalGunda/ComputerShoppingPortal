import javax.swing.*;

import jdk.javadoc.internal.doclets.formats.html.resources.standard;

import java.awt.event.*;
import java.awt.*;
import java.util.*;

class App
{
    public static native int CalcBill(int [] params);

    static{System.loadLibrary("calc");}

    public static void main(String args[])
    {
        
    }
}