package com.allemas.classfile.constantpool;

import com.allemas.classfile.ConstantPoolType;


public class Double extends ConstantPoolInfo {
    private double value;

    public Double(double value) {
        super(ConstantPoolType.Double);
        this.value = value;
    }

    public float getValue() {
        return value;
    }


}
