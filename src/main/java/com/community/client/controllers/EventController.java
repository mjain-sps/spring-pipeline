package com.community.client.controllers;

import com.community.client.models.Community;
import com.community.client.models.Event;
import com.community.client.models.Project;
import com.community.client.services.CommunityService;
import com.community.client.services.EventService;
import com.community.client.services.ProjectService;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

/**
 * Represents the REST Controller for handling requests related to creating events and
 * adding an event to a community or project.
 */
@RestController
public class EventController {


    // Dependency injections. Use of dependency injection reduces coupling.
    private final EventService eventService;
    private final CommunityService communityService;
    private final ProjectService projectService;

    private EventController(EventService eventService, CommunityService communityService, ProjectService projectService) {
        this.eventService = eventService;
        this.communityService = communityService;
        this.projectService = projectService;
    }

    /**
     * Handles HTTP POST requests. Maps event information contained in the @RequestBody
     * and @PathVariables onto the Event model and saves the new event to the event database.
     * @param event JSON containing event name, date, address, aboutSection, contributors,
     *              creatorUserId, and eventImage(optional).
     * @param parent String from request which determines if the event is related to a
     *               "community" or "project".
     * @param id Community or project id from request.
     * @return savedEvent Contains event information for the individual event.
     */
    @PostMapping("/api/add-event/{parent}/{id}")
    private Event addEvent(@RequestBody Event event, @PathVariable String parent, @PathVariable Long id){

        Event savedEvent = eventService.saveEvent(event);

        if (parent.equals("community")) {
            Community community = communityService.getCommunityById(id);
            savedEvent.setCommunity(community);
            Set<Event> eventsInCommunity = community.getEvent();
            eventsInCommunity.add(savedEvent);
            community.setEvent(eventsInCommunity);
            communityService.saveCommunity(community);

        } else if (parent.equals("project")) {
            Project project = projectService.getProjectById(id);
            savedEvent.setProject(project);
            Set<Event> eventsInProject = project.getEvent();
            eventsInProject.add(savedEvent);
            project.setEvent(eventsInProject);
            projectService.saveProject(project);
        }
        return savedEvent;
    }
}
