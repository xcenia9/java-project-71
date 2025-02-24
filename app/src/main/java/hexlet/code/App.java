package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
        name = "app",
        mixinStandardHelpOptions = true,
        version = "app 1.0",
        description = "Compares two configuration files and shows a difference."
)

 class App implements Runnable {
    @Option(names = { "-h", "--help" }, usageHelp = true, description = "display a help message")
    private boolean helpRequested = false;

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "display version info")
    boolean versionInfoRequested;

    @Override
    public void run() {
        System.out.println("Hello, World!");
    }

    public static void main(String[] args) {
        CommandLine.run(new App(), args);
    }
 }