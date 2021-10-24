package com.abnamro.spring.data.cassandra.repository;

import com.abnamro.spring.data.cassandra.model.Recipes;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RecipeRepository extends CassandraRepository<Recipes, UUID> {
    @AllowFiltering
    List<Recipes> findByCategory(String category);

}
