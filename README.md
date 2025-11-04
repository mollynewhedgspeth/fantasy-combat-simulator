# Fantasy Combat Simulator - ML-Powered Enemy AI Lab

## Lab Overview
Build an AI enemy bot that uses Machine Learning to decide between Strong and Weak attacks based on combat situations, while following SOLID principles for clean, extensible code.

## Prerequisites
- Java 11 or higher (Java 17 recommended)
- IntelliJ IDEA Community Edition

## Package Structure
```
com/
├── combat/
│   ├── app/
│   │   └── SimulatorApp.java          (Main application)
│   ├── core/
│   │   ├── Hero.java                   (Hero character)
│   │   └── Enemy.java                  (Enemy character)
│   ├── ml/
│   │   ├── DecisionModel.java          (ML interface)
│   │   ├── DecisionInput.java          (Feature data)
│   │   ├── DecisionTreeModel.java      (Concrete ML model #1)
│   │   └── NaiveBayesModel.java        (Concrete ML model #2)
│   └── strategy/
│       ├── AttackStrategy.java         (Attack interface)
│       ├── StrongAttackStrategy.java   (Strong attack)
│       ├── WeakAttackStrategy.java     (Weak attack)
│       └── AdaptiveAttack.java         (ML-powered attack)
```

## Build & Run
```bash
# Compile
mvn compile

# Run
mvn exec:java

# Package as JAR
mvn package
```
---

## Lab Steps

### **Step 1: Define ML Strategy Contracts (10 minutes)**
**Focus: ISP & DIP**

1. Create `com.combat.ml.DecisionInput.java`
    - Data class holding combat features
    - Makes it easy to add new features later (SRP)

2. Create `com.combat.ml.DecisionModel.java`
    - Core ML abstraction (interface)
    - High-level code depends on this, not concrete models (DIP)
    - Small, focused interface (ISP)

**Key Concept:** Abstractions allow swapping implementations without changing dependent code.

---

### **Step 2: Implement Concrete ML Strategies (15 minutes)**
**Focus: OCP & LSP**

3. Create `com.combat.ml.DecisionTreeModel.java`
    - First ML model implementation
    - Uses branching logic (if-else tree)

4. Create `com.combat.ml.NaiveBayesModel.java`
    - Second ML model implementation
    - Uses threat scoring logic
    - Proves OCP: added WITHOUT modifying existing code
    - Proves LSP: can substitute DecisionTreeModel seamlessly

**Key Concept:** New models extend functionality without modifying existing code.

---

### **Step 3: Create ML-Driven Attack Strategy (15 minutes)**
**Focus: SRP**

5. Create attack interfaces and implementations:
    - `com.combat.strategy.AttackStrategy.java` (interface)
    - `com.combat.strategy.StrongAttackStrategy.java`
    - `com.combat.strategy.WeakAttackStrategy.java`

6. Create `com.combat.strategy.AdaptiveAttack.java`
    - **Single Responsibility:** Orchestrate ML decision → attack execution
    - Delegates to DecisionModel for predictions
    - Delegates to concrete attacks for damage
    - Clean separation of concerns

**Key Concept:** Each class should do ONE thing and do it well.

---

### **Step 4: Update Hero and Run Simulation (15 minutes)**
**Focus: DIP & LSP**

7. Create `com.combat.core.Hero.java`
    - Manages hero state (health, armor)
    - Provides data for ML features

8. Create `com.combat.core.Enemy.java`
    - Uses AttackStrategy abstraction (DIP)
    - Works with ANY attack strategy (LSP)

9. Create `com.combat.app.SimulatorApp.java`
    - Main application demonstrating the complete system
    - Shows easy model swapping
    - Runs two scenarios with different ML models

**Key Concept:** Depend on abstractions, not concretions.

---

## Running the Lab

### In IntelliJ:

1. **Create new Java project:**
    - File → New → Project
    - Name: "FantasyCombatSimulator"
    - JDK: 17 (or 11+)

2. **Add all files:**
    - Right-click `src` folder
    - New → Package → `com.combat.app` (create each package)
    - Copy corresponding `.java` files into each package

3. **Run the simulation:**
    - Right-click `SimulatorApp.java`
    - Select "Run 'SimulatorApp.main()'"

4. **Expected output:**
    - Two combat scenarios
    - Different ML models making different decisions
    - Clear demonstration of SOLID principles

---

## SOLID Principles Demonstrated

### ✅ **Single Responsibility Principle (SRP)**
- `DecisionInput`: Only handles feature data
- `AdaptiveAttack`: Only orchestrates decisions
- `Hero`: Only manages hero state
- Each class has ONE reason to change

### ✅ **Open/Closed Principle (OCP)**
- Add new ML models by implementing `DecisionModel`
- Add new attacks by implementing `AttackStrategy`
- No modification to existing code required

### ✅ **Liskov Substitution Principle (LSP)**
- `DecisionTreeModel` and `NaiveBayesModel` are interchangeable
- System works identically regardless of which model is used
- Any `AttackStrategy` can replace another

### ✅ **Interface Segregation Principle (ISP)**
- `DecisionModel`: Only 2-3 essential methods
- `AttackStrategy`: Only 2 essential methods
- No client depends on methods it doesn't use

### ✅ **Dependency Inversion Principle (DIP)**
- High-level `AdaptiveAttack` depends on abstractions
- Not on concrete `DecisionTreeModel` or `StrongAttackStrategy`
- Dependencies injected via constructor

---

## Extension Exercises

Try these to practice SOLID principles:

1. **Add a new ML model** (OCP):
    - Create `RandomForestModel.java`
    - Implement different decision logic
    - No changes to existing code!

2. **Add new features** (SRP):
    - Modify `DecisionInput` to include:
        - Enemy health percentage
        - Distance between combatants
        - Hero mana level
    - Update models to use new features

3. **Add new attack types** (OCP, LSP):
    - Create `MagicAttackStrategy.java`
    - Create `DefensiveAttackStrategy.java`
    - Modify models to return more attack types

4. **Add confidence thresholds**:
    - Only execute prediction if confidence > 70%
    - Otherwise, use default attack

---

## Learning Outcomes

After completing this lab, you should understand:

1. ✅ How to design extensible ML systems using SOLID principles
2. ✅ The difference between depending on abstractions vs. concrete classes
3. ✅ How to add functionality without modifying existing code
4. ✅ Why each class should have a single, clear responsibility
5. ✅ How to make systems testable and maintainable

---

## Common Issues & Solutions

**Issue:** "Package does not exist"
- **Solution:** Ensure package declarations match folder structure
- Right-click → Refactor → Move to correct package

**Issue:** "Cannot find symbol"
- **Solution:** Check import statements at top of file
- IntelliJ shortcut: `Alt+Enter` on red errors for auto-import

**Issue:** "Main method not found"
- **Solution:** Ensure `SimulatorApp` has:
  ```java
  public static void main(String[] args) { ... }
  ```

---

## Questions for Reflection

1. What would happen if we made `AdaptiveAttack` depend directly on `DecisionTreeModel` instead of `DecisionModel`?

2. How does the SRP make testing easier? What would you test in `DecisionInput` vs. `AdaptiveAttack`?

3. If you wanted to add a "Critical Hit" mechanic, where would you add it without violating SOLID principles?

4. How would you add logging/telemetry to track ML decision accuracy over time?

---

## Resources

- **SOLID Principles:** https://en.wikipedia.org/wiki/SOLID
- **Strategy Pattern:** https://refactoring.guru/design-patterns/strategy
- **Dependency Injection:** https://en.wikipedia.org/wiki/Dependency_injection

---

**Happy Coding! 🚀**