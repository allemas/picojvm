package com.allemas.classfile.constantpool;


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
public class String extends ConstantPoolInfo {
    private int stringIndex;

    public String(int stringIndex) {
        super(ConstantPoolType.String);
        this.stringIndex = stringIndex;
    }

    public int getStringIndex() {
        return stringIndex;
    }

    public void setStringIndex(int stringIndex) {
        this.stringIndex = stringIndex;
    }


}
