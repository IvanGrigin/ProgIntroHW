import java.io.*;
import java.util.*;

public class MyScanner implements AutoCloseable {
    private BufferedReader reader;
    private char[] buffer;
    private int bufferSize;
    private int bufferPos;
    private int charsRead;

    public MyScanner(InputStream inputStream) {
        reader = new BufferedReader(new InputStreamReader(inputStream));
        bufferSize = 1024;
        buffer = new char[bufferSize];
        bufferPos = 0;
        charsRead = 0;
    }

    public MyScanner(String fileName) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(fileName));
        bufferSize = 1024;
        buffer = new char[bufferSize];
        bufferPos = 0;
        charsRead = 0;
    }

    private void fillBuffer() throws IOException {
        charsRead = reader.read(buffer);
        bufferPos = 0;
    }

    public String nextLine() throws IOException {
        StringBuilder line = new StringBuilder();
        while (true) {
            if (bufferPos >= charsRead) {
                fillBuffer();
                if (charsRead == -1) {
                    if (!line.isEmpty()) {
                        return line.toString();
                    } else {
                        return null;
                    }
                }
            }
            char c = buffer[bufferPos];
            bufferPos += 1;
            if (c == '\n') {
                break;
            } else if (c != '\r') {
                line.append(c);
            }
        }
        return line.toString();
    }

    public boolean hasNext() throws IOException {
        if (bufferPos < charsRead) {
            return true;
        }
        fillBuffer();
        return charsRead != -1;
    }

    public void close() throws IOException {
        reader.close();
    }
}
