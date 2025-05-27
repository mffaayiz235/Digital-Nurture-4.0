// Core Java Exercises - Full Code Solutions

// 1. Hello World
class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}

// 2. Simple Calculator
import java.util.Scanner;
class SimpleCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first number: ");
        double a = sc.nextDouble();
        System.out.print("Enter second number: ");
        double b = sc.nextDouble();
        System.out.print("Choose operation (+, -, *, /): ");
        char op = sc.next().charAt(0);
        double result = 0;
        switch(op) {
            case '+': result = a + b; break;
            case '-': result = a - b; break;
            case '*': result = a * b; break;
            case '/': result = b != 0 ? a / b : Double.NaN; break;
            default: System.out.println("Invalid operation"); return;
        }
        System.out.println("Result: " + result);
    }
}

// 3. Even or Odd Checker
import java.util.Scanner;
class EvenOddChecker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int num = sc.nextInt();
        System.out.println(num % 2 == 0 ? "Even" : "Odd");
    }
}

// 4. Leap Year Checker
import java.util.Scanner;
class LeapYearChecker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a year: ");
        int year = sc.nextInt();
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
            System.out.println("Leap Year");
        else
            System.out.println("Not a Leap Year");
    }
}

// 5. Multiplication Table
import java.util.Scanner;
class MultiplicationTable {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = sc.nextInt();
        for (int i = 1; i <= 10; i++) {
            System.out.println(num + " x " + i + " = " + (num * i));
        }
    }
}

// 6. Data Type Demonstration
class DataTypeDemo {
    public static void main(String[] args) {
        int a = 10;
        float b = 5.5f;
        double c = 20.123;
        char d = 'X';
        boolean e = true;
        System.out.println("int: " + a);
        System.out.println("float: " + b);
        System.out.println("double: " + c);
        System.out.println("char: " + d);
        System.out.println("boolean: " + e);
    }
}

// 7. Type Casting Example
class TypeCasting {
    public static void main(String[] args) {
        double d = 9.7;
        int i = (int) d;
        System.out.println("Double to int: " + i);
        int x = 5;
        double y = x;
        System.out.println("Int to double: " + y);
    }
}

// 8. Operator Precedence
class OperatorPrecedence {
    public static void main(String[] args) {
        int result = 10 + 5 * 2;
        System.out.println("Result: " + result); // Multiplication has higher precedence
    }
}

// 9. Grade Calculator
import java.util.Scanner;
class GradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter marks (0-100): ");
        int marks = sc.nextInt();
        char grade;
        if (marks >= 90) grade = 'A';
        else if (marks >= 80) grade = 'B';
        else if (marks >= 70) grade = 'C';
        else if (marks >= 60) grade = 'D';
        else grade = 'F';
        System.out.println("Grade: " + grade);
    }
}

// 10. Number Guessing Game
import java.util.Scanner;
import java.util.Random;
class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int number = rand.nextInt(100) + 1;
        int guess = 0;
        while (guess != number) {
            System.out.print("Guess the number (1-100): ");
            guess = sc.nextInt();
            if (guess > number) System.out.println("Too high!");
            else if (guess < number) System.out.println("Too low!");
            else System.out.println("Correct!");
        }
    }
}

// 11. Factorial Calculator
import java.util.Scanner;
class FactorialCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a non-negative integer: ");
        int n = sc.nextInt();
        long fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        System.out.println("Factorial: " + fact);
    }
}

