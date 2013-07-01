package AmazonS3;

/*
 * Copyright 2010-2013 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.UUID;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.dishminer.placesmatching.algorithm.MatchStringUtils;
import com.dishminer.resturants.tasks.IResturantDO;
import com.dishminer.resturantsservice.AddressDO;
import com.dishminer.resturantsservice.RestaurantDO;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 * This sample demonstrates how to make basic requests to Amazon S3 using
 * the AWS SDK for Java.
 * <p>
 * <b>Prerequisites:</b> You must have a valid Amazon Web Services developer
 * account, and be signed up to use Amazon S3. For more information on
 * Amazon S3, see http://aws.amazon.com/s3.
 * <p>
 * <b>Important:</b> Be sure to fill in your AWS access credentials in the
 *                   AwsCredentials.properties file before you try to run this
 *                   sample.
 * http://aws.amazon.com/security-credentials
 * 
 * 
 * 
 */
public class S3Processor {

    
     private List<IResturantDO> restList = new ArrayList<IResturantDO>();
     private static String OBJECT_MAPPER= "object_mapper";
     private final static  String tempBucketName = "resturant.dishminer.temp";
      
     private  AmazonS3 s3 ; 
     private String apiname;
     private static int numberOfObjectPushed = 0;
     private String zipcode;
     ClientConfiguration config = new ClientConfiguration();
     private int numberOfSameEntries;
     private HashMap<String,Integer> numberofSameAPiCalls;

    public HashMap<String, Integer> getNumberofSameAPiCalls() {
        return numberofSameAPiCalls;
    }

    public void setNumberofSameAPiCalls(HashMap<String, Integer> numberofSameAPiCalls) {
        this.numberofSameAPiCalls = numberofSameAPiCalls;
    }
     
     

    public int getNumberOfSameEntries() {
        return numberOfSameEntries;
    }

    public void setNumberOfSameEntries(int numberOfSameEntries) {
        this.numberOfSameEntries = numberOfSameEntries;
    }
     
     
     

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
     
     

    public String getApiname() {
        return apiname;
    }

    public void setApiname(String apiname) {
        this.apiname = apiname;
    }

     
     
