package de.linushuck.utopia.skyblock.libs.api.skyblock.enums;

import java.util.ArrayList;

public enum SkyBlockProfileFruit
{
    APPLE,
    BANANA,
    CHERRY,
    MANGO,
    ORANGE,
    LEMON,
    WATERMELON,
    PUMPKIN,
    RASPBERRY,
    ;

    public static SkyBlockProfileFruit getRandomFruit(ArrayList<SkyBlockProfileFruit> toIgnore)
    {
        ArrayList<SkyBlockProfileFruit> fruits = new ArrayList<>();
        for(SkyBlockProfileFruit fruit : SkyBlockProfileFruit.values())
        {
            if(!toIgnore.contains(fruit))
            {
                fruits.add(fruit);
            }
        }
        return fruits.get((int) (Math.random() * fruits.size()));
    }
}
