import java.io.*;

public class cut {
    public static void main(String[] args) throws IOException {
        String inputName = null;
        String outputName = null;
        boolean byChar = false;
        boolean byWord = false;
        int start = 0;
        int end = Integer.MAX_VALUE;

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (args[i].equals("-c")) {
                byChar = true;
            } else if (args[i].equals("-w")) {
                byWord = true;
            } else if (args[i].contains("-o")) {
                outputName = args[++i];
            } else if (args[i].contains("-")) {
                if (arg.startsWith("-")) {
                    end = Integer.parseInt(arg.substring(1));
                } else if (arg.endsWith("-")) {
                    start = Integer.parseInt(arg.substring(0, arg.length() - 1));
                } else {
                    int dashIndex = arg.indexOf("-");
                    start = Integer.parseInt(arg.substring(0, dashIndex));
                    end = Integer.parseInt(arg.substring(dashIndex + 1));
                }
            } else {
                inputName = args[i];
            }

        }

        if (byChar && byWord) throw new IllegalStateException("We have tho parameters");


        BufferedReader reader;
        if (inputName != null) {
            try {
                reader = new BufferedReader(new FileReader(inputName));
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + inputName);
                throw new FileNotFoundException();
            }
        } else {
            reader = new BufferedReader(new InputStreamReader(System.in));
        }


        BufferedWriter writer;
        if (outputName != null) {
            try {
                writer = new BufferedWriter(new FileWriter(outputName));
            } catch (IOException e) {
                System.err.println("File can`t write: " + outputName);
                return;
            }
        } else {
            writer = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        String line;
        while ((line = reader.readLine()) != null) {
            int length = byChar ? line.length() : line.split("\\s+").length;
            start = Math.min(start, length);
            end = Math.min(end, length);
            String result = null;
            if (byChar) {
                result = line.substring(start, end);
            } else {
                String[] words = line.split("\\s+");
                StringBuilder str = new StringBuilder();
                for (int i = start; i < end && i < words.length; i++) {
                    str.append(words[i]).append(" ");
                }
                result = str.toString().trim();
            }
            writer.write(result);
            writer.newLine();
        }
        reader.close();
        writer.close();
    }

}
