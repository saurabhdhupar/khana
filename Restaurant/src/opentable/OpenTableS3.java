package opentable;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class OpenTableS3 {
	
	private static String OPEN_TABLE_BUCKET_NAME = "restaurant.opentable.dishminer.com";
	private AmazonS3 s3;
	private String countryName;
	
	public static void main(String args[]) {
		OpenTableS3 opentables3 = new OpenTableS3("");
		opentables3.createOpenTableBucket();
	}
	
	public OpenTableS3(String countryName) {
		s3 = new AmazonS3Client(new ClasspathPropertiesFileCredentialsProvider());
		Region usWest2 = Region.getRegion(Regions.US_WEST_2);
		s3.setRegion(usWest2);
		this.countryName = countryName;
	}
	
	public void createOpenTableBucket() {
		/*
        * Create a new S3 bucket - Amazon S3 bucket names are globally unique,
        * so once a bucket name has been taken by any user, you can't create
        * another bucket with that same name.
        *
        * You can optionally specify a location for your bucket if you want to
        * keep your data closer to your applications or users.
        */
        s3.createBucket(getBucketName());
	}
	
	public void putOpenTableRestaurantObject(OpenTableRestaurant restaurant) throws IOException {
		String key = generateKey(restaurant);
        s3.putObject(new PutObjectRequest(getBucketName(), key, createSampleFile(restaurant)));
	}

	private String generateKey(OpenTableRestaurant restaurant) {
		return "opentable.dishminer."+String.valueOf(restaurant.hashCode());
	}
	
    private static File createSampleFile(OpenTableRestaurant restaurant) throws IOException {
        File file = File.createTempFile("Restaurant", ".json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, restaurant);
        file.deleteOnExit();
        return file;
    }
    
    private String getBucketName() {
    	return countryName+"."+OPEN_TABLE_BUCKET_NAME;
    }
}
