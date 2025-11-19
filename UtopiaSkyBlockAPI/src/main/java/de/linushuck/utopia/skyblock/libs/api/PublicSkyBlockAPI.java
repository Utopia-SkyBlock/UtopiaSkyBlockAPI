package de.linushuck.utopia.skyblock.libs.api;

import de.linushuck.utopia.skyblock.libs.api.interfaces.*;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class PublicSkyBlockAPI
{
    @Getter
    private static PublicSkyBlockAPI instance;
    private final JavaPlugin plugin;
    private IMongoDBConnection mongoDBConnection;
    private IRedisConnection redisConnection;
    private ICooldownHelper cooldownHelper;
    private IRedisCommonRequests redisCommonRequests;
    private ISkyBlockProfileHelper skyBlockProfileHelper;
    private IUIModule uiModule;
    private IGUIHelper guiHelper;

    public PublicSkyBlockAPI(JavaPlugin plugin)
    {
        this.plugin = plugin;
        instance = this;
    }

    public void setMongoDBConnection(IMongoDBConnection mongoDBConnection)
    {
        if(this.mongoDBConnection != null)
        {
            throw new IllegalStateException("MongoDBConnection already set");
        }
        this.mongoDBConnection = mongoDBConnection;
    }

    public void setRedisConnection(IRedisConnection redisConnection)
    {
        if(this.redisConnection != null)
        {
            throw new IllegalStateException("RedisConnection already set");
        }
        this.redisConnection = redisConnection;
    }

    public void setCooldownHelper(ICooldownHelper iCooldownHelper)
    {
        if(this.cooldownHelper != null)
        {
            throw new IllegalStateException("CooldownHelper already set");
        }
        this.cooldownHelper = iCooldownHelper;
    }

    public void setRedisCommonRequests(IRedisCommonRequests iRedisCommonRequests)
    {
        if(this.redisCommonRequests != null)
        {
            throw new IllegalStateException("RedisCommonRequests already set");
        }
        this.redisCommonRequests = iRedisCommonRequests;
    }

    public void setSkyBlockProfileHelper(ISkyBlockProfileHelper iSkyBlockProfileHelper)
    {
        if(this.skyBlockProfileHelper != null)
        {

            throw new IllegalStateException("SkyBlockProfileHelper already set");
        }
        this.skyBlockProfileHelper = iSkyBlockProfileHelper;
    }

    public void setUIModule(IUIModule uiModule)
    {
        if(this.uiModule != null)
        {

            throw new IllegalStateException("UIModule already set");
        }
        this.uiModule = uiModule;
    }

    public void setGuiHelper(IGUIHelper guiHelper)
    {
        if(this.guiHelper != null)
        {

            throw new IllegalStateException("GUIHelper already set");
        }
        this.guiHelper = guiHelper;
    }
}
