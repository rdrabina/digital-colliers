package com.task.interview.colliers.digital.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import java.util.*


@Configuration
open class MongoConfig : AbstractMongoClientConfiguration() {

    @Value("\${mongodb.database.name}")
    lateinit var dbName: String

    @Value("\${mongodb.user}")
    lateinit var user: String

    @Value("\${mongodb.user.password}")
    lateinit var password: String

    @Value("\${mongodb.cluster.name}")
    lateinit var cluster: String

    override fun getDatabaseName(): String {
        return dbName
    }

    override fun mongoClient(): com.mongodb.client.MongoClient {
        val connectionString = ConnectionString("mongodb+srv://$user:$password@$cluster.pkxql.mongodb.net/$dbName?retryWrites=true&w=majority")

        val mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build()
        return MongoClients.create(mongoClientSettings)
    }

    public override fun getMappingBasePackages(): MutableCollection<String> {
        return Collections.singleton("com.task.interview.colliers.digital")
    }

}