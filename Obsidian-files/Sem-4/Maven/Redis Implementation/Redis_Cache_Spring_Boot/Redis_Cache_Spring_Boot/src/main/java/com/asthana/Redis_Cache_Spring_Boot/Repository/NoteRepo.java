package com.asthana.Redis_Cache_Spring_Boot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asthana.Redis_Cache_Spring_Boot.Entity.NoteEntity;

public interface NoteRepo extends JpaRepository<NoteEntity,String> {

}
