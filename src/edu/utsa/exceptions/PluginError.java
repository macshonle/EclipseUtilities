package edu.utsa.exceptions;

import edu.utsa.eclipse.EclipseUIUtil;

public class PluginError extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    // A String.format-style message
    public PluginError(Exception cause, String format, Object... args) {
        super(String.format(format, args), cause);
    }
    
    public PluginError(String format, Object... args) {
        super(String.format(format, args));
    }
    
    public PluginError(String message) {
        super(message);
    }

    public PluginError(Exception cause) {
        super(cause);
    }

    // Code related to "location" is commented out for now. It was related to Arcum source file locations, and
    // may be useful in a different form in the future
    public static void userError(/*SourceLocation location,*/ String format, Object... args) {
        String message = String.format(format, args);
        System.out.flush();
        System.err.printf("%s%n", message);
        message = message.trim();
        // Without 'location':
        EclipseUIUtil.error(message, "An Error Has Occurred");
        // With 'location':
//        try {
//            if (location != null) {
//                location.createMarker(message);
//            }
//            else {
//                EclipseUIUtil.error(message, "An Error Has Occurred");
//            }
//        }
//        catch (CoreException e) {
//            e.printStackTrace();
//            PluginError.fatalError(format, args);
//        }
    }

    // EXAMPLE: This function does not return. An extra annotation and checking on it
    // will help prevent coding errors.
    public static void fatalUserError(/*SourceLocation location,*/ String format,
        Object... args)
    {
        userError(/*location,*/ format, args);
        throw new FatalError(String.format(format, args));
    }

    public static void fatalError(String format, Object... args) {
        String message = String.format(format, args);
        System.out.flush();
        System.err.printf("%s%n", message);
        EclipseUIUtil.error(message, "An Error Has Occurred");
        throw new PluginError(message);
    }
//
//    public static void stop() {
//        throw new StopCompilation();
//    }
}