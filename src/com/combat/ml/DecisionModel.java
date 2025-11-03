package com.combat.ml;

/**
 * DecisionModel: The core ML abstraction for decision-making.
 * This is the "ML equivalent" of AttackStrategy.
 *
 * SOLID Principles Applied:
 *
 * 1. Interface Segregation Principle (ISP):
 *    - Small, focused interface with only essential methods
 *    - Clients depend only on what they need
 *
 * 2. Dependency Inversion Principle (DIP):
 *    - High-level decision logic depends on this abstraction
 *    - Not on concrete implementations (Decision Tree, Naive Bayes, etc.)
 *    - "Depend on abstractions, not concretions"
 *
 * 3. Open/Closed Principle (OCP):
 *    - Open for extension: Add new ML models by implementing this interface
 *    - Closed for modification: Existing code doesn't change when adding models
 */
public interface DecisionModel {

    /**
     * Make a prediction based on the current com.combat situation.
     *
     * @param input The com.combat features to base the decision on
     * @return The predicted attack type: "STRONG" or "WEAK"
     */
    String predict(DecisionInput input);

    /**
     * Get the name of this ML model (for logging/debugging).
     *
     * @return A human-readable model name (e.g., "Decision Tree", "Naive Bayes")
     */
    String getModelName();

    /**
     * Optional: Get confidence score for the prediction (0.0 to 1.0).
     * Default implementation returns 1.0 (100% confidence).
     *
     * @param input The com.combat features
     * @return Confidence score between 0.0 and 1.0
     */
    default double getConfidence(DecisionInput input) {
        return 1.0;
    }
}
