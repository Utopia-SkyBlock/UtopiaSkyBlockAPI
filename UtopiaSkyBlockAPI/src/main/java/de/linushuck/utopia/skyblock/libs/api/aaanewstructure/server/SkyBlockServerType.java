package de.linushuck.utopia.skyblock.libs.api.aaanewstructure.server;

import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Data
public abstract class SkyBlockServerType
{
    private String name;
    private UUID serverIdentifier;
    private String description;
    private String version;
    private String mainMethod;
    private String apiVersion;
    private String[] authors;

    public abstract void load();
    public abstract void init();
    public abstract void unload();
}
