# SOLID Principles Quick Reference

## Overview
SOLID is an acronym for five design principles that make software more understandable, flexible, and maintainable.

---

## 🔷 **S - Single Responsibility Principle (SRP)**

**Definition:** A class should have ONE reason to change.

**In Our Lab:**
- ✅ `DecisionInput` - Only holds feature data
- ✅ `AdaptiveAttack` - Only orchestrates decisions
- ✅ `Hero` - Only manages hero state
- ❌ **BAD:** A `HeroAndAttackAndML` class that does everything

**Real-World Example:**
```java
// ❌ BAD - Multiple responsibilities
class User {
    void saveToDatabase() { ... }
    void sendEmail() { ... }
    void generateReport() { ... }
}

// ✅ GOOD - Single responsibilities
class User { ... }
class UserRepository { void save(User user) { ... } }
class EmailService { void send(User user) { ... } }
class ReportGenerator { void generate(User user) { ... } }
```

**Benefits:**
- Easier to understand
- Easier to test
- Easier to maintain
- Changes affect fewer parts of the system

---

## 🔷 **O - Open/Closed Principle (OCP)**

**Definition:** Classes should be OPEN for extension but CLOSED for modification.

**In Our Lab:**
- ✅ Add `NaiveBayesModel` WITHOUT modifying `DecisionModel` interface
- ✅ Add new attack types WITHOUT modifying `AttackStrategy` interface
- ❌ **BAD:** Modifying `AdaptiveAttack` every time we add a new ML model

**Real-World Example:**
```java
// ❌ BAD - Must modify class for new shapes
class AreaCalculator {
    double calculate(Object shape) {
        if (shape instanceof Circle) {
            return Math.PI * ((Circle)shape).radius * ((Circle)shape).radius;
        } else if (shape instanceof Square) {
            return ((Square)shape).side * ((Square)shape).side;
        }
        // Must ADD code for every new shape!
    }
}

// ✅ GOOD - Extend via interface, no modification
interface Shape {
    double area();
}
class Circle implements Shape {
    public double area() { return Math.PI * radius * radius; }
}
class Square implements Shape {
    public double area() { return side * side; }
}
// Add new shapes without modifying existing code!
```

**Benefits:**
- Add functionality without breaking existing code
- Reduces bugs from changes
- Promotes reusability

---

## 🔷 **L - Liskov Substitution Principle (LSP)**

**Definition:** Objects of a superclass should be replaceable with objects of subclasses without breaking the application.

**In Our Lab:**
- ✅ `NaiveBayesModel` can replace `DecisionTreeModel` anywhere
- ✅ System works identically with either model
- ❌ **BAD:** `NaiveBayesModel` requires different setup than `DecisionTreeModel`

**Real-World Example:**
```java
// ❌ BAD - Violates LSP
class Bird {
    void fly() { /* flies */ }
}
class Penguin extends Bird {
    void fly() { throw new Exception("Can't fly!"); } // Breaks contract!
}

// ✅ GOOD - Honors contract
interface Bird {
    void move();
}
class Sparrow implements Bird {
    void move() { fly(); }
}
class Penguin implements Bird {
    void move() { swim(); }
}
```

**Benefits:**
- Predictable behavior
- Safe polymorphism
- Easier testing with mocks

---

## 🔷 **I - Interface Segregation Principle (ISP)**

**Definition:** Clients should not be forced to depend on methods they don't use.

**In Our Lab:**
- ✅ `DecisionModel` has only essential methods
- ✅ `AttackStrategy` has only 2 methods
- ❌ **BAD:** A giant `MLModelWithEverything` interface with 20 methods

**Real-World Example:**
```java
// ❌ BAD - Fat interface
interface Worker {
    void work();
    void eat();
    void sleep();
    void code(); // Not all workers code!
}

// ✅ GOOD - Segregated interfaces
interface Workable {
    void work();
}
interface Eatable {
    void eat();
}
interface Codeable {
    void code();
}
class Programmer implements Workable, Eatable, Codeable { ... }
class Robot implements Workable { ... } // No eating!
```

**Benefits:**
- Focused, easy-to-understand interfaces
- Reduced coupling
- Easier to implement

---

## 🔷 **D - Dependency Inversion Principle (DIP)**

**Definition:** Depend on abstractions (interfaces), not on concrete classes.

**In Our Lab:**
- ✅ `AdaptiveAttack` depends on `DecisionModel` interface
- ✅ NOT on `DecisionTreeModel` concrete class
- ✅ Dependencies injected via constructor
- ❌ **BAD:** `new DecisionTreeModel()` hardcoded inside `AdaptiveAttack`

**Real-World Example:**
```java
// ❌ BAD - Depends on concrete class
class PaymentProcessor {
    private StripePayment stripe = new StripePayment(); // Tight coupling!
    
    void process() {
        stripe.charge(); // Can't swap payment providers!
    }
}

// ✅ GOOD - Depends on abstraction
interface PaymentGateway {
    void charge();
}
class PaymentProcessor {
    private PaymentGateway gateway; // Abstraction!
    
    PaymentProcessor(PaymentGateway gateway) {
        this.gateway = gateway; // Injected!
    }
    
    void process() {
        gateway.charge(); // Works with ANY gateway!
    }
}
class StripePayment implements PaymentGateway { ... }
class PayPalPayment implements PaymentGateway { ... }
```

**Benefits:**
- Easy to swap implementations
- Easier testing with mocks
- Reduced coupling
- More flexible architecture

---

## 🎯 How They Work Together

In our Fantasy Combat Simulator:

1. **SRP**: Each class has ONE job
    - `DecisionInput` = hold data
    - `DecisionTreeModel` = make predictions
    - `AdaptiveAttack` = orchestrate

2. **OCP**: Add new models/attacks without changing existing code
    - New `RandomForestModel` → implements `DecisionModel`
    - Zero changes to `AdaptiveAttack`

3. **LSP**: Models are interchangeable
    - Swap `DecisionTreeModel` ↔ `NaiveBayesModel`
    - System still works perfectly

4. **ISP**: Small, focused interfaces
    - `DecisionModel` = just `predict()` and `getModelName()`
    - Not a bloated 20-method interface

5. **DIP**: Depend on abstractions
    - `AdaptiveAttack` depends on `DecisionModel` (interface)
    - Not on `DecisionTreeModel` (concrete class)

---

## 📝 Quick Decision Guide

**When designing a new feature, ask:**

✅ **SRP**: Does this class have ONE clear responsibility?
✅ **OCP**: Can I add new behavior without modifying this class?
✅ **LSP**: Can I substitute implementations without breaking things?
✅ **ISP**: Are my interfaces small and focused?
✅ **DIP**: Am I depending on abstractions, not concrete classes?

If you answer "no" to any question, reconsider your design!

---

## 🚀 Real-World Impact

**Without SOLID:**
- Adding a new ML model requires changing 5+ files
- Testing requires real database connections
- Bug fixes break unrelated features
- Code becomes unmaintainable over time

**With SOLID:**
- Adding a new ML model = 1 new file
- Testing uses mock objects
- Bug fixes are isolated
- Code remains maintainable for years

---

## 📚 Further Reading

- **Clean Code** by Robert C. Martin
- **Design Patterns** by Gang of Four
- **Refactoring Guru**: https://refactoring.guru/design-patterns

---

**Remember:** SOLID principles are guidelines, not laws. Use judgment based on project needs!