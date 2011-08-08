package edu.utsa.eclipse;

import java.io.PrintStream;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.*;

public class SystemUtil
{
    private static PrintStream outStream;
    private static PrintStream errStream;

    public static PrintStream getOutStream() {
        if (outStream == null) {
            MessageConsole myConsole = findConsole("Plug-in Output");
            MessageConsoleStream output = myConsole.newMessageStream();
            SystemUtil.outStream = new PrintStream(output);
        }
        return outStream;
    }

    public static PrintStream getErrStream() {
        if (errStream == null) {
            MessageConsole myConsole = findConsole("Plug-in Output");
            MessageConsoleStream output = myConsole.newMessageStream();
            Color red = new Color(Display.getCurrent(), 0xFF, 0, 0);
            output.setColor(red);
            SystemUtil.errStream = new PrintStream(output);
        }
        return errStream;
    }

    private static MessageConsole findConsole(String name) {
        ConsolePlugin plugin = ConsolePlugin.getDefault();
        IConsoleManager conMan = plugin.getConsoleManager();
        IConsole[] existing = conMan.getConsoles();
        for (int i = 0; i < existing.length; i++) {
            if (name.equals(existing[i].getName())) {
                return (MessageConsole)existing[i];
            }
        }
        // no console found, so create a new one
        MessageConsole myConsole = new MessageConsole(name, null);
        conMan.addConsoles(new IConsole[] { myConsole });
        return myConsole;
    }
}