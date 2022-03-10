package com.game.cricket.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter @NoArgsConstructor
public class ResponseDTO {

    private String result;
    private String message;
    private Object data;

    public ResponseDTO(String result, String message, Object data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }
}
