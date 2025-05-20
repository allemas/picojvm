package com.allemas.classfile.field;


import com.allemas.classfile.AttributeInfo;
import com.allemas.classfile.ConstantPoolType;
import com.allemas.classfile.Flag;
import com.allemas.classfile.constantpool.ConstantPoolInfo;
import com.allemas.classfile.constantpool.Utf8;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * field_info {
 * u2             access_flags;
 * u2             name_index;
 * u2             descriptor_index;
 * u2             attributes_count;
 * attribute_info attributes[attributes_count];
 * }
 */
public class FieldInfo {
    private List<Flag> accessFlags;
    private String name;
    private String description;
    private AttributeInfo[] attributeInfos;


    public FieldInfo(
            List<Flag> accessFlags, String name, String description, AttributeInfo[] attributeInfo) {

        this.accessFlags = accessFlags;
        this.name = name;
        this.description = description;
        this.attributeInfos = attributeInfo;

    }
}
