package de.linushuck.utopia.skyblock.libs.api.ability;

import lombok.Getter;

import java.time.Duration;
import java.util.HashMap;

public abstract class BaseAbility
{

    @Getter
    private static final HashMap<String, BaseAbility> abilities = new HashMap<>();

    public abstract String getLore();

    public abstract String getDisplayName();

    public abstract Duration getCooldown();

    public abstract int getManaCost();

    public abstract int getAdditionalInfo();

    public String getDevName()
    {
        return this.getClass().getSimpleName().toLowerCase();
    }
}