package smmmix.content;

import mindustry.type.StatusEffect;

public class SMMMStatusEffects {
    public static StatusEffect

    //for use in editor
    frenzying, enduring, resistant,

    //applied
    kineticStunned;

    public static void load(){
        frenzying = new StatusEffect("frenzying"){{
            speedMultiplier = 1.4f;
            reloadMultiplier = 1.6f;
            buildSpeedMultiplier = 1.3f;
            permanent = true;

            effect = SMMMFx.frenzying;
        }};
        enduring = new StatusEffect("enduring"){{
            healthMultiplier = 2f;
            speedMultiplier = 0.8f;
            damageMultiplier = 1.2f;
            reloadMultiplier = 1.2f;
            permanent = true;

            effect = SMMMFx.enduring;
            effectChance = 0.075f;
            parentizeEffect = true;
        }};
        resistant = new StatusEffect("resistant"){{
            healthMultiplier = 3.6f;
            permanent = true;

            effect = SMMMFx.resistant;
            effectChance = 0.05f;
            parentizeEffect = true;
        }};

        kineticStunned = new StatusEffect("kinetic-stunned"){{
            speedMultiplier = 0.1f;
            dragMultiplier = 0.1f;
            disarm = true;
        }};
    }
}
