package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DataController {

    private final List<Note> notes = new ArrayList<>();

    // Public endpoint
    @GetMapping("/public/data")
    public ResponseEntity<String> publicData() {
        return ResponseEntity.ok("This is public data");
    }

    // Secure GET
    @GetMapping("/secure/data")
    public ResponseEntity<List<Note>> getSecureData() {
        return ResponseEntity.ok(notes);
    }

    // Secure POST
    @PostMapping("/secure/data")
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        note.setId(notes.size() + 1);
        notes.add(note);

        return ResponseEntity.status(HttpStatus.CREATED).body(note);
    }

    // Secure PUT
    @PutMapping("/secure/data/{id}")
    public ResponseEntity<Note> updateNote(
            @PathVariable int id,
            @RequestBody Note updatedNote) {

        for (Note note : notes) {
            if (note.getId() == id) {
                note.setText(updatedNote.getText());
                return ResponseEntity.ok(note);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // Secure DELETE
    @DeleteMapping("/secure/data/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable int id) {

        for (Note note : notes) {
            if (note.getId() == id) {
                notes.remove(note);
                return ResponseEntity.noContent().build();
            }
        }

        return ResponseEntity.notFound().build();
    }

    // Simple Note class
    public static class Note {

        private int id;
        private String text;

        public Note() {
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}