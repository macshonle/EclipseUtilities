package edu.utsa.strings;

import static java.lang.String.valueOf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.utsa.collections.ArrayIterator;

public class StringUtil
{
    public static <T> String enumerate(T[] args) {
        return enumerate(new ArrayIterator(args));
    }

    public static String enumerate(Iterable<?> args) {
        List<Object> elements = new ArrayList<Object>();
        for (Object arg : args) {
            elements.add(arg);
        }
        if (elements.size() == 1) {
            return elements.get(0).toString();
        }
        else {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < elements.size(); ++i) {
                final int count = i + 1;
                builder.append(count);
                builder.append(") ");
                builder.append(elements.get(i));
                if (i + 1 < elements.size()) {
                    builder.append("; ");
                }
            }
            return builder.toString();
        }
    }

    public static void separate(StringBuilder buff, Iterable<?> args,
            String separator) {
        internalSeparate(buff, args, separator, null);
    }

    public static void separate(StringBuilder buff, Iterable<?> args,
            String separator, String useWhenNone) {
        internalSeparate(buff, args, separator, useWhenNone);
    }

    public static void internalSeparate(StringBuilder buff, Iterable<?> args,
                String separator, String useWhenNone) {
        Iterator<?> i = args.iterator();
        if (i.hasNext()) {
            for (;;) {
                Object thing = i.next();
                buff.append(thing.toString());
                if (i.hasNext()) {
                    buff.append(separator);
                }
                else
                    break;
            }
        }
        else {
            buff.append(useWhenNone);
        }
    }

    public static String separate(Iterable<?> args, String separator) {
        StringBuilder buff = new StringBuilder();
        separate(buff, args, separator);
        return buff.toString();
    }

    // EXAMPLE: Changed from "Collection" to "Iterable" (and down called
    // methods, like
    // the other two static 'separate' methods): The iterable aspect of it was
    // all
    // that was needed.
    public static String separate(Iterable<?> args) {
        return separate(args, ", ");
    }

    public static String separate(Map<?, ?> map) {
        StringBuilder buff = new StringBuilder();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            buff.append(String.format(" --%s = %s%n", entry.getKey(),
                    entry.getValue()));
        }
        return buff.toString();
    }

    public static String extractString(char[] cs, int start, int length) {
        StringBuilder buff = new StringBuilder();
        buff.append(cs, start, length);
        return buff.toString();
    }

    public static String stripWhitespace(Object object) {
        if (object != null) {
            return stripWhitespace(object.toString());
        }
        else {
            return "null";
        }
    }
    
    public static String stripWhitespace(String str) {
        StringBuilder buff = new StringBuilder();
        int len = str.length();
        for (int i = 0; i < len; ++i) {
            char c = str.charAt(i);
            if (!Character.isWhitespace(c)) {
                buff.append(c);
            }
        }
        return buff.toString();
    }

    public static Object minimizeWhitespace(Object o) {
        return minimizeWhitespace(String.valueOf(o));
    }

    public static String minimizeWhitespace(String str) {
        str = str.trim();

        StringBuilder buff = new StringBuilder();
        int len = str.length();
        boolean lastWasNonWhite = true;
        for (int i = 0; i < len; ++i) {
            char c = str.charAt(i);
            if (Character.isWhitespace(c)) {
                if (lastWasNonWhite) {
                    lastWasNonWhite = false;
                    buff.append(' ');
                }
            }
            else {
                lastWasNonWhite = true;
                buff.append(c);
            }
        }
        return buff.toString();
    }

    public static String valueOfAndClass(Object obj) {
        return String.format("%s [%s]", valueOf(obj), (obj == null) ? "null"
                : obj.getClass().getName());
    }

    public static String debugDisplay(Object obj) {
        if (obj == null) {
            return "null";
        }
        else {
            String clazzName = obj.getClass().getSimpleName();
            int id = System.identityHashCode(obj);
            return String.format("%s [%s:%x]", minimizeWhitespace(obj),
                    clazzName, id);
        }
    }

    public static String firstLine(String string) {
        int i = string.indexOf(String.format("%n"));
        if (i == -1)
            i = string.indexOf("\n");
        if (i == -1)
            return string;
        else
            return string.substring(0, i);
    }

    // Returns a string with backslashes inserted so that the given string can
    // be
    // a string literal
    public static String escape(String text) {
        // MACNEIL: Just return the text for now: exact output isn't important
        // now
        return text;
    }
}
