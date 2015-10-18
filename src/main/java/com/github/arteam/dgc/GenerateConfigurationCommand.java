package com.github.arteam.dgc;

import com.google.common.annotations.VisibleForTesting;
import io.dropwizard.cli.Command;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;

import java.io.PrintWriter;

/**
 * Date: 10/18/15
 * Time: 3:16 PM
 *
 * @author Artem Prigoda
 */
public class GenerateConfigurationCommand extends Command {

    private PrintWriter printWriter;

    @VisibleForTesting
    public void setPrintWriter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public GenerateConfigurationCommand() {
        super("gen-conf", "Generate configuration example");
    }

    @Override
    public void configure(Subparser subparser) {
    }

    @Override
    public void run(Bootstrap<?> bootstrap, Namespace namespace) throws Exception {
        String server = "server:\n" +
                "  applicationConnectors:\n" +
                "    - type: http\n" +
                "      port: 8080\n" +
                "  adminConnectors:\n" +
                "    - type: http\n" +
                "      port: 8081\n";
        String fileRequestLog = "  requestLog:\n" +
                "    appenders:\n" +
                "     - type: file\n" +
                "       currentLogFilename: '/var/log/application_request.log'\n" +
                "       archive: true\n" +
                "       archivedLogFilenamePattern: '/var/log/application_request.%d.log.gz'\n" +
                "       archivedFileCount: 7\n";
        String fileLog = "logging:\n" +
                "  appenders:\n" +
                "   - type: file\n" +
                "     threshold: INFO\n" +
                "     logFormat: '%d{HH:mm:ss.SSS} %-5level %25logger{5} - %msg%n'\n" +
                "     currentLogFilename: '/var/log/application.log'\n" +
                "     archive: true\n" +
                "     archivedLogFilenamePattern: '/var/log/application.%d.log.gz'\n" +
                "     archivedFileCount: 7\n";
        String db = "database:\n" +
                "  driverClass : 'org.postgresql.Driver'\n" +
                "  url: 'jdbc:postgresql://host:5432/db'\n" +
                "  user: ''\n" +
                "  password: ''";
        printWriter.println(server + fileRequestLog + fileLog + db);
    }
}
