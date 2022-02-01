package br.com.mportal.challengeMP.dto;

public class ErrorDto {

    private String header;
    private String message;

    public ErrorDto(){}
    
    public ErrorDto(String header, String message){
        this.header = header;
        this.message = message;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHeader() {
        return header;
    }

    public String getMessage() {
        return message;
    }
}
