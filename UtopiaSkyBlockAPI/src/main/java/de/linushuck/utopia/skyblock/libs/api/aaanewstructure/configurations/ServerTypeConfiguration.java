package de.linushuck.utopia.skyblock.libs.api.aaanewstructure.configurations;

import lombok.Getter;
import lombok.Setter;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;
import java.util.List;

@Getter
@Setter
public class ServerTypeConfiguration
{
    private String name;
    private String description;
    private String version;
    private String main;
    private List<String> authors;

    public static ServerTypeConfiguration load(InputStream inputStream)
    {
        LoaderOptions loaderOptions = new LoaderOptions();
        Yaml yaml = new Yaml(new Constructor(ServerTypeConfiguration.class, loaderOptions));
        return yaml.load(inputStream);
    }
}