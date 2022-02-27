package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.TimerPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = IEntityProcessingService.class)
public class BulletControlSystem implements IEntityProcessingService {
    private int shoot = 0;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity bullet : world.getEntities(Bullet.class)) {
            for (Entity player: world.getEntities()) {
                if (player.getClass().toString().equals("class dk.sdu.mmmi.cbse.playersystem.Player")) {
                    PositionPart positionPart = bullet.getPart(PositionPart.class);
                    MovingPart movingPart = bullet.getPart(MovingPart.class);
                    PositionPart playerMov = player.getPart(PositionPart.class);
                    shoot++;
                    if (shoot % 30 == 1) {
                        movingPart.setUp(true);
                        positionPart.setPosition(playerMov.getX(), playerMov.getY());
                        positionPart.setRadians(playerMov.getRadians());
                    }
                    movingPart.setLeft(false);
                    movingPart.setRight(false);

                    movingPart.process(gameData, bullet);
                    positionPart.process(gameData, bullet);

                    updateShape(bullet);
                }
            }

        }
    }

    private void updateShape(Entity entity) {
        entity.setShapeX(new float[2]);
        entity.setShapeY(new float[2]);
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * 4);
        shapey[0] = (float) (y + Math.sin(radians) * 4);

        shapex[1] = (float) (x + Math.cos(radians) * 8);
        shapey[1] = (float) (y + Math.sin(radians) * 8);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}
