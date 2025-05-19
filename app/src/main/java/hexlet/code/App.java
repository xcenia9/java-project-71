package hexlet.code;

import lombok.Getter;
import lombok.Setter;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine;
import picocli.CommandLine.ExitCode;
import java.util.concurrent.Callable;

@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference."
)
public final class App implements Callable<Integer> {

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "Show this help message and exit")
    private boolean helpRequested = false;

    @Getter
    @Setter
    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit")
    private boolean versionInfoRequested;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]", defaultValue = "stylish")
    private String format;

    @Parameters(index = "0", arity = "0..1", description = "path to first file")
    private String filePath1;

    @Parameters(index = "1", arity = "0..1", description = "path to second file")
    private String filePath2;

    @Override
    public Integer call() {

        if (helpRequested) {
            return ExitCode.OK;
        }
        if (isVersionInfoRequested()) {
            return ExitCode.OK;
        }
        try {
            String diffResult = Differ.generate(filePath1, filePath2, format);
            System.out.println(diffResult);
        } catch (Exception e) {
            System.err.println("Error processing files: " + e.getMessage());
            return ExitCode.SOFTWARE;
        }

        return ExitCode.OK;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
