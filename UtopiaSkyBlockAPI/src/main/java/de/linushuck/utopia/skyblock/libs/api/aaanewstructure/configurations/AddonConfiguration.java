package de.linushuck.utopia.skyblock.libs.api.aaanewstructure.configurations;

import de.exlll.configlib.*;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.*;

@Configuration
@Getter
@Setter
public class AddonConfiguration
{
    private String name;
    private String version;
    private String main;
    private List<String> authors;
    private String description;

    public static AddonConfiguration load(File file) {
        YamlConfigurationProperties properties = YamlConfigurationProperties.newBuilder()
                .build();

        return YamlConfigurations.update(
                file.toPath(),
                AddonConfiguration.class,
                properties
        );
    }
}