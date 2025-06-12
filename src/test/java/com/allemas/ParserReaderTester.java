package com.allemas;

import com.allemas.classfile.AttributeInfo;
import com.allemas.classfile.ClassFileParser;
import com.allemas.classfile.Flag;
import jdk.jshell.spi.ExecutionControl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.logging.Logger;

public class ParserReaderTester {

    private Logger logger = Logger.getLogger(ParserReaderTester.class.getName());

    @Test
    public void checkBytes() {
        var flags = Flag.values();
        for (Flag flag : flags) {
            logger.info(flag.name() + " " + flag.getValue());
        }

    }

    @Test()
    public void parseClassFile() throws IOException, ExecutionControl.NotImplementedException {
        //  final File initialFile = new File("src/test/java/resources/MainBytecode.bytecode");
        final File initialFile = new File("src/test/java/resources/PicoJVM.class");
        final InputStream bytecodeStream = new DataInputStream(new FileInputStream(initialFile));
        ClassFileParser parser = new ClassFileParser();

        parser.parse(bytecodeStream);

    }

    @Test
    public void testLineNumber() {
        AttributeInfo.LineNumberTable lineNumberTable = new AttributeInfo.LineNumberTable();
        lineNumberTable.add(10, 10);
        lineNumberTable.add(20, 20);
        Assertions.assertEquals(lineNumberTable.getLineNumberTable().length, 2);
    }
}
