package com.allemas.classfile.constantpool;

import com.allemas.classfile.ConstantPoolType;


public class Float extends ConstantPoolInfo {
    private float value;

    public Float(float value) {
        super(ConstantPoolType.Float);
        this.value = value;
    }

    public float getValue() {
        return value;
    }


}
