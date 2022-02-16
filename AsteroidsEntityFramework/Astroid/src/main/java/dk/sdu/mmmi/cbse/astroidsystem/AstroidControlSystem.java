package dk.sdu.mmmi.cbse.astroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import static dk.sdu.mmmi.cbse.common.data.GameKeys.*;

/**
 *
 * @author jcs
 */
public class AstroidControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity astroid : world.getEntities(Astroid.class)) {
            PositionPart positionPart = astroid.getPart(PositionPart.class);
            MovingPart movingPart = astroid.getPart(MovingPart.class);
            LifePart lifePart = astroid.getPart(LifePart.class);

            movingPart.setUp(true);

            movingPart.process(gameData, astroid);
            positionPart.process(gameData, astroid);
            lifePart.process(gameData,astroid);


            updateShape(astroid);
        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = new float[8];
        float[] shapey = new float[8];

        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();
        float radius = entity.getRadius();

        for(int i = 0; i < shapex.length; i++ ) {
                shapex[i] = (float) (x + Math.cos( radians + 3.1415*2 * ((float) i * (1 / (float) shapex.length))) * radius );
                shapey[i] = (float) (y + Math.sin( radians + 3.1415*2 * ((float) i * (1 / (float) shapex.length))) * radius );
        }

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

}
