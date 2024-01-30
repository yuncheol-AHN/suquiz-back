package com.example.entity.global.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class CommonResponse<T> {
    @Builder.Default // Builder default 지정
    private String id = UUID.randomUUID().toString(); // uuid
    @Builder.Default
    private Date dateTime = new Date(); // date
    private int status;
    private String message;
    private Object data;


//    public CommonResponse(ResponseType responseType, T data) {
//        this.code = responseType.getCode();
//        this.message = responseType.getMessage();
//        this.data = data;
//    }

//    public static CommonResponse success(){
//        return Response.builder()
//                .responseType(SUCCESS)
//                .build();
//    }

//    public static  <T> CommonResponse<T> success(T data){
//        return new CommonResponse<>(SUCCESS,data);
//    }
//
//    public static CommonResponse failure() {
//        return CommonResponse.builder()
//                .responseType(FAILURE)
//                .build();
//    }

}