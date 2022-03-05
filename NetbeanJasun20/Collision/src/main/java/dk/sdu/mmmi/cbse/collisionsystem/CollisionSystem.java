package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = IPostEntityProcessingService.class)
public class CollisionSystem implements IPostEntityProcessingService{
    String PLAYER = "class dk.sdu.mmmi.cbse.playersystem.Player";
    String ENEMY = "class dk.sdu.mmmi.cbse.enemysystem.Enemy";
    String BULLET = "class dk.sdu.mmmi.cbse.bulletsystem.Bullet";
    int kills = 0;

    @Override
    public void process(GameData gameData, World world) {
        for(Entity entity : world.getEntities()){
            for(Entity entity1 : world.getEntities()){
                if (!(entity.getClass() == entity1.getClass())) {
                    if (collision(entity, entity1)) {
                        if (entity1.getClass().toString().equals(PLAYER) || entity1.getClass().toString().equals(ENEMY)) {
                            world.removeEntity(entity1);
                        } else {
                            System.out.println("\n\n\n\n\n");
                            kills++;
                            System.out.println("----------------------");
                            System.out.println("KILLS: " + kills);
                            System.out.println("----------------------");
                            if (kills == 10) {
                                System.out.println("YOU WIN");
                            }
                            if (entity.getClass().toString().equals(BULLET)) {
                                world.removeEntity(entity1);
                            } else {
                                world.removeEntity(entity);
                            }
                        }
                    }
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
