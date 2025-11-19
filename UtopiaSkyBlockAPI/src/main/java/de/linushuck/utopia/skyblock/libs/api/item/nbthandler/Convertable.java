package de.linushuck.utopia.skyblock.libs.api.item.nbthandler;

public abstract class Convertable<A>
{
    public Convertable(){}

    public abstract String toString();

    // Change to return the current instance type, not A directly
    public abstract Convertable<A> fromString(String string);
}
