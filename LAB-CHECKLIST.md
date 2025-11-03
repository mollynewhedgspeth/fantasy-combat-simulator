# Fantasy Combat Simulator - Lab Completion Checklist

## Pre-Lab Setup ✓
- [ ] Java 11+ installed (Java 17 recommended)
- [ ] IntelliJ IDEA Community Edition installed
- [ ] New project "FantasyCombatSimulator" created
- [ ] All package folders created in `src/`:
    - [ ] com.combat.app
    - [ ] com.combat.core
    - [ ] com.combat.ml
    - [ ] com.combat.strategy

---

## Step 1: ML Strategy Contracts (10 min) ✓

### Files to Create:
- [ ] `com.combat.ml.DecisionInput.java`
    - [ ] Has `heroHealthPercentage` field
    - [ ] Has `heroArmorRating` field
    - [ ] Has `toArray()` method
    - [ ] Has `toString()` method

- [ ] `com.combat.ml.DecisionModel.java`
    - [ ] Is an **interface** (not class)
    - [ ] Has `predict()` method
    - [ ] Has `getModelName()` method
    - [ ] Has `getConfidence()` default method

### Verification:
- [ ] Both files compile without errors
- [ ] No red underlines in IntelliJ
- [ ] Package declarations match folder structure

### SOLID Check:
- [ ] **ISP**: Interface is small and focused ✓
- [ ] **DIP**: Interface is an abstraction, not concrete ✓

---

## Step 2: Concrete ML Strategies (15 min) ✓

### Files to Create:
- [ ] `com.combat.ml.DecisionTreeModel.java`
    - [ ] Implements `DecisionModel` interface
    - [ ] Has `predict()` logic (if-else branching)
    - [ ] Returns "STRONG" or "WEAK"
    - [ ] Has `getModelName()` returning "Decision Tree"

- [ ] `com.combat.ml.NaiveBayesModel.java`
    - [ ] Implements `DecisionModel` interface
    - [ ] Has DIFFERENT logic than DecisionTree
    - [ ] Uses threat scoring approach
    - [ ] Has `getModelName()` returning "Naive Bayes"

### Verification:
- [ ] Both classes implement `DecisionModel`
- [ ] Both compile without errors
- [ ] Logic is DIFFERENT between the two models
- [ ] No import errors

### SOLID Check:
- [ ] **OCP**: Added new model WITHOUT modifying interface ✓
- [ ] **LSP**: Both models can substitute each other ✓

---

## Step 3: ML-Driven Attack Strategy (15 min) ✓

### Files to Create:
- [ ] `com.combat.strategy.AttackStrategy.java`
    - [ ] Is an **interface**
    - [ ] Has `execute(Hero target)` method
    - [ ] Has `getName()` method

- [ ] `com.combat.strategy.StrongAttackStrategy.java`
    - [ ] Implements `AttackStrategy`
    - [ ] Has high damage (around 25-35)
    - [ ] Calls `target.takeDamage()`

- [ ] `com.combat.strategy.WeakAttackStrategy.java`
    - [ ] Implements `AttackStrategy`
    - [ ] Has low damage (around 8-13)
    - [ ] Calls `target.takeDamage()`

- [ ] `com.combat.strategy.AdaptiveAttack.java`
    - [ ] Implements `AttackStrategy`
    - [ ] Has constructor with 3 parameters:
        - `DecisionModel model`
        - `AttackStrategy strongAttack`
        - `AttackStrategy weakAttack`
    - [ ] `execute()` method:
        1. Creates `DecisionInput`
        2. Calls `model.predict()`
        3. Selects appropriate attack
        4. Executes the attack

### Verification:
- [ ] All 4 files compile
- [ ] `AdaptiveAttack` constructor uses Dependency Injection
- [ ] No concrete classes are instantiated inside `AdaptiveAttack`

### SOLID Check:
- [ ] **SRP**: Each class has ONE responsibility ✓
- [ ] **DIP**: AdaptiveAttack depends on abstractions ✓

---

## Step 4: Hero and Simulation (15 min) ✓

### Files to Create:
- [ ] `com.combat.core.Hero.java`
    - [ ] Has `health`, `maxHealth`, `armorRating` fields
    - [ ] Has `getHealthPercentage()` method
    - [ ] Has `setHealth()` method
    - [ ] Has `takeDamage()` method
    - [ ] Has `isAlive()` method

- [ ] `com.combat.core.Enemy.java`
    - [ ] Has `name` field
    - [ ] Has `attackStrategy` field (type: `AttackStrategy`)
    - [ ] Has constructor with name and strategy
    - [ ] Has `performAttack(Hero hero)` method

- [ ] `com.combat.app.SimulatorApp.java`
    - [ ] Has `main()` method
    - [ ] Creates two scenarios (DecisionTree and NaiveBayes)
    - [ ] Each scenario:
        - Creates Hero
        - Creates attack strategies
        - Creates AdaptiveAttack
        - Creates Enemy
        - Runs 5 combat turns
        - Prints results

