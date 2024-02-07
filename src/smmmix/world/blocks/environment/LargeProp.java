package smmmix.world.blocks.environment;

import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.util.*;
import mindustry.*;
import mindustry.graphics.*;
import mindustry.world.*;

import static mindustry.Vars.*;

//im stealing from steamvent lol
//size2 props will center on the top left
public class LargeProp extends Block {
    public float shadowOffset = 2f;
    public boolean sway = false;
    public int propSize = size;
    public float layer = Layer.power + 1;
    public static final Point2[] offsets3 = {
            new Point2(0, 0),
            new Point2(1, 0),
            new Point2(1, 1),
            new Point2(0, 1),
            new Point2(-1, 1),
            new Point2(-1, 0),
            new Point2(-1, -1),
            new Point2(0, -1),
            new Point2(1, -1),
    };
    public static final Point2[] offsets2 = {
            new Point2(0, 0),
            new Point2(1, 0),
            new Point2(1, 1),
            new Point2(0, 1),
    };

    static{
        for(var p : offsets3){
            p.sub(1, 1);
        }
        for(var p : offsets2){
            p.sub(1, 1);
        }
    }

    public LargeProp(String name){
        super(name);
        variants = 2;
        solid = true;
        customShadow = true;
        breakable = false;
        destructible = false;
    }

    @Override
    public void drawBase(Tile tile){
        if(checkAdjacent(tile)){
            float sOffset = propSize == 3? 0: tilesize / 2;
            Mathf.rand.setSeed(tile.pos());
            TextureRegion reg = variants > 0 ? variantRegions[Mathf.randomSeed(tile.pos(), 0, Math.max(0, variantRegions.length - 1))]: region;
            Draw.z(layer);
            if(!sway) {
                Draw.rect(reg, tile.worldx() - sOffset, tile.worldy() - sOffset);
            }else{
                float
                        rot = Mathf.randomSeed(tile.pos(), 0, 4) * 90 + Mathf.sin(Time.time + tile.worldx(), 50f, 0.5f) + Mathf.sin(Time.time - tile.worldy(), 65f, 0.9f) + Mathf.sin(Time.time + tile.worldy() - tile.worldx(), 85f, 0.9f),
                        w = region.width * region.scl(), h = region.height * region.scl(),
                        scl = 30f, mag = 0.2f;
                Draw.rectv(reg, tile.worldx() - sOffset, tile.worldy() - sOffset, w, h, rot, vec -> vec.add(
                        Mathf.sin(vec.y*3 + Time.time, scl, mag) + Mathf.sin(vec.x*3 - Time.time, 70, 0.8f),
                        Mathf.cos(vec.x*3 + Time.time + 8, scl + 6f, mag * 1.1f) + Mathf.sin(vec.y*3 - Time.time, 50, 0.2f)
                ));
            }
        }
    }

    @Override
    public void drawShadow(Tile tile){
        if(checkAdjacent(tile)) {
            float shOffset = (propSize == 3 ? 0 : tilesize / 2) + shadowOffset;
            Mathf.rand.setSeed(tile.pos());
            TextureRegion reg = variants > 0 ? variantShadowRegions[Mathf.randomSeed(tile.pos(), 0, Math.max(0, variantShadowRegions.length - 1))] : customShadowRegion;
            Draw.z(layer - 2);
            Draw.color(0f, 0f, 0f, BlockRenderer.shadowColor.a);
            if (!sway) {
                Draw.rect(reg, tile.worldx() - shOffset, tile.worldy() - shOffset);
            } else {
                float
                        rot = Mathf.randomSeed(tile.pos(), 0, 4) * 90 + Mathf.sin(Time.time + tile.worldx(), 50f, 0.5f) + Mathf.sin(Time.time - tile.worldy(), 65f, 0.9f) + Mathf.sin(Time.time + tile.worldy() - tile.worldx(), 85f, 0.9f),
                        w = region.width * region.scl(), h = region.height * region.scl(),
                        scl = 30f, mag = 0.2f;
                Draw.rectv(reg, tile.worldx() - shOffset, tile.worldy() - shOffset, w, h, rot, vec -> vec.add(
                        Mathf.sin(vec.y * 3 + Time.time, scl, mag) + Mathf.sin(vec.x * 3 - Time.time, 70, 0.8f),
                        Mathf.cos(vec.x * 3 + Time.time + 8, scl + 6f, mag * 1.1f) + Mathf.sin(vec.y * 3 - Time.time, 50, 0.2f)
                ));
            }
            Draw.color();
        }
    }

    public boolean checkAdjacent(Tile tile){
        if(propSize == 3){
            for(var point : offsets3){
                Tile other = Vars.world.tile(tile.x + point.x, tile.y + point.y);
                if(other == null || other.block() != this){
                    return false;
                }
            }
        }else if(propSize == 2) {
            for (var point : offsets2) {
                Tile other = Vars.world.tile(tile.x + point.x, tile.y + point.y);
                if (other == null || other.block() != this) {
                    return false;
                }
            }
        }
        return true;
    }
}