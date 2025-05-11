package com.allemas.classfile.constantpool;


public class ConstantPoolInfo {
    private final ConstantPoolType type;


    public ConstantPoolInfo(ConstantPoolType type) {
        this.type = type;
    }

    public ConstantPoolType getType() {
        return type;
    }
}
