package smmmix.world.blocks.environment;

import arc.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.util.Tmp;
import mindustry.game.*;
import mindustry.graphics.*;
import mindustry.world.*;
import mindustry.world.blocks.environment.*;
import smmmix.graphics.TextureRegionf;
import java.util.Arrays;
import java.util.OptionalInt;

import static mindustry.Vars.*;

public class StaticLargeWall extends StaticWall{
    public int maxSize = 3;
    public int[] sizeVariants = {2, 1};
    public boolean drawOres = true;
    // gotta fuck with rendering if walls drawing over each other will ever be a thing unfortunately
    //public boolean overlap = false;

    /** Wall placement algorithm adapted from AvantTeam/ProjectUnityPublic's LargeStaticWall class. */
    public int[][] taken;
    public int[][] takenSize;
    public int[][] varSeed;

    /** for reference, [size][variant][row][col] */
    public TextureRegion[][][][] variantSplits;
    /** [size][row][col] */
    public TextureRegion[][][] splits;
    public int maxVariants;


    public StaticLargeWall(String name){
        super(name);
        //cacheLayer = CacheLayer.normal;
        //layer = Layer.blockProp + 2f - 0.91f;
        Events.on(EventType.WorldLoadEndEvent.class, e->{
            taken = new int[world.width()][world.height()];
            takenSize = new int[world.width()][world.height()];
            varSeed = new int[world.width()][world.height()];
        });
    }

    @Override
    public void drawBase(Tile tile){
        int sdx, vdx, rdx, cdx;
        sdx = vdx = rdx = cdx = 0;
        boolean large = false;
        TextureRegion reg;
        TextureRegion r = Tmp.tr1;

        for(int s = maxSize;s >= 2; s--){
            if(eq(tile.x, tile.y, s)){
                take(tile.x, tile.y, s);
                sdx = s - 2;
                vdx = Mathf.randomSeed(varSeed[tile.x][tile.y], 0, Math.max(0, sdx < sizeVariants.length? sizeVariants[sdx] - 1: 0));
                cdx = s - 1;
                large = true;
                break;
            }
            if(taken[tile.x][tile.y]!=0 && takenSize[tile.x][tile.y] == s){
                sdx = s - 2;
                vdx = Mathf.randomSeed(varSeed[tile.x][tile.y], 0, Math.max(0, sdx < sizeVariants.length? sizeVariants[sdx] - 1: 0));
                rdx = Point2.x(taken[tile.x][tile.y]);
                cdx = s - 1 - Point2.y(taken[tile.x][tile.y]);
                large = true;
                break;
            }
        }
        if(!large){
            reg = variants > 0 ? variantRegions[Mathf.randomSeed(tile.pos(), 0, Math.max(0, variantRegions.length - 1))]: region;
        }else if(maxVariants > 1){
            reg = variantSplits[sdx][vdx][rdx][cdx];
        }else{
            reg = splits[sdx][rdx][cdx];
        }

        r.set(reg);
        int crop = (r.width - tilesize * 4) / 2;
        float ox = 0;
        float oy = 0;
        //int nearbySize = 0;
        boolean overlapping = false;
        for(int i = 0; i < 4; i++){
            /*if(overlap){
                overlapping = true;
                if(tile.nearby(i) != null && tile.nearby(i).block() instanceof StaticLargeWall) {
                    switch (i) {
                        case 0 -> nearbySize = takenSize[tile.x + 1][tile.y];
                        case 1 -> nearbySize = takenSize[tile.x][tile.y + 1];
                        case 2 -> nearbySize = takenSize[tile.x - 1][tile.y];
                        case 3 -> nearbySize = takenSize[tile.x][tile.y - 1];
                    }
                    overlapping = nearbySize != takenSize[tile.x][tile.y];
                }
            }*/
            if(tile.nearby(i) != null && tile.nearby(i).block() instanceof StaticWall && !overlapping){
                if(i == 0){
                    r.setWidth(r.width - crop);
                    ox -= crop / 2f;
                }else if(i == 1){
                    r.setY(r.getY() + crop);
                    oy -= crop / 2f;
                }else if(i == 2){
                    r.setX(r.getX() + crop);
                    ox += crop / 2f;
                }else{
                    r.setHeight(r.height - crop);
                    oy += crop / 2f;
                }
            }
        }
        /*if(overlap && large){
            Draw.z(Layer.blockProp + 0.0001f * (sdx + 1));
        }*/
        Draw.rect(r, tile.drawx() + ox * Draw.scl, tile.drawy() + oy * Draw.scl);
        if(tile.overlay().wallOre && drawOres) tile.overlay().drawBase(tile);
    }

    @Override
    public void load(){
        super.load();
        OptionalInt mxvar = Arrays.stream(sizeVariants).max();
        maxVariants = mxvar.isPresent()? mxvar.getAsInt(): 0;
        TextureRegion baseRegion;
        if(maxVariants < 2){
            splits = new TextureRegion[maxSize - 1][maxSize][maxSize];
            for(int i = 1; i < maxSize; i++){
                baseRegion = Core.atlas.find(name+"-"+(i+1));
                splits[i-1] = TextureRegionf.uncroppedSplit(baseRegion, i+1, i+1);
            }
        }else{
            variantSplits = new TextureRegion[maxSize - 1][maxVariants][maxSize][maxSize];
            for(int i = 1; i < maxSize; i++){
                int sv = i <= sizeVariants.length? Math.max(2, sizeVariants[i-1] + 1): 2;
                for(int v = 1; v < sv; v++){
                    if(sizeVariants[i-1] > 1){
                        baseRegion = Core.atlas.find(name+"-"+(i+1)+"-"+(v));
                    }else{
                        baseRegion = Core.atlas.find(name+"-"+(i+1));
                    }
                    variantSplits[i - 1][v - 1] = TextureRegionf.uncroppedSplit(baseRegion, i + 1, i + 1);
                }
            }
        }
    }

    void take(int rx, int ry, int s){
        if(!(rx < world.width() - 1 && ry < world.height() - 1)){
            return;
        }
        if(taken==null){
            taken = new int[world.width()][world.height()];
            takenSize = new int[world.width()][world.height()];
            varSeed = new int[world.width()][world.height()];
        }
        for(int i = rx; i < rx + s; i++){
            for(int j = ry; j < ry + s; j++){
                taken[i][j] = Point2.pack(i-rx,j-ry);
                takenSize[i][j] = s;
                varSeed[i][j] = Point2.pack(rx, ry);
            }
        }
    }

    boolean eq(int rx, int ry, int s){
        if(!(rx < world.width() - 1 && ry < world.height() - 1) || Mathf.randomSeed(Point2.pack(rx, ry)) < 0.5){
            return false;
        }
        if(taken == null || taken.length != world.width()){
            taken = new int[world.width()][world.height()];
            takenSize = new int[world.width()][world.height()];
            varSeed = new int[world.width()][world.height()];
        }
        for(int i = rx; i < rx + s; i++){
            for(int j = ry; j < ry + s; j++){
                if(world.tile(i, j) == null || world.tile(i, j).block() != this || taken[i][j] != 0){
                    return false;
                }
            }
        }
        return true;
    }
}