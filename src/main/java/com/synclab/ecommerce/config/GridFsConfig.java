package com.synclab.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

public class GridFsConfig {
	
	@Autowired
	private MongoDatabaseFactory mongoDatabaseFactory;
	
	@Autowired
	private MappingMongoConverter mappingMongoConverter;

	@Bean
	public GridFsTemplate gridFsTemplate() throws Exception {
		return new GridFsTemplate(mongoDatabaseFactory, mappingMongoConverter);
	}
	
}
