package com.allemas.classfile.constantpool;

import com.allemas.classfile.ConstantPoolType;


/**
 * #7 = Fieldref           #8.#9          // java/lang/System.out:Ljava/io/PrintStream;
 */
public class FieldRef extends ConstantPoolInfo {
    private final int classIndex;
    private final int nameAndTypeIndex;


    public FieldRef(int classIndex, int nameAndTypeIndex) {
        super(ConstantPoolType.Fieldref);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public int getClassIndex() {
        return classIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }


}
