package com.allemas.classfile;

import java.text.StringCharacterIterator;
import java.util.Scanner;

public class DesciptionParser {

    public static void parseMethod(String desc) {
        StringCharacterIterator scanner = new StringCharacterIterator(desc);
        if (scanner.first() != '(')
            throw new RuntimeException("Invalid field description");



    }
}
