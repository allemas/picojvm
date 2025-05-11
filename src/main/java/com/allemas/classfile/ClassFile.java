package com.allemas.classfile;

import com.allemas.classfile.constantpool.ConstantPoolInfo;

import java.util.List;
import java.util.Set;

public class ClassFile {
    int magicNumber;
    Integer minVersion;
    Integer maxVersion;

    public ConstantPoolInfo[] constantPoolInfos;
    List<Flag> flags;

    ConstantPoolInfo thisClass;
    ConstantPoolInfo thisSuperclass;


    public int getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    public Integer getMinVersion() {
        return minVersion;
    }

    public void setMinVersion(Integer minVersion) {
        this.minVersion = minVersion;
    }

    public Integer getMaxVersion() {
        return maxVersion;
    }

    public void setMaxVersion(Integer maxVersion) {
        this.maxVersion = maxVersion;
    }

    public List<Flag> getFlags() {
        return flags;
    }

    public void setFlags(List<Flag> flags) {
        this.flags = flags;
    }

    public ConstantPoolInfo[] getConstantPoolInfos() {
        return constantPoolInfos;
    }

    public void setConstantPoolInfos(ConstantPoolInfo[] constantPoolInfos) {
        this.constantPoolInfos = constantPoolInfos;
    }

    public void setThisClass(int thisClassIndex) {
        this.thisClass = constantPoolInfos[thisClassIndex - 1];
    }

    public void setThisSuperclass(int thisSuperclass) {
        this.thisSuperclass = constantPoolInfos[thisSuperclass - 1];
    }


    @Override
    public String toString() {
        return "ClassFile [magicNumber=" + magicNumber + ", minVersion=" + minVersion
                + ", maxVersion=" + maxVersion + ", constantPoolInfos=" + constantPoolInfos
                + ", flags=" + flags + ", thisClass=" + thisClass + ", thisSuperclass="
                + thisSuperclass + "]";

    }
}
