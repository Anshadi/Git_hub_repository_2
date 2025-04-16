package com.asthana.Redis_Cache_Spring_Boot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.asthana.Redis_Cache_Spring_Boot.Entity.NoteEntity;
import com.asthana.Redis_Cache_Spring_Boot.Service.NoteService;

@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/create")
    public ResponseEntity<NoteEntity> create(@RequestBody NoteEntity noteEntity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(noteService.create(noteEntity));
    }

    @GetMapping
    public ResponseEntity<List<NoteEntity>> getAll() {
        return ResponseEntity.ok(noteService.getAll());
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<NoteEntity> getSingle(@PathVariable String noteId) {
        return ResponseEntity.ok(noteService.getById(noteId));
    }

    @DeleteMapping("/{noteId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String noteId) {
        noteService.delete(noteId);
    }
}
