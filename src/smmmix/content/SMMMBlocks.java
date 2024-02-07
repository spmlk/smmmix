package smmmix.content;

import arc.graphics.Color;
import mindustry.content.Fx;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.meta.*;
import smmmix.entities.bullet.*;
import smmmix.graphics.Modpal;
import smmmix.world.blocks.environment.*;
import smmmix.world.blocks.turrets.*;

import static smmmix.SMMMix.Name;

public class SMMMBlocks{
    public static Block

    //environment - floors
    lushGrass,
    //environment - walls
    lushShrubs,lushPine,
    //environment - props
    lushGrowth,

    //turrets - environment
    lushGreatFlower;
    public static void load(){

        lushGrass = new Floor("lush-grass"){{
            variants = 4;
        }};

        lushShrubs = new StaticWall("lush-shrubs"){{
            variants = 3;
        }};

        lushPine = new StaticLargeWall("lush-pine"){{
            maxSize = 2;
            variants = 5;
            sizeVariants = new int[]{2};
            drawOres = false;
        }};

        lushGrowth = new LargeProp("lush-growth"){{
            propSize = 2;
            variants = 0;
            sway = true;
        }};

        lushGreatFlower = new SyntheticPowerTurret("lush-great-flower"){{
            requirements(Category.turret, BuildVisibility.editorOnly, ItemStack.with());
            size = 3;
            health = 360 * size * size;
            drawCracks = false;
            createRubble = false;
            customShadow = true;

            reload = 360f;
            range = 336f;
            shootCone = 360f;
            rotateSpeed = 0;

            inaccuracy = 360f;
            velocityRnd = 0.12f;
            shootY = -6f;
            shootSound = Sounds.spray;
            shoot.shots = 5;
            shoot.shotDelay = 9f;
            shootType = new BaseBulletType(2.5f, 120f, Name + "-pollen-bullet"){{
                hitSize = 20f;
                splashDamage = 210f;
                splashDamageRadius = 44f;
                scaledSplashDamage = true;
                drag = 0.005f;

                reflectable = false;
                lifetime = 720f;

                homingPower = 0.012f;
                homingRange = 72f;
                incendAmount = 2;
                incendChance = 0.4f;

                smokeEffect = Fx.none;
                shootEffect = SMMMFx.lgfShoot;
                hitSound = Sounds.explosion;
                hitEffect = SMMMFx.lgfHit;
                backColor = Color.white;
                frontColor = Color.white;
                width = height = 12f;
                rotated = false;
                trailColor = Modpal.lushPollenLight;
                trailInterval = 9f;
                trailParam = 2f;
            }};
        }};

    }
}
