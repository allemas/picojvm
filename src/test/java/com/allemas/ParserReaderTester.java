package com.allemas;

import com.allemas.classfile.ClassFileParser;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.logging.Logger;

public class ParserReaderTester {

    private Logger logger = Logger.getLogger(ParserReaderTester.class.getName());

    @Test()
    public void parseClassFile() throws IOException {
        //  final File initialFile = new File("src/test/java/resources/MainBytecode.bytecode");
        final File initialFile = new File("src/test/java/resources/PicoJVM.class");
        final InputStream bytecodeStream = new DataInputStream(new FileInputStream(initialFile));
        ClassFileParser parser = new ClassFileParser();

        parser.parse(bytecodeStream);

    }
}
