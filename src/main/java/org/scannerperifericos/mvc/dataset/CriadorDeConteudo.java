package org.scannerperifericos.mvc.dataset;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class CriadorDeConteudo {

    private final Random random = new Random();

    private char posicaoAtual;
    public static char[] tabelaDePossições=new char[2000];
    private static int contagem=0;
    // Sorteia de A até I
    public void posicaoAleatoria() {
        posicaoAtual = (char) ('A' + random.nextInt(9));
    }

    // Converte A-I em coordenadas da matriz 3x3
    public int[] interpretarPosicao(char posicao) {

        switch (posicao) {
            case 'A': return new int[]{0,0};
            case 'B': return new int[]{1,0};
            case 'C': return new int[]{2,0};

            case 'D': return new int[]{0,1};
            case 'E': return new int[]{1,1};
            case 'F': return new int[]{2,1};

            case 'G': return new int[]{0,2};
            case 'H': return new int[]{1,2};
            case 'I': return new int[]{2,2};

            default:
                throw new IllegalArgumentException("Posição inválida");
        }
    }

    public void novaImagem(String nomeArquivo, String pasta) throws IOException {

        BufferedImage img =
                new BufferedImage(
                        3,
                        3,
                        BufferedImage.TYPE_INT_RGB);

        // fundo preto
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                img.setRGB(x, y, 0x000000);
            }
        }

        posicaoAleatoria();

        int[] coordenadas =
                interpretarPosicao(posicaoAtual);

        int x = coordenadas[0];
        int y = coordenadas[1];

        // objeto vermelho
        img.setRGB(x, y, 0xFF0000);

        ImageIO.write(
                img,
                "png",
                new File(pasta,nomeArquivo)
        );

        tabelaDePossições[contagem] = posicaoAtual;
        System.out.println(
                "Origem: " +tabelaDePossições[contagem] +
                        " | Destino: " + posicaoAtual +
                        " | Numero: "+ contagem

        );

        contagem++;
    }
}
