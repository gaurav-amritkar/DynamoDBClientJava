package org.aws.dynamodb;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.aws.dynamodb.item.GetItem;
import org.aws.dynamodb.item.PutItem;
import org.aws.dynamodb.model.UCLocation;
import org.aws.dynamodb.table.CreateTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class DynamoDBApplication {

    private static final String tableName = "uc-locations";
    private static final String primaryKey = "location_alias";

    private static DynamoDbClient dynamoDbClient = DynamoDBApplication.dynamoDbClient();
    private static DynamoDbEnhancedClient enhancedClient = DynamoDBApplication.enhancedClient();

    public static void main(String[] args) {
        System.out.println("Dynamo Db Application Started");
        // Create table
        /*CreateTable.create(dynamoDbClient, tableName, primaryKey);*/

        // Read CSV File and convert it to Java Objects List
        String fileName = "D:\\workspace\\DynamoDBClient\\src\\main\\resources\\uc-locations.csv";

        List<UCLocation> beans = null;
        try {
            beans = new CsvToBeanBuilder(new FileReader(fileName))
                    .withType(UCLocation.class)
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //beans.forEach(System.out::println);
        beans.forEach(ucLocation -> {
            PutItem.put(enhancedClient, tableName, ucLocation);
        });

        // Put Data into table
        /*UCLocation ucLocation = new UCLocation();
        ucLocation.setLocation_alias("1");
        ucLocation.setSource_system("soarian");
        ucLocation.setSoarian_location_id("1111");
        ucLocation.setSoarian_waiting_room_id("1111");
        ucLocation.setMillenium_location_id("1111");
        ucLocation.setMillenium_waiting_room_id("1111");

        PutItem.put(enhancedClient, tableName, ucLocation);*/

        // Get Data from table
        UCLocation ucLocation2 = GetItem.get(enhancedClient, tableName, "1");
        System.out.println("Received Item : " + ucLocation2.toString());

        dynamoDbClient.close();
        System.out.println("Dynamo Db Application Ended");
    }

    public static DynamoDbClient dynamoDbClient() {
        return DynamoDbClient.builder().build();
    }

    public static DynamoDbEnhancedClient enhancedClient() {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient())
                .build();
    }
}
