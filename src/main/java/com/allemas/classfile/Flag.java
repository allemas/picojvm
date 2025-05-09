package com.allemas.classfile;

public enum Flag {
    ACC_PUBLIC(0x0001),
    ACC_FINAL(0x0010),
    ACC_SUPER(0x0020),
    ACC_INTERFACE(0x0200),
    ACC_ABSTRACT(0x0400),
    ACC_SYNTHETIC(0x1000),
    ACC_ANNOTATION(0x2000),
    ACC_ENUM(0x4000);

    private final int value;

    Flag(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isSet(int accessFlags) {
        return (accessFlags & this.value) != 0;
    }

}
