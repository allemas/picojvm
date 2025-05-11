package com.allemas.classfile.constantpool;

import com.allemas.classfile.ConstantPoolType;


public class Integer extends ConstantPoolInfo {
    private int value;

    public Integer(int value) {
        super(ConstantPoolType.Integer);
        this.value = value;
    }


}
