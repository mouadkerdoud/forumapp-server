package com.duodrek.forumappserver.service;

import java.util.List;
import java.util.Optional;

import com.duodrek.forumappserver.model.Doc;
import com.duodrek.forumappserver.model.User;
import com.duodrek.forumappserver.repository.DocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class DocStorageServiceImplementation implements DocStorageService {
    @Autowired
    private DocRepository docRepository;

    @Override
    public Doc saveFileUser(MultipartFile file, Long userId) {
        String docname = file.getOriginalFilename();
        try {
            Doc doc = new Doc(docname, file.getContentType(), file.getBytes(), userId);
            return docRepository.save(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Doc updateFileUser(MultipartFile file, Long userId){
        Doc existingDocument = docRepository.findByUserId(userId).orElse(null);
        existingDocument.setDocName(file.getOriginalFilename());
        try {
            existingDocument.setDocType(file.getContentType());
            existingDocument.setData(file.getBytes());
            existingDocument.setUserId(userId);
            return docRepository.save(existingDocument);
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


    @Override
    public Doc findByUserId(Long userId){
        return docRepository.findByUserId(userId).orElse(null);
    }


}
