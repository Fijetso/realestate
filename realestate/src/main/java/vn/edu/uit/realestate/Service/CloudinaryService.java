package vn.edu.uit.realestate.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
	@Autowired
    private Cloudinary cloudinaryConfig;

	@Value("${cloudinary.folder}")
	private String folder;
	
    public String uploadFile(MultipartFile file) {
        try {
            File uploadedFile = convertMultiPartToFile(file);
            Transformation parseToPNG = new Transformation().fetchFormat("png");
            Map params = ObjectUtils.asMap(
            		"folder", folder,
//            		"use_filename", false,
//            		"unique_filename", true,
            		"transformation", parseToPNG);
            Map uploadResult = cloudinaryConfig.uploader().upload(uploadedFile, params);
            return  uploadResult.get("url").toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
