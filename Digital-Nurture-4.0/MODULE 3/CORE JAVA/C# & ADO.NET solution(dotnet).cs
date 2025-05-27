using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Text.Encodings.Web;
using System.Text.Json;
using System.Threading;
using System.Threading.Tasks;
using System.Data;
using System.Data.SqlClient;

#region 1. Hello World
class HelloWorld
{
    public static void Run() => Console.WriteLine("Hello, World!");
}
#endregion

#region 2. Value vs Reference Types
class MyClass { public int Value; }
class ValueVsReference
{
    static void ChangeValueType(int x) { x = 100; }
    static void ChangeReferenceType(MyClass obj) { obj.Value = 100; }
    public static void Run()
    {
        int a = 10;
        MyClass myObj = new() { Value = 10 };
        Console.WriteLine($"Before: a = {a}, obj.Value = {myObj.Value}");
        ChangeValueType(a);
        ChangeReferenceType(myObj);
        Console.WriteLine($"After: a = {a}, obj.Value = {myObj.Value}");
    }
}
#endregion

#region 3. Primary Constructors (C# 12)
public class Person(string name, int age)
{
    public string Name { get; } = name;
    public int Age { get; } = age;
    public void Display() => Console.WriteLine($"Name: {Name}, Age: {Age}");
}
#endregion

#region 4. Type Inference
class TypeInference
{
    public static void Run()
    {
        var number = 42;
        var text = "Hello";
        var obj = new MyClass();
        MyClass obj2 = new();
        Console.WriteLine($"{number.GetType()} - {number}");
        Console.WriteLine($"{text.GetType()} - {text}");
        Console.WriteLine($"{obj.GetType()} - Value: {obj.Value}");
        Console.WriteLine($"{obj2.GetType()} - Value: {obj2.Value}");
    }
}
#endregion

#region 5. Grade Calculation
class GradeCalc
{
    public static void Run()
    {
        Console.Write("Enter score (0-100): ");
        int score = int.Parse(Console.ReadLine());
        string grade = score switch
        {
            >= 90 => "A",
            >= 80 => "B",
            >= 70 => "C",
            >= 60 => "D",
            _ => "F"
        };
        Console.WriteLine($"Grade: {grade}");
    }
}
#endregion

#region 6. Loop Through Array
class LoopArray
{
    public static void Run()
    {
        int[] nums = { 1, 2, 3, 4, 5 };
        Console.WriteLine("For loop:");
        for (int i = 0; i < nums.Length; i++) Console.WriteLine(nums[i]);
        Console.WriteLine("Foreach:");
        foreach (var num in nums) Console.WriteLine(num);
        Console.WriteLine("While:");
        int j = 0;
        while (j < nums.Length) Console.WriteLine(nums[j++]);
        Console.WriteLine("Do-While:");
        int k = 0;
        do { Console.WriteLine(nums[k++]); } while (k < nums.Length);
    }
}
#endregion

#region 7. Method Overloading
class Overloading
{
    static int CalculateTotal(int a, int b) => a + b;
    static double CalculateTotal(double a, double b, double c) => a + b + c;
    public static void Run()
    {
        Console.WriteLine(CalculateTotal(2, 3));
        Console.WriteLine(CalculateTotal(1.1, 2.2, 3.3));
    }
}
#endregion

#region 8. ref, out, in Parameters
class RefOutIn
{
    static void RefMethod(ref int x) { x *= 2; }
    static void OutMethod(out int y) { y = 42; }
    static void InMethod(in int z) => Console.WriteLine($"In: {z}");
    public static void Run()
    {
        int a = 5, b;
        RefMethod(ref a);
        OutMethod(out b);
        InMethod(a);
        Console.WriteLine($"Ref: {a}, Out: {b}");
    }
}
#endregion

#region 9. Local Functions
class LocalFunctionDemo
{
    public static void Run()
    {
        int CalculateFactorial(int n)
        {
            int Factorial(int x) => x <= 1 ? 1 : x * Factorial(x - 1);
            return Factorial(n);
        }
        Console.WriteLine(CalculateFactorial(5));
    }
}
#endregion

#region 10. OOP Basics with Constructors
class Car
{
    public string Make, Model;
    public int Year;
    public Car() { Make = "Honda"; Model = "Civic"; Year = 2020; }
    public Car(string make, string model, int year) { Make = make; Model = model; Year = year; }
    public void Display() => Console.WriteLine($"{Make} {Model} ({Year})");
}
#endregion

