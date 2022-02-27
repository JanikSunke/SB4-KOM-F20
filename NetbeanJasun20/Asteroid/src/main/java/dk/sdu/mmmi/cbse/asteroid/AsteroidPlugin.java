package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author corfixen
 */
@ServiceProvider(service = IGamePluginService.class)
public class AsteroidPlugin implements IGamePluginService {

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        for (int i = 0; i < 8; i++) {
            world.addEntity(createAsteroid(gameData));
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            world.removeEntity(asteroid);
        }
    }

    private Entity createAsteroid(GameData gameData) {
        float deacceleration = 10;
        float acceleration = 30;
        float maxSpeed = 80;
        float rotationSpeed = 5;
        float radius = 8;
        float x = gameData.getDisplayHeight()/4;
        float y = gameData.getDisplayWidth()/4;

        float radians = 3.1415f * (float)(Math.random()*2);

        Entity astroidEntity = new Asteroid();
        astroidEntity.setRadius(radius);

        astroidEntity.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        astroidEntity.add(new PositionPart(x, y, radians));
        astroidEntity.add(new LifePart(3));

        return astroidEntity;
    }
}
