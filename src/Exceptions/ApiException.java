package Exceptions;

import Models.modelApi;

public class ApiException extends Throwable {
    private static String mensagem;

    public ApiException(modelApi apiModulada) {
        switch (apiModulada.error_type()) {
            case "unsupported-code" :
                mensagem = "O código de pelo menos uma das moedas escolhidas, está errado";
                break;
            case "quota-reached" :
                mensagem = "A minha chave de acesso à API ExchangeRate, atingiu o limite mensal";
                break;
            case "malformed-request" :
                mensagem = "O endereço para requerir a API ExchangeRate é incorreto";
                break;
            default:
                mensagem = apiModulada.error_type();
                break;
        }
    }

    @Override
    public String getMessage() {
        return mensagem;
    }
}
