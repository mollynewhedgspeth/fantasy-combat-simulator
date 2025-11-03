# Fantasy Combat Simulator - Quick Start Guide

## 🚀 Get Started in 5 Minutes!

### What You're Building
An AI enemy that uses Machine Learning to choose between Strong and Weak attacks based on:
- Hero's current health
- Hero's armor rating

Two different ML models will make DIFFERENT decisions in the same situation!

---

## Step-by-Step Setup

### 1️⃣ Download All Files (1 minute)
All files are in the `com/` folder with proper package structure:
```
com/combat/
├── app/SimulatorApp.java
├── core/Hero.java, Enemy.java
├── ml/DecisionModel.java, DecisionInput.java, DecisionTreeModel.java, NaiveBayesModel.java
└── strategy/AttackStrategy.java, StrongAttackStrategy.java, WeakAttackStrategy.java, AdaptiveAttack.java
```

### 2️⃣ Create IntelliJ Project (2 minutes)
1. Open IntelliJ IDEA
2. **File → New → Project**
3. Name: `FantasyCombatSimulator`
4. JDK: 17 (or 11+)
5. Click **Create**

### 3️⃣ Copy Files (1 minute)
1. Right-click on `src` folder
2. Select **New → Directory**
3. Name it `com`
4. Drag the downloaded `com` folder into `src`
    - Or manually recreate the package structure and copy files

### 4️⃣ Run the Simulation (1 minute)
1. Navigate to `com.combat.app.SimulatorApp`
2. Right-click on the file
3. Select **Run 'SimulatorApp.main()'**
4. Watch the AI in action! 🎉

---

## What You'll See

### Scenario 1: Decision Tree Model
```
🎮 SCENARIO 1: Enemy AI with Decision Tree Model
═══════════════════════════════════════════════════════════════

🛡️  Hero[HP: 100/100, Armor: 3]

⚔️  TURN 1
───────────────────────────────────────────────────────────────
🔥 Dark Mage attacks using: ML-Adaptive Attack (Decision Tree)
   🤖 ML Model: Decision Tree
   📊 Analysis: DecisionInput[Health: 100.0%, Armor: 3]
   🎯 Decision: STRONG (Confidence: 95%)
   🗡️  STRONG ATTACK executed! Damage: 32
   💔 Hero takes 29 damage! Health: 71/100
```

### Scenario 2: Naive Bayes Model
Same hero, DIFFERENT decisions! Shows the power of swappable ML models.

---

## Understanding the Code Flow

### When Enemy Attacks:
```
1. Enemy.performAttack(hero)
   ↓
2. AdaptiveAttack.execute(hero)
   ↓
3. Creates DecisionInput(hero's health, hero's armor)
   ↓
4. model.predict(input) → returns "STRONG" or "WEAK"
   ↓
5. Selects appropriate attack (strong vs weak)
   ↓
6. attack.execute(hero) → deals damage
```

---

## SOLID Principles At Work

### 🔹 Single Responsibility (SRP)
- `DecisionInput` = Hold data
- `DecisionTreeModel` = Make predictions
- `AdaptiveAttack` = Orchestrate

### 🔹 Open/Closed (OCP)
Add `NaiveBayesModel` WITHOUT changing:
- ✅ `DecisionModel` interface
- ✅ `AdaptiveAttack` class
- ✅ Any existing code!

### 🔹 Liskov Substitution (LSP)
Replace `DecisionTreeModel` with `NaiveBayesModel`:
- ✅ System works perfectly
- ✅ No code changes needed

### 🔹 Interface Segregation (ISP)
`DecisionModel` has only 2-3 methods:
- ✅ `predict()`
- ✅ `getModelName()`
- ✅ `getConfidence()` (optional)

### 🔹 Dependency Inversion (DIP)
`AdaptiveAttack` depends on:
- ✅ `DecisionModel` interface (abstraction)
- ❌ NOT `DecisionTreeModel` (concrete class)

---

## Try These Experiments!

