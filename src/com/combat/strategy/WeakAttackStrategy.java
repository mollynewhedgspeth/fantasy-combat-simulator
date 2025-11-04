package com.combat.strategy;

import com.combat.core.Hero;

/**
 * WeakAttackStrategy: Low damage, low risk attack.
 * Represents conservative combat behavior.
 */
public class WeakAttackStrategy implements AttackStrategy {

    private static final int BASE_DAMAGE = 8;
    private static final int DAMAGE_VARIANCE = 5;

    @Override
    public void execute(Hero target) {
        int damage = BASE_DAMAGE + (int)(Math.random() * DAMAGE_VARIANCE);
        System.out.println("   Weak Attack executed! Damage: " + damage);
        target.takeDamage(damage);
    }

    @Override
    public String getName() {
        return "Weak Attack";
    }
}