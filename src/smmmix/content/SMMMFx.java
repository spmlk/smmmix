package smmmix.content;

import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.math.Interp;
import arc.math.Mathf;
import mindustry.entities.Effect;
import smmmix.graphics.Modpal;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.*;

public class SMMMFx {
    public static Effect

    lgfShoot = new Effect(90f, e -> {
        color(Modpal.lushPollen);
        randLenVectors(e.id, 3, e.fin() * 5,  (x, y) ->
                Fill.circle(e.x + x, e.y + y, 2f * e.fout(Interp.pow2Out)));
        color(Modpal.lushPollenLight);
        randLenVectors(e.id + 1, 2, e.fin() * 7,  (x, y) ->
            Fill.circle(e.x + x, e.y + y, e.fout(Interp.pow2Out))
        );
    }),

    lgfHit = new Effect(30f, e -> {
        var r = 44f;
        color(Modpal.lushPollen);
        Fill.circle(e.x, e.y, r * e.fout(Interp.pow3In));
        stroke(3f * e.fout(Interp.pow2In), Modpal.lushPollenLight);
        circle(e.x, e.y, r);
        randLenVectors(e.id, 7, e.fin() * 20f, (x, y) -> {
            float a = Mathf.angle(x, y) * Mathf.degRad;
            lineAngle((float)(e.x + x + Math.cos(a) * r), (float)(e.y + y + Math.sin(a) * r), Mathf.angle(x, y), e.fout() * 4f);
        });

    }),

    frenzying = new Effect(30f, e -> {
        color(Modpal.statusEffAccent, Color.white, e.fin(Interp.pow2In));
        randLenVectors(e.id, 2, 2f + e.fin(), (x, y) -> {
            Fill.rect(e.x + x, e.y + y, 1f + 9f * e.fin(Interp.pow2In), e.fout(Interp.pow2Out));
        });
    }),

    enduring = new Effect(60f, e -> {
        color(Modpal.statusEffAccent, Color.darkGray, e.fin(Interp.pow2In));
        randLenVectors(e.id, 1, 8f * e.fin(), (x, y) -> {
            Fill.square(e.x + x, e.y + y, 3f * e.fslope(), 45f);
        });
    }),

    resistant = new Effect(75f, e -> {
        color(Color.darkGray);
        randLenVectors(e.id, 1, 6f * e.fin(), (x, y) -> {
            Fill.square(e.x + x, e.y + y, 2f * e.fslope(), 45f);
        });
        color(Modpal.statusEffAccent);
        alpha(e.fin() * 0.4f);
        randLenVectors(e.id, 1, 6f * e.fin(), (x, y) -> {
            Fill.circle(e.x + x, e.y + y, 7f * e.fout());
        });
    });

}