### Verification:
- [ ] All files compile
- [ ] Can right-click `SimulatorApp` and see "Run" option
- [ ] No red underlines or errors

### SOLID Check:
- [ ] **DIP**: Enemy depends on `AttackStrategy`, not concrete attacks ✓
- [ ] **LSP**: Any `AttackStrategy` works with Enemy ✓

---

## Running the Lab ✓

### Execution:
- [ ] Right-click `SimulatorApp.java`
- [ ] Select "Run 'SimulatorApp.main()'"
- [ ] Program runs without crashes

### Expected Output:
- [ ] Prints header with SOLID principles
- [ ] Prints "SCENARIO 1" with Decision Tree
- [ ] Shows 5 turns of combat (or until hero dies)
- [ ] Shows ML model analysis for each turn
- [ ] Shows different attack choices (STRONG/WEAK)
- [ ] Prints "SCENARIO 2" with Naive Bayes
- [ ] Shows DIFFERENT decisions than Scenario 1
- [ ] Prints footer with observations

### Sample Output Should Include:
```
═══════════════════════════════════════════════════════════════════
         FANTASY COMBAT SIMULATOR - ML-Powered Enemy AI
                    SOLID Principles Demonstration
═══════════════════════════════════════════════════════════════════

🎮 SCENARIO 1: Enemy AI with Decision Tree Model
...
🤖 ML Model: Decision Tree
📊 Analysis: DecisionInput[Health: 100.0%, Armor: 3]
🎯 Decision: STRONG (Confidence: 95%)
...
```

---

## Testing Understanding ✓

### Comprehension Check:
- [ ] Can explain what each SOLID principle means
- [ ] Can identify which principle prevents modifying `AdaptiveAttack` when adding models
- [ ] Can explain why `DecisionModel` is an interface, not a class
- [ ] Can describe the role of Dependency Injection

### Code Exploration:
- [ ] Try changing Hero's health to 30 - does behavior change?
- [ ] Try changing Hero's armor to 10 - does behavior change?
- [ ] Both models make different decisions? (Should be YES)

---

## Extension Challenges ✓

### Challenge 1: Add New ML Model
- [ ] Create `RandomForestModel.java`
- [ ] Implement `DecisionModel` interface
- [ ] Add to `SimulatorApp` as Scenario 3
- [ ] Run successfully

### Challenge 2: Add New Feature
- [ ] Add `enemyHealthPercentage` to `DecisionInput`
- [ ] Update models to use this new feature
- [ ] Verify system still works

### Challenge 3: Add New Attack Type
- [ ] Create `MagicAttackStrategy.java`
- [ ] Implement `AttackStrategy` interface
- [ ] Modify model to return "MAGIC" option
- [ ] Update `AdaptiveAttack` to handle it

---

## Troubleshooting ✓

### Common Issues:

**"Package does not exist"**
- [ ] Check package declaration matches folder structure
- [ ] Verify folders exist in `src/`

**"Cannot find symbol"**
- [ ] Add import statement at top of file
- [ ] Use Alt+Enter for auto-import

**"Main method not found"**
- [ ] Verify signature: `public static void main(String[] args)`
- [ ] Located in `SimulatorApp.java`

**Different output than expected**
- [ ] Check random number generation (normal)
- [ ] Verify Hero starting health is 100
- [ ] Verify armor is 3

---

## Final Verification ✓

### Code Quality:
- [ ] No compiler errors (red underlines)
- [ ] All imports resolved
- [ ] Proper indentation
- [ ] Descriptive variable names

### SOLID Principles:
- [ ] ✅ **SRP**: Each class has single responsibility
- [ ] ✅ **OCP**: Can add models without modifying code
- [ ] ✅ **LSP**: Models are interchangeable
- [ ] ✅ **ISP**: Interfaces are small and focused
- [ ] ✅ **DIP**: Depend on abstractions, not concretions

### Documentation:
- [ ] Read README.md
- [ ] Understand each step's purpose
- [ ] Can explain design decisions

---

## Submission Checklist (If Applicable) ✓

- [ ] All 11 Java files created and working
- [ ] Program runs successfully
- [ ] Screenshot of output saved
- [ ] README.md reviewed
- [ ] Extension challenges attempted (bonus)
- [ ] Reflection questions answered

---

## Congratulations! 🎉

You've successfully built an ML-powered enemy AI using SOLID principles!

### Key Achievements:
✅ Separated ML decision logic from attack execution
✅ Created extensible architecture (add models without changing code)
✅ Applied all 5 SOLID principles correctly
✅ Built testable, maintainable code

### Next Steps:
- Experiment with extension challenges
- Apply SOLID principles to your own projects
- Share what you learned!

---

**Questions? Review the SOLID-PRINCIPLES.md guide!**