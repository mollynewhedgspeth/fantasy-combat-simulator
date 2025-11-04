package com.combat.core;

/**
 * Hero: Represents the player character in combat.
 *
 * SOLID Principles:
 * - Single Responsibility: Manages hero state (health, armor)
 * - Open/Closed: Can extend with new attributes without changing core logic
 */
public class Hero {
    private int health;
    private final int maxHealth;
    private final int armorRating;

    /**
     * Create a hero with specified stats.
     * @param maxHealth Maximum health points
     * @param armorRating Armor rating (reduces incoming damage)
     */
    public Hero(int maxHealth, int armorRating) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.armorRating = armorRating;
    }

    // Getters
    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getArmorRating() {
        return armorRating;
    }

    /**
     * Get current health as a percentage.
     * Used by ML models for decision-making.
     * @return Health percentage (0-100)
     */
    public double getHealthPercentage() {
        return (double) health / maxHealth * 100.0;
    }

    /**
     * Setter for health (for testing or special game mechanics).
     * @param health New health value
     */
    public void setHealth(int health) {
        this.health = Math.max(0, Math.min(health, maxHealth));
    }

    /**
     * Apply damage to hero, accounting for armor.
     * @param damage Raw damage amount
     */
    public void takeDamage(int damage) {
        // Armor reduces damage
        int actualDamage = Math.max(0, damage - armorRating);
        health = Math.max(0, health - actualDamage);
        System.out.println("   Hero takes " + actualDamage + " damage! " +
                "Health: " + health + "/" + maxHealth);
    }

    /**
     * Check if hero is still alive.
     * @return true if health > 0
     */
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public String toString() {
        return String.format("Hero[HP: %d/%d, Armor: %d]", health, maxHealth, armorRating);
    }
}
