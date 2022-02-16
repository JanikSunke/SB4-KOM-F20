package dk.sdu.mmmi.cbse.shootingsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class ShootingPlugin implements IGamePluginService {

    private Entity bullet;

    public ShootingPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        bullet = createBullet(gameData);
        world.addEntity(bullet);
    }

    private Entity createBullet(GameData gameData) {
        float deacceleration = 0;
        float acceleration = 5000000;
        float maxSpeed = 350;
        float rotationSpeed = 5;
        float x = 0;
        float y = 0;
        float radians = 3.1415f / 2;

        Entity bullet = new Shooting();
        bullet.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        bullet.add(new PositionPart(x, y, radians));
        bullet.setRadius(10);

        return bullet;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(bullet);
    }

}

