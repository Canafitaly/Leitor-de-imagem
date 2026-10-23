package org.scannerperifericos.mvc;

import org.scannerperifericos.mvc.controller.ConteudoController;
import org.scannerperifericos.mvc.models.Cerrebro;
import org.scannerperifericos.mvc.models.CriadorDeConteudo;
import org.scannerperifericos.mvc.models.Scanner;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        //Scanner scaner= new Scanner();
        //scaner.roteiroAcao("C:\\Users\\pedro\\OneDrive\\Área de Trabalho\\WorkSpace\\ScannerPerifericos\\src\\main\\java\\org\\scannerperifericos\\imagens",3);
       // Cerrebro cerrebro=new Cerrebro();
       // cerrebro.carregarDados("C:\\Users\\pedro\\OneDrive\\Área de Trabalho\\WorkSpace\\ScannerPerifericos\\src\\main\\java\\org\\scannerperifericos\\dados.arff");
        //CriadorDeConteudo ato =new CriadorDeConteudo();
       // ato.novaImagem("teste.png");
        ConteudoController conteudoController =new ConteudoController();
        for(int x=0;x<9;x++){
             conteudoController.gerenciar("teste");
        }
    }
}
