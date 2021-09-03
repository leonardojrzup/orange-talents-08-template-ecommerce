package br.com.leonardo.mercadolivre.excecao;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class TratamentoDeExcecao extends ResponseEntityExceptionHandler {

    //Rescrevo o parametro o método retornando adicionalmente retornando uma lista com os erros indicando uma mensagem para o usuario e uma mensagem para o desenvolvedor
    //Ajustar conforme necessidade, pois trata somente erros de argumentos de metodos, exemplo, os parametros do metodo POST de salvar algum usuario

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Erro> erros = gerarListDeErros(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);//Altera o handleExceptionInternal, pois o corpo pode ser nulo (Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleContraintViolation(ConstraintViolationException ex, WebRequest request) {
        String msgUsuario = ex.getMessage();
        String msgDesenvolvedor = ex.getMessage();
        List<Erro> erros = Arrays.asList(new Erro(msgUsuario, msgDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    //Tratar IllegalStateException
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Object> handleRegraNegocioException(IllegalStateException ex, WebRequest request) {
        String msgUsuario = ex.getMessage();
        String msgDesenvolvedor = ex.getMessage();
        List<Erro> erros = Arrays.asList(new Erro(msgUsuario, msgDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }



    //Tratar IllegalArgumentException
    public ResponseEntity<Object> handleRegraNegocioException(IllegalArgumentException ex, WebRequest request) {
        String msgUsuario = ex.getMessage();
        String msgDesenvolvedor = ex.getMessage();
        List<Erro> erros = Arrays.asList(new Erro(msgUsuario, msgDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


    //Pega uma lista dos erros que estão sendo apresentados
    private List<Erro> gerarListDeErros(BindingResult bindingResult) { // BindingResult é Basicamente, uma interface que determina como o objeto que armazena o resultado da validação deve armazenar e recuperar o resultado da validação (erros, tentativa de vinculação a campos não permitidos, etc.)
        List<Erro> erros = new ArrayList<Erro>();//Lista do meu objeto erro, que possui uma mensagem para o DEV e outra para o Usuario
        bindingResult.getFieldErrors().forEach(fieldError -> {
            String msgUsuario = tratarMensagemDeErroParaUsuario(fieldError);//Percorre o BindingResult verificando os erros e os insere em uma lista
            String msgDesenvolvedor = fieldError.toString();
            erros.add(new Erro(msgUsuario, msgDesenvolvedor));
        });
        return erros;
    }


    private String tratarMensagemDeErroParaUsuario(FieldError fieldError) { //Basicamente pega a mensagem de erro que foi especificada na anotação e adiciona uma mensagem mais amigavél, deve ser alteradada caso a mensagem não seja inserida na anotação
        if (fieldError.getCode().equals("NotBlank")) {

            return fieldError.getDefaultMessage().concat(" é obrigatorio");
        }
        if (fieldError.getCode().equals("NotNull")) {

            return fieldError.getDefaultMessage().concat(" é obrigatorio");
        }

        if (fieldError.getCode().equals("Email")) {

            return fieldError.getDefaultMessage().concat(" deve conter um formato de email válido");
        }
        if (fieldError.getCode().equals("Unique")) {

            return fieldError.getDefaultMessage();
        }
        if (fieldError.getCode().equals("Future")) {
            return fieldError.getDefaultMessage();
        }
        if (fieldError.getCode().equals("min")) {
            return fieldError.getDefaultMessage().concat(String.format(" deve ser maior ou igual a %S",
                    fieldError.getArguments()[1], fieldError.getArguments()[1]));
        }

        if (fieldError.getCode().equals("Length")) {
            return fieldError.getDefaultMessage().concat(String.format(" deve ter no minimo %s caracteres ",
                    fieldError.getArguments()[2]));
        }
        if (fieldError.getCode().equals("Documento")) {
            return fieldError.getDefaultMessage();
        }

        //System.out.println(fieldError.getCode());//fins de debug para confirmar o codigo dos erros que estão sendo retornados
        return fieldError.toString();
    }
}

//confirmar usabilidade do método

