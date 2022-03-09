package com.task.manager.dto;

public class GenericResponse {

    private Object data;

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

}
