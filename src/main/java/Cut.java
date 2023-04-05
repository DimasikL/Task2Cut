import java.io.*;

public class Cut {
    public static void runs(boolean byChar, boolean byWord, String inputName, String outputName, String range) {
        if (byChar == byWord) {
            System.err.println("Either -c or -w must be specified, but not both");
            return;
        }

        BufferedReader reader;
        if (inputName == null) {
            reader = new BufferedReader(new InputStreamReader(System.in));
        } else {
            try {
                reader = new BufferedReader(new FileReader(inputName));
            } catch (IOException e) {
                System.err.println("Failed to open input file: " + e.getMessage());
                return;
            }
        }

        PrintWriter writer;
        if (outputName == null) {
            writer = new PrintWriter(System.out);
        } else {
            try {
                writer = new PrintWriter(new FileWriter(outputName));
            } catch (IOException e) {
                System.err.println("Failed to open output file: " + e.getMessage());
                return;
            }
        }

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String result = processLine(line, range, byChar);
                writer.println(result);
            }
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        } finally {
            try {
                reader.close();
            } catch (IOException ignored) {
            }
            writer.close();
        }
    }

    static String processLine(String line, String range, boolean byChar) {
        try {
            String[] parts = range.split("-");

            int start, end;
            if (parts.length == 1) {
                start = 0;
                end = Integer.parseInt(parts[0]);
            } else if (parts[0].isEmpty()) {
                start = 0;
                end = Integer.parseInt(parts[1]);
            } else {
                start = Integer.parseInt(parts[0]);
                end = Integer.parseInt(parts[1]);
            }
            if (start >= line.length()) {
                return "";
            }

            if (end >= line.length()) {
                end = line.length() - 1;
            }

            if (end < start) {
                return "";
            }
            if (start < 0) {
                start = 0;
            }
            if (end < 0) {
                return "";
            }

            if (byChar) {
                return cutChars(line, start, end);
            } else {
                return cutWords(line, start, end);
            }
        }catch (NumberFormatException e){ return "";}
        }


    public static String cutChars(String line, int start, int end) {
        return line.substring(start, end + 1);
    }

    public static String cutWords(String line, int start, int end) {
        String[] words = line.split("\\s+");
        String result;
        StringBuilder str = new StringBuilder();
        for (int i = start; i < end; i++) {
            str.append(words[i]).append(" ");
        }
        result = str.toString().trim();

        return result;
    }


}


