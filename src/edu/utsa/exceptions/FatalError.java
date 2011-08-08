package edu.utsa.exceptions;

public class FatalError extends PluginError
{
    private static final long serialVersionUID = 1L;

    public FatalError(String message) {
        super(message);
    }
}