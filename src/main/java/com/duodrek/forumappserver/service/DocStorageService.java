package com.duodrek.forumappserver.service;

import com.duodrek.forumappserver.model.Doc;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface DocStorageService {
    Doc saveFile(MultipartFile file);

    Optional<Doc> getFile(Integer fileId);

    List<Doc> getFiles();
}
