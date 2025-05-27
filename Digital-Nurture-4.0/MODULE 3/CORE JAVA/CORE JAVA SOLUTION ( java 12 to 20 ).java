// For exercises 12 to 20

import java.util.*;
import java.io.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class CoreJavaExercises_12to20 {

    // 12. Method Overloading
    static class MethodOverloading {
        public int add(int a, int b) {
            return a + b;
        }
        public double add(double a, double b) {
            return a + b;
        }
        public int add(int a, int b, int c) {
            return a + b + c;
        }
    }

    // 13. Recursive Fibonacci
    static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // 14. Array Sum and Average
    static void arraySumAverage(Scanner sc) {
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter elements:");
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        int sum = 0;
        for (int num : arr) sum += num;
        double avg = (double) sum / n;
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + avg);
    }

    // 15. String Reversal
    static void stringReversal(Scanner sc) {
        System.out.print("Enter a string: ");
        sc.nextLine(); // consume newline
        String input = sc.nextLine();
        StringBuilder reversed = new StringBuilder(input).reverse();
        System.out.println("Reversed string: " + reversed);
    }

    // 16. Palindrome Checker
    static void palindromeChecker(Scanner sc) {
        System.out.print("Enter a string: ");
        sc.nextLine(); // consume newline if needed
        String input = sc.nextLine();
        String cleaned = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String reversed = new StringBuilder(cleaned).reverse().toString();
        if (cleaned.equals(reversed)) {
            System.out.println("The string is a palindrome.");
        } else {
            System.out.println("The string is not a palindrome.");
        }
    }

    // 17. Class and Object Creation
    static class Car {
        String make, model;
        int year;

        Car(String make, String model, int year) {
            this.make = make;
            this.model = model;
            this.year = year;
        }

        void displayDetails() {
            System.out.println("Car Make: " + make);
            System.out.println("Car Model: " + model);
            System.out.println("Year: " + year);
        }
    }

    // 18. Inheritance Example
    static class Animal {
        void makeSound() {
            System.out.println("Animal sound");
        }
    }

    static class Dog extends Animal {
        @Override
        void makeSound() {
            System.out.println("Bark");
        }
    }

    // 19. Interface Implementation
    interface Playable {
        void play();
    }

    static class Guitar implements Playable {
        public void play() {
            System.out.println("Playing the guitar");
        }
    }

    static class Piano implements Playable {
        public void play() {
            System.out.println("Playing the piano");
        }
    }

    // 20. Try-Catch Example
    static void tryCatchExample(Scanner sc) {
        try {
            System.out.print("Enter first integer: ");
            int a = sc.nextInt();
            System.out.print("Enter second integer: ");
            int b = sc.nextInt();
            int result = a / b;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Division by zero is not allowed.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.close();
    }
}

