package com.allemas.classfile.constantpool;

import java.lang.String;
import java.nio.charset.StandardCharsets;

import com.allemas.classfile.ConstantPoolType;


public class Utf8 extends ConstantPoolInfo {

    private final byte[] bytes;

    Utf8(byte[] bytes) {
        super(ConstantPoolType.Utf8);
        this.bytes = bytes;
    }

    public java.lang.String getValue() {
        return new java.lang.String(bytes, StandardCharsets.UTF_8);
    }
}
