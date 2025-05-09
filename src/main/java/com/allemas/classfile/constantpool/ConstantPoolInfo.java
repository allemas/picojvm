package com.allemas.classfile.constantpool;


import com.allemas.classfile.ConstantPoolType;

public class ConstantPoolInfo {
    private final ConstantPoolType type;


    public ConstantPoolInfo(ConstantPoolType type) {
        this.type = type;
    }

    public ConstantPoolType getType() {
        return type;
    }
}
