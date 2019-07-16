package ru.test.testproject.controller.business_logic;

import ru.test.testproject.model.db_entity.DataTable;

public interface CompletableService {
    void completeTask(DataTable dt);
}
