package ru.test.testproject.utils;

import org.postgresql.util.PGobject;

import java.sql.SQLException;

public class PgObjectFactory {
    public static PGobject getUuidObject(String value) throws SQLException {
        PGobject pGobject = new PGobject();
        pGobject.setType("uuid");
        pGobject.setValue(value);

        return pGobject;
    }
}
