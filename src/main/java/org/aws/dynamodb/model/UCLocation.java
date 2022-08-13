package org.aws.dynamodb.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Data
@DynamoDbBean
public class UCLocation {

    @CsvBindByName(column = "location_alias")
    private String location_alias;

    @CsvBindByName(column = "source_system")
    private String source_system;

    @CsvBindByName(column = "soarian_location_id")
    private String soarian_location_id;

    @CsvBindByName(column = "soarian_waiting_room_id")
    private String soarian_waiting_room_id;

    @CsvBindByName(column = "millenium_location_id")
    private String millenium_location_id;

    @CsvBindByName(column = "millenium_waiting_room_id")
    private String millenium_waiting_room_id;

    @DynamoDbPartitionKey
    public String getLocation_alias() {
        return location_alias;
    }

    public void setLocation_alias(String location_alias) {
        this.location_alias = location_alias;
    }

    public String getSource_system() {
        return source_system;
    }

    public void setSource_system(String source_system) {
        this.source_system = source_system;
    }

    public String getSoarian_location_id() {
        return soarian_location_id;
    }

    public void setSoarian_location_id(String soarian_location_id) {
        this.soarian_location_id = soarian_location_id;
    }

    public String getSoarian_waiting_room_id() {
        return soarian_waiting_room_id;
    }

    public void setSoarian_waiting_room_id(String soarian_waiting_room_id) {
        this.soarian_waiting_room_id = soarian_waiting_room_id;
    }

    public String getMillenium_location_id() {
        return millenium_location_id;
    }

    public void setMillenium_location_id(String millenium_location_id) {
        this.millenium_location_id = millenium_location_id;
    }

    public String getMillenium_waiting_room_id() {
        return millenium_waiting_room_id;
    }

    public void setMillenium_waiting_room_id(String millenium_waiting_room_id) {
        this.millenium_waiting_room_id = millenium_waiting_room_id;
    }
}
