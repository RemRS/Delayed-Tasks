package ru.test.testproject.controller.web_service;

import ru.test.testproject.controller.business_logic.TaskService;
import ru.test.testproject.model.Response;
import ru.test.testproject.model.db_entity.DataTable;
import ru.test.testproject.model.type.ResponseCode;
import ru.test.testproject.model.type.Status;
import ru.test.testproject.utils.JsonFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "task", urlPatterns = "/task/*")
public class TaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getPathInfo() != null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        TaskService service = new TaskService();
        Response<DataTable> taskResponse = service.createNewTask();
        if (taskResponse.getResponseCode() != ResponseCode.OK) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        else {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            response.getWriter().print(taskResponse.getResponse().getGuid());
        }
        service.runTask(taskResponse.getResponse());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getPathInfo() == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String[] requestParams = request.getPathInfo().split("/");
        if(requestParams.length != 2) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        
        String guid = requestParams[1];
        try {
            UUID.fromString(guid);
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        
        TaskService service = new TaskService();
        Response<DataTable> statusResponse = service.getTaskByGuid(guid);
                        
        if(statusResponse.getResponseCode() != ResponseCode.OK) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        DataTable dataTable = statusResponse.getResponse();
        if(dataTable.getStatus() == Status.NOT_FOUND) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        else {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(JsonFactory.createTaskResponseJson(dataTable).toString());
        }
    }
}
