package com.allemas.classfile.field;

import com.allemas.classfile.AttributeInfo;
import com.allemas.classfile.Flag;
import com.allemas.classfile.constantpool.ConstantPoolInfo;
import com.allemas.classfile.constantpool.Utf8;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

public class FieldParser {
    public static FieldInfo[] build(DataInputStream stream, int fieldCount, ConstantPoolInfo[] constantPoolTypes) throws IOException {

        FieldInfo[] fieldInfos = new FieldInfo[fieldCount];

        for (int index = 0; index < fieldCount; index++) {
            List<Flag> flag = Flag.fromInt(stream.readUnsignedShort());
            String name = ((Utf8) constantPoolTypes[stream.readUnsignedShort() - 1]).getValue();
            String descriptionNotParsed = ((Utf8) constantPoolTypes[stream.readUnsignedShort() - 1]).getValue();

            for (int attrIndex = 0; attrIndex < stream.readUnsignedShort(); attrIndex++) {
                int attributeNameIndex = stream.readUnsignedShort();
                int attributeLength = stream.readInt();
                String attributeName = ((Utf8) constantPoolTypes[stream.readUnsignedShort() - 1]).getValue();

                System.out.println(attributeName);
                System.out.println("You should parse !");
                stream.skipBytes(attributeLength);
            }

            fieldInfos[index] = new FieldInfo(flag, name, descriptionNotParsed, new AttributeInfo[1]);

        }
        return fieldInfos;
    }


}
