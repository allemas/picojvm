package com.allemas.classfile;

import com.allemas.classfile.constantpool.ConstantPool;
import com.allemas.classfile.constantpool.ConstantPoolInfo;
import com.allemas.classfile.constantpool.MethodRef;
import com.allemas.classfile.constantpool.Utf8;
import com.allemas.classfile.field.FieldInfo;
import com.allemas.classfile.field.FieldParser;
import com.allemas.classfile.field.FieldType;
import jdk.jshell.spi.ExecutionControl;

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


    public void parse(InputStream classFilestream) throws IOException, ExecutionControl.NotImplementedException {
        DataInputStream stream = new DataInputStream(classFilestream);
        ClassFile classFile = new ClassFile();

        classFile.setMagicNumber(stream.readInt());
        classFile.setMinVersion(stream.readUnsignedShort());
        classFile.setMaxVersion(stream.readUnsignedShort());

        int constantPoolSize = stream.readUnsignedShort();
        ConstantPoolInfo[] constantPoolTypes =
                ConstantPool.parseConstantPoolInfo(stream, constantPoolSize - 1);
        classFile.setConstantPoolInfos(ConstantPool.resolve(constantPoolTypes));

        int accessflag = stream.readUnsignedShort();
        classFile.setFlags(Flag.fromInt(accessflag));
        classFile.setThisClass(stream.readUnsignedShort());
        classFile.setThisSuperclass(stream.readUnsignedShort());

        int interfacesCount = stream.readUnsignedShort();
        System.out.println("Interfaces: " + interfacesCount);
        for (int i = 0; i < interfacesCount; i++) {
            //    int interfaceIndex = stream.readUnsignedByte();
            //  System.out.println(interfaceIndex);
        }
        int fieldsCount = stream.readUnsignedShort();
        System.out.println("fieldsCount" + fieldsCount);
        FieldInfo[] fieldTypes = FieldParser.build(stream, fieldsCount, constantPoolTypes);

        int methodCount = stream.readUnsignedShort();
        System.out.println("methodCount " + methodCount);
        MethodInfo[] methods = MethodInfo.build(stream, methodCount, constantPoolTypes);

        int attributesCount = stream.readUnsignedShort();
        System.out.println("attributeInfo=>>" + attributesCount);
        for (int i = 0; i < attributesCount; i++) {
            int attributeIndex = stream.readUnsignedShort();
            System.out.println(attributeIndex);
            System.out.println(stream.readInt());

            String attributeName = getString(constantPoolTypes, attributeIndex);
            System.out.println(attributeName);
        }

    }

    private String getString(ConstantPoolInfo[] constantPool, int index) {
        return ((Utf8) constantPool[index - 1]).getValue();
    }

}
