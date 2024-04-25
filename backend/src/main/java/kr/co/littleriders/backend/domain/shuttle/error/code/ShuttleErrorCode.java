package kr.co.littleriders.backend.domain.shuttle.error.code;

import kr.co.littleriders.backend.global.error.code.LittleRidersErrorCode;
import org.springframework.http.HttpStatus;


public enum ShuttleErrorCode implements LittleRidersErrorCode {


    NOT_FOUND(HttpStatus.NOT_FOUND, "001", "뭘 넣어야 할까요;;;"); //TODO : 진짜 뭐넣지


    ShuttleErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = "SHUTTLE_" + code;
        this.message = message;
    }

    private final HttpStatus status;
    private final String code;
    private final String message;


    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}