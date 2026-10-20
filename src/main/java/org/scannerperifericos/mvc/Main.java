package org.scannerperifericos.mvc;

import org.scannerperifericos.mvc.models.Scanner;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scaner= new Scanner();
        scaner.roteiroAcao("C:\\Users\\pedro\\OneDrive\\Área de Trabalho\\WorkSpace\\ScannerPerifericos\\src\\main\\java\\org\\scannerperifericos\\imagens",3);
    }
}
