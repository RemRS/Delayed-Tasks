package ru.test.testproject.model.timer_task;

import ru.test.testproject.controller.business_logic.CompletableService;
import ru.test.testproject.model.db_entity.DataTable;

import java.util.TimerTask;

public class CompletableTimerTask extends TimerTask {
    private DataTable dataTable;
    private CompletableService completableService;

    public CompletableTimerTask(DataTable dataTable, CompletableService completableService) {
        this.dataTable = dataTable;
        this.completableService = completableService;
    }

    @Override
    public void run() {
        completableService.completeTask(this.dataTable);
    }

    public DataTable getDataTable() {
        return dataTable;
    }
}
