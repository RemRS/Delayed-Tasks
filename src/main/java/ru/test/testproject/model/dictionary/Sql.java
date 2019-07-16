package ru.test.testproject.model.dictionary;

public class Sql {
    public static class DataTable {
        public static final String INSERT = "INSERT INTO datatable (guid, timestamp, status) VALUES (?, ?, ?)";
        public static final String UPDATE_TIMESTAMP_AND_STATUS = "UPDATE datatable SET timestamp = ?, status = ? WHERE GUID = ?";
        public static final String SELECT_BY_GUID = "SELECT timestamp, status FROM datatable WHERE GUID = ?";
    }
}
