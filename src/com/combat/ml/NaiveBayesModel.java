package com.combat.ml;

/**
 * NaiveBayesModel: Simulates a Naive Bayes classifier.
 *
 * SOLID Principles Applied:
 *
 * 1. Open/Closed Principle (OCP):
 *    - Added WITHOUT modifying existing code
 *    - Proves we can extend with new ML models easily
 *
 * 2. Liskov Substitution Principle (LSP):
 *    - Drop-in replacement for DecisionTreeModel
 *    - System works identically regardless of which model is used
 *
 * Naive Bayes Logic (Simplified):
 * - Calculates a "threat score" based on features
 * - Uses probabilistic threshold approach
 * - Different from Decision Tree's branching logic
 *
 * Threat Score = Health% + (Armor × 5)
 * - If score > 60: Use STRONG attack
 * - If score ≤ 60: Use WEAK attack
 */
public class NaiveBayesModel implements DecisionModel {

    private static final double THREAT_THRESHOLD = 60.0;
    private static final int ARMOR_WEIGHT = 5;

    @Override
    public String predict(DecisionInput input) {
        double health = input.getHeroHealthPercentage();
        int armor = input.getHeroArmorRating();

        // Calculate threat score (simplified Bayesian approach)
        // High health = high threat, high armor = high threat
        double threatScore = health + (armor * ARMOR_WEIGHT);

        // Probabilistic decision based on threshold
        if (threatScore > THREAT_THRESHOLD) {
            return "STRONG";
        } else {
            return "WEAK";
        }
    }

    @Override
    public String getModelName() {
        return "Naive Bayes";
    }

    @Override
    public double getConfidence(DecisionInput input) {
        double health = input.getHeroHealthPercentage();
        int armor = input.getHeroArmorRating();
        double threatScore = health + (armor * ARMOR_WEIGHT);

        // Calculate confidence based on distance from threshold
        double distance = Math.abs(threatScore - THREAT_THRESHOLD);

        // Normalize to 0.6-1.0 range (Naive Bayes is less confident than Decision Trees)
        return Math.min(1.0, 0.6 + (distance / 100.0));
    }
}