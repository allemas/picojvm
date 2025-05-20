package com.allemas.classfile;

import com.allemas.classfile.constantpool.ConstantPoolInfo;
import com.allemas.classfile.constantpool.Utf8;
import com.allemas.classfile.field.FieldInfo;

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


    public static MethodInfo[] build(DataInputStream stream, int methodCount, ConstantPoolInfo[] constantPoolTypes) throws IOException {
        MethodInfo[] methodInfos = new MethodInfo[methodCount];
        for (int index = 0; index < methodCount; index++) {
            var flags = Flag.fromInt(stream.readUnsignedShort());
            var nameIndex = stream.readUnsignedShort();
            String name = ((Utf8) constantPoolTypes[nameIndex - 1]).getValue();
            System.out.println(name);
            var descIndex = stream.readUnsignedShort();
            String descriptionNotParsed = ((Utf8) constantPoolTypes[nameIndex - 1]).getValue();

            for (int attrIndex = 0; attrIndex < stream.readUnsignedShort(); attrIndex++) {
                var nadme = stream.readUnsignedShort();
                var attribute_length = stream.readInt();
                String attributeName = ((Utf8) constantPoolTypes[nadme - 1]).getValue();
                var attributeLength = stream.readInt();


                System.out.println(attributeName);
                System.out.println("You should parse !");
                if (attributeName.equals("Code")) {
                    var nameindex = stream.readUnsignedShort();
                    var codeAttribute_length = stream.readInt();
                    var maxStack = stream.readUnsignedShort();
                    var minLocal = stream.readUnsignedShort();
                    var codeLenght = stream.readInt();

                    for (int c = 0; c < codeLenght; c++) {
                        byte code = (byte) stream.readUnsignedByte();
                        Opcode opc = Opcode.getOpcode(code);
                        System.out.println(opc);
                    }


                }
                stream.skipBytes(attributeLength);
            }

            methodInfos[index] = new MethodInfo(name, descriptionNotParsed, new AttributeInfo[0]);
        }

        return methodInfos;
    }


}
