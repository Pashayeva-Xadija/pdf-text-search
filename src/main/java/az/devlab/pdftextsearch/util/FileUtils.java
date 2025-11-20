package az.devlab.pdftextsearch.util;

import lombok.experimental.UtilityClass;

import java.nio.file.Path;
import java.nio.file.Paths;

@UtilityClass
public class FileUtils {

    public String normalizeFileName(String originalFileName) {
        if (originalFileName == null) {
            return "unknown.pdf";
        }
        String fileName = originalFileName.trim();

        fileName = fileName.replace("\\", "/");
        int lastSlashIndex = fileName.lastIndexOf('/');
        if (lastSlashIndex != -1) {
            fileName = fileName.substring(lastSlashIndex + 1);
        }

        if (fileName.isBlank()) {
            return "unknown.pdf";
        }
        return fileName;
    }

    public Path buildStoragePath(String baseDir, String fileName) {
        return Paths.get(baseDir).resolve(fileName).normalize();
    }
}
