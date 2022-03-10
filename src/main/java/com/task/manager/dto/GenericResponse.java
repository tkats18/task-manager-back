package com.task.manager.dto;

public class GenericResponse {

    private Object data;

    public GenericResponse(Object data) {
        this.data = data;
    }

    public GenericResponse() {
    }

    public static GenericResponse noReturnValue(){
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.data = null;
        return genericResponse;
    }

    public static GenericResponse successObject(Object result){
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.data = result;
        return genericResponse;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
