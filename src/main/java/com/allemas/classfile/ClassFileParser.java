package com.allemas.classfile;

import com.allemas.classfile.constantpool.ConstantPool;
import com.allemas.classfile.constantpool.ConstantPoolInfo;
import com.allemas.classfile.constantpool.ConstantPoolType;
import com.allemas.classfile.constantpool.MethodRef;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


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
        ConstantPoolInfo[] constantPoolTypes =
                ConstantPool.parseConstantPoolInfo(stream, constantPoolSize - 1);
        constantPoolTypes = ConstantPool.resolve(constantPoolTypes);
        classFile.setConstantPoolInfos(constantPoolTypes);

        int accessflag = stream.readUnsignedShort();
        classFile.setFlags(Flag.fromInt(accessflag));
        classFile.setThisClass(stream.readUnsignedShort());
        classFile.setThisSuperclass(stream.readUnsignedShort());

        int interfacesCount = stream.readUnsignedShort();


    }


}
