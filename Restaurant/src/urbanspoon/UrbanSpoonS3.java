package urbanspoon;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

public class UrbanSpoonS3 {
	
	private static String URBANSPOON_TABLE_BUCKET_NAME = "restaurant.urbanspoon.dishminer.com";
	private AmazonS3 s3;
	private String countryName;
	
	public static void main(String args[]) throws JsonParseException, JsonMappingException, IOException {
		UrbanSpoonS3 urbanspoons3 = new UrbanSpoonS3("us");
		S3Object obj = urbanspoons3.getObject("urbanspoon.dishminer.-1000359214");
		ObjectMapper mapper = new ObjectMapper();
        RestaurantDO restaurnt = mapper.readValue(obj.getObjectContent(), RestaurantDO.class);
        System.out.println(restaurnt.getName());
		//urbanspoons3.createUrbanSpoonBucket();
	}
	
	public UrbanSpoonS3(String countryName) {
		s3 = new AmazonS3Client(new ClasspathPropertiesFileCredentialsProvider());
		Region usWest2 = Region.getRegion(Regions.US_WEST_2);
		s3.setRegion(usWest2);
		this.countryName = countryName;
	}
	
	public void createUrbanSpoonBucket() {
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
	
	public void putUrbanSpoonRestaurantObject(UrbanSpoonRestaurant restaurant) throws IOException {
		String key = generateKey(restaurant);
        s3.putObject(new PutObjectRequest(getBucketName(), key, createSampleFile(restaurant)));
	}
	
	public S3Object getObject(String objectName) {
		String bucketname = "resturant.dishminer";
		objectName = "Turmeric Restaurant_94086_South Murphy Avenue#_#a59abd01-419c-4a7b-ac16-d604dcb37936";
		return s3.getObject(new GetObjectRequest(bucketname, objectName));
	}

	private String generateKey(UrbanSpoonRestaurant restaurant) {
		return "urbanspoon.dishminer."+String.valueOf(restaurant.hashCode());
	}
	
    private static File createSampleFile(UrbanSpoonRestaurant restaurant) throws IOException {
        File file = File.createTempFile("Restaurant", ".json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, restaurant);
        
        file.deleteOnExit();
        return file;
    }
    
    private String getBucketName() {
    	return countryName+"."+URBANSPOON_TABLE_BUCKET_NAME;
    }

}
