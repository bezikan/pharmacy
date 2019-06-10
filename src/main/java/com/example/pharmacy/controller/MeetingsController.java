package com.example.pharmacy.controller;

import com.example.pharmacy.exception.MeetingsNotFoundException;
import com.example.pharmacy.model.Meetings;
import com.example.pharmacy.repository.MeetingsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class MeetingsController  implements HandlerInterceptor {

    @Autowired
    public MeetingsRepository meetingsRepository;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/meetings/{id}")
    public ResponseEntity<?> getMeeting(@PathVariable Long id) {
        Meetings meetings = meetingsRepository.findById(id).orElseThrow(()->new MeetingsNotFoundException(id));
        return new ResponseEntity<>(meetings, HttpStatus.OK);
    }
    @GetMapping("/meetings")
    public ResponseEntity<?> getAllMeetings() {
        List<Meetings> meetingsList = meetingsRepository.findAll();
        if(meetingsList.isEmpty()) {
            logger.info("lista jest pusta");
        }
        return new ResponseEntity<>(meetingsList, HttpStatus.OK);
    }

    @PostMapping("/meeting")
    public ResponseEntity<?> createMeetings (@RequestBody Meetings meeting) {
        if(meetingsRepository.findById(meeting.getId()).isPresent()) {
            logger.info("meetings o danym  juz istnieje.");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/meetings/{id}")
    public ResponseEntity<?> editMeetings(@PathVariable Long id, @RequestBody Meetings meeting) {
        return meetingsRepository.findById(id).map(theMeeting -> {
            theMeeting.setId(meeting.getId());
            theMeeting.setPatient(meeting.getPatient());
            theMeeting.setDoctor(meeting.getDoctor());
            theMeeting.setDate(meeting.getDate());
            return new ResponseEntity(theMeeting, HttpStatus.CREATED);
        }).orElseThrow(()->new MeetingsNotFoundException(meeting.getId()));
    }

    @DeleteMapping("/meetings/{id}")
    public ResponseEntity<?> deleteMeeting(@PathVariable Long id) {
        meetingsRepository.findById(id).orElseThrow(()->new MeetingsNotFoundException(id));
        meetingsRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//////////////////////////////////////////////////////////////////
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("prehandle");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("posthandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("afterCompletion");
    }





















}
