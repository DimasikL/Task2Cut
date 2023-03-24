import org.junit.jupiter.api.Test;
import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class cutTest {
    @Test
    public void testCutByChar() throws IOException {
        String[] args = {"-c", "-o", "output.txt", "5-14", "input.txt"};
        cut.main(args);
        BufferedReader reader = new BufferedReader(new FileReader("output.txt"));
        String line = reader.readLine();
        assertEquals("is a test", line);
        reader.close();
    }


}
