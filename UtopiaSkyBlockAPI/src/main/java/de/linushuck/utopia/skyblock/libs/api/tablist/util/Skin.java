package de.linushuck.utopia.skyblock.libs.api.tablist.util;

import com.comphenix.protocol.wrappers.WrappedSignedProperty;
import com.google.common.base.Preconditions;

import java.util.Objects;

/**
 * Represents the skin/avatar of a tab item.
 */
public record Skin(WrappedSignedProperty property)
{
    public static final String TEXTURE_KEY = "textures";

    public Skin(String value, String signature)
    {
        this(new WrappedSignedProperty(TEXTURE_KEY, value, signature));
    }

    public Skin
    {
        Preconditions.checkArgument(property.getName().equals(TEXTURE_KEY));
    }

    @Override
    public boolean equals(Object object)
    {
        if(object == this)
        {
            return true;
        }
        else if(object instanceof Skin other)
        {
            boolean sign = Objects.equals(this.property.getSignature(), other.property().getSignature());
            boolean value = Objects.equals(this.property.getValue(), other.property().getValue());
            return sign && value;
        }
        return false;
    }
}