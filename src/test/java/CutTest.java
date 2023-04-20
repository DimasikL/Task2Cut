import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CutTest {
    @Test
    public void testRunInvalidOptions() throws IOException {
        CutOptions options = new CutOptions();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setErr(new PrintStream(out));
        Cut cut = new Cut();
        cut.runs(true, true,options.getInputName(), options.getOutputName(), options.getRange());
        String output = out.toString().trim();
        assertEquals("Either -c or -w must be specified, but not both", output);
    }
    @Test
    public void testRunWithFiles() throws IOException {
        CutOptions options = new CutOptions();
        File inputFile = File.createTempFile("input", ".txt");
        File outputFile = File.createTempFile("test", ".txt");
        PrintWriter writer = new PrintWriter(new FileWriter(inputFile));
        writer.println("Hello, world!");
        writer.close();
        Cut cut = new Cut();
        cut.runs(true, options.isByWord(),inputFile.getAbsolutePath(), outputFile.getAbsolutePath(), "0-5");
        BufferedReader reader = new BufferedReader(new FileReader(outputFile));
        String result = reader.readLine();
        reader.close();
        assertEquals("Hello,", result);
    }

    @Test
    public void finTest() throws IOException {
        String[] args = {"-c", "-o", "input/output.txt", "input/input.txt", "1-10"};
        CutOptions options = new CutOptions();
        options.parse(args);
        Cut cut = new Cut();
        cut.runs(options.isByChar(), options.isByWord(),options.getInputName(), options.getOutputName(), options.getRange());
        BufferedReader reader = new BufferedReader(new FileReader(options.getOutputName()));
        String result = reader.readLine();
        assertEquals("his is a t", result);
        assertEquals("t contains", reader.readLine());
        assertEquals("ach line h", reader.readLine());
        reader.close();
    }

}