package se331.passport.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import se331.passport.util.StorageFileDto;
import se331.passport.util.SupabaseStorageService;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class SupabaseController {

    final SupabaseStorageService supabaseStorageService;

    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileUrl = supabaseStorageService.uploadFile(file);
            return ResponseEntity.ok(fileUrl);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
        }
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) {
        try {
            StorageFileDto fileUrl = supabaseStorageService.uploadImage(file);
            return ResponseEntity.ok(fileUrl);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
        }
    }

    @GetMapping("/presignedUrl")
    public ResponseEntity<String> getPresignedUrl(
            @RequestParam(value = "fileName", required = false) String fileName,
            @RequestParam(value = "key", required = false) String key) {
        try {
            String targetFile = (key != null && !key.isEmpty()) ? key : fileName;
            String presignedUrl = supabaseStorageService.getPresignedUrl(targetFile);
            return ResponseEntity.ok(presignedUrl);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error generating presigned url: " + e.getMessage());
        }
    }
}