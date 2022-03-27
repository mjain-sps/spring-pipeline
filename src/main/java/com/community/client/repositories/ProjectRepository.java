package com.community.client.repositories;

import com.community.client.models.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {
    //1. Save the project details
    Project save(Project project);

    //2. Retrieve all project
    Set<Project> findAll();

    //3. Get one project by id;
    Optional<Project> findProjectById(Long id);

    //4. Get one project by name;
    @Query(value = "SELECT * from project_table where project_name LIKE %:keyword%", nativeQuery = true)
    Set<Project> findProjectByName(@Param("keyword") String keyword);
}
