package de.linushuck.utopia.skyblock.libs.api.interfaces;

import net.kyori.adventure.text.Component;

public interface IActionBar
{

    void stop();

    void setNext(Component message, int i);

    void setNextPartial(Component message, int i, int componentReplace);

    void setNext(String message, int i);

    void setNextPartial(String message, int i, int componentReplace);
}
