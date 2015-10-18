package com.github.arteam.dgc;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Resources;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.sourceforge.argparse4j.inf.Namespace;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Date: 10/18/15
 * Time: 5:27 PM
 *
 * @author Artem Prigoda
 */
public class GenerateConfigurationCommandTest {

    GenerateConfigurationCommand cmd = new GenerateConfigurationCommand();
    Bootstrap<Configuration> bootstrap = new Bootstrap<>(new Application<Configuration>() {
        @Override
        public void run(Configuration configuration, Environment environment) throws Exception {

        }
    });

    @Test
    public void testRun() throws Exception {
        StringWriter stringWriter = new StringWriter();
        cmd.setPrintWriter(new PrintWriter(stringWriter));
        cmd.run(bootstrap, new Namespace(ImmutableMap.of()));

        String defaultConf = Resources.toString(Resources.getResource("default_conf.yml"), Charsets.UTF_8);
        Assert.assertEquals(defaultConf, stringWriter.toString());

    }
}