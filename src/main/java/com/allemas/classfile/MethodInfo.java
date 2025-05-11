package com.allemas.classfile;

import java.util.List;

public class MethodInfo {

    List<Flag> flags;
    int nameIndex;
    int descriptorIndex;
    int attributeCount;
    AttributeInfo[] attributes;

    MethodInfo(int nameIndex, int descriptorIndex, int attributeCount, AttributeInfo[] attributes) {
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.attributeCount = attributeCount;
        this.attributes = attributes;
    }

}
