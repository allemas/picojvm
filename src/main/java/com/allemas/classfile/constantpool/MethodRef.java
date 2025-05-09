package com.allemas.classfile.constantpool;

import com.allemas.classfile.ConstantPool;
import com.allemas.classfile.ConstantPoolType;

public class MethodRef extends ConstantPoolInfo {

    int classIndex;
    int nameAndTypeIndex;

    public MethodRef(int classIndex, int nameAndTypeIndex) {
        super(ConstantPoolType.Methodref);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}
