import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class CutOptions {

    @Option(name = "-c", usage = "cut by characters")
    boolean byChar;

    @Option(name = "-w", usage = "cut by words")
    boolean byWord;

    @Option(name = "-o", usage = "output file")
    String outputName;

    @Argument(metaVar = "file and range")
    String inputName;

    @Argument(index = 1, metaVar = "range")
    String range;

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

    public void run(boolean byChar, boolean byWord, String inputName, String outputName, String range) {
        Cut.runs(byChar, byWord, inputName, outputName, range);
    }


}
