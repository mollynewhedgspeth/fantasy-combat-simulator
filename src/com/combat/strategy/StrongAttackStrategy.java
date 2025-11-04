package com.combat.strategy;

import com.combat.core.Hero;

/**
 * StrongAttackStrategy: High damage, high risk attack.
 * Represents aggressive combat behavior.
 */
public class StrongAttackStrategy implements AttackStrategy {

    private static final int BASE_DAMAGE = 25;
    private static final int DAMAGE_VARIANCE = 10;

    @Override
    public void execute(Hero target) {
        int damage = BASE_DAMAGE + (int)(Math.random() * DAMAGE_VARIANCE);
        System.out.println("   STRONG ATTACK executed! Damage: " + damage);
        target.takeDamage(damage);
    }

    @Override
    public String getName() {
        return "Strong Attack";
    }
}
