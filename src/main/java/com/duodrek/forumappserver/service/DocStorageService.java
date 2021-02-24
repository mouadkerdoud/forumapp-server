package com.duodrek.forumappserver.service;

import com.duodrek.forumappserver.model.Doc;
import com.duodrek.forumappserver.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface DocStorageService {


    Doc saveFileUser(MultipartFile file, Long userId);

    Doc updateFileUser(MultipartFile file, Long userId);


    List<Doc> getFiles();

    Doc findByUserId(Long userId);

}
