package ru.test.testproject.model;

import ru.test.testproject.model.type.ResponseCode;

public class Response<T> {
    private T response;
    private ResponseCode responseCode;

    public Response(T response, ResponseCode responseCode) {
        this.response = response;
        this.responseCode = responseCode;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public String toString() {
        return "Response{" +
                "response=" + response +
                ", responseCode=" + responseCode +
                '}';
    }
}
