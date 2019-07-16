
package ru.test.testproject.datasource;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataSource {
    private static final Logger LOG = Logger.getLogger(DataSource.class.getName());
    private static final BasicDataSource ds = new BasicDataSource();

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(new File("../config/project.properties")));
            System.out.println(properties);
        }
        catch (IOException e) {
            LOG.log(Level.SEVERE, "Properties loading error. Application can not be started\n" + e.toString());
            System.exit(-1);
        }
        ds.setUrl(properties.getProperty("url"));
        ds.setUsername(properties.getProperty("user"));
        ds.setPassword(properties.getProperty("password"));
        ds.setMinIdle(5);
        ds.setMaxIdle(100);
        ds.setMaxOpenPreparedStatements(100);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}
