package com.allemas.classfile;

public class Instruction {

    private final Opcode opcode;
    private final byte[] operands;
    private final int padSize;

    Instruction(Opcode opcode, byte[] operands, int padSize) {
        this.opcode = opcode;
        this.operands = operands;
        this.padSize = padSize;
    }





}
