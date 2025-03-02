package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.utils.Differ;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference."
)

 class App implements Runnable {
    @Option(names = { "-h", "--help" }, usageHelp = true, description = "Show this help message and exit")
    private boolean helpRequested = false;

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit")
    boolean versionInfoRequested;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]", defaultValue = "stylish")
    private String format;

    @Parameters(index = "0", arity = "0..1", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", arity = "0..1", description = "path to second file")
    private String filepath2;

    public static void main(String[] args) {
        CommandLine.run(new App(), args);
    }

    @Override
    public void run() {
        if (helpRequested) {
            return;
        }
        if (versionInfoRequested) {
            return;
        }
        if (filepath1 == null || filepath2 == null) {
            System.out.println("Hello, World!");
        } else {
            try {
                Map<String, Object> data1 = getData(filepath1);
                Map<String, Object> data2 = getData(filepath2);
                String diffResult = Differ.generate(data1, data2);
                System.out.println(diffResult);
            } catch (Exception e) {
                System.err.println("Error processing files: " + e.getMessage());
            }
        }
    }

    public Map<String, Object> getData(String filepath) throws Exception {
        Path fullPath = Paths.get(filepath);
        if (Files.notExists(fullPath)) {
            Path currentDirectory = Paths.get(System.getProperty("user.dir"));
            Path relativePath = currentDirectory.resolve("src/main/java/hexlet/code/files").resolve(filepath);
            if (Files.exists(relativePath)) {
                fullPath = relativePath;
            }
        }
        String content = new String(Files.readAllBytes(fullPath));
        return parse(content);
    }
    public Map<String, Object> parse(String content) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, Map.class);
    }
}
