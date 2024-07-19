package arquitecture.ecommerce.application.services;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import arquitecture.ecommerce.application.usecases.ImageUploadService;

@Service
public class ImageUploadManagementService implements ImageUploadService {

    private final Path rootLocation = Paths.get("src/main/resources/static/uploads");
    
    @Override
    public String saveImage(MultipartFile image) {
        try {
            Files.createDirectories(rootLocation);
            if (image.isEmpty()) {
                throw new RuntimeException("Failed to store empty file");
            }
            String filename = UUID.randomUUID().toString() + "-" + image.getOriginalFilename();
            Path destinationFile = this.rootLocation.resolve(filename);
            Files.copy(image.getInputStream(), destinationFile);
            return filename;
        } catch (Exception e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    @Override
    public Resource loadImage(String image) throws MalformedURLException {
        Path absolutePath = rootLocation.resolve(image).toAbsolutePath();

		Resource resource = new UrlResource(absolutePath.toUri());
		
		if (!resource.exists() || !resource.isReadable()) {
			throw new RuntimeException("Error in path: " + absolutePath.toString());
		}
		return resource;
    }
    
}