#region 11. Access Modifiers
class Base
{
    public int Pub = 1;
    private int Pri = 2;
    protected int Pro = 3;
    public void ShowBase() => Console.WriteLine($"Public: {Pub}, Private: {Pri}, Protected: {Pro}");
}
class Derived : Base
{
    public void ShowDerived() => Console.WriteLine($"Inherited Public: {Pub}, Protected: {Pro}");
}
#endregion

#region 12. Auto-Properties and Backing Fields
class Product
{
    public string Name { get; set; }
    private decimal price;
    public decimal Price
    {
        get => price;
        set => price = value < 0 ? 0 : value;
    }
}
#endregion

#region 13. Records with init
public record Employee
{
    public string Name { get; init; }
    public int Id { get; init; }
}
#endregion

#region 14. Inheritance and Overriding
class Shape { public virtual void Draw() => Console.WriteLine("Drawing Shape"); }
class Circle : Shape { public override void Draw() => Console.WriteLine("Drawing Circle"); }
class Rectangle : Shape { public override void Draw() => Console.WriteLine("Drawing Rectangle"); }
#endregion

#region 15. Abstract Classes and Interfaces
abstract class Vehicle { public abstract void Drive(); }
interface IDrivable { void Start(); }
class Car2 : Vehicle, IDrivable
{
    public override void Drive() => Console.WriteLine("Car driving...");
    public void Start() => Console.WriteLine("Car starting...");
}
#endregion

#region 16. Null References
class Person2
{
    public string? Name { get; set; }
}
#endregion

#region 17. Null-Conditional Chaining
class Contact
{
    public string? Name { get; set; }
    public string? PhoneNumber { get; set; }
}
#endregion

#region 18. Required Modifier (C# 12)
class Student
{
    public required string Name { get; init; }
    public required int RollNo { get; init; }
}
#endregion

#region 19. Lists and Dictionaries
class ListDictDemo
{
    public static void Run()
    {
        var names = new List<string> { "Alice", "Bob" };
        var dict = new Dictionary<int, string> { { 1, "One" }, { 2, "Two" } };
        foreach (var name in names) Console.WriteLine(name);
        foreach (var kv in dict) Console.WriteLine($"{kv.Key}: {kv.Value}");
    }
}
#endregion

#region 20. LINQ Filtering
class Order
{
    public int OrderId;
    public string CustomerName;
    public double TotalAmount;
}
class LINQDemo
{
    public static void Run()
    {
        var orders = new List<Order>
        {
            new() { OrderId = 1, CustomerName = "Alice", TotalAmount = 250 },
            new() { OrderId = 2, CustomerName = "Bob", TotalAmount = 100 }
        };
        var result = orders.Where(o => o.TotalAmount > 150)
                           .Select(o => new { o.CustomerName, o.TotalAmount });
        foreach (var r in result) Console.WriteLine($"{r.CustomerName} - {r.TotalAmount}");
    }
}
#endregion

#region 21. Pattern Matching
class PatternMatching
{
    public static void Run(object obj)
    {
        switch (obj)
        {
            case int i: Console.WriteLine($"Integer: {i}"); break;
            case string s: Console.WriteLine($"String: {s}"); break;
            default: Console.WriteLine("Unknown type"); break;
        }
    }
}
#endregion

#region 22. Tuples
class TupleDemo
{
    static (int, string) GetInfo() => (1, "Test");
    public static void Run()
    {
        var (id, name) = GetInfo();
        Console.WriteLine($"ID: {id}, Name: {name}");
    }
}
#endregion

#region 23. Async File Upload
class AsyncUpload
{
    public static async Task Run()
    {
        try
        {
            await Task.Delay(3000);
            Console.WriteLine("Upload successful!");
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Error: {ex.Message}");
        }
    }
}
#endregion

#region 24. JSON Serialization
class User
{
    public string Name { get; set; }
    public int Age { get; set; }
    public string Email { get; set; }
}
class JsonDemo
{
    public static void Run()
    {
        var user = new User { Name = "John", Age = 30, Email = "john@example.com" };
        var json = JsonSerializer.Serialize(user);
        File.WriteAllText("user.json", json);
        var deserialized = JsonSerializer.Deserialize<User>(File.ReadAllText("user.json"));
        Console.WriteLine($"{deserialized.Name}, {deserialized.Age}, {deserialized.Email}");
    }
}
#endregion

