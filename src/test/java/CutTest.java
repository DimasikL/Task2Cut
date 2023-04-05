import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CutTest {
    @Test
    public void testCutChars() {
        Cut cut = new Cut();
        String line = "Hello, world!";
        String result = cut.cutChars(line, 0, 5);
        assertEquals("Hello,", result);
    }
    @Test
    public void testCutWords() {
        Cut cut = new Cut();
        String line = "The quick brown fox jumps over the lazy dog";
        String result = cut.cutWords(line, 1, 4);
        assertEquals("quick brown fox", result);
    }
    @Test
    public void testProcessLineOutOfBounds() {
        Cut cut = new Cut();
        String line = "Hello, world!";
        String result = cut.processLine(line, "10-15", true);
        assertEquals("ld!", result);
    }
    @Test
    public void testParse() {
        String[] args = {"-c", "-o", "output.txt", "input.txt", "1-10"};
        CutOptions options = new CutOptions();
        options.parse(args);
        Assertions.assertTrue(options.byChar);
        Assertions.assertFalse(options.byWord);
        Assertions.assertEquals("output.txt", options.outputName);
        Assertions.assertEquals("input.txt", options.inputName);
        Assertions.assertEquals("1-10", options.range);
    }
    @Test
    public void testRunInvalidOptions() {
        CutOptions options = new CutOptions();
        options.byChar = true;
        options.byWord = true;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setErr(new PrintStream(out));
        options.run(true, true,options.inputName, options.outputName, options.range);
        String output = out.toString().trim();
        assertEquals("Either -c or -w must be specified, but not both", output);
    }
    @Test
    public void testRunWithFiles() throws IOException {
        CutOptions options = new CutOptions();
        options.byChar = true;
        options.range = "0-5";
        File inputFile = File.createTempFile("test", ".txt");
        File outputFile = File.createTempFile("test", ".txt");
        PrintWriter writer = new PrintWriter(new FileWriter(inputFile));
        writer.println("Hello, world!");
        writer.close();
        options.inputName = inputFile.getAbsolutePath();
        options.outputName = outputFile.getAbsolutePath();
        options.run(true, options.byWord,options.inputName, options.outputName, options.range);
        BufferedReader reader = new BufferedReader(new FileReader(outputFile));
        String result = reader.readLine();
        reader.close();
        assertEquals("Hello,", result);
    }
    @Test
    public void testProcessLineInvalidRangeFormat() {
        String line = "This is a test line";
        String range = "x-y";
        Cut cut = new Cut();
        String result = cut.processLine(line, range, true);
        assertEquals("", result);
    }
    @Test
    public void finTest() throws IOException {
        String[] args = {"-c", "-o", "input/output.txt", "input/input.txt", "1-10"};
        CutOptions options = new CutOptions();
        options.parse(args);
        options.run(options.byChar, options.byWord,options.inputName, options.outputName, options.range);
        BufferedReader reader = new BufferedReader(new FileReader(options.outputName));
        String result = reader.readLine();
        assertEquals("his is a t", result);
        assertEquals("t contains", reader.readLine());
        assertEquals("ach line h", reader.readLine());
        reader.close();
    }

}