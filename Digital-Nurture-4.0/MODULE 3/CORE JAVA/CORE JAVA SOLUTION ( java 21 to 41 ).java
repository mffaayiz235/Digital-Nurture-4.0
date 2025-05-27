import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.net.*;
import java.net.http.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class CoreJavaExercises_21to41 {

    // 21. Custom Exception
    static class InvalidAgeException extends Exception {
        public InvalidAgeException(String message) {
            super(message);
        }
    }

    static void checkAge(int age) throws InvalidAgeException {
        if (age < 18) throw new InvalidAgeException("Age must be at least 18.");
        System.out.println("Age is valid.");
    }

    // 22. File Writing
    static void writeFile(Scanner sc) {
        System.out.print("Enter text to write to output.txt: ");
        sc.nextLine();
        String text = sc.nextLine();
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("output.txt"))) {
            writer.write(text);
            System.out.println("Data written to output.txt successfully.");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    // 23. File Reading
    static void readFile() {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("output.txt"))) {
            String line;
            System.out.println("Contents of output.txt:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // 24. ArrayList Example
    static void arrayListExample(Scanner sc) {
        ArrayList<String> names = new ArrayList<>();
        System.out.println("Enter student names (type 'end' to stop):");
        while (true) {
            String name = sc.next();
            if (name.equalsIgnoreCase("end")) break;
            names.add(name);
        }
        System.out.println("Names entered: " + names);
    }

    // 25. HashMap Example
    static void hashMapExample(Scanner sc) {
        HashMap<Integer, String> studentMap = new HashMap<>();
        System.out.println("Enter student ID and name (ID=0 to stop):");
        while (true) {
            System.out.print("ID: ");
            int id = sc.nextInt();
            if (id == 0) break;
            System.out.print("Name: ");
            String name = sc.next();
            studentMap.put(id, name);
        }
        System.out.print("Enter ID to search: ");
        int searchId = sc.nextInt();
        String result = studentMap.get(searchId);
        if (result != null) {
            System.out.println("Student name: " + result);
        } else {
            System.out.println("Student not found.");
        }
    }

    // 26. Thread Creation
    static class MyThread extends Thread {
        String name;
        MyThread(String name) { this.name = name; }
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(name + " is running: " + i);
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
        }
    }

    // 27. Lambda Expressions
    static void lambdaExpressions() {
        List<String> fruits = new ArrayList<>(Arrays.asList("banana", "apple", "orange", "mango"));
        fruits.sort((a, b) -> a.compareToIgnoreCase(b));
        System.out.println("Sorted fruits: " + fruits);
    }

    // 28. Stream API
    static void streamApi() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evens = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Even numbers: " + evens);
    }

    // 29. Records (Java 16+)
    public record Person(String name, int age) {}

    static void recordsExample() {
        List<Person> people = List.of(
                new Person("Alice", 30),
                new Person("Bob", 20),
                new Person("Charlie", 25)
        );
        System.out.println("People older than 22:");
        people.stream()
              .filter(p -> p.age() > 22)
              .forEach(System.out::println);
    }

    // 30. Pattern Matching for switch (Java 21)
    static void patternMatchingSwitch(Object obj) {
        String result = switch (obj) {
            case Integer i -> "Integer: " + i;
            case String s -> "String: " + s;
            case Double d -> "Double: " + d;
            default -> "Unknown type";
        };
        System.out.println(result);
    }

    // 31. Basic JDBC Connection (Example for SQLite)
    static void jdbcConnection() {
        String url = "jdbc:sqlite:test.db"; // SQLite DB in current directory
        String query = "SELECT name FROM students"; // Assume table 'students' with 'name' column

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Student names:");
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("JDBC Error: " + e.getMessage());
        }
    }

    // 32. Insert and Update Operations in JDBC
    static class StudentDAO {
        private final Connection conn;
        public StudentDAO(Connection conn) { this.conn = conn; }

        public void insertStudent(int id, String name) throws SQLException {
            String sql = "INSERT INTO students(id, name) VALUES (?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.setString(2, name);
                ps.executeUpdate();
            }
        }

        public void updateStudentName(int id, String newName) throws SQLException {
            String sql = "UPDATE students SET name = ? WHERE id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, newName);
                ps.setInt(2, id);
                ps.executeUpdate();
            }
        }
    }

    // 33. Transaction Handling in JDBC
    static void transferMoney(Connection conn, int fromAccount, int toAccount, double amount) {
        try {
            conn.setAutoCommit(false);
            String debitSql = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
            String creditSql = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
            try (PreparedStatement debit = conn.prepareStatement(debitSql);
                 PreparedStatement credit = conn.prepareStatement(creditSql)) {
                debit.setDouble(1, amount);
                debit.setInt(2, fromAccount);
                debit.executeUpdate();

                credit.setDouble(1, amount);
                credit.setInt(2, toAccount);
                credit.executeUpdate();

                conn.commit();
                System.out.println("Transfer successful.");
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Transfer failed: " + e.getMessage());
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.out.println("Transaction error: " + e.getMessage());
        }
    }


    // 35. TCP Client-Server Chat (Server part)
    static class TCPServer {
        public static void startServer() {
            try (ServerSocket serverSocket = new ServerSocket(12345)) {
                System.out.println("Server listening on port 12345...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected.");
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                String msg;
                Scanner sc = new Scanner(System.in);
                while ((msg = in.readLine()) != null) {
                    System.out.println("Client: " + msg);
                    System.out.print("Server: ");
                    String reply = sc.nextLine();
                    out.println(reply);
                    if (reply.equalsIgnoreCase("bye")) break;
                }
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Server error: " + e.getMessage());
            }
        }
    }

    // 36. HTTP Client API (Java 11+)
    static void httpClientExample() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com/"))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
              .thenApply(HttpResponse::body)
              .thenAccept(System.out::println)
              .join();
    }



    // 39. Reflection in Java
    static void reflectionExample() {
        try {
            Class<?> clazz = Class.forName("java.util.ArrayList");
            System.out.println("Methods of ArrayList:");
            Arrays.stream(clazz.getMethods())
                    .map(m -> m.getName())
                    .distinct()
                    .sorted()
                    .forEach(System.out::println);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found.");
        }
    }

    // 40. Annotations Example
    @interface MyAnnotation {
        String author();
        String date();
    }

    @MyAnnotation(author = "ChatGPT", date = "2025-05-27")
    static class AnnotatedClass {
        void show() {
            System.out.println("Inside annotated class.");
        }
    }

    static void printAnnotation() {
        Class<AnnotatedClass> cls = AnnotatedClass.class;
        if (cls.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation ann = cls.getAnnotation(MyAnnotation.class);
            System.out.println("Author: " + ann.author());
            System.out.println("Date: " + ann.date());
        }
    }

    // 41. Generics Example
    static class Box<T> {
        private T t;
        public void set(T t) { this.t = t; }
        public T get() { return t; }
    }

    static void genericsExample() {
        Box<Integer> integerBox = new Box<>();
        integerBox.set(10);
        System.out.println("Integer Value: " + integerBox.get());

        Box<String> stringBox = new Box<>();
        stringBox.set("Hello Generics");
        System.out.println("String Value: " + stringBox.get());
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        sc.close();
    }
}
