package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
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
import org.openide.util.lookup.ServiceProviders;

@ServiceProviders(value = {
@ServiceProvider(service = IEntityProcessingService.class), @ServiceProvider(service = BulletSPI.class)})
public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            PositionPart positionPart = bullet.getPart(PositionPart.class);
            MovingPart movingPart = bullet.getPart(MovingPart.class);
            movingPart.setUp(true);
            movingPart.setLeft(false);
            movingPart.setRight(false);

            movingPart.process(gameData, bullet);
            positionPart.process(gameData, bullet);

            updateShape(bullet);
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

    @Override
    public void updateShape(Entity entity, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            PositionPart positionPart = bullet.getPart(PositionPart.class);
            PositionPart playerMov = entity.getPart(PositionPart.class);
            positionPart.setPosition(playerMov.getX(), playerMov.getY());
            positionPart.setRadians(playerMov.getRadians());
        }

    }
}
