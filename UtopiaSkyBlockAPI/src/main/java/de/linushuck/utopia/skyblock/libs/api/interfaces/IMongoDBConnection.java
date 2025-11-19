package de.linushuck.utopia.skyblock.libs.api.interfaces;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

public interface IMongoDBConnection
{
    MongoCollection<Document> getSkyBlockDB(String collectionName);

    <T> MongoCollection<T> getSkyBlockDB(String collectionName, Class<T> clazz);
}
