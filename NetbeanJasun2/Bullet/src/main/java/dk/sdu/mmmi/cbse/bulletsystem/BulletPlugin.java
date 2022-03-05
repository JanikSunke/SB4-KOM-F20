package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

@ServiceProviders(value = {
    @ServiceProvider(service = IGamePluginService.class),})
public class BulletPlugin implements IGamePluginService {

    private Entity bullet;

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

        Entity bullet = new Bullet();
        MovingPart movingPart = new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed);
        movingPart.setUp(true);
        bullet.add(movingPart);
        bullet.add(new PositionPart(x, y, radians));
        bullet.add(new LifePart(-1));
        bullet.setRadius(10);

        return bullet;
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Bullet.class) {
                world.removeEntity(e);
            }
        }
    }

}
