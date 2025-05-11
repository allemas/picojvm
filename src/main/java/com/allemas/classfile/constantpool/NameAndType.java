package com.allemas.classfile.constantpool;


public class NameAndType extends ConstantPoolInfo {
    private int nameIndex;
    private int typeIndex;

    public NameAndType(int nameIndex, int typeIndex) {
        super(ConstantPoolType.NameAndType);
        this.nameIndex = nameIndex;
        this.typeIndex = typeIndex;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getTypeIndex() {
        return typeIndex;
    }
}
