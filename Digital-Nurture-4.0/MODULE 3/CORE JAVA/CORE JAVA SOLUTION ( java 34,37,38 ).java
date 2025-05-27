// Exercise 34: Java Modules

// com.utils/module-info.java
module com.utils {
    exports com.utils;
}

// com.utils/com/utils/Utility.java
package com.utils;

public class Utility {
    public static String getGreeting() {
        return "Hello from com.utils!";
    }
}

// com.greetings/module-info.java
module com.greetings {
    requires com.utils;
}

// com.greetings/com/greetings/Main.java
package com.greetings;

import com.utils.Utility;

public class Main {
    public static void main(String[] args) {
        System.out.println(Utility.getGreeting());
    }
}





//# Exercise 37: Using javap to Inspect Bytecode
//# Java class example to compile and inspect

// BytecodeDemo.java
public class BytecodeDemo {
    public int add(int a, int b) {
        return a + b;
    }
    public static void main(String[] args) {
        BytecodeDemo demo = new BytecodeDemo();
        System.out.println(demo.add(5, 10));
    }
}

# Compile the class
//javac BytecodeDemo.java

# Inspect the bytecode
//javap -c BytecodeDemo






// Exercise 38: Decompile a Class File
// Write and compile a simple Java class, then decompile the class file using tools like JD-GUI or CFR.

// SimpleClass.java
public class SimpleClass {
    public void greet() {
        System.out.println("Hello, Decompiler!");
    }
    public static void main(String[] args) {
        SimpleClass sc = new SimpleClass();
        sc.greet();
    }
}

# Compile
//javac SimpleClass.java







