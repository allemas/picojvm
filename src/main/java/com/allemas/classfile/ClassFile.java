package com.allemas.classfile;

import java.util.Set;

public class ClassFile {
    int magicNumber;
    Integer minVersion;
    Integer maxVersion;
    Set<Flag> flags;

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

    public Set<Flag> getFlags() {
        return flags;
    }

    public void setFlags(Set<Flag> flags) {
        this.flags = flags;
    }


    @Override
    public String toString() {
        return String.format("ClassFile=(magicNumber=%s,minVersion=%s,maxVersion=%s)", magicNumber, minVersion, maxVersion);
    }
}
