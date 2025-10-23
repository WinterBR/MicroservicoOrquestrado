package br.com.winter.core.controller;

import br.com.winter.core.dto.EventFilters;
import br.com.winter.core.entity.Event;
import br.com.winter.core.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/event")
public class EventController {

    private final EventService eventService;

    public Event findByFilters(EventFilters filters) {
        return eventService.findByFilters(filters);
    }

    @GetMapping("/all")
    public List<Event> findAll() {
        return eventService.findAll();
    }
}
