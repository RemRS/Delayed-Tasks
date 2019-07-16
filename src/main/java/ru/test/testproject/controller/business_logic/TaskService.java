package ru.test.testproject.controller.business_logic;

import ru.test.testproject.controller.job_executor.JobExecutor;
import ru.test.testproject.dao.DataTableDao;
import ru.test.testproject.model.Response;
import ru.test.testproject.model.db_entity.DataTable;
import ru.test.testproject.model.timer_task.CompletableTimerTask;
import ru.test.testproject.model.type.ResponseCode;
import ru.test.testproject.model.type.Status;

import java.sql.Timestamp;
import java.util.Calendar;

public class TaskService implements CompletableService {

    public Response<DataTable> createNewTask() {
        DataTable dt = new DataTable();
        DataTableDao dao = new DataTableDao();
        boolean isInserted = dao.insert(dt);

        return isInserted ? new Response<>(dt, ResponseCode.OK) : new Response<>(dt, ResponseCode.ERROR);
    }

    public void runTask(DataTable dataTable) {
        dataTable.setStatus(Status.RUNNING);
        dataTable.setTimestamp(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        DataTableDao dao = new DataTableDao();

        boolean isUpdated = dao.updateTimeStampAndStatus(dataTable);

        if(isUpdated) {
            JobExecutor.submitTask(new CompletableTimerTask(dataTable, this));
        }
    }

    public Response<DataTable> getTaskByGuid(String guid) {
        if(JobExecutor.containsTask(guid)) {
            DataTable dataTable = JobExecutor.getTaskByGuid(guid);
            return new Response<>(dataTable, ResponseCode.OK);
        }

        DataTableDao dao = new DataTableDao();
        DataTable dataTable = dao.getTaskByGuid(guid);

        return dataTable == null ?
                new Response<DataTable>(null, ResponseCode.ERROR) :
                new Response<>(dataTable, ResponseCode.OK);

    }

    @Override
    public void completeTask(DataTable dataTable) {
        DataTableDao dao = new DataTableDao();
        dataTable.setStatus(Status.FINISHED);
        dataTable.setTimestamp(new Timestamp(Calendar.getInstance().getTimeInMillis()));

        boolean isUpdated = dao.updateTimeStampAndStatus(dataTable);

        if(isUpdated) {
            JobExecutor.removeTask(dataTable.getGuid().toString());
        }
    }
}
