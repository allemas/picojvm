# picojvm

> A tiny Java Virtual Machine built from scratch — part of the **Bytecode Adventure**.

---

## What is picojvm?

**picojvm** is an **experimental** project:  
the goal is to **rebuild a JVM from scratch**, understanding and explaining each piece of the journey.

Starting from:
- Parsing `.class` files manually
- Interpreting a minimal subset of Java bytecode
- Managing a simple stack frame
- Building a tiny class loader
- Executing basic Java programs

...and eventually compiling the result to a native executable with **GraalVM**.

## Why?

Because the one way to **really** understand something... is to rebuild it.

Through **picojvm**, we explore:
- How a `.class` file is structured
- How bytecode instructions are read and interpreted
- How method calls and stack management work under the hood
- How class loading happens

Along the way, expect mistakes, experiments, and a lot of learning.  
This is a **Bytecode Adventure**, not a perfect reimplementation.

## ❤️ A humble note and Acknowledgements

This project is both a technical challenge and a learning journey.
I'm sharing the process as it happens, including possible mistakes and corrections along the way — because learning publicly is how we grow.

A **big thank you** to the open-source community and the developers of the following JVM projects, whose code provides invaluable inspiration and learning for picojvm:

- **Parsing and VM core:** [jjvm](https://github.com/k0kubun/jjvm/tree/master) by k0kubun
- **ClassFile structure and access flags:** [java-class-parser](https://github.com/viridiansoftware/java-class-parser) by viridiansoftware

Your work is essential to the progress of this project!

Feel free to contribute, correct, or simply discuss! 🙏