package com.allemas.classfile.constantpool;

import java.lang.String;

import com.allemas.classfile.ConstantPoolType;

public class Utf8 extends ConstantPoolInfo {

    private String value;

    public Utf8(String value) {
        super(ConstantPoolType.Utf8);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
