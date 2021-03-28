package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public int addFile(File file) {
        return fileMapper.insert(
                new File(null, file.getFilename(), file.getContenttype(), file.getFilesize(), file.getUserid(), file.getFiledata())
        );
    }

    public File getFile(Integer fileId) {
        return fileMapper.getFile(fileId);
    }

    public List<File> getFiles(Integer userid) {
        return fileMapper.getFiles(userid);
    }

    public void deleteFile(Integer fileId) {
        fileMapper.delete(fileId);
    }
}
