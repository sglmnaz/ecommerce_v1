package com.synclab.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;

import java.util.Arrays;

@Component
public class GridFsConfig {
	
	@Autowired
	private MongoDatabaseFactory mongoDatabaseFactory;
	
	@Autowired
	private MappingMongoConverter mappingMongoConverter;

	@Bean
	public GridFsTemplate gridFsTemplate() throws Exception {
		return new GridFsTemplate(mongoDatabaseFactory, mappingMongoConverter);
	}
	
	MongoCredential credential = MongoCredential.createCredential("admin", "Ecommerce", "password".toCharArray());
    MongoClient mongoClient = MongoClients.create(MongoClientSettings.builder()
            .applyToClusterSettings(builder ->
                    builder.hosts(Arrays.asList(new ServerAddress("localhost", 27017))))
            .credential(credential)
            .build());
		
	private MongoDatabase db = mongoClient.getDatabase("Ecommerce");

	private GridFSBucket gridFSBucket = GridFSBuckets.create(db);

	public GridFSBucket getGridBucket() {
		return gridFSBucket;
	}

	
	
}
