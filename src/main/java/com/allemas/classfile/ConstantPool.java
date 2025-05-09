package com.allemas.classfile;

import java.io.DataInputStream;
import java.io.IOException;
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
     */
    public static Set<ConstantPoolType> parseConstantPoolTypes(DataInputStream input, int constantPoolCount) throws IOException {
        for (int index = 0; index < constantPoolCount; index++) {
            int constantTag = input.readUnsignedByte();
            ConstantPoolType t = ConstantPoolType.build(constantTag);


        }
        return null;
    }

    public static



}
