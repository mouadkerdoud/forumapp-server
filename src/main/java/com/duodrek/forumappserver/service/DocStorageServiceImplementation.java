package com.duodrek.forumappserver.service;

import java.util.List;
import java.util.Optional;

import com.duodrek.forumappserver.model.Doc;
import com.duodrek.forumappserver.repository.DocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class DocStorageServiceImplementation implements DocStorageService {
    @Autowired
    private DocRepository docRepository;

    @Override
    public Doc saveFile(MultipartFile file) {
        String docname = file.getOriginalFilename();
        try {
            Doc doc = new Doc(docname, file.getContentType(), file.getBytes());
            return docRepository.save(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Doc> getFile(Integer fileId) {
        return docRepository.findById(fileId);
    }

    @Override
    public List<Doc> getFiles() {
        return docRepository.findAll();
    }

}
