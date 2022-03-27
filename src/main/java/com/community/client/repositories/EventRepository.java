package com.community.client.repositories;

import com.community.client.models.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface EventRepository extends CrudRepository <Event, Long> {

    Event save(Event event);

    Set<Event> findAll();

    @Query(value = "SELECT * from event_table event where event_name LIKE %?1%", nativeQuery = true)
    Set<Event> findByKeyword(@Param("keyword") String keyword);

    Optional<Event> findEventById (Long Id);

}
