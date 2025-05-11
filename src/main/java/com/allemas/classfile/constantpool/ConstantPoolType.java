package com.allemas.classfile.constantpool;

public enum ConstantPoolType {
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

    ConstantPoolType(int val) {
        this.value = val;
    }

    public static ConstantPoolType build(int value) {
        for (ConstantPoolType constantPoolType : ConstantPoolType.values()) {
            if (constantPoolType.value == value) return constantPoolType;
        }
        throw new RuntimeException(java.lang.String.format("Could not find the corresponding constantPoolType from %d value", value));
    }


}
