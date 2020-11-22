package com.synclab.ecommerce.controller.managementAPIs;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.synclab.ecommerce.config.GridFsConfig;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;

@RestController()
@RequestMapping("/image/gridfs")
public class ImageControllerGridFs {

    @Autowired
    private GridFsOperations gridFsOperations;
    
    @Autowired
    private GridFsConfig gridFsConfig;

    
    //post
    @PostMapping(value = "/insert")
    public ResponseEntity<String> insertGridFs(@RequestPart(value = "file") MultipartFile file) throws IOException {

        if (file == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
           
        DBObject metaDataDbObject = new BasicDBObject();
        metaDataDbObject.put("fileName", file.getOriginalFilename());
        metaDataDbObject.put("fileSize", file.getSize());
        metaDataDbObject.put("fileType", file.getContentType());

        ObjectId fileId = gridFsOperations.store(file.getInputStream(),file.getOriginalFilename(),metaDataDbObject);
        
        return ResponseEntity.ok(fileId.toString());

    }
    
    //get 
//    @GetMapping(value = "/get/{id}")
//    public ResponseEntity<byte[]> getGridFs(@PathVariable(value = "id") String id) {
//
//    	GridFSFile image = gridFsOperations.findOne(new Query(Criteria.where("_id").is(id)));
//
//    	if (image == null)
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//            	
//    	try {
//    		ObjectId objectId = new ObjectId(id);
//    	    FileOutputStream streamToDownloadTo = new FileOutputStream("image");
//    	    gridFsConfig.getGridBucket().downloadToStream(objectId, streamToDownloadTo);;
//    	    streamToDownloadTo.close();
//    	    return ResponseEntity.ok(streamToDownloadTo);
//    	}
//    	catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    	}
//    	
//    	
//    }


}

