package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Bullet extends SpaceObject {

    public Bullet() {
        shapex = new float[2];
        shapey = new float[2];

    }

    @Override
    public void wrap() {

    }

    private void setShape(float rad) {
        shapex[0] = x + MathUtils.cos(rad) * 4;
        shapey[0] = y + MathUtils.sin(rad) * 4;
        shapex[1] = x + MathUtils.cos(rad) * 8;
        shapey[1] = y + MathUtils.sin(rad) * 8;
    }

    public void update(float dt, Enemy enemy) {
        int move = (int) (Math.random() * 100);
        // living
        if (move < 1) {
            x = enemy.x;
            y = enemy.y;
            dx = MathUtils.cos(enemy.radians) * 200;
            dy = MathUtils.sin(enemy.radians) * 200;
        }
        x += dx * dt;
        y += dy * dt;

        // set shape
        setShape(enemy.radians);

        // screen wrap
        wrap();

    }

    public void draw(ShapeRenderer sr) {

        sr.setColor(255, 0, 71, 1);

        sr.begin(ShapeRenderer.ShapeType.Line);

        for (int i = 0, j = shapex.length - 1;
             i < shapex.length;
             j = i++) {

            sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);

        }

        sr.end();

    }

}

