package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IGamePluginService {
    /**
     * Precondition: An entity object is instantisated, but not started <br>
     * Description: Used to initialise an entity in the "world" <br>
     * Postcondition: An entity is initalised in the game world
     *
     *
     * @param gameData Data about the game and events present
     * @param world Data about the maps state?
     *
     *
     */
    void start(GameData gameData, World world);


    /**
     * Precondition: An entity is present in the "world"
     * Description: Tears down an entity from the game world.
     * Postcondition: An entity is removed from the "world"
     *
     * @param gameData
     * @param world
     */
    void stop(GameData gameData, World world);
}
