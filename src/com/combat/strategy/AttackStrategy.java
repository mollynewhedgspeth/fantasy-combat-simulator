package com.combat.strategy;

import com.combat.core.Hero;

/**
 * AttackStrategy: Defines the contract for attack behaviors.
 *
 * Classic Strategy Pattern - allows different attack algorithms
 * to be selected at runtime.
 */
public interface AttackStrategy {

    /**
     * Execute an attack on the target hero.
     * @param target The hero being attacked
     */
    void execute(Hero target);

    /**
     * Get the name of this attack strategy.
     * @return Attack name for display
     */
    String getName();
}
