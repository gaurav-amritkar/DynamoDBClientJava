package org.aws.dynamodb.item;

import org.aws.dynamodb.DynamoDBApplication;
import org.aws.dynamodb.model.UCLocation;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

public class GetItem {

    public static UCLocation get(DynamoDbEnhancedClient enhancedClient, String tableName, String primaryKey) {
        UCLocation ucLocation;
        try {
            DynamoDbTable<UCLocation> ucLocationTable =
                    enhancedClient.table(tableName, TableSchema.fromBean(UCLocation.class));

            Key key = Key.builder()
                    .partitionValue(primaryKey)
                    .build();

            // Get the item by using the key.
            ucLocation = ucLocationTable.getItem(r->r.key(key));
            System.out.println("******* The source system value is " + ucLocation.getSource_system() + " *******");

            return ucLocation;
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            throw e;
            //System.exit(1);
        }
    }
}
