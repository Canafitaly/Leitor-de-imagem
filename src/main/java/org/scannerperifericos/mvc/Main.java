package org.scannerperifericos.mvc;

import org.scannerperifericos.mvc.dataset.ConteudoController;
import org.scannerperifericos.mvc.models.Cerrebro;
import org.scannerperifericos.mvc.models.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        ConteudoController conteudoController =new ConteudoController();
        for(int x=0;x<1025;x++){
            conteudoController.gerenciar("teste","C:\\Users\\pedro\\OneDrive\\Área de Trabalho\\WorkSpace\\ScannerPerifericos\\src\\main\\java\\org\\scannerperifericos\\imagens");
        }
        Scanner scaner= new Scanner();
        scaner.roteiroAcao("C:\\Users\\pedro\\OneDrive\\Área de Trabalho\\WorkSpace\\ScannerPerifericos\\src\\main\\java\\org\\scannerperifericos\\imagens",3);
        Cerrebro cerrebro=new Cerrebro();
        cerrebro.carregarDados("C:\\Users\\pedro\\OneDrive\\Área de Trabalho\\WorkSpace\\ScannerPerifericos\\src\\main\\java\\org\\scannerperifericos\\dados.arff");
        cerrebro.gerarArvore();
        cerrebro.testarArvore();

    }
}
