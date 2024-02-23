package smmmix.content;

import arc.graphics.Color;
import arc.math.Interp;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.*;
import mindustry.gen.Sounds;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.*;
import smmmix.entities.bullet.*;
import smmmix.gen.*;
import smmmix.graphics.Modpal;

import static smmmix.SMMMix.Name;

public class SMMMUnits{

    public static UnitType metasoma, akrav, chactic, hadruon, leiurus;
    public static void load(){

        EntityRegistry.register();

        metasoma = EntityRegistry.content("metasoma", LegsUnit.class, metasoma -> new UnitType(metasoma){{
            speed = 0.75f;
            drag = 0.5f;
            hitSize = 10f;
            health = 220;

            rotateSpeed = 4.8f;
            allowLegStep = false;
            legPhysicsLayer = false;
            legCount = 4;
            legGroupSize = 2;
            lockLegBase = false;
            legLength = 8f;
            legExtension = 2f;
            legBaseOffset = 2f;
            legMaxLength = 1.2f;
            legMinLength = 0.4f;
            rippleScale = 0.16f;

            shadowElevation = 0.2f;
            groundLayer = Layer.legUnit - 1;

            weapons.add(
                new Weapon(Name + "-metasoma-weapon") {{
                    reload = 18f;

                    shootStatus = StatusEffects.slow;
                    shootStatusDuration = 30f;

                    x = 4f;
                    y = 1f;
                    top = false;

                    ejectEffect = Fx.casing1;
                    bullet = new BasicBulletType(2.5f, 9) {{
                        lifetime = 60f;

                        width = 7f;
                        height = 9f;
                    }};
                }},
                new Weapon(Name + "-metasoma-stinger") {{
                    reload = 75f;
                    rotate = true;

                    x = 0f;
                    y = -4.75f;
                    mirror = false;
                    controllable = false;
                    autoTarget = true;

                    ejectEffect = Fx.casing1;
                    bullet = new BasicBulletType(4.5f, 12) {{
                        lifetime = 45f;
                        pierce = true;
                        pierceBuilding = true;
                        pierceCap = 2;

                        width = 7f;
                        height = 11f;
                        trailColor = Color.lightGray;
                        trailLength = 3;
                    }};
                }}
            );
        }});
        akrav = EntityRegistry.content("akrav", LegsUnit.class, akrav -> new UnitType(akrav){{
            speed = 0.6f;
            drag = 0.3f;
            hitSize = 12f;
            health = 750;
            armor = 5f;
            hovering = true;
            targetAir = false;

            rotateSpeed = 4f;
            allowLegStep = false;
            legPhysicsLayer = false;
            legCount = 6;
            legGroupSize = 2;
            lockLegBase = true;
            legLength = 10f;
            legExtension = 2f;
            legBaseOffset = 3f;
            legMaxLength = 1.8f;
            legMinLength = 0.4f;
            rippleScale = 0.25f;

            shadowElevation = 0.2f;
            groundLayer = Layer.legUnit - 1;

            weapons.add(
                new Weapon(Name + "-akrav-weapon") {{
                    reload = 27f;
                    rotate = true;
                    rotateSpeed = 1f;
                    rotationLimit = 30f;
                    shootCone = 90f;

                    shootStatus = StatusEffects.slow;
                    shootStatusDuration = 45f;

                    x = 6.5f;
                    y = 1f;
                    shootY = 7f;
                    recoil = -3f;
                    top = false;

                    ejectEffect = Fx.none;
                    shootSound = Sounds.shotgun;
                    bullet = new ShrapnelBulletType() {{
                        damage = 36;
                        length = 30f;
                        width = 12f;
                        pierceCap = 3;
                        pierceBuilding = true;

                        serrations = 3;
                        serrationWidth = 2f;
                        serrationSpacing = 6f;
                    }};
                }},
                new Weapon(Name + "-akrav-stinger") {{
                    reload = 85f;
                    rotate = true;
                    rotateSpeed = 2f;
                    shootCone = 90f;
                    shoot = new ShootSpread(5, 15f){{
                        shotDelay = 5f;
                    }};

                    x = 0f;
                    y = -9;
                    mirror = false;
                    controllable = false;
                    autoTarget = true;

                    ejectEffect = Fx.casing1;
                    bullet = new BasicBulletType(4f, 21) {{
                        lifetime = 45f;

                        homingPower = 0.2f;
                        homingDelay = 15f;

                        width = 9f;
                        height = 11f;
                        trailColor = Color.lightGray;
                        trailLength = 3;
                    }};
                }}
            );
        }});
        chactic = EntityRegistry.content("chactic", LegsUnit.class, chactic -> new UnitType(chactic){{
            speed = 0.4f;
            drag = 0.24f;
            hitSize = 15f;
            health = 1420;
            armor = 8f;
            hovering = true;
            targetAir = false;

            rotateSpeed = 2.4f;
            allowLegStep = true;
            legCount = 6;
            legGroupSize = 2;
            lockLegBase = true;
            legLength = 14f;
            legExtension = 3f;
            legBaseOffset = 5f;
            legMoveSpace = 1.2f;
            legMaxLength = 1.8f;
            legMinLength = 0.4f;
            rippleScale = 0.32f;

            shadowElevation = 0.3f;
            groundLayer = Layer.legUnit;

            weapons.add(
                new Weapon(Name + "-chactic-weapon") {{
                    reload = 21f;
                    rotate = true;
                    rotateSpeed = 1f;
                    rotationLimit = 30f;
                    shootCone = 30f;
                    inaccuracy = 3f;

                    shootStatus = StatusEffects.slow;
                    shootStatusDuration = 20f;

                    x = 6.5f;
                    y = 1f;
                    shootX = -1f;
                    shootY = 2f;
                    recoil = 2f;
                    alternate = false;

                    ejectEffect = Fx.none;
                    shootSound = Sounds.shootSnap;
                    bullet = new ShrapnelBulletType() {{
                        damage = 36;
                        speed = 5f;
                        drag = 0.2f;
                        length = 20f;
                        width = 18f;
                        collides = true;
                        pierceCap = 5;
                        pierceBuilding = true;

                        knockback = 10f;
                        recoil = 1f;
                        status = SMMMStatusEffects.kineticStunned;
                        statusDuration = 8f;

                        serrations = 3;
                        serrationWidth = 2f;
                        serrationSpacing = 3f;
                    }};
                }},
                new Weapon(Name + "-chactic-stinger") {{
                    reload = 104f;
                    rotate = true;
                    rotateSpeed = 2f;
                    rotationLimit = 120f;
                    shootCone = 10f;

                    x = 0f;
                    y = -12.25f;
                    shootY = 3.25f;
                    recoil = 2f;
                    mirror = false;
                    controllable = false;
                    autoTarget = true;

                    ejectEffect = Fx.none;
                    shootSound = Sounds.laser;
                    bullet = new LaserBulletType(80){{
                        length = 240f;
                        buildingDamageMultiplier = 0.4f;

                        colors = new Color[]{Pal.lightOrange.cpy().a(0.4f), Pal.lightOrange, Color.white};
                        sideAngle = 75f;
                        sideWidth = 1f;
                        sideLength = 30f;
                    }};
                }}
            );
        }});
        hadruon = EntityRegistry.content("hadruon", LegsUnit.class, hadruon -> new UnitType(hadruon){{
            speed = 0.48f;
            drag = 0.1f;
            hitSize = 22f;
            health = 13200;
            armor = 11f;
            hovering = true;
            drownTimeMultiplier = 1.5f;

            rotateSpeed = 1.8f;
            allowLegStep = true;
            legCount = 8;
            legGroupSize = 2;
            lockLegBase = true;
            legLength = 21f;
            legExtension = 3f;
            legBaseOffset = 7f;
            legMoveSpace = 1.4f;
            legMaxLength = 1.8f;
            legMinLength = 0.4f;
            rippleScale = 0.4f;
            stepShake = 0.3f;

            legSplashDamage = 54;
            legSplashRange = 6;

            shadowElevation = 0.5f;
            groundLayer = Layer.legUnit;

            BulletType mountBullet = new BasicBulletType(6f, 33) {{
                lifetime = 25f;
                buildingDamageMultiplier = 0.5f;

                homingPower = 0.07f;

                width = 7f;
                height = 11f;
                trailColor = Pal.lightOrange;
                trailLength = 1;
            }};
            weapons.add(
                new Weapon(Name + "-hadruon-mount") {{
                    reload = 10f;
                    rotate = true;
                    rotateSpeed = 2f;
                    shootCone = 90f;

                    x = 6f;
                    y = 1f;
                    recoil = 1f;
                    alternate = false;
                    controllable = false;
                    autoTarget = true;

                    ejectEffect = Fx.casing1;
                    bullet = mountBullet;
                }},
                new Weapon(Name + "-hadruon-mount") {{
                    reload = 17f;
                    rotate = true;
                    rotateSpeed = 2.4f;
                    shootCone = 90f;

                    x = 0f;
                    y = -3f;
                    recoil = 1f;
                    mirror = false;
                    controllable = false;
                    autoTarget = true;

                    ejectEffect = Fx.casing1;
                    bullet = mountBullet;
                }},
                new Weapon(Name + "-hadruon-weapon") {{
                    reload = 25f;
                    rotate = true;
                    rotateSpeed = 1f;
                    rotationLimit = 30f;
                    shootCone = 30f;
                    inaccuracy = 3f;

                    shootStatus = StatusEffects.slow;
                    shootStatusDuration = 15f;

                    x = 12f;
                    y = 9.5f;
                    shootX = 0.25f;
                    shootY = 5f;
                    recoil = 3f;
                    alternate = false;

                    ejectEffect = Fx.none;
                    shootSound = Sounds.shootSnap;
                    bullet = new ShrapnelBulletType() {{
                        damage = 96;
                        lifetime = 15f;
                        speed = 7f;
                        drag = 0.1f;
                        length = 50f;
                        width = 20f;
                        collides = true;
                        pierceCap = 7;
                        pierceBuilding = true;

                        knockback = 16f;
                        recoil = 1.5f;
                        status = SMMMStatusEffects.kineticStunned;
                        statusDuration = 12f;

                        toColor = Pal.lightOrange;
                        serrations = 5;
                        serrationWidth = 3f;
                        serrationSpacing = 9f;
                    }};
                }},
                new Weapon(Name + "-hadruon-stinger") {{
                    reload = 96f;
                    rotate = true;
                    rotateSpeed = 2f;
                    rotationLimit = 120f;
                    shootCone = 3f;
                    shoot.firstShotDelay = 20f;

                    shootStatus = StatusEffects.unmoving;
                    shootStatusDuration = 20f;

                    x = 0f;
                    y = -20f;
                    shootY = 6.75f;
                    recoil = 4f;
                    mirror = false;
                    controllable = false;
                    autoTarget = true;

                    ejectEffect = Fx.none;
                    shootSound = Sounds.artillery;
                    parts.add(new RegionPart("-round"){{
                        x = 0f;
                        y = 6.75f;
                        //lmfao
                        progress = PartProgress.charge.inv().mul(1000f).add(-1000f).add(PartProgress.reload.shorten(0.45f));
                        moveY = -10.5f;
                        outline = false;
                        under = true;
                        mirror = false;
                    }});

                    bullet = new BaseBulletType(4f, 240, Name + "-hadruon-stinger-round"){{
                        hitSize = 6f;
                        splashDamage = 140;
                        splashDamageRadius = 24f;
                        lifetime = 60f;

                        collidesAir = false;
                        reflectable = false;

                        smokeEffect = Fx.none;
                        shootEffect = Fx.shootBig;
                        hitSound = Sounds.explosion;
                        hitEffect = Fx.blastExplosion;
                        backColor = Color.white;
                        frontColor = Color.white;
                        width = 5f;
                        height = 8f;
                        trailColor = Pal.lightOrange;
                        trailLength = 9;
                    }};
                }}
            );
        }});
        leiurus = EntityRegistry.content("leiurus", LegsUnit.class, leiurus -> new UnitType(leiurus) {{
            speed = 0.51f;
            hitSize = 40f;
            health = 30400;
            armor = 15f;
            hovering = true;
            targetAir = false;
            drownTimeMultiplier = 2.5f;

            rotateSpeed = 1.4f;
            allowLegStep = true;
            legCount = 8;
            legGroupSize = 2;
            lockLegBase = true;
            legLength = 27f;
            legExtension = 3f;
            legBaseOffset = 8f;
            legMoveSpace = 1.4f;
            legMaxLength = 1.8f;
            legMinLength = 0.4f;
            rippleScale = 0.6f;
            stepShake = 0.8f;

            legSplashDamage = 132;
            legSplashRange = 9;

            shadowElevation = 0.5f;
            groundLayer = Layer.legUnit;

            weapons.add(
                new Weapon(Name + "-leiurus-weapon") {{
                    reload = 32f;
                    rotate = true;
                    rotateSpeed = 0.8f;
                    rotationLimit = 30f;
                    shootCone = 90f;
                    inaccuracy = 3f;

                    shootStatus = StatusEffects.slow;
                    shootStatusDuration = 12f;

                    x = 24f;
                    y = 20f;
                    shootX = 0.5f;
                    shootY = 10f;
                    recoil = 5f;
                    alternate = false;

                    ejectEffect = Fx.none;
                    shootSound = Sounds.shootSnap;
                    bullet = new ShrapnelBulletType() {{
                        damage = 120;
                        lifetime = 20f;
                        speed = 10f;
                        drag = 0.1f;
                        length = 70f;
                        width = 28f;
                        collides = true;
                        pierceCap = 7;
                        pierceBuilding = true;

                        knockback = 27f;
                        recoil = 2.25f;
                        status = SMMMStatusEffects.kineticStunned;
                        statusDuration = 15f;

                        toColor = Pal.lightOrange;
                        serrations = 7;
                        serrationWidth = 5f;
                        serrationSpacing = 12f;

                        for(int i = 0; i < 3; i++){
                            int j = i;
                            spawnBullets.add(new LightningBulletType(){{
                                damage = 32 + j * 12;
                                lightningLength = 12 - j * 3;

                                status = StatusEffects.shocked;
                                statusDuration = 10f;
                            }});
                        }
                    }};
                }},
                new Weapon(Name + "-leiurus-stinger") {{
                    reload = 144f;
                    minWarmup = 0.99f;
                    rotate = true;
                    rotateSpeed = 1.5f;
                    rotationLimit = 120f;
                    shootCone = 90f;

                    shootStatus = StatusEffects.unmoving;
                    shootStatusDuration = 20f;

                    x = 0f;
                    y = -32f;
                    layerOffset = 0.001f;
                    shootY = 8f;
                    recoil = 3f;
                    mirror = false;
                    controllable = false;
                    autoTarget = true;

                    ejectEffect = Fx.none;
                    shootSound = Sounds.artillery;
                    parts.add(new RegionPart("-rail"){{
                        x = 0f;
                        y = 0f;
                        //lmfao
                        progress = PartProgress.warmup.curve(Interp.pow2Out);
                        moveY = 14f;
                        outline = true;
                        under = true;
                        mirror = false;

                        moves.add(new PartMove(PartProgress.reload, 0f, -4f, 0f));
                    }});
                    parts.add(new RegionPart("-marking"){{
                        x = 0f;
                        y = 0f;
                        //lmfao
                        progress = PartProgress.warmup.curve(Interp.pow2Out);
                        moveY = 20f;
                        outline = false;
                        under = true;
                        mirror = false;

                        moves.add(new PartMove(PartProgress.reload, 0f, -4f, 0f));
                    }});

                    bullet = new BasicBulletType(11f, 324){{
                        lifetime = 27f;
                        pierceArmor = true;
                        pierce = true;
                        pierceCap = 3;
                        pierceBuilding = true;

                        knockback = 7f;
                        recoil = 1f;
                        status = SMMMStatusEffects.kineticStunned;
                        statusDuration = 24f;
                        fragBullets = 2;
                        fragRandomSpread = 30f;
                        fragVelocityMin = 0.5f;
                        fragBullet = new ShrapnelBulletType() {{
                            damage = 72;
                            lifetime = 10f;
                            speed = 5f;
                            drag = 0.1f;
                            length = 30f;
                            width = 15f;
                            collides = true;
                            pierceCap = 7;
                            pierceBuilding = true;

                            recoil = 2.25f;
                            status = SMMMStatusEffects.kineticStunned;
                            statusDuration = 3f;

                            toColor = Modpal.kineticAccent;
                            serrations = 2;
                            serrationWidth = 5f;
                            serrationSpacing = 12f;
                        }};

                        intervalBullets = 2;
                        bulletInterval = 3f;
                        intervalRandomSpread = 0f;
                        intervalSpread = 180f;
                        intervalBullet = new BasicBulletType(6f, 84) {{
                            lifetime = 45f;
                            pierce = true;
                            pierceCap = 3;
                            pierceBuilding = true;
                            buildingDamageMultiplier = 0.5f;

                            homingPower = 0.2f;
                            homingRange = 180f;
                            homingDelay = 10f;

                            width = 7f;
                            height = 11f;
                            frontColor = Modpal.kineticAccent;
                            backColor = Modpal.kineticAccentDark;
                            trailColor = Modpal.kineticAccentDark;
                            trailLength = 2;
                        }};

                        sprite = Name + "-leiurus-stinger-round";
                        width = 16f;
                        height = 20f;
                        shrinkX = shrinkY = 0f;
                        frontColor = Modpal.kineticAccent;
                        backColor = Modpal.kineticAccentDark;
                        trailColor = Modpal.kineticAccent;
                        trailLength = 9;
                    }};
                }}
            );
        }});
    }
}