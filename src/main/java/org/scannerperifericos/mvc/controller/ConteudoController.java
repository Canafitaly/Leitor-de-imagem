package org.scannerperifericos.mvc.controller;

import org.scannerperifericos.mvc.models.CriadorDeConteudo;

import java.io.IOException;

public class ConteudoController {
    CriadorDeConteudo criadorDeConteudo= new CriadorDeConteudo();
    static int numero=0;
    public void gerenciar(String nome) throws IOException {
        criadorDeConteudo.novaImagem(texto(nome));

    }

    private String texto(String nome) {
        String texto =nome+this.numero+".png";
        numero=numero+1;
        return texto;


    }
}
