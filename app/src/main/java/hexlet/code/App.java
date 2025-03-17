package hexlet.code;

import hexlet.code.utils.Differ;
import hexlet.code.utils.Parser;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine;
import picocli.CommandLine.ExitCode;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Spec;
import java.util.Map;
import java.util.concurrent.Callable;

@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference."
)
public class App implements Callable<Integer> {

    @Spec
    private CommandSpec spec;

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "Show this help message and exit")
    private boolean helpRequested = false;

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit")
    boolean versionInfoRequested;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]", defaultValue = "stylish")
    private String format = "stylish";

    @Parameters(index = "0", arity = "0..1", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", arity = "0..1", description = "path to second file")
    private String filepath2;

    @Override
    public Integer call() {
        if (filepath1 == null || filepath2 == null) {
            System.out.println("Hello, World!");
        } else {
            try {
                Map<String, Object> data1 = Parser.getData(filepath1);
                Map<String, Object> data2 = Parser.getData(filepath2);
                String diffResult = Differ.generate(data1, data2, format);
                System.out.println(diffResult);
            } catch (Exception e) {
                System.err.println("Error processing files: " + e.getMessage());
                return ExitCode.SOFTWARE;
            }
        }
        return ExitCode.OK;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}