package org.scannerperifericos;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Scanner0_1 {
    public static void main(String[] args) throws IOException {

        //var
        double[][] mediasRGB = new double[9][3]; // 9 blocos, 3 canais
        double[][] mediasRGB_Anteriores=null;
        double[] variacao =new double[9];

        //Inicio

        /*A Captura de imagem*/
        BufferedImage img = ImageIO.read(new File("C:\\Users\\pedro\\OneDrive\\Área de Trabalho\\WorkSpace\\ScannerPerifericos\\src\\main\\java\\org\\scannerperifericos\\teste1.png"));
        int largura = img.getWidth();
        int altura = img.getHeight();

        // Divisão em 3x3 blocos
        int blocoLargura = largura / 3;
        int blocoAltura = altura / 3;
        /*fim captura*/


        /*preenche os as mediaRGB */
        int blocoIndex = 0;
        for (int by = 0; by < 3; by++) {//linhas da da matriz
            for (int bx = 0; bx < 3; bx++) {//coluna da matriz
                //inicialização dos valores
                long somaR = 0, somaG = 0, somaB = 0;
                int pixels = 0;

                for (int y = by * blocoAltura; y < (by+1) * blocoAltura; y++) {
                    for (int x = bx * blocoLargura; x < (bx+1) * blocoLargura; x++) {
                        int rgb = img.getRGB(x, y);
                        int r = (rgb >> 16) & 0xFF;
                        int g = (rgb >> 8) & 0xFF;
                        int b = rgb & 0xFF;
                        somaR += r; somaG += g; somaB += b;
                        pixels++;
                    }
                }

                mediasRGB[blocoIndex][0] = somaR / (double) pixels;
                mediasRGB[blocoIndex][1] = somaG / (double) pixels;
                mediasRGB[blocoIndex][2] = somaB / (double) pixels;
                blocoIndex++;
            }
        }
        /*Fim preencher mediaRGB*/


        /*Inicialização do RGB anterior*/
        if(mediasRGB_Anteriores==null){
            mediasRGB_Anteriores=new double[9][3];
            for (int i =0;i < 9; i++){
                for (int j= 0; j <3;j++){
                    mediasRGB_Anteriores[i][j] = mediasRGB[i][j];
                }
            }
        }
        /*fim da inicialização*/


        /*delta de mudança*/
        for (int i = 0; i < 9; i++) {
            double deltaR = mediasRGB[i][0] - mediasRGB_Anteriores[i][0];
            double deltaG = mediasRGB[i][1] - mediasRGB_Anteriores[i][1];
            double deltaB = mediasRGB[i][2] - mediasRGB_Anteriores[i][2];

            variacao[i] = Math.abs(deltaR) + Math.abs(deltaG) + Math.abs(deltaB);
        }
        /*fim do delta*/


        /*gardando os RGB anteriores*/
        // Atualiza anterior para próxima rodada
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                mediasRGB_Anteriores[i][j] = mediasRGB[i][j];
            }
        }
        /*Fim guardar*/
        

        //fim

        /* apuração*/
        for (int i = 0; i < 9; i++) {
            System.out.println("Bloco " + (char)('A' + i) + ": " +
                    "R=" + mediasRGB[i][0] +
                    ", G=" + mediasRGB[i][1] +
                    ", B=" + mediasRGB[i][2]);
        }

        for (int i = 0; i < 9; i++) {
            System.out.println("Variação bloco " + (char)('A' + i) + ": " + variacao[i]);
        }
        /*fim apuração*/


    }



}
