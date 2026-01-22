package com.app.upload.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class UploadService {

    private final String UPLOAD_DIR = "/home/ec2-user/uploads/";

    public void saveImages(String number, MultipartFile[] files) {
        try {
            File folder = new File(UPLOAD_DIR + number);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    File dest = new File(folder, file.getOriginalFilename());
                    file.transferTo(dest);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error saving files: " + e.getMessage());
        }
    }

    public List<String> loadImages(String number) {
        List<String> list = new ArrayList<>();
        File folder = new File(UPLOAD_DIR + number);

        if (folder.exists()) {
            for (File file : folder.listFiles()) {
                list.add("/uploads/" + number + "/" + file.getName());
            }
        }
        return list;
    }
}
