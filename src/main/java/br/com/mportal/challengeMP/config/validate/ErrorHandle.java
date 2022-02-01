package br.com.mportal.challengeMP.config.validate;

import java.io.IOException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.mportal.challengeMP.dto.ErrorDto;

import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class ErrorHandle {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullPointerException.class)
    public ErrorDto nullPointerHanler(NullPointerException exception) {
        return new ErrorDto("Arquivo Nulo", "Favor selecionar um arquivo válido antes de fazer o upload.");

    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IOException.class)
    public ErrorDto ioExceptionHandler(IOException exception) {

        return new ErrorDto("Erro ao processar o arquivo.", "Erro ao carregar o arquivo, favor tente novamente com um arquivo da extensão .csv e com os campos válidos.");

    }
}
