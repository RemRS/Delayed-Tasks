package ru.test.testproject.controller.job_executor;

import ru.test.testproject.model.timer_task.CompletableTimerTask;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import ru.test.testproject.model.db_entity.DataTable;

public class JobExecutor {
    private static final Map<String, CompletableTimerTask> runningTasks = new ConcurrentHashMap<>();
    private static final int DELAY_MILLIS = 2 * 60 * 1000;

    public static void submitTask(CompletableTimerTask timerTask) {
        Timer timer = new Timer(true);
        timer.schedule(timerTask, DELAY_MILLIS);
        runningTasks.put(timerTask.getDataTable().getGuid().toString(), timerTask);
    }

    public static void removeTask(String guid) {
        runningTasks.remove(guid);
    }

    public static boolean containsTask(String guid) {
        return runningTasks.containsKey(guid);
    }
    
    public static DataTable getTaskByGuid(String guid) {
        return runningTasks.get(guid).getDataTable();
    }
}
