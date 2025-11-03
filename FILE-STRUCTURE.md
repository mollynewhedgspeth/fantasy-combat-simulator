# Fantasy Combat Simulator - Complete File Structure

```
FantasyCombatSimulator/
│
├── src/
│   └── com/
│       └── combat/
│           │
│           ├── app/
│           │   └── SimulatorApp.java          ⭐ Main entry point
│           │
│           ├── core/
│           │   ├── Hero.java                  👤 Player character
│           │   └── Enemy.java                 🤖 AI character
│           │
│           ├── ml/                            🧠 Machine Learning Layer
│           │   ├── DecisionModel.java         📝 ML interface (abstraction)
│           │   ├── DecisionInput.java         📊 Feature data
│           │   ├── DecisionTreeModel.java     🌳 Concrete ML model #1
│           │   └── NaiveBayesModel.java       🎲 Concrete ML model #2
│           │
│           └── strategy/                      ⚔️  Attack Strategy Layer
│               ├── AttackStrategy.java        📝 Attack interface
│               ├── StrongAttackStrategy.java  🗡️  High damage attack
│               ├── WeakAttackStrategy.java    ⚔️  Low damage attack
│               └── AdaptiveAttack.java        🎯 ML-powered orchestrator
│
└── Documentation/
    ├── README.md                              📖 Complete lab guide
    ├── QUICK-START.md                         🚀 5-minute setup guide
    ├── LAB-CHECKLIST.md                       ✅ Step-by-step checklist
    ├── SOLID-PRINCIPLES.md                    📚 SOLID principles explained
    └── FILE-STRUCTURE.md                      📁 This file!
```

---

## File Count
- **Java Files:** 11
- **Documentation Files:** 6
- **Total:** 17 files

---

## Package Breakdown

### 📦 com.combat.app (1 file)
**Purpose:** Application entry point
- `SimulatorApp.java` - Main class, runs combat scenarios

### 📦 com.combat.core (2 files)
**Purpose:** Core combat entities
- `Hero.java` - Player character with health/armor
- `Enemy.java` - AI character that uses strategies

### 📦 com.combat.ml (4 files)
**Purpose:** Machine Learning decision-making
- `DecisionModel.java` - Interface for ML models ⭐
- `DecisionInput.java` - Data class for features
- `DecisionTreeModel.java` - Branching logic implementation
- `NaiveBayesModel.java` - Threat scoring implementation

### 📦 com.combat.strategy (4 files)
**Purpose:** Attack behaviors and strategies
- `AttackStrategy.java` - Interface for attacks ⭐
- `StrongAttackStrategy.java` - High damage implementation
- `WeakAttackStrategy.java` - Low damage implementation
- `AdaptiveAttack.java` - ML-powered strategy ⭐⭐⭐

⭐ = Interface (abstraction)
⭐⭐⭐ = Key integration point

---

## Dependency Graph

```
SimulatorApp
    ↓
┌───────────────┐
│ Hero + Enemy  │
└───────┬───────┘
        ↓
    AttackStrategy ← (interface)
        ↓
┌───────┴───────────────┐
│   AdaptiveAttack      │ ← ⭐ Central orchestrator
└───┬───────────────┬───┘
    ↓               ↓
DecisionModel    StrongAttack
(interface)      WeakAttack
    ↓
DecisionTreeModel
NaiveBayesModel
```

---

## File Sizes (Approximate)

| File | Lines | Description |
|------|-------|-------------|
| SimulatorApp.java | 130 | Main application with scenarios |
| Hero.java | 75 | Hero state management |
| Enemy.java | 50 | Enemy with strategy pattern |
| DecisionModel.java | 35 | ML interface |
| DecisionInput.java | 55 | Feature data class |
| DecisionTreeModel.java | 60 | Decision tree logic |
| NaiveBayesModel.java | 60 | Naive Bayes logic |
| AttackStrategy.java | 25 | Attack interface |
| StrongAttackStrategy.java | 35 | Strong attack |
| WeakAttackStrategy.java | 35 | Weak attack |
| AdaptiveAttack.java | 95 | ML orchestrator |

**Total:** ~655 lines of well-documented code

---

## Import Relationships

### SimulatorApp imports:
- `com.combat.core.*`
- `com.combat.ml.*`
- `com.combat.strategy.*`

### AdaptiveAttack imports:
- `com.combat.core.Hero`
- `com.combat.ml.DecisionModel`
- `com.combat.ml.DecisionInput`

### Concrete ML models import:
- `com.combat.ml.DecisionModel`
- `com.combat.ml.DecisionInput`

### Strategy implementations import:
- `com.combat.core.Hero`
- `com.combat.strategy.AttackStrategy`

---

## Compilation Order

To compile manually (IntelliJ does this automatically):

1. **Interfaces first:**
    - `DecisionModel.java`
    - `AttackStrategy.java`

2. **Data classes:**
    - `DecisionInput.java`
    - `Hero.java`

3. **Implementations:**
    - `DecisionTreeModel.java`
    - `NaiveBayesModel.java`
    - `StrongAttackStrategy.java`
    - `WeakAttackStrategy.java`

4. **Orchestrators:**
    - `AdaptiveAttack.java`
    - `Enemy.java`

5. **Main application:**
    - `SimulatorApp.java`

---

## SOLID Principle Mapping

| Principle | Primary Files |
|-----------|--------------|
| **SRP** | DecisionInput, Hero, each Strategy |
| **OCP** | DecisionModel, AttackStrategy interfaces |
| **LSP** | All model/strategy implementations |
| **ISP** | DecisionModel, AttackStrategy (small interfaces) |
| **DIP** | AdaptiveAttack, Enemy (depend on abstractions) |

---

## Testing Strategy

### Unit Tests (if you add them):
```
test/
├── ml/
│   ├── DecisionTreeModelTest.java
│   └── NaiveBayesModelTest.java
├── strategy/
│   └── AdaptiveAttackTest.java
└── core/
    └── HeroTest.java
```

### What to Test:
1. **DecisionTreeModel:** Verify branching logic
2. **NaiveBayesModel:** Verify threat calculations
3. **Hero:** Verify damage calculation with armor
4. **AdaptiveAttack:** Verify model selection works

---

## Extension Points

### Easy to Add:
1. **New ML model** → Create new class implementing `DecisionModel`
2. **New attack type** → Create new class implementing `AttackStrategy`
3. **New features** → Modify `DecisionInput`

### Requires More Work:
1. **Multiple enemies** → Modify main simulation loop
2. **Hero attacks** → Add hero strategy pattern
3. **Inventory system** → Add Equipment classes

---

## Quick Navigation Commands

In IntelliJ:
- **Find file:** `Ctrl+Shift+N` or `Cmd+Shift+O` (Mac)
- **Find class:** `Ctrl+N` or `Cmd+O` (Mac)
- **Go to declaration:** `Ctrl+B` or `Cmd+B` (Mac)
- **Find usages:** `Alt+F7`

Search for:
- `SimulatorApp` → Main entry point
- `AdaptiveAttack` → Core ML integration
- `DecisionModel` → See all ML implementations

---

## File Checklist for Submission

- [ ] All 11 Java files present
- [ ] Proper package structure (`com.combat.*`)
- [ ] No compilation errors
- [ ] README.md reviewed
- [ ] Program runs successfully
- [ ] Output screenshot captured (optional)

---

**Everything is ready! Follow QUICK-START.md to begin!**