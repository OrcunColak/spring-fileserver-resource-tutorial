package com.colak.springtutorial.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

@Service
@RequiredArgsConstructor
public class FileService {

    // Spring’s ResourceLoader is an interface that provides a convenient way to load resources from various locations (e.g., classpath, file system, URL).
    private final ResourceLoader resourceLoader;

    public static File getFileFromClassPath(String filename) throws FileNotFoundException {

        String filePath = "classpath:" + filename;
        // The ResourceUtils utility class offers a straightforward method to load files from the classpath or the file system.
        return ResourceUtils.getFile(filePath);

    }

    public Resource getResourceFromClassPath(String filename) {
        String filePath = "classpath:" + filename;
        return resourceLoader.getResource(filePath);
    }
}
