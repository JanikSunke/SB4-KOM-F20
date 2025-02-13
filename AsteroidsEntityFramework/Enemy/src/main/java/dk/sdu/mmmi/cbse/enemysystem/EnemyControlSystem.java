package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class EnemyControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity enemy : world.getEntities(Enemy.class)) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovingPart movingPart = enemy.getPart(MovingPart.class);
            int move = (int)(Math.random() * 10);
            if (move < 2) {
                movingPart.setLeft(true);
                movingPart.setRight(false);
            } else if (move > 9) {
                movingPart.setRight(true);
                movingPart.setLeft(false);
            } else {
                movingPart.setLeft(false);
                movingPart.setRight(false);
            }
            if (move < 2) {
                movingPart.setUp(true);
            }

            movingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);

            updateShape(enemy);
        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * 4);
        shapey[0] = (float) (y + Math.sin(radians) * 4);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 10);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 10);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 12);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 12);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 10);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 10);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}
