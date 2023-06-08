package com.socialnetwork.main.controller;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.socialnetwork.main.model.ApprovedRegistration;
import com.socialnetwork.main.model.Event;
import com.socialnetwork.main.model.Student;
import com.socialnetwork.main.repository.EventRepository;
import com.socialnetwork.main.repository.StudentRepository;



@Controller
@RequestMapping("/staff")
public class StaffController {
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/students")
    public ResponseEntity<?> getStudents(HttpSession session) {
        ApprovedRegistration user = (ApprovedRegistration) session.getAttribute("loggedInUser");
        if (user == null || !user.getRole().equals("staff")) {
            return ResponseEntity.badRequest().body("You need to login as staff to access this page.");
        }
        
        List<Student> students = studentRepository.findAll();
        
        return ResponseEntity.ok(students);
    }
    
    @PostMapping("/events")
    public ResponseEntity<?> addEvent(@RequestBody EventRequest eventRequest, HttpSession session) {
        ApprovedRegistration user = (ApprovedRegistration) session.getAttribute("loggedInUser");
        if (user == null || !user.getRole().equals("staff")) {
            return ResponseEntity.badRequest().body("You need to login as staff to add events.");
        }
        
        Event event = new Event();
        event.setEvent(eventRequest.getEvent());
        event.setDateOfEvent(eventRequest.getDateOfEvent());
        event.setPeriod(eventRequest.getPeriod());
        
        eventRepository.save(event);
        
        return ResponseEntity.ok("Event added successfully!");
    }

        @PutMapping("/events/{id}")
        public ResponseEntity<?> updateEvent(@PathVariable Long id, @RequestBody EventRequest eventRequest, HttpSession session) {
            ApprovedRegistration user = (ApprovedRegistration) session.getAttribute("loggedInUser");
            if (user == null || !user.getRole().equals("staff")) {
                return ResponseEntity.badRequest().body("You need to login as staff to update events.");
            }
            
            Optional<Event> optionalEvent = eventRepository.findById(id);
            if (optionalEvent.isPresent()) {
                Event event = optionalEvent.get();
                event.setEvent(eventRequest.getEvent());
                event.setDateOfEvent(eventRequest.getDateOfEvent());
                event.setPeriod(eventRequest.getPeriod());
                
                eventRepository.save(event);
                return ResponseEntity.ok("Event updated successfully!");
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/events/{id}")
        public ResponseEntity<?> deleteEvent(@PathVariable Long id, HttpSession session) {
            ApprovedRegistration user = (ApprovedRegistration) session.getAttribute("loggedInUser");
            if (user == null || !user.getRole().equals("staff")) {
                return ResponseEntity.badRequest().body("You need to login as staff to delete events.");
            }
            
            Optional<Event> optionalEvent = eventRepository.findById(id);
            if (optionalEvent.isPresent()) {
                Event event = optionalEvent.get();
                eventRepository.delete(event);
                return ResponseEntity.ok("Event deleted successfully!");
            } else {
                return ResponseEntity.notFound().build();
            }
        }


    public static class EventRequest {
        private String event;
        private LocalDate dateOfEvent;
        private int period;

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }

        public LocalDate getDateOfEvent() {
            return dateOfEvent;
        }

        public void setDateOfEvent(LocalDate dateOfEvent) {
            this.dateOfEvent = dateOfEvent;
        }

        public int getPeriod() {
            return period;
        }

        public void setPeriod(int period) {
            this.period = period;
        }
    }
}

