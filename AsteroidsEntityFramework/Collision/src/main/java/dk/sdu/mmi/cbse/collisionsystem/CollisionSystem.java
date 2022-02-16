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
                PositionPart pos1 = entity.getPart(PositionPart.class);
                PositionPart pos2 = entity1.getPart(PositionPart.class);
                float distance = (float) Math.sqrt(Math.pow(pos1.getX() - pos2.getX(),2) - Math.pow(pos1.getY() - pos2.getY(),2));
                if(distance < entity.getRadius() + entity1.getRadius()){
                    //collision
                    LifePart lifepartE = entity.getPart(LifePart.class);
                    LifePart lifepartE1 = entity.getPart(LifePart.class);
                    world.removeEntity(entity);
                    //todo proper life collision
                    if(lifepartE != null && lifepartE1 != null){

                        lifepartE.setLife(lifepartE.getLife() - 1);
                        lifepartE1.setLife(lifepartE1.getLife() - 1);
                    }
                }
            }
        }
    }
}
