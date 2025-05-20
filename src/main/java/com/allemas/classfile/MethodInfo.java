package com.allemas.classfile;

import com.allemas.classfile.constantpool.ConstantPoolInfo;
import com.allemas.classfile.constantpool.Utf8;
import com.allemas.classfile.field.FieldInfo;
import jdk.jshell.spi.ExecutionControl;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

public class MethodInfo {

    List<Flag> flags;
    String name;
    String MethodInfo;
    int attributeCount;
    AttributeInfo[] attributes;

    MethodInfo(String name, String descriptor, AttributeInfo[] attributes) {
        name = name;
        descriptor = descriptor;
        this.attributes = attributes;
    }


    public static MethodInfo[] build(DataInputStream stream, int methodCount, ConstantPoolInfo[] constantPoolTypes) throws IOException, ExecutionControl.NotImplementedException {
        MethodInfo[] methodInfos = new MethodInfo[methodCount];
        for (int index = 0; index < methodCount; index++) {
            int accessFlags = stream.readUnsignedShort();
            int nameIndex = stream.readUnsignedShort();
            int descriptorIndex = stream.readUnsignedShort();
            int attributesCount = stream.readUnsignedShort();

            String name = ((Utf8) constantPoolTypes[nameIndex - 1]).getValue();
            String descriptionNotParsed = ((Utf8) constantPoolTypes[descriptorIndex - 1]).getValue();
            System.out.println(name);
            AttributeInfo[] attributeInfos = AttributeInfo.build(stream, attributesCount, constantPoolTypes);

            methodInfos[index] = new MethodInfo(name, descriptionNotParsed, attributeInfos);
        }

        return methodInfos;
    }


}
