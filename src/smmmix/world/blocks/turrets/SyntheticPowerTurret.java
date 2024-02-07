package smmmix.world.blocks.turrets;

import arc.graphics.Color;
import arc.math.*;
import mindustry.content.Fx;
import mindustry.world.blocks.defense.turrets.PowerTurret;

public class SyntheticPowerTurret extends PowerTurret{
    public float healPercent = 12f;
    public float healthHealPercent = 75f;
    public float healInterval = 180f;
    public Color healColor = Color.valueOf("84f491");

    public SyntheticPowerTurret(String name){
        super(name);
        suppressable = false;
        canOverdrive = false;
        enableDrawStatus = false;
        drawTeamOverlay = false;
        fillsTile = false;
    }

    public class SyntheticPowerTurretBuild extends PowerTurretBuild{
        public float intervalCharge = Mathf.random(reload);

        public void updateTile(){
            super.updateTile();
            intervalCharge += delta();

            if(intervalCharge >= healInterval){
                intervalCharge = 0f;

                if(damaged()){
                    heal(
                            health() * healPercent * healthHealPercent / 10000f +
                                    maxHealth() * healPercent * (100 - healthHealPercent) / 10000f
                    );
                    Fx.healBlockFull.at(x, y, block.size, healColor, block);
                }
            }
        }
    }
}
