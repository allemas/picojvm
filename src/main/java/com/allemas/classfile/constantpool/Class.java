package com.allemas.classfile.constantpool;

import com.allemas.classfile.ConstantPoolType;

/**
 * From class file bytecode
 * <p>
 * #2 = Class              #4             // java/lang/Object
 * #3 = NameAndType        #5:#6          // "<init>":()V
 * #4 = Utf8               java/lang/Object
 * <p>
 * When Class tag is parsed, it's contains only one value the description index.
 * The class name attribute will be defined later
 */
public class Class extends ConstantPoolInfo {
    private int descriptorIndex;
    private java.lang.String name;

    public Class(int descriptorIndex) {
        super(ConstantPoolType.Class);
        System.out.println("class=" + descriptorIndex);
        this.descriptorIndex = descriptorIndex;
    }

    public int getIndex() {
        return descriptorIndex;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.String getName() {
        return name;

    }
}
