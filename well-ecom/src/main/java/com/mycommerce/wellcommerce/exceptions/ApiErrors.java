package com.mycommerce.wellcommerce.exceptions;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiErrors {

    @Getter
    private List<String> erros;

    public ApiErrors(String mensagemErro) {
        super();
        this.erros = Arrays.asList(mensagemErro);

    }

    public ApiErrors(List<String> erros) {
        super();
        this.erros = erros;
    }

}
