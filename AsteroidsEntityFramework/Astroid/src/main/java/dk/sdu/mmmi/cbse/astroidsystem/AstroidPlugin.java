package dk.sdu.mmmi.cbse.astroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class AstroidPlugin implements IGamePluginService {

    private Entity astroid;

    public AstroidPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        for (int i = 0; i < 8; i++) {
            world.addEntity(createAstroid(gameData));
        }

    }

    private Entity createAstroid(GameData gameData) {
        float deacceleration = 10;
        float acceleration = 30;
        float maxSpeed = 80;
        float rotationSpeed = 5;
        float radius = 8;
        float x = gameData.getDisplayHeight()/4;
        float y = gameData.getDisplayWidth()/4;

        float radians = 3.1415f * (float)(Math.random()*2);

        Entity astroidEntity = new Astroid();
        astroidEntity.setRadius(radius);

        astroidEntity.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        astroidEntity.add(new PositionPart(x, y, radians));
        astroidEntity.add(new LifePart(3,1000));
        
        return astroidEntity;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(astroid);
    }

}
