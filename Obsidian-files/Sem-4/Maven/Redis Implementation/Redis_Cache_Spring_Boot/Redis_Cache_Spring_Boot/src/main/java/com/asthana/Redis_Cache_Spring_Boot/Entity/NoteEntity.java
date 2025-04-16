package com.asthana.Redis_Cache_Spring_Boot.Entity;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Stream_Note")
public class NoteEntity implements Serializable {

    @Id
    private String id;
    private String title;
    private String content;

    private Date dateAdded;

    private Boolean live = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Boolean isLive() {
        return live;
    }

    public void setLive(Boolean live) {
        this.live = live;
    }

    public NoteEntity(String id, String title, String content, Date dateAdded, Boolean live) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dateAdded = dateAdded;
        this.live = live;
    }

    public NoteEntity() {
    }
}
