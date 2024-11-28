package com.colak.springtutorial.download.controller.memory.staticresource.urlresource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("api/v1/inputstreamsource")

@RequiredArgsConstructor
@Slf4j
public class UrlResourceController {

    @Value("${upload-dir}")
    private String uploadDir;

    // See https://blog.devops.dev/spring-boot-file-upload-download-delete-94982145bea0
    // The :.+ regex pattern allows the path variable to include dots (e.g., "file.txt" or "document.pdf").
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        try {
            Path directory = Paths.get(uploadDir);
            Path filePath = directory.resolve(fileName).normalize();
            log.info("Download file path: {}", fileName);

            Resource resource = new UrlResource(filePath.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (IOException exception) {
            throw new RuntimeException("Failed to download file " + fileName, exception);
        }
    }
}
