package com.combat.app;

import com.combat.core.Enemy;
import com.combat.core.Hero;
import com.combat.ml.DecisionTreeModel;
import com.combat.ml.NaiveBayesModel;
import com.combat.strategy.AdaptiveAttack;
import com.combat.strategy.StrongAttackStrategy;
import com.combat.strategy.WeakAttackStrategy;

/**
 * SimulatorApp: Main application demonstrating ML-powered enemy AI with SOLID design.
 *
 * This simulation proves:
 * 1. Easy swapping of ML models (OCP, DIP, LSP)
 * 2. Clean separation of concerns (SRP)
 * 3. Extensible architecture (can add new models, attacks, features)
 *
 * Key SOLID Demonstrations:
 * - DIP: Enemy depends on AttackStrategy abstraction, not concrete attacks
 * - OCP: Can add new ML models without modifying existing code
 * - LSP: Any DecisionModel can replace another seamlessly
 * - SRP: Each class has one clear responsibility
 * - ISP: Small, focused interfaces
 */
public class SimulatorApp {

    public static void main(String[] args) {
        printHeader();

        // Scenario 1: Enemy using Decision Tree Model
        System.out.println("\n🎮 SCENARIO 1: Enemy AI with Decision Tree Model");
        System.out.println("═".repeat(70));
        runCombatScenario(new DecisionTreeModel(), "Dark Mage");

        // Scenario 2: Enemy using Naive Bayes Model (Easy swap! OCP/LSP in action)
        System.out.println("\n\n🎮 SCENARIO 2: Enemy AI with Naive Bayes Model");
        System.out.println("═".repeat(70));
        runCombatScenario(new NaiveBayesModel(), "Shadow Warrior");

        printFooter();
    }

    /**
     * Run a combat scenario with a specific ML model.
     * Demonstrates how easily we can swap models thanks to DIP and LSP.
     */
    private static void runCombatScenario(com.combat.ml.DecisionModel model, String enemyName) {
        // Create hero with moderate stats
        Hero hero = new Hero(100, 3);
        System.out.println("🛡️  " + hero);

        // Create concrete attack strategies
        StrongAttackStrategy strongAttack = new StrongAttackStrategy();
        WeakAttackStrategy weakAttack = new WeakAttackStrategy();

        // Create ML-powered adaptive attack (Dependency Injection)
        AdaptiveAttack adaptiveAttack = new AdaptiveAttack(model, strongAttack, weakAttack);

        // Create enemy with adaptive attack strategy
        Enemy enemy = new Enemy(enemyName, adaptiveAttack);

        // Run combat simulation for 5 turns or until hero dies
        int turn = 1;
        while (hero.isAlive() && turn <= 5) {
            System.out.println("\n" + "─".repeat(70));
            System.out.println("⚔️  TURN " + turn);
            System.out.println("─".repeat(70));

            enemy.performAttack(hero);

            if (!hero.isAlive()) {
                System.out.println("\n💀 Hero has been defeated!");
                break;
            }

            turn++;
        }

        // Display result
        System.out.println("\n" + "═".repeat(70));
        if (hero.isAlive()) {
            System.out.println("✅ Hero survived! Final health: " +
                    hero.getHealth() + "/" + hero.getMaxHealth());
        } else {
            System.out.println("❌ Hero was defeated in combat!");
        }
        System.out.println("═".repeat(70));
    }

    private static void printHeader() {
        System.out.println("\n" + "═".repeat(70));
        System.out.println("         FANTASY COMBAT SIMULATOR - ML-Powered Enemy AI");
        System.out.println("                    SOLID Principles Demonstration");
        System.out.println("═".repeat(70));
        System.out.println("\n📚 SOLID Principles in Action:");
        System.out.println("   • SRP: Each class has ONE clear responsibility");
        System.out.println("   • OCP: Add new ML models without changing existing code");
        System.out.println("   • LSP: Any DecisionModel can substitute another");
        System.out.println("   • ISP: Small, focused interfaces");
        System.out.println("   • DIP: Depend on abstractions, not concrete classes");
        System.out.println("═".repeat(70));
    }

    private static void printFooter() {
        System.out.println("\n\n" + "═".repeat(70));
        System.out.println("                     SIMULATION COMPLETE");
        System.out.println("═".repeat(70));
        System.out.println("\n🎯 Key Observations:");
        System.out.println("   1. ML models made DIFFERENT decisions based on their logic");
        System.out.println("   2. We swapped models with ZERO changes to core combat code");
        System.out.println("   3. Each component is INDEPENDENT and TESTABLE");
        System.out.println("   4. Adding new features/models requires NO modification to existing code");
        System.out.println("\n💡 Extension Ideas:");
        System.out.println("   • Add new ML model (e.g., RandomForestModel)");
        System.out.println("   • Add new features (distance, mana, enemy health)");
        System.out.println("   • Add new attack types (MagicAttack, DefensiveAttack)");
        System.out.println("   • Add confidence thresholds for decisions");
        System.out.println("\n✨ That's SOLID design in action!");
        System.out.println("═".repeat(70) + "\n");
    }
}