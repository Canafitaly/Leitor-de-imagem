package org.scannerperifericos.mvc.dataset;

import java.io.IOException;

public class ConteudoController {
    CriadorDeConteudo criadorDeConteudo= new CriadorDeConteudo();
    public static int numero=0;
    public void gerenciar(String nome,String pasta) throws IOException {
        criadorDeConteudo.novaImagem(texto(nome),pasta);

    }

    private String texto(String nome) {
        String texto = nome + String.format("%04d", this.numero) + ".png";

        numero++;
        return texto;
    }
}
