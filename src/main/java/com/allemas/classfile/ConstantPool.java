package com.allemas.classfile;

import com.allemas.classfile.constantpool.*;
import com.allemas.classfile.constantpool.Class;
import com.allemas.classfile.constantpool.String;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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
     * <p>
     * read https://github.com/k0kubun/jjvm/blob/master/src/main/java/com/github/k0kubun/jjvm/classfile/ClassFileParser.java#L79
     * Help me to understand how read and parse Constantpool values and where store them
     */
    public static Set<ConstantPoolInfo> parseConstantPoolTypes(DataInputStream input, int constantPoolCount) throws IOException {
        Set<ConstantPoolInfo> constantPoolTypes = new HashSet<ConstantPoolInfo>();

        for (int index = 0; index < constantPoolCount; index++) {
            int constantTag = input.readUnsignedByte();
            ConstantPoolType tag = ConstantPoolType.build(constantTag);

            switch (tag) {
                case ConstantPoolType.Methodref -> {
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
                    constantPoolTypes.add(new MethodRef(classIndex, nameIndex));
                }

                case ConstantPoolType.Class -> {
                    int classDescriptionIndex = input.readUnsignedShort();
                    constantPoolTypes.add(new Class(classDescriptionIndex));
                }
                case ConstantPoolType.NameAndType -> {
                    int nameIndex = input.readUnsignedShort();
                    int typeIndex = input.readUnsignedShort();
                    constantPoolTypes.add(new NameAndType(nameIndex, typeIndex));
                }

                case ConstantPoolType.Utf8 -> {
                    /**
                     * Thanks to .readUTF we dont get mind with random string size
                     *    #4 = Utf8               java/lang/Object
                     *    #5 = Utf8               <init>
                     *    #6 = Utf8               ()V
                     */
                    constantPoolTypes.add(new Utf8(input.readUTF()));
                }
                case ConstantPoolType.Fieldref -> {
                    int classIndex = input.readUnsignedShort();
                    int nameAndTypeIndex = input.readUnsignedShort();
                    constantPoolTypes.add(new FieldRef(classIndex, nameAndTypeIndex));
                }
                case ConstantPoolType.String -> {
                    int utf8Index = input.readUnsignedShort();
                    constantPoolTypes.add(new com.allemas.classfile.constantpool.String(utf8Index));
                }

            }

        }
        return null;
    }


}
