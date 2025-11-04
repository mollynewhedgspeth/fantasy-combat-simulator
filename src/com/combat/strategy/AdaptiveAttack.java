package com.combat.strategy;

import com.combat.core.Hero;
import com.combat.ml.DecisionInput;
import com.combat.ml.DecisionModel;

/**
 * AdaptiveAttack: ML-powered attack strategy that adapts to combat situations.
 *
 * SOLID Principles Applied:
 *
 * 1. Single Responsibility Principle (SRP):
 *    - ONE job: Orchestrate ML decision-making and execute the chosen attack
 *    - Delegates prediction to DecisionModel
 *    - Delegates damage calculation to concrete attack strategies
 *    - Delegates feature extraction to DecisionInput
 *
 * 2. Dependency Inversion Principle (DIP):
 *    - Depends on abstractions (DecisionModel, AttackStrategy)
 *    - Not on concrete implementations
 *    - Can swap ML models and attacks without changing this class
 *
 * 3. Open/Closed Principle (OCP):
 *    - Closed for modification - core logic doesn't change
 *    - Open for extension - inject different models/strategies
 *
 * This is the "glue" that connects ML predictions to combat actions.
 */
public class AdaptiveAttack implements AttackStrategy {

    private final DecisionModel model;
    private final AttackStrategy strongAttack;
    private final AttackStrategy weakAttack;

    /**
     * Constructor with Dependency Injection.
     * All dependencies are injected, following DIP.
     *
     * @param model The ML model to use for decisions
     * @param strongAttack The strong attack strategy
     * @param weakAttack The weak attack strategy
     */
    public AdaptiveAttack(DecisionModel model,
                          AttackStrategy strongAttack,
                          AttackStrategy weakAttack) {
        this.model = model;
        this.strongAttack = strongAttack;
        this.weakAttack = weakAttack;
    }

    @Override
    public void execute(Hero target) {
        // Step 1: Extract features from current combat state
        DecisionInput input = new DecisionInput(
                target.getHealthPercentage(),
                target.getArmorRating()
        );

        // Step 2: Get ML prediction
        String prediction = model.predict(input);
        double confidence = model.getConfidence(input);

        // Step 3: Display decision reasoning
        System.out.println("   ML Model: " + model.getModelName());
        System.out.println("   Analysis: " + input);
        System.out.println("   Decision: " + prediction + " (Confidence: "
                + String.format("%.0f%%", confidence * 100) + ")");

        // Step 4: Select and execute appropriate attack
        AttackStrategy chosenAttack = prediction.equals("STRONG") ? strongAttack : weakAttack;
        chosenAttack.execute(target);
    }

    @Override
    public String getName() {
        return "ML-Adaptive Attack (" + model.getModelName() + ")";
    }
}
