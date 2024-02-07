package smmmix.entities.bullet;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.entities.bullet.BulletType;
import mindustry.gen.Bullet;
import mindustry.graphics.*;

//it's literally just basicbullettype with some more draw options
public class BaseBulletType extends BulletType{
    public Color backColor = Pal.bulletYellowBack, frontColor = Pal.bulletYellow;
    public float trailLayer = Layer.bullet - 0.0001f, layer = 112f;
    public Color mixColorFrom = new Color(1f, 1f, 1f, 0f), mixColorTo = new Color(1f, 1f, 1f, 0f);
    public float width = 5f, height = 7f;
    public float shrinkX = 0f, shrinkY = 0f;
    public Interp shrinkInterp = Interp.linear;
    public float spin = 0, rotationOffset = 0f;
    public boolean rotated = true;
    public String sprite;
    public float speed = 1f, damage = 1f;
    public @Nullable String backSprite;

    public TextureRegion backRegion;
    public TextureRegion frontRegion;

    public BaseBulletType(float speed, float damage, String... bulletSprite){
        super(speed, damage);
        this.sprite = bulletSprite.length > 0? bulletSprite[0]: "bullet";
    }
    @Override
    public void load(){
        super.load();
        backRegion = Core.atlas.find(backSprite == null ? (sprite + "-back") : backSprite);
        frontRegion = Core.atlas.find(sprite);
    }

    @Override
    public void draw(Bullet b){
        if(trailLength > 0 && b.trail != null){
            Draw.z(trailLayer);
            b.trail.draw(trailColor, trailWidth);
        }
        Draw.z(layer);
        float shrink = shrinkInterp.apply(b.fout());
        float height = this.height * ((1f - shrinkY) + shrinkY * shrink);
        float width = this.width * ((1f - shrinkX) + shrinkX * shrink);
        float offset = -90 + (spin != 0 ? Mathf.randomSeed(b.id, 360f) + b.time * spin : 0f) + rotationOffset;

        Color mix = Tmp.c1.set(mixColorFrom).lerp(mixColorTo, b.fin());

        Draw.mixcol(mix, mix.a);

        if(backRegion.found()){
            Draw.color(backColor);
            Draw.rect(backRegion, b.x, b.y, width, height, (rotated? b.rotation() + offset: 0));
        }

        Draw.color(frontColor);
        Draw.rect(frontRegion, b.x, b.y, width, height, (rotated? b.rotation() + offset: 0));

        Draw.reset();
    }
}
