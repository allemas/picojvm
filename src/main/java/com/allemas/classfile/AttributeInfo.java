package com.allemas.classfile;

import com.allemas.classfile.constantpool.ConstantPoolInfo;
import com.allemas.classfile.constantpool.Utf8;
import jdk.jshell.spi.ExecutionControl;

import java.io.DataInputStream;
import java.io.IOException;

public class AttributeInfo {
    int attributeNameIndex;
    int attributeLength;


    public static AttributeInfo[] build(DataInputStream stream, int attributesCount, ConstantPoolInfo[] constantPoolTypes) throws IOException, ExecutionControl.NotImplementedException {
        AttributeInfo[] attributeInfos = new AttributeInfo[attributesCount];

        for (int attrIndex = 0; attrIndex < attributesCount; attrIndex++) {
            int attributeNameIndex = stream.readUnsignedShort();
            int attributeLength = stream.readInt();

            String attributeName = ((Utf8) constantPoolTypes[attributeNameIndex - 1]).getValue();
            System.out.println(attributeName);
            if (attributeName.equals("Code")) {
                int maxStack = stream.readUnsignedShort();
                int maxLocals = stream.readUnsignedShort();

                int codeLength = stream.readInt();
                System.out.println(codeLength);

                for (int c = 0; c < codeLength; ) {
                    byte code = (byte) stream.readUnsignedByte();
                    Opcode opc = Opcode.getOpcode(code);

                    byte[] operands;
                    int argc = opc.getOperandLength();
                    int padSize = 0;

                    System.out.println(opc.name());
                    System.out.println(opc.getOperandLength());
                    if (opc.getOperandLength() == -1) {
                        throw new ExecutionControl.NotImplementedException("Tableswitch and Lookupswitch not implmented");
                    } else {
                        operands = new byte[opc.getOperandLength()];
                        for (int j = 0; j < opc.getOperandLength(); j++) {
                            operands[j] = (byte) stream.readUnsignedByte();
                        }
                        c += 1 + operands.length;

                    }
                }

                int exceptionTableLength = stream.readUnsignedShort();
                System.out.println("exceptionTableLength=" + exceptionTableLength);
                for (int i = 0; i < exceptionTableLength; i++) {
                    int startPc = stream.readUnsignedShort();
                    int endPc = stream.readUnsignedShort();
                    int handlerPc = stream.readUnsignedShort();
                    int catchType = stream.readUnsignedShort();
                }
                int attributesCount2 = stream.readUnsignedShort();
                AttributeInfo.build(stream, attributesCount2, constantPoolTypes);
            } else if (attributeName.equals("LineNumberTable")) {
                //  int attributeLengthTableLinesIndex = stream.readInt();

                int lineNumberTableLength = stream.readUnsignedShort();
                for (int i = 0; i < lineNumberTableLength; i++) {
                    int lineNumberTableLengdth = stream.readUnsignedShort();
                    int lineNumberTableLengddddth = stream.readUnsignedShort();
                    System.out.println("-> " + lineNumberTableLengdth);
                    System.out.println("-> " + lineNumberTableLengddddth);
                }

            } else {
                System.out.println("skip");
                stream.skipBytes(attributeLength);
            }

            attributeInfos[attrIndex] = new AttributeInfo();


        }
        return attributeInfos;
    }


}
