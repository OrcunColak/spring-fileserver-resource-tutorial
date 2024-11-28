package com.colak.springtutorial.download.controller.memory.staticresource.file;

import com.colak.springtutorial.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("api/v1/file")

@RequiredArgsConstructor
public class ClassPathResourceController3 {


    @GetMapping(value = "download3")
    public ResponseEntity<Resource> download(@PathVariable("filename") String filename) throws IOException {

        File file = FileService.getFileFromClassPath(filename);

        // Read file size
        long fileSizeInBytes = file.length();
        // Create an input stream for the file
        FileInputStream fileInputStream = new FileInputStream(file);

        // Create a spring boot input stream wrapper
        InputStreamResource inputStreamResource = new InputStreamResource(fileInputStream);

        // return the input stream a response
        return ResponseEntity.ok()
                .contentLength(fileSizeInBytes)
                // APPLICATION_OCTET_STREAM shows that this is a stream
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(inputStreamResource);
    }
}
