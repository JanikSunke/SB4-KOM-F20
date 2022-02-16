package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.main.Game;

public class Enemy extends SpaceObject {

    private float maxSpeed;
    private float acceleration;
    private float deceleration;

    public Enemy() {
        x = 20;
        y = 20;

        maxSpeed = 150;
        acceleration = 100;
        deceleration = 10;

        shapex = new float[4];
        shapey = new float[4];

        radians = 3.1415f / 2;
        rotationSpeed = 3;

    }

    private void setShape() {
        shapex[0] = x + MathUtils.cos(radians) * 4;
        shapey[0] = y + MathUtils.sin(radians) * 4;

        shapex[1] = x + MathUtils.cos(radians - 4 * 3.1415f / 5) * 10;
        shapey[1] = y + MathUtils.sin(radians - 4 * 3.1145f / 5) * 10;

        shapex[2] = x + MathUtils.cos(radians + 3.1415f) * 12;
        shapey[2] = y + MathUtils.sin(radians + 3.1415f) * 12;

        shapex[3] = x + MathUtils.cos(radians + 4 * 3.1415f / 5) * 10;
        shapey[3] = y + MathUtils.sin(radians + 4 * 3.1415f / 5) * 10;
    }

    public void update(float dt) {
        int move = (int)(Math.random() * 10);
        // turning
        if (move < 5) {
            radians += rotationSpeed * dt;
        } else {
            radians -= rotationSpeed * dt;
        }

        // accelerating
        if (move < 2) {
            dx += MathUtils.cos(radians) * acceleration * dt;
            dy += MathUtils.sin(radians) * acceleration * dt;
        }

        // deceleration
        float vec = (float) Math.sqrt(dx * dx + dy * dy);
        if (vec > 0) {
            dx -= (dx / vec) * deceleration * dt;
            dy -= (dy / vec) * deceleration * dt;
        }
        if (vec > maxSpeed) {
            dx = (dx / vec) * maxSpeed;
            dy = (dy / vec) * maxSpeed;
        }

        // set position
        x += dx * dt;
        y += dy * dt;

        // set shape
        setShape();

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

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }
}
