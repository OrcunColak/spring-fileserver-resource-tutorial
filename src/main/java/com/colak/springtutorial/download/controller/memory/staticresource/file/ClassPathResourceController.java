package com.colak.springtutorial.download.controller.memory.staticresource.file;

import com.colak.springtutorial.service.FileService;
import lombok.RequiredArgsConstructor;
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
public class ClassPathResourceController {

    private final FileService fileService;

    // http://localhost:8080/api/v1/file/download
    @GetMapping(value = "download")
    ResponseEntity<Resource> downloadCsv() {

        HttpHeaders headers = new HttpHeaders();
        // File name
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=songs.csv");
        // File type
        headers.setContentType(MediaType.parseMediaType("text/csv"));

        Resource fileResource = fileService.getResourceFromClassPath("songs.csv");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(fileResource);
    }
}
