import java.io.*;
import java.util.NoSuchElementException;

public class MyScanner implements AutoCloseable {
    private BufferedReader reader;
    private char[] buffer;
    private int bufferSize;
    private int bufferPos;
    private int charsRead;

    public MyScanner(InputStream inputStream) {
        reader = new BufferedReader(new InputStreamReader(inputStream));
        initBuffer();
    }

    public MyScanner(String input) {
        reader = new BufferedReader(new StringReader(input));
        initBuffer();
    }

    public MyScanner(File file) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(file));
        initBuffer();
    }

    private void initBuffer() {
        bufferSize = 1;
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
            bufferPos++;
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

    // Метод для проверки наличия целого числа
    public boolean hasNextInt() throws IOException {
        skipWhitespace();
        if (bufferPos >= charsRead) {
            fillBuffer();
            if (charsRead == -1) return false;
        }
        char c = buffer[bufferPos];
        return Character.isDigit(c) || (c == '-');
    }

    public long nextInt() throws IOException {
        skipWhitespace();
        if (!hasNextInt()) {
            throw new NoSuchElementException("No integer found");
        }

        StringBuilder number = new StringBuilder();
        while (bufferPos < charsRead || fillBufferAndCheck()) {
            char c = buffer[bufferPos];
            if (Character.isDigit(c) || (c == '-' && number.isEmpty())) {
                number.append(c);
                bufferPos++;
            } else {
                break;
            }
        }
        return Long.parseLong(number.toString());
    }

    private void skipWhitespace() throws IOException {
        while (bufferPos < charsRead || fillBufferAndCheck()) {
            char c = buffer[bufferPos];
            if (!Character.isWhitespace(c)) {
                break;
            }
            bufferPos++;
        }
    }

    private boolean fillBufferAndCheck() throws IOException {
        fillBuffer();
        return charsRead != -1;
    }

    public void close() throws IOException {
        reader.close();
    }
}
