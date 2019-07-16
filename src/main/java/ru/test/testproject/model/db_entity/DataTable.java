package ru.test.testproject.model.db_entity;

import ru.test.testproject.model.type.Status;

import java.util.Calendar;
import java.util.UUID;
import java.sql.Timestamp;

public class DataTable {
    private UUID guid;
    private Timestamp timestamp;
    private Status status;

    public DataTable() {
        long timeMillis = Calendar.getInstance().getTimeInMillis();
        this.guid = UUID.randomUUID();
        this.timestamp = new Timestamp(timeMillis);
        this.status = Status.CREATED;
    }

    public DataTable(String guid, Timestamp timestamp, Status status) {
        this.guid = UUID.fromString(guid);
        this.timestamp = timestamp;
        this.status = status;
    }

    public DataTable(Status status) {
        this.status = status;
    }

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(UUID uuid) {
        this.guid = uuid;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DataTable{" +
                "guid=" + guid +
                ", timestamp=" + timestamp +
                ", status=" + status +
                '}';
    }
}