     public  AmazonS3 getinstance(){
     if(s3 == null){
         config.setSocketTimeout(2000);
          AWSCredentials awsCredentials = new BasicAWSCredentials("AKIAJRIKBK4PS2JODPJQ", "94k7Okh00O1CpOjvxeuUkJxY7djfZz+pGSUuqxvI");

         s3= new AmazonS3Client(awsCredentials);
     }
     return  s3;
     }
     
    
    public void s3Writer() throws IOException {
        /*
         * This credentials provider implementation loads your AWS credentials
         * from a properties file at the root of your classpath.
         *
         * Important: Be sure to fill in your AWS access credentials in the
         *            AwsCredentials.properties file before you try to run this
         *            sample.
         * http://aws.amazon.com/security-credentials
         */s3 = getinstance();
        	Region usWest2 = Region.getRegion(Regions.US_WEST_2);
		s3.setRegion(usWest2);

       // String key = "MyObjectKey";

        System.out.println("===========================================");
        System.out.println("Getting Started with Amazon S3");
        System.out.println("===========================================\n");

        try {
            /*
             * Create a new S3 bucket - Amazon S3 bucket names are globally unique,
             * so once a bucket name has been taken by any user, you can't create
             * another bucket with that same name.
             *
             * You can optionally specify a location for your bucket if you want to
             * keep your data closer to your applications or users.
             */
            
            /*
             * List the buckets in your account
             */
            System.out.println("Listing buckets");
            boolean bucketExists=false;
            for (Bucket bucket : s3.listBuckets()) {
                System.out.println(" - " + bucket.getName());
                if(bucket.getName().equalsIgnoreCase(tempBucketName))
                    bucketExists = true;
                
            }
            if(!bucketExists){
            System.out.println("Creating bucket " + tempBucketName + "\n");
            s3.createBucket(tempBucketName);
            }

            /*
             * Upload an object to your bucket - You can easily upload a file to
             * S3, or upload directly an InputStream if you know the length of
             * the data in the stream. You can also specify your own metadata
             * when uploading to S3, which allows you set a variety of options
             * like content-type and content-encoding, plus additional metadata
             * specific to your applications.
             */
            //System.out.println("Uploading a new object to S3 from a file\n");
           //  object.getObjectContent();
           int numberofObjPushedPerApi=0;
             System.out.println("Zipcode to Write : " + zipcode );
               
            for(IResturantDO rest : restList){
                 
                 boolean isZipSimilar = false;
                 double zipScore=0;
                String  key =null;
                if(rest != null
                        && rest.getName() != null && rest.getAddress() != null && !rest.getAddress().isEmpty()) {
                    key = rest.getName() +"_"+rest.getLattitude()+ "_"+rest.getLongitude()+"_"+rest.getAddress().get(0).getZipCode() +"_"+rest.getAddress().get(0).getAddressLine()+"#_#"+getApiname();
                }
                
               RestaurantDO storedrest= null;
               if(key != null) {
                    storedrest = readJSon(tempBucketName , key);
                }
                
               if(storedrest != null || key == null ){
                    System.out.println(key +" Object Already Stored. Skipping..");
                    
                    
               continue;
               }
               if(rest.getAddress().get(0).getZipCode() != null) {
                    zipScore=  MatchStringUtils.zipcodeSimilar(zipcode,rest.getAddress().get(0).getZipCode() );
                }
               
               if(zipScore > 50) {
                    System.out.println("ZipCode Matched.. Wrtie object to s3" );
          
                    isZipSimilar = true;
                }
             //   UUID randomUUID= UUID.randomUUID();
                
            //    createSampleFile(key ,randomUUID );
                // s3.putObject(new PutObjectRequest(tempBucketName, OBJECT_MAPPER,  createSampleFile(key )));
            System.out.println("Trying to upload a " + key+"  object to S3 from a file\n");
           if(isZipSimilar){
            s3.putObject(new PutObjectRequest(tempBucketName, key, createSampleFile(rest)));
           
            System.out.println("Uploading a " + key+"  object to S3 from a file\n");
                        System.out.println("Number of objects pushed in S3 " + numberOfObjectPushed);
                        numberOfObjectPushed++;
                        numberofObjPushedPerApi++;
           

           }
           else
           {
               System.out.println(" Zip "+zipcode+" is not same for " + key+"\n");
            
               
           }
           
            
           
            
            }
            System.out.println(" numberofObjPushedPerApi :-  "+numberofObjPushedPerApi);
            
            if(numberofObjPushedPerApi == 0){
                 
                numberOfSameEntries++;
                System.out.println(" numberOfSameEntries :-  "+numberOfSameEntries);
           
                  int value =0;
                if(numberofSameAPiCalls.get(apiname) != null) {
                    value  = numberofSameAPiCalls.get(apiname);
                }
                
                numberofSameAPiCalls.put(apiname, ++value);
            }
            
            
            
              /*System.out.println("Downloading an object");
               S3Object object = s3.getObject(new GetObjectRequest(tempBucketName, OBJECT_MAPPER));
            
            InputStream in = object.getObjectContent();
            byte[] buf = new byte[1024];
            OutputStream out = new FileOutputStream(file);
            int count;
             while( (count = in.read(buf)) != -1)
            {
                if( Thread.interrupted() )
                {
                    try {
                        throw new InterruptedException();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(S3Sample.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
                out.write(buf, 0, count);
                }
            out.close();
            in.close();*/
            
            /*

            //s3.putObject(new PutObjectRequest(tempBucketName, key, createSampleFile()));

            /*
             * Download an object - When you download an object, you get all of
             * the object's metadata and a stream from which to read the contents.
             * It's important to read the contents of the stream as quickly as
             * possibly since the data is streamed directly from Amazon S3 and your
             * network connection will remain open until you read all the data or
             * close the input stream.
             *
             * GetObjectRequest also supports several other options, including
             * conditional downloading of objects based on modification times,
             * ETags, and selectively downloading a range of an object.
             */
           

            /*
             * List objects in your bucket by prefix - There are many options for
             * listing the objects in your bucket.  Keep in mind that buckets with
             * many objects might truncate their results when listing their objects,
             * so be sure to check if the returned object listing is truncated, and
             * use the AmazonS3.listNextBatchOfObjects(...) operation to retrieve
             * additional results.
             */
            
            /*
             * Delete an object - Unless versioning has been turned on for your bucket,
             * there is no way to undelete an object, so use caution when deleting objects.
             */
            //System.out.println("Deleting an object\n");
            //s3.deleteObject(tempBucketName, key);

            /*
             * Delete a bucket - A bucket must be completely empty before it can be
             * deleted, so remember to delete any objects from your buckets before
             * you try to delete them.
             */
          
           // s3.deleteBucket(tempBucketName);
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon S3, but was rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with S3, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
    }

    /**
     * Creates a temporary file with text data to demonstrate uploading a file
     * to Amazon S3
     *
     * @return A newly created temporary file with text data.
     *
     * @throws IOException
     */
    private static File createSampleFile(String key) throws IOException {
        File file = File.createTempFile("Object_mapper", ".txt");
        file.deleteOnExit();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write(key);
        
        writer.close();

        return file;
    }
    private static File createSampleFile(IResturantDO restaurant) throws IOException {
        File file = File.createTempFile("Restaurant", ".json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, restaurant);
        file.deleteOnExit();
        return file;
    }
    /**
     * Displays the contents of the specified input stream as text.
     *
     * @param input
     *            The input stream to display as text.
     *
     * @throws IOException
     */
    private static void displayTextInputStream(InputStream input) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        while (true) {
            String line = reader.readLine();
            if (line == null) break;

            System.out.println("    " + line);
        }
        System.out.println();
    }

    /**
     * @return the googleRestList
     */
    public List<IResturantDO> getGoogleRestList() {
        return restList;
    }

    /**
     * @param googleRestList the googleRestList to set
     */
    public void setGoogleRestList(List<IResturantDO> restList) {
        this.restList = restList;
    }
    
    
    public void writeLog(String log){ 
     AmazonS3 s3 = getinstance();
     DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	   //get current date time with Date()
	   Date date = new Date();
	 //  System.out.println(dateFormat.format(date));
 
	   //get current date time with Calendar()
	   Calendar cal = Calendar.getInstance();
	//   System.out.println(dateFormat.format(cal.getTime()));
         try {
             s3.putObject(new PutObjectRequest("dishminerlogger", log+"_"+dateFormat.format(date)+"_"+dateFormat.format(cal.getTime()), createSampleFile(log)));
         } catch (IOException ex) {
             Logger.getLogger(S3Processor.class.getName()).log(Level.SEVERE, null, ex);
         }
         
    
    
    }
    
    public  String[] getZipcodes(String bucketName , String key){
        
        
         AmazonS3 s3 = getinstance();
         
            S3Object object = null;
              object = s3.getObject(new GetObjectRequest(bucketName, key));
              
            if(object == null ) {
            return null;
        }
      
   
                String [] zipcodes=null;
           // System.out.println("Content-Type: "  + object.getObjectMetadata().getContentType());
           BufferedReader reader = new BufferedReader(new InputStreamReader(object.getObjectContent()));
        while (true) {
            String line = null;
            try {
                line = reader.readLine();
                
            } catch (IOException ex) {
                Logger.getLogger(S3Processor.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (line == null) break;
            line=line.replaceAll("\"", "");
            System.out.println("Zipcodes :- " + line);
            zipcodes = line.split(",");
        }
        
        return zipcodes;
  
    }
    
    
    
    public void deleteObjects(){
     AmazonS3 s3 = getinstance();
                ListObjectsRequest request = new ListObjectsRequest();
            request.setBucketName(tempBucketName);
            
         ObjectListing objectListing =  s3.listObjects(request);
           
            for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
               s3.deleteObject(tempBucketName, objectSummary.getKey());
                   
            }
      
    
    
    }
    
    
    
    
    public  RestaurantDO readJSon(String bucketName , String key){
         
    boolean check = false;
     AmazonS3 s3 = getinstance();
                ListObjectsRequest request = new ListObjectsRequest();
            request.setBucketName(bucketName);
            
         ObjectListing objectListing =  s3.listObjects(request);
           
            for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
               if(objectSummary.getKey().equals(key))
                   check = true;
                   
            }
        if(!check) return null;
      S3Object object = s3.getObject(new GetObjectRequest(bucketName, key));
                 if(object != null ){
            
                ObjectMapper mapper = new ObjectMapper();
                 RestaurantDO actualObj;
            try {
                actualObj = mapper.readValue(object.getObjectContent(),  RestaurantDO.class);
                return actualObj;
                //System.out.println(actualObj.getName());
            } catch (JsonParseException ex) {
                Logger.getLogger(S3Processor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JsonMappingException ex) {
                Logger.getLogger(S3Processor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(S3Processor.class.getName()).log(Level.SEVERE, null, ex);
            } 
                 
            } 
        return null;
        
    }
    
    
    
    private RestaurantDO createRestOBJ(RestaurantDO  storedObj , IResturantDO newObj){
        
        RestaurantDO newRestOBj = new RestaurantDO();
       Map<String,String> apiUrl = storedObj.getAPIURL();
       if(newObj.getAPIURL() !=null){
        if(storedObj.getAPIURL() != null ) {
            storedObj.getAPIURL().putAll(newObj.getAPIURL());
            apiUrl = storedObj.getAPIURL();
        }
        else {
            apiUrl = newObj.getAPIURL();
        }
       }
       
        newRestOBj.setAPIURL(apiUrl);
        
         Map<String,String> apiID = storedObj.getApiID();
         if(newObj.getApiID() != null){
        if(storedObj.getApiID() != null) {
            storedObj.getApiID().putAll(newObj.getApiID());
            apiID = storedObj.getApiID();
        }
        else {
            apiID = newObj.getApiID();
        }
         }
         newRestOBj.setAPIURL(apiID);
         
         String name = storedObj.getName();
         if(name != null)
             newRestOBj.setName(name);
         else
             newRestOBj.setName(newObj.getName());
         String pricing = storedObj.getPricing();
         if(pricing !=null)
             newRestOBj.setPricing(pricing);
         else
             newRestOBj.setPricing(newObj.getPricing());
        String phoneNumber = storedObj.getPhoneNumber();
        if(phoneNumber != null)
            newRestOBj.setPhoneNumber(phoneNumber);
        else
            newRestOBj.setPhoneNumber(newObj.getPhoneNumber());
        String longitude = storedObj.getLongitude();
        
         if(longitude != null)
            newRestOBj.setLongitude(longitude);
        else
            newRestOBj.setLongitude(newObj.getLongitude());
         String lattitude = storedObj.getLattitude();
        
         if(lattitude != null)
            newRestOBj.setLattitude(lattitude);
        else
            newRestOBj.setLattitude(newObj.getLattitude());
         
         String restCusine = storedObj.getRestCusine();
        
         if(restCusine != null)
            newRestOBj.setRestCusine(restCusine);
        else
            newRestOBj.setRestCusine(newObj.getRestCusine());
         
            String restRatings = storedObj.getResturantRating();
      
         if(restRatings != null)
            newRestOBj.setResturantRating(restRatings);
        else
            newRestOBj.setResturantRating(newObj.getRestCusine());
         
            String timeStamp = storedObj.getTimeStamp();
      
         if(timeStamp != null)
            newRestOBj.setTimeStamp(timeStamp);
        else
            newRestOBj.setTimeStamp(newObj.getRestCusine());
         
         List<AddressDO> addList = storedObj.getAddress();
          AddressDO add  = null;
        
         if(addList !=null && !addList.isEmpty() && addList.get(0) != null){
             add = addList.get(0);
              String country = add.getCountry();
      
             if(country == null)
            add.setCountry(newObj.getAddress().get(0).getCountry());
              if(add.getCity() == null)
            add.setCity(newObj.getAddress().get(0).getCity());
            if(add.getAddressLine() == null)
            add.setAddressLine(newObj.getAddress().get(0).getAddressLine());
            if(add.getZipCode() == null)
              add.setCountry(newObj.getAddress().get(0).getZipCode());
            if(add.getState() == null)
            add.setCountry(newObj.getAddress().get(0).getState());
            if(add.getRegion() == null)
            add.setCountry(newObj.getAddress().get(0).getRegion());
          
         }else{
         addList = new ArrayList<AddressDO>();
          add  = new AddressDO();
         
            add.setCountry(newObj.getAddress().get(0).getCountry());
            add.setCity(newObj.getAddress().get(0).getCity());
            add.setAddressLine(newObj.getAddress().get(0).getAddressLine());
            add.setCountry(newObj.getAddress().get(0).getZipCode());
            add.setCountry(newObj.getAddress().get(0).getState());
            add.setCountry(newObj.getAddress().get(0).getRegion());
          
         }
         
         addList.add(add);
         newRestOBj.setAddress(addList);
         
        return newRestOBj;
    
    }
    
    
    
    
    
    
   

}


