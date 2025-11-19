package de.linushuck.utopia.skyblock.libs.api.item.nbthandler;

import de.tr7zw.changeme.nbtapi.iface.NBTHandler;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import de.tr7zw.changeme.nbtapi.iface.ReadableNBT;

import java.util.HashMap;
import java.util.Map;

public class CustomHashMap<A1, B1, A extends Convertable<A1>, B extends Convertable<B1>> implements NBTHandler<Map<A, B>>
{

    private final Class<A> classA;
    private final Class<B> classB;

    // Constructor to accept Class types for A and B
    public CustomHashMap(Class<A> classA, Class<B> classB)
    {
        this.classA = classA;
        this.classB = classB;
    }

    @Override
    public void set(ReadWriteNBT nbt, String key, Map<A, B> value)
    {
        StringBuilder builder = new StringBuilder();
        for(Map.Entry<A, B> entry : value.entrySet())
        {
            builder.append(entry.getKey().toString())
                    .append("|:HM:|")
                    .append(entry.getValue().toString())
                    .append("|,HM,|");
        }
        if(!builder.isEmpty())
        {
            builder.setLength(builder.length() - 6);
        }
        nbt.setString(key, builder.toString());
    }

    @Override
    public HashMap<A, B> get(ReadableNBT nbt, String key)
    {
        String string = nbt.getString(key);
        HashMap<A, B> map = new HashMap<>();
        String[] pairs = string.split("\\|,HM,\\|", -1);
        for(String pair : pairs)
        {
            String[] keyValue = pair.split("\\|:HM:\\|");
            if(keyValue.length == 2)
            {
                try
                {
                    A a = (A) classA.getDeclaredConstructor().newInstance().fromString(keyValue[0]);
                    B b = (B) classB.getDeclaredConstructor().newInstance().fromString(keyValue[1]);
                    map.put(a, b);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }
}