package smmmix.graphics;

import arc.graphics.g2d.TextureRegion;

import static mindustry.Vars.*;

public class TextureRegionf {
    public static TextureRegion[][] uncroppedSplit(TextureRegion region, int rows, int cols, int... size){
        TextureRegion reg = new TextureRegion(region);
        if(reg.texture == null) return null;
        int width = size.length > 1? size[0]: tilesize * 4;
        int height = size.length > 1? size[1]: tilesize * 4;
        int ox, oy;
        TextureRegion[][] split = new TextureRegion[rows][cols];

        int splitWidth = reg.width - width * (rows - 1);
        int splitHeight = reg.height - height * (cols - 1);
        for(int i = 0; i < rows; i++){
            ox = i * width;
            for(int j = 0; j < cols; j++){
                oy = j * height;
                split[i][j] = new TextureRegion(reg, ox, oy, splitWidth, splitHeight);
            }
        }
        return split;
    }
}