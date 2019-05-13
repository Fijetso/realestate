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
	
    public String uploadImage(MultipartFile file) {
        try {
            File uploadedFile = convertMultiPartToFile(file);
            Transformation parseToWebp = new Transformation().fetchFormat("webp").quality("30");
            Map params = ObjectUtils.asMap(
            		"folder", folder,
            		"transformation", parseToWebp
            		);
            Map uploadResult = cloudinaryConfig.uploader().upload(uploadedFile, params);
            return  uploadResult.get("url").toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private String getPublicId(String imageLink) {
    	String[] stringList = imageLink.split(this.folder+"/");
    	int startIndex = imageLink.indexOf(this.folder) + this.folder.length() +1;
    	int endIndex = imageLink.indexOf(".",startIndex);
    	String public_id = imageLink.substring(startIndex, endIndex);
    	return public_id;
    }
    public boolean deleteImage(String imageLink) {
    	try {
    		String public_id = getPublicId(imageLink);
			cloudinaryConfig.uploader().destroy(public_id, ObjectUtils.asMap("invalidate", true));
		} catch (IOException e) {
			e.printStackTrace();
	    	return false;
		}
    	return true;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
