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
            if (attributeName.equals("Code")) {
                int maxStack = stream.readUnsignedShort();
                int maxLocals = stream.readUnsignedShort();

                int codeLength = stream.readInt();
                Instruction[] instructions = new Instruction[codeLength];

                for (int c = 0; c < codeLength; ) {
                    byte code = (byte) stream.readUnsignedByte();
                    Opcode opc = Opcode.getOpcode(code);

                    byte[] operands;
                    int argc = opc.getOperandLength();
                    int padSize = 0;

                    if (opc.getOperandLength() == -1) {
                        //padsize is wrong
                        throw new ExecutionControl.NotImplementedException("Table switch and Lookup switch not implemented");
                    } else {
                        operands = new byte[opc.getOperandLength()];
                        for (int j = 0; j < opc.getOperandLength(); j++) {
                            operands[j] = (byte) stream.readUnsignedByte();
                        }
                    }

                    instructions[c] = new Instruction(opc, operands, padSize);
                    c += 1 + operands.length;
                }

                int exceptionTableLength = stream.readUnsignedShort();

                CodeAttributeInfo.ExceptionTableEntry[] exceptionTableEntries = new CodeAttributeInfo.ExceptionTableEntry[exceptionTableLength];
                for (int i = 0; i < exceptionTableLength; i++) {
                    int startPc = stream.readUnsignedShort();
                    int endPc = stream.readUnsignedShort();
                    int handlerPc = stream.readUnsignedShort();
                    int catchType = stream.readUnsignedShort();
                    exceptionTableEntries[i] = new CodeAttributeInfo.ExceptionTableEntry(startPc, endPc, handlerPc, catchType);
                }

                int codeAttributesCount = stream.readUnsignedShort();
                AttributeInfo[] attributesInfos = AttributeInfo.build(stream, codeAttributesCount, constantPoolTypes);

                attributeInfos[attrIndex] = new CodeAttributeInfo(maxStack, maxLocals, instructions, exceptionTableEntries, attributesInfos);

            } else if (attributeName.equals("LineNumberTable")) {
                int lineNumberTableLength = stream.readUnsignedShort();

                LineNumberTable lineNumberTables = new LineNumberTable();
                for (int i = 0; i < lineNumberTableLength; i++) {
                    int startpc = stream.readUnsignedShort();
                    int lineNumbr = stream.readUnsignedShort();
                    lineNumberTables.add(startpc, lineNumbr);
                }

                attributeInfos[attrIndex] = lineNumberTables;

            } else if (attributeName.equals("SourceFile")) {
                int index = stream.readUnsignedShort();
                attributeInfos[attrIndex] = new AttributeInfo.SourceFile(index);
            } else {
                System.out.println("skip:: " + attributeName);
                stream.skipBytes(attributeLength);
            }

        }


        return attributeInfos;
    }

    public static class SourceFile extends AttributeInfo {
        private final int index;

        SourceFile(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }


    public static class CodeAttributeInfo extends AttributeInfo {
        private final int maxStack;
        private final int maxLocals;
        private final Instruction[] instructions;
        private final ExceptionTableEntry[] exceptionTable;
        private final AttributeInfo[] attributes;


        public CodeAttributeInfo(int maxStack, int maxLocals, Instruction[] instructions, ExceptionTableEntry[] exceptionTable, AttributeInfo[] attributes) {
            this.maxStack = maxStack;
            this.maxLocals = maxLocals;
            this.instructions = instructions;
            this.exceptionTable = exceptionTable;
            this.attributes = attributes;
        }


        public static class ExceptionTableEntry {
            private final int startPc;
            private final int endPc;
            private final int handlerPc;
            private final int catchType;

            ExceptionTableEntry(int startPc, int endPc, int handlerPc, int catchType) {
                this.startPc = startPc;
                this.endPc = endPc;
                this.handlerPc = handlerPc;
                this.catchType = catchType;
            }

            public int getStartPc() {
                return startPc;
            }

            public int getEndPc() {
                return endPc;
            }

            public int getHandlerPc() {
                return handlerPc;
            }

            public int getCatchType() {
                return catchType;
            }
        }

    }


    public static class LineNumberTable extends AttributeInfo {
        private LineNumberEntry[] lineNumberTable;

        public LineNumberTable() {
            this.lineNumberTable = new LineNumberEntry[0];
        }

        public void add(int startPc, int lineNumber) {
            LineNumberEntry newEntry = new LineNumberEntry(startPc, lineNumber);
            int length = lineNumberTable.length;


            LineNumberEntry[] tmp = new LineNumberEntry[length + 1];
            System.arraycopy(this.lineNumberTable, 0, tmp, 0, length);
            tmp[length] = newEntry;
            this.lineNumberTable = tmp;
        }

        public LineNumberEntry[] getLineNumberTable() {
            return lineNumberTable;
        }

        public static class LineNumberEntry {
            private final int startPc;
            private final int lineNumber;

            LineNumberEntry(int startPc, int lineNumber) {
                this.startPc = startPc;
                this.lineNumber = lineNumber;
            }

            public int getStartPc() {
                return startPc;
            }

            public int getLineNumber() {
                return lineNumber;
            }

        }


    }

}
