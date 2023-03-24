import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class cutTest {
    @Test
    public void testCutByChar() throws IOException {
        String[] args = {"-c", "-o", "input/output.txt", "5-14", "input/input.txt"};
        cut.main(args);
        BufferedReader reader = new BufferedReader(new FileReader("input/output.txt"));
        String line = reader.readLine();
        assertEquals("is a test", line);
        reader.close();
    }

    @Test
    public void testCutByWords() throws IOException {
        String[] args = {"-w", "-o", "input/output.txt", "2-5", "input/input.txt"};
        cut.main(args);
        BufferedReader reader = new BufferedReader(new FileReader("input/output.txt"));
        String line = reader.readLine();
        assertEquals("a test file", line);
        line= reader.readLine();
        assertEquals("multiple lines of", line);
        reader.close();
    }
    @Test
    public void testInvalidArguments() {
        String[] args = {"-c", "-w", "-o", "output.txt", "2-5", "input.txt"};
        assertThrows(IllegalStateException.class, () -> cut.main(args));
    }
    @Test
    public void testNotFindFile() {
        String[] args = {"-c", "-o", "output.txt", "2-5", "iput.txt"};
        assertThrows(FileNotFoundException.class, () -> cut.main(args));
    }


}
