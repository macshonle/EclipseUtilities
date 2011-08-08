package edu.utsa.strings;

public class Indenter
{
    private static final String DEFAULT_INDENT_INCR = "    ";
    private int curLevel = 0;
    private String cached = null;
    private String increment;

    public Indenter() {
        this(DEFAULT_INDENT_INCR);
    }
    
    public Indenter(String increment) {
        this.increment = increment;
    }

    public void indent() {
        ++curLevel;
        this.cached = null;
    }

    public void unindent() {
        --curLevel;
        this.cached = null;
    }

    // return a string representing a newline character and then the proper
    // number of indentations
    public String newLine() {
        return String.format("%n%s", this.toString());
    }
    
    @Override
    public String toString() {
        if (this.cached == null) {
            StringBuilder buff = new StringBuilder();
            for (int i=0; i<curLevel; ++i) {
                buff.append(increment);
            }
            this.cached = buff.toString();
        }
        return this.cached;
    }
}
