package com.asthana.Redis_Cache_Spring_Boot.Service;

import java.util.UUID;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.asthana.Redis_Cache_Spring_Boot.Entity.NoteEntity;
import com.asthana.Redis_Cache_Spring_Boot.Repository.NoteRepo;

@Service
public class NoteService {

    private final NoteRepo noteRepo;

    public NoteService(NoteRepo noteRepo) {
        this.noteRepo = noteRepo;
    }

    // Save
    @CachePut(value = "notes", key = "#noteEntity.Id")      //saved in cache at the time of creation
    public NoteEntity create(NoteEntity noteEntity) {
        noteEntity.setId(UUID.randomUUID().toString());
        return noteRepo.save(noteEntity);
    }

    // Get all notes
    public List<NoteEntity> getAll() {
        return noteRepo.findAll();
    }

    // Get note by ID
    // @Cacheable(value = "user",key = "#Id")           //here the cache name is notes and the Id of that particular cache data is noteId
    @Cacheable(value = "notes", key = "#noteId")        //saved in cache at the time of first call
    public NoteEntity getById(String noteId) {
        return noteRepo.findById(noteId).orElseThrow(() -> new RuntimeException("Note not found"));
    }

    // Update note
    public NoteEntity updateNote(String noteId, NoteEntity noteEntity) {
        NoteEntity noteToUpdate = noteRepo.findById(noteId).orElseThrow(() -> new RuntimeException("Note not found"));
        noteToUpdate.setTitle(noteEntity.getTitle());
        noteToUpdate.setContent(noteEntity.getContent());
        noteToUpdate.setLive(noteEntity.isLive());
        return noteRepo.save(noteToUpdate);
    }

    // Delete note
    @CacheEvict(value = "notes", key = "#noteId")               //deleted from cache
    public void delete(String noteId) {
        NoteEntity noteToDelete = noteRepo.findById(noteId).orElseThrow(() -> new RuntimeException("Note not found"));
        noteRepo.delete(noteToDelete);
    }
}
