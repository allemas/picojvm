package com.allemas.classfile.constantpool;

public enum ConstantPoolKind {
    Class(7),
    Fieldref(9),
    Methodref(10),
    InterfaceMethodref(11),
    String(8),
    Integer(3),
    Float(4),
    Long(5),
    Double(6),
    NameAndType(12),
    Utf8(1),
    MethodHandle(15),
    MethodType(16),
    InvokeDynamic(18);

    private int value;

    ConstantPoolKind(int val) {
        this.value = val;
    }

    public static ConstantPoolKind build(int value) {
        for (ConstantPoolKind constantPoolKind : ConstantPoolKind.values()) {
            if (constantPoolKind.value == value) return constantPoolKind;
        }
        throw new RuntimeException(java.lang.String.format("Could not find the corresponding constantPoolType from %d value", value));
    }


}