#region 25. FileStream and MemoryStream
class StreamDemo
{
    public static void Run()
    {
        using var fs = new FileStream("test.txt", FileMode.OpenOrCreate);
        using var writer = new StreamWriter(fs);
        writer.Write("Hello Stream");
        writer.Flush();

        using var ms = new MemoryStream();
        var buffer = System.Text.Encoding.UTF8.GetBytes("MemoryStream Data");
        ms.Write(buffer, 0, buffer.Length);
        Console.WriteLine($"Bytes written: {ms.Length}");
    }
}
#endregion

#region 26. Race Conditions
class RaceCondition
{
    static int counter = 0;
    static object lockObj = new();
    public static void Run()
    {
        var threads = new Thread[10];
        for (int i = 0; i < threads.Length; i++)
        {
            threads[i] = new Thread(() =>
            {
                for (int j = 0; j < 1000; j++)
                {
                    lock (lockObj) counter++;
                }
            });
            threads[i].Start();
        }
        foreach (var t in threads) t.Join();
        Console.WriteLine($"Counter: {counter}");
    }
}
#endregion

#region 27. Deadlock Simulation
class Deadlock
{
    static object lock1 = new(), lock2 = new();
    public static void Run()
    {
        Thread t1 = new(() =>
        {
            if (Monitor.TryEnter(lock1, 1000))
            {
                Thread.Sleep(100);
                if (Monitor.TryEnter(lock2, 1000))
                {
                    Console.WriteLine("Thread1 acquired both locks");
                    Monitor.Exit(lock2);
                }
                Monitor.Exit(lock1);
            }
        });

        Thread t2 = new(() =>
        {
            if (Monitor.TryEnter(lock2, 1000))
            {
                Thread.Sleep(100);
                if (Monitor.TryEnter(lock1, 1000))
                {
                    Console.WriteLine("Thread2 acquired both locks");
                    Monitor.Exit(lock1);
                }
                Monitor.Exit(lock2);
            }
        });

        t1.Start(); t2.Start();
        t1.Join(); t2.Join();
    }
}
#endregion

#region 28. Trace Logging
class Logger
{
    public static void Run()
    {
        TextWriterTraceListener twtl = new("log.txt");
        Trace.Listeners.Add(twtl);
        Trace.WriteLine("Logging message");
        Trace.Flush();
        Console.WriteLine("Logged to file");
    }
}
#endregion

#region 29. Sanitize Input
class SanitizeInput
{
    public static void Run()
    {
        Console.Write("Enter input: ");
        var input = Console.ReadLine();
        var encoded = HtmlEncoder.Default.Encode(input);
        Console.WriteLine($"Sanitized: {encoded}");
    }
}
#endregion

#region 30. CRUD with ADO.NET
class AdoCrud
{
    public static void Run()
    {
        string connStr = "Server=localhost;Database=YourDb;Trusted_Connection=True;";
        using var conn = new SqlConnection(connStr);
        conn.Open();
        var cmd = new SqlCommand("SELECT * FROM Employees", conn);
        using var reader = cmd.ExecuteReader();
        while (reader.Read())
        {
            Console.WriteLine($"{reader["Id"]}, {reader["Name"]}");
        }
    }
}
#endregion

class Program
{
    static async Task Main()
    {
        HelloWorld.Run();
        ValueVsReference.Run();
        new Person("Alice", 30).Display();
        TypeInference.Run();
        GradeCalc.Run();
        LoopArray.Run();
        Overloading.Run();
        RefOutIn.Run();
        LocalFunctionDemo.Run();
        var car1 = new Car();
        var car2 = new Car("Toyota", "Camry", 2022);
        car1.Display(); car2.Display();
        new Base().ShowBase();
        new Derived().ShowDerived();
        var product = new Product { Name = "Item", Price = -50 };
        Console.WriteLine($"{product.Name} - {product.Price}");
        var emp1 = new Employee { Name = "Sam", Id = 1 };
        var emp2 = emp1 with { Name = "Max" };
        Console.WriteLine(emp1); Console.WriteLine(emp2);
        new Circle().Draw(); new Rectangle().Draw();
        var car3 = new Car2(); car3.Start(); car3.Drive();
        Person2 p = null; Console.WriteLine(p?.Name ?? "Name is null");
        var contact = new Contact(); Console.WriteLine(contact?.Name ?? "No name");
        var student = new Student { Name = "Jane", RollNo = 123 };
        ListDictDemo.Run();
        LINQDemo.Run();
        PatternMatching.Run("Hello");
        TupleDemo.Run();
        await AsyncUpload.Run();
        JsonDemo.Run();
        StreamDemo.Run();
        RaceCondition.Run();
        Deadlock.Run();
        Logger.Run();
        SanitizeInput.Run();
        AdoCrud.Run();
    }
}
