package com.fresco.dbrestapi.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fresco.dbrestapi.model.Userposts;

@Repository
public interface UserpostsRepository extends MongoRepository<Userposts, String>{

}
