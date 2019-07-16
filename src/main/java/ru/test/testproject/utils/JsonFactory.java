package ru.test.testproject.utils;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import ru.test.testproject.model.db_entity.DataTable;

public class JsonFactory {
    private static final Logger LOG = Logger.getLogger(JsonFactory.class.getName());
    public static JSONObject createTaskResponseJson(DataTable dataTable) {
        JSONObject result = new JSONObject();
        
        try {
            result.put("timestamp", DateUtil.convertTo8601(dataTable.getTimestamp()));
        } catch (ParseException parseException) {
            LOG.log(Level.SEVERE, "Date parse error. Putting as is");
            result.put("timestamp", dataTable.getTimestamp());
        }
        
        result.put("status", dataTable.getStatus().toString());

        return result;
    }
}
