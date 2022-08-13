package org.aws.dynamodb.item;

import org.aws.dynamodb.DynamoDBApplication;
import org.aws.dynamodb.model.UCLocation;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

public class PutItem {

    public static void put(DynamoDbEnhancedClient enhancedClient, String tableName, UCLocation ucLocation) {
        try {
            DynamoDbTable<UCLocation> ucLocationTable =
                    enhancedClient.table(tableName, TableSchema.fromBean(UCLocation.class));

            // Put the user data into an Amazon DynamoDB table.
            ucLocationTable.putItem(ucLocation);

        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            throw e;
            //System.exit(1);
        }
        System.out.println("UC Location data added to the table." + ucLocation.toString());
    }
}
