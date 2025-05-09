package com.allemas.classfile;

import com.allemas.classfile.constantpool.ConstantPoolInfo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;


public class ClassFileParser {

    private Logger logger = Logger.getLogger(ClassFileParser.class.getName());
    private static final int MAGIC_NUMBER = 0xCAFEBABE;


    public void parse(InputStream classFilestream) throws IOException {
        DataInputStream stream = new DataInputStream(classFilestream);
        ClassFile classFile = new ClassFile();

        classFile.setMagicNumber(stream.readInt());
        classFile.setMinVersion(stream.readUnsignedShort());
        classFile.setMaxVersion(stream.readUnsignedShort());

        int constantPoolSize = stream.readUnsignedShort();
        Set<ConstantPoolInfo> constantPoolTypes =
                ConstantPool.parseConstantPoolTypes(stream, constantPoolSize - 1);


    }


}
