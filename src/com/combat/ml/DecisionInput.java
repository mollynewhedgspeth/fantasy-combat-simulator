package com.combat.ml;

/**
 * DecisionInput: Encapsulates all features used for ML decision-making.
 *
 * SOLID Principle: Single Responsibility Principle (SRP)
 * - Only responsible for holding and providing access to com.combat features
 * - Easy to extend with new features without affecting other classes
 *
 * Design Notes:
 * - Immutable by design (final fields)
 * - Can easily add new features (e.g., enemyHealth, distance, mana)
 * - Clean separation between data and decision logic
 */
public class DecisionInput {
    private final double heroHealthPercentage;
    private final int heroArmorRating;

    /**
     * Constructor for com.combat features.
     * @param heroHealthPercentage Current hero health as percentage (0-100)
     * @param heroArmorRating Hero's armor rating (damage reduction)
     */
    public DecisionInput(double heroHealthPercentage, int heroArmorRating) {
        this.heroHealthPercentage = heroHealthPercentage;
        this.heroArmorRating = heroArmorRating;
    }

    public double getHeroHealthPercentage() {
        return heroHealthPercentage;
    }

    public int getHeroArmorRating() {
        return heroArmorRating;
    }

    /**
     * Convert to array format for models that need numerical arrays.
     * Makes it easy to add features - just extend this array.
     */
    public double[] toArray() {
        return new double[] {
                heroHealthPercentage,
                heroArmorRating
        };
    }

    @Override
    public String toString() {
        return String.format("DecisionInput[Health: %.1f%%, Armor: %d]",
                heroHealthPercentage, heroArmorRating);
    }
}