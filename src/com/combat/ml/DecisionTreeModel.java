package com.combat.ml;

/**
 * DecisionTreeModel: Simulates a Decision Tree classifier.
 *
 * SOLID Principles Applied:
 *
 * 1. Open/Closed Principle (OCP):
 *    - Extends functionality by implementing DecisionModel interface
 *    - No modification to existing code needed
 *
 * 2. Liskov Substitution Principle (LSP):
 *    - Can be substituted for any DecisionModel without breaking behavior
 *    - Honors the contract defined by the interface
 *
 * Decision Tree Logic:
 * ┌─────────────────────────┐
 * │  Hero Health > 50%?     │
 * └────┬──────────────┬─────┘
 *      Yes            No
 *      │              │
 *   STRONG         ┌──┴──────────────-┐
 *                  │ Armor < 5?       │
 *                  └──┬──────────┬────┘
 *                     Yes        No
 *                     │          │
 *                  STRONG      WEAK
 */
public class DecisionTreeModel implements DecisionModel {

    @Override
    public String predict(DecisionInput input) {
        double health = input.getHeroHealthPercentage();
        int armor = input.getHeroArmorRating();

        // Decision Tree branching logic
        if (health > 50) {
            // Hero is strong - go aggressive
            return "STRONG";
        } else {
            // Hero is weakened
            if (armor < 5) {
                // Unprotected - finish them!
                return "STRONG";
            } else {
                // Protected - play it safe
                return "WEAK";
            }
        }
    }

    @Override
    public String getModelName() {
        return "Decision Tree";
    }

    @Override
    public double getConfidence(DecisionInput input) {
        // Decision trees are deterministic - always confident
        return 0.95;
    }
}
