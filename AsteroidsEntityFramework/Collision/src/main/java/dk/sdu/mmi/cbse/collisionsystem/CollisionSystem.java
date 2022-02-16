package dk.sdu.mmi.cbse.collisionsystem;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class CollisionSystem implements IPostEntityProcessingService{
    @Override
    public void process(GameData gameData, World world) {
        for(Entity entity : world.getEntities()){
            for(Entity entity1 : world.getEntities()){
                if (entity.getClass() == entity1.getClass()){
                    continue;
                }
                if (collision(entity, entity1)) {
                    world.removeEntity(entity);
                }
            }
        }
    }
    public boolean collision(Entity p1, Entity p2) {
        float dx = p1.getShapeX()[1] - p2.getShapeX()[1];
        float dy = p1.getShapeY()[1] - p2.getShapeY()[1];
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance < p1.getRadius() + p2.getRadius();
    }
}
