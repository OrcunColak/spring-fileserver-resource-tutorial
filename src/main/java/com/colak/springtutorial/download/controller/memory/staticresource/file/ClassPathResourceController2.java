package com.colak.springtutorial.download.controller.memory.staticresource.file;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/file")

// Downloads songs.csv file
// For serving static files, such as images, PDFs, or documents, Spring Boot’s default configuration automatically serves
// files from the /static, /public, /resources, and /META-INF/resources directories on the classpath.
// Ensure that files are placed in one of these directories to make them accessible to clients.
@RequiredArgsConstructor
public class ClassPathResourceController2 {

    // Instead of using ResourceLoader use the file name
    @Value("classpath:songs.csv")
    private Resource fileResource;

    // http://localhost:8080/api/v1/file/download2
    @GetMapping(value = "download2")
    ResponseEntity<Resource> downloadCsv() {

        HttpHeaders headers = new HttpHeaders();
        // File name
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=songs.csv");
        // File type
        headers.setContentType(MediaType.parseMediaType("text/csv"));

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(fileResource);
    }
}
