package com.allemas.classfile.constantpool;

import com.allemas.classfile.ConstantPoolType;

import java.lang.String;

public class InterfaceMethodref extends ConstantPoolInfo {

    int classIndex;
    int nameAndTypeIndex;

    public InterfaceMethodref(int classIndex, int nameAndTypeIndex) {
        super(ConstantPoolType.Utf8);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public int getClassIndex() {
        return classIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }


}
