package com.combat.core;

import com.combat.strategy.AttackStrategy;

/**
 * Enemy: AI-controlled enemy that uses strategies to attack.
 *
 * SOLID Principles:
 * - Dependency Inversion Principle (DIP): Depends on AttackStrategy abstraction
 * - Liskov Substitution Principle (LSP): Works with ANY AttackStrategy implementation
 * - Open/Closed: Can use any attack strategy without modification
 */
public class Enemy {
    private final String name;
    private AttackStrategy attackStrategy;

    /**
     * Create an enemy with a name and attack strategy.
     * @param name Enemy name
     * @param attackStrategy The attack strategy to use
     */
    public Enemy(String name, AttackStrategy attackStrategy) {
        this.name = name;
        this.attackStrategy = attackStrategy;
    }

    /**
     * Change the enemy's attack strategy at runtime.
     * Demonstrates Strategy Pattern flexibility.
     * @param attackStrategy New attack strategy
     */
    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    /**
     * Execute a turn - attack the hero.
     * @param hero The hero to attack
     */
    public void performAttack(Hero hero) {
        System.out.println("\n🔥 " + name + " attacks using: " + attackStrategy.getName());
        attackStrategy.execute(hero);
    }

    public String getName() {
        return name;
    }
}
