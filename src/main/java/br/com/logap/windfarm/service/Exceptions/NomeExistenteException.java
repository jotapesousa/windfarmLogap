package br.com.logap.windfarm.service.Exceptions;

public class NomeExistenteException extends Exception {

    public NomeExistenteException() {
        super("Nome já existe. Insira um novo!");
    }

    public NomeExistenteException(String msg) {
        super(msg);
    }
}
