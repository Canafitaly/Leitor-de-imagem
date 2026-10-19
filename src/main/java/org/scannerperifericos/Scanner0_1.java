package org.scannerperifericos;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Scanner0_1 {
    public static void main(String[] args) throws IOException {

        //var
        double[][] mediasRGB = new double[9][3]; // 9 blocos, 3 canais
        double[][] mediasRGB_Anteriores=null;
        double[] variacao =new double[9];

        //Inicio

        /*cabeçalho do documento*/
        try (FileWriter fw = new FileWriter("C:\\Users\\pedro\\OneDrive\\Área de Trabalho\\WorkSpace\\ScannerPerifericos\\src\\main\\java\\org\\scannerperifericos\\dados.arff")) {
            fw.write("@relation Scanner0.1\n\n");
            fw.write("@attribute largura numeric\n");
            fw.write("@attribute altura numeric\n");
            for (char bloco = 'A'; bloco <= 'I'; bloco++) {
                fw.write("@attribute bloco" + bloco + "_R numeric\n");
                fw.write("@attribute bloco" + bloco + "_G numeric\n");
                fw.write("@attribute bloco" + bloco + "_B numeric\n");
            }
            for (char bloco = 'A'; bloco <= 'I'; bloco++) {
                fw.write("@attribute variacao" + bloco + " numeric\n");
            }
            fw.write("@attribute objeto {A,B,C,D,E,F,G,H,I}\n");
            fw.write("@attribute origem {A,B,C,D,E,F,G,H,I}\n");
            fw.write("@attribute destino {A,B,C,D,E,F,G,H,I}\n\n");
            fw.write("@data\n");
        }

        /*fim cabeçalho*/

        /*A Captura de imagem*/
        File pasta = new File("C:\\Users\\pedro\\OneDrive\\Área de Trabalho\\WorkSpace\\ScannerPerifericos\\src\\main\\java\\org\\scannerperifericos\\imagens");
        File[] arquivos = pasta.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));

        for (File arquivo : arquivos) {
            BufferedImage img = ImageIO.read(arquivo);
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

                    for (int y = by * blocoAltura; y < (by + 1) * blocoAltura; y++) {
                        for (int x = bx * blocoLargura; x < (bx + 1) * blocoLargura; x++) {
                            int rgb = img.getRGB(x, y);
                            int r = (rgb >> 16) & 0xFF;
                            int g = (rgb >> 8) & 0xFF;
                            int b = rgb & 0xFF;
                            somaR += r;
                            somaG += g;
                            somaB += b;
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
            if (mediasRGB_Anteriores == null) {
                mediasRGB_Anteriores = new double[9][3];
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 3; j++) {
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

            /*registrando no ARFF*/
            StringBuilder linha = new StringBuilder();
            linha.append(largura).append(",").append(altura);

            for (int i = 0; i < 9; i++) {
                linha.append(",").append(mediasRGB[i][0]); // R
                linha.append(",").append(mediasRGB[i][1]); // G
                linha.append(",").append(mediasRGB[i][2]); // B
            }

            for (int i = 0; i < 9; i++) {
                linha.append(",").append(variacao[i]);
            }

            // valores nominais fictícios (pode ajustar depois)
            linha.append(",A,B,C");

            try (FileWriter fw = new FileWriter("C:\\Users\\pedro\\OneDrive\\Área de Trabalho\\WorkSpace\\ScannerPerifericos\\src\\main\\java\\org\\scannerperifericos\\dados.arff", true)) {
                fw.write(linha.toString() + "\n");
            }
            /*Fim registração*/

        }
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
