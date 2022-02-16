package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IEntityProcessingService {
    /**
     * Precondition: An entity to run a process on is present <br>
     * Description: Runs Processes of a service. Can be rendering and/or movement <br>
     * Postcondition: A process has been run <br>
     *
     * @param gameData
     * @param world
     *
     *
     */

    void process(GameData gameData, World world);
}
