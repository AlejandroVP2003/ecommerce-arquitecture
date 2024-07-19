package arquitecture.ecommerce.application.usecases;

import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService {

    String saveImage(MultipartFile image);

    Resource loadImage(String image) throws MalformedURLException;

}
