package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public int addNote(Note note) {
        return noteMapper.insert(
                new Note(null, note.getNotetitle(), note.getNotedescription(), note.getUserid())
        );
    }

    public Note getNote(Integer noteid) {
        return noteMapper.getNote(noteid);
    }

    public List<Note> getNotes(Integer userid) {
        return noteMapper.getNotes(userid);
    }

    public void updateNote(Note note) {
        noteMapper.update(note);
    }

    public void deleteNote(Integer noteid) {
        noteMapper.delete(noteid);
    }
}