### 🧪 Experiment 1: Change Hero Stats
**In SimulatorApp.java, line ~55:**
```java
// Try different values:
Hero hero = new Hero(100, 3);  // Original
Hero hero = new Hero(100, 10); // High armor
Hero hero = new Hero(50, 3);   // Low health
```
**Question:** Do ML decisions change? Why?

### 🧪 Experiment 2: Add Debug Output
**In DecisionTreeModel.java, inside predict():**
```java
System.out.println("   DEBUG: Health=" + health + ", Armor=" + armor);
```
**Question:** Can you see the decision tree logic in action?

### 🧪 Experiment 3: Create New Model
**Create `com.combat.ml.AggressiveModel.java`:**
```java
public class AggressiveModel implements DecisionModel {
    public String predict(DecisionInput input) {
        return "STRONG"; // Always aggressive!
    }
    public String getModelName() {
        return "Always Aggressive";
    }
}
```
**Add to SimulatorApp as Scenario 3!**

---

## Common Questions

### Q: Why use interfaces?
**A:** Interfaces allow us to swap implementations (Decision Tree ↔ Naive Bayes) without changing any other code. That's the power of abstraction!

### Q: What's Dependency Injection?
**A:** Passing dependencies via constructor:
```java
// Good: Dependencies injected
new AdaptiveAttack(model, strongAttack, weakAttack)

// Bad: Dependencies hardcoded
class AdaptiveAttack {
    DecisionTreeModel model = new DecisionTreeModel(); // Can't swap!
}
```

### Q: Why separate DecisionInput?
**A:** Single Responsibility! It only holds data. If we want to add features (enemy health, distance), we change ONE class, not the entire system.

### Q: How is this "Machine Learning"?
**A:** We're simulating ML models. Real ML would use Weka, TensorFlow, etc. But the **architecture** is identical - that's the point!

---

## Next Steps

### ✅ Completed the Basic Lab?
Try these challenges:

1. **Add new features** to `DecisionInput`
    - Enemy health
    - Distance between combatants
    - Hero mana

2. **Create new ML models**
    - `RandomForestModel`
    - `AggressiveModel`
    - `DefensiveModel`

3. **Add new attack types**
    - `MagicAttackStrategy`
    - `DefensiveAttackStrategy`
    - `CriticalAttackStrategy`

4. **Add confidence thresholds**
    - Only use ML if confidence > 70%
    - Otherwise fall back to default

### 📚 Want to Learn More?
- Read `SOLID-PRINCIPLES.md` for detailed explanations
- Review `README.md` for complete documentation
- Use `LAB-CHECKLIST.md` to verify your understanding

---

## Troubleshooting

### ❌ "Package com.combat.app does not exist"
**Fix:** Ensure folder structure matches:
```
src/
  com/
    combat/
      app/
        SimulatorApp.java
```

### ❌ "Cannot find symbol: DecisionModel"
**Fix:** Add import at top of file:
```java
import com.combat.ml.DecisionModel;
```
Or use **Alt+Enter** for auto-import.

### ❌ "Main method not found"
**Fix:** Check `SimulatorApp.java` has:
```java
public static void main(String[] args) { ... }
```

### ❌ Different output each time?
**That's normal!** Attacks use random damage within a range.

---

## Success Checklist

- [ ] Program runs without errors
- [ ] Two scenarios show DIFFERENT decisions
- [ ] ML model names appear in output
- [ ] Damage varies between turns (randomness)
- [ ] Hero health decreases over time
- [ ] Can explain why models make different choices

---

## 🎉 You Did It!

You've built a production-quality ML system using SOLID principles!

**Key Achievement:** You can now:
- ✅ Design extensible systems
- ✅ Separate concerns cleanly
- ✅ Add features without breaking code
- ✅ Write testable, maintainable code

**Apply this to real projects:**
- Web applications
- Game AI systems
- Business logic layers
- API design

---

**Questions? Check the full README.md or ask your instructor!**

Happy coding! 🚀