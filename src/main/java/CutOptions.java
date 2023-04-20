import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class CutOptions {

    @Option(name = "-c", usage = "cut by characters")
    private boolean byChar;

    @Option(name = "-w", usage = "cut by words")
    private boolean byWord;

    @Option(name = "-o", usage = "output file")
    private String outputName;

    @Argument(metaVar = "file and range")
    private String inputName;

    @Argument(index = 1, metaVar = "range")
    private String range;

    public CutOptions() {
    }

    public static void main(String[] args) {
        new CutOptions().parse(args);
    }

    public void parse(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
        }

    }

    public boolean isByChar() {
        return byChar;
    }

    public boolean isByWord() {
        return byWord;
    }

    public String getOutputName() {
        return outputName;
    }

    public String getInputName() {
        return inputName;
    }

    public String getRange() {
        return range;
    }
}
