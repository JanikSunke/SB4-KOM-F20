package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *
 * @author jcs
 *
 */
public interface IPostEntityProcessingService  {

        /**
         * Precondition: A process has been run from IEntityProcessingService <br>
         * Description: Runs Processes post of a service. Can be used to implement collision systems. <br>
         * Postcondition: A process has been run <br>
         *
         * @param gameData
         * @param world
         *
         */
        void process(GameData gameData, World world);
}
