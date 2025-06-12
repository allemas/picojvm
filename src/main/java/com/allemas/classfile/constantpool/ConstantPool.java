package com.allemas.classfile.constantpool;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ConstantPool {

    /**
     * ClassFile
     * u4             magic;
     * u2             minor_version;
     * u2             major_version;
     * u2             constant_pool_count; <---
     * cp_info        constant_pool[constant_pool_count-1]; <---
     * <p>
     * Parse with the constant pool size the constant pool and create a Set with all types inside
     * <p>
     * When an iteration is done, you can find the constant pool tag, don't for forget to read
     * the value above, if it's not done, you read the value not the next tag.
     */
    public static ConstantPoolInfo[] parseConstantPoolInfo(DataInputStream input, int constantPoolCount) throws IOException {
        ConstantPoolInfo[] constantPoolTypes = new ConstantPoolInfo[constantPoolCount];

        for (int index = 0; index < constantPoolCount; index++) {
            int constantTag = input.readUnsignedByte();
            ConstantPoolKind tag = ConstantPoolKind.build(constantTag);
            switch (tag) {
                case ConstantPoolKind.Methodref -> {
                    /**
                     *  #1 = Methodref          #2.#3          // java/lang/Object."<init>":()V
                     *  #2 = Class              #4             // java/lang/Object
                     *  #3 = NameAndType        #5:#6          // "<init>":()V
                     *  #4 = Utf8               java/lang/Object
                     *
                     *  classIndex = #2
                     *  classAndNameIndex = #3
                     */

                    int classIndex = input.readUnsignedShort();
                    int nameIndex = input.readUnsignedShort();
                    constantPoolTypes[index] = new MethodRef(classIndex, nameIndex);
                }

                case ConstantPoolKind.Class -> {
                    int classDescriptionIndex = input.readUnsignedShort();
                    constantPoolTypes[index] = new Class(classDescriptionIndex);
                }
                case ConstantPoolKind.NameAndType -> {
                    int nameIndex = input.readUnsignedShort();
                    int typeIndex = input.readUnsignedShort();
                    constantPoolTypes[index] = new NameAndType(nameIndex, typeIndex);
                }

                case ConstantPoolKind.Utf8 -> {
                    /**
                     * Thanks to .readUTF we don't get mind with random string size
                     *    #4 = Utf8               java/lang/Object
                     *    #5 = Utf8               <init>
                     *    #6 = Utf8               ()V
                     */
                    byte[] bytes = new byte[input.readUnsignedShort()];
                    input.read(bytes);
                    constantPoolTypes[index] = new Utf8(bytes);
                    //                    constantPoolTypes[index] = new Utf8(input.readUTF());
                }
                case ConstantPoolKind.Fieldref -> {
                    int classIndex = input.readUnsignedShort();
                    int nameAndTypeIndex = input.readUnsignedShort();
                    constantPoolTypes[index] = new FieldRef(classIndex, nameAndTypeIndex);
                }
                case ConstantPoolKind.String -> {
                    int utf8Index = input.readUnsignedShort();
                    constantPoolTypes[index] = new com.allemas.classfile.constantpool.String(utf8Index);
                }
                case ConstantPoolKind.Integer -> {
                    int integerValue = input.readInt();
                    constantPoolTypes[index] = new com.allemas.classfile.constantpool.Integer(integerValue);
                }

                case ConstantPoolKind.Float -> {
                    /**
                     * Be careful ! Float is composed with 4 bytes !
                     * https://www.baeldung.com/jvm-constant-pool#2-format
                     */
                    byte[] floatValue = new byte[4];
                    input.readFully(floatValue);
                    constantPoolTypes[index] = new com.allemas.classfile.constantpool.Float(ByteBuffer.wrap(floatValue).getFloat());
                }
                default -> {
                    System.out.println("????????");
                }
            }

        }
        return constantPoolTypes;
    }

    public static ConstantPoolInfo[] resolve(ConstantPoolInfo[] constantPool) {
        for (ConstantPoolInfo cs : constantPool) {
            switch (cs.getType()) {
                case Methodref -> {
                    MethodRef methodRef = (MethodRef) cs;
                    Class c = (Class) constantPool[methodRef.getClassIndex() - 1];
                    NameAndType nameAndType = (NameAndType) constantPool[methodRef.getNameAndTypeIndex() - 1];
                    methodRef.setMethodClass(c);
                    methodRef.setNameAndType(nameAndType);
                }

                case Class -> {
                    Class tmpClass = (Class) cs;
                    Utf8 stringData = (Utf8) constantPool[tmpClass.getIndex() - 1];
                    tmpClass.setName(stringData.getValue());
                }
            }


        }
        return constantPool;
    }


}
