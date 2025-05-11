package com.allemas.classfile.constantpool;

public class MethodRef extends ConstantPoolInfo {
    int classIndex;


    int nameAndTypeIndex;

    Class methodClass;
    NameAndType nameAndType;


    public MethodRef(int classIndex, int nameAndTypeIndex) {
        super(ConstantPoolType.Methodref);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public Class getMethodClass() {
        return methodClass;
    }

    public void setMethodClass(Class methodClass) {
        this.methodClass = methodClass;
    }

    public NameAndType getNameAndType() {
        return nameAndType;
    }

    public void setNameAndType(NameAndType nameAndType) {
        this.nameAndType = nameAndType;
    }

    public int getClassIndex() {
        return classIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

}
