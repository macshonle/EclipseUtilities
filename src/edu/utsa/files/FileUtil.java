package edu.utsa.files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

public class FileUtil
{
    public static String readStream(InputStream is) throws IOException {
        Reader in = new BufferedReader(new InputStreamReader(is));
        StringBuilder result = new StringBuilder(2048);
        char[] buff = new char[2048];
        for (;;) {
            int c = in.read(buff);
            if (c <= 0)
                break;
            result.append(buff, 0, c);
        }
        return result.toString();
    }

    public static String readFileWithThrow(IFile in) throws CoreException, IOException {
        InputStream contents = in.getContents();
        return readStream(contents);
    }
}
