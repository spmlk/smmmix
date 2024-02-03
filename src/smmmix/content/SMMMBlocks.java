package smmmix.content;

import mindustry.world.Block;
import mindustry.world.blocks.environment.*;
import smmmix.world.blocks.environment.*;

public class SMMMBlocks{
    public static Block

    //environment - floors
    lushGrass,

    //environment - walls
    lushShrubs,lushPine;
    //environment - props

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
            fillsTile = false;
        }};

    }
}
