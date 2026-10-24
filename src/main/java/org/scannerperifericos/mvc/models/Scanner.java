package org.scannerperifericos.mvc.models;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Scanner {
    //var
    private double[][] mediasRGB = new double[9][3]; // 9 blocos, 3 canais
    private double[][]  mediasRGB_Anteriores = null;
    private double[] variacao =new double[9];
    private int largura;
    private int altura;
    private int blocoLargura;
    private int blocoAltura;
    private boolean primeira_execulsao=true;
    private int matriz;
    private Escrivan escrivan= new Escrivan();

    public void roteiroAcao(String caminho_pasta, int matriz) throws IOException {
        File[] imagens= capiturarPastaImagem(caminho_pasta);
        escrivan.cabesalho();

        for (File imagem :imagens){
            System.out.println(imagem.getName());
            preenchendoMediasRGB(definicaoDeMatriz(imagem,matriz));

            if(this.primeira_execulsao){
                anterioresVazios();
                this.primeira_execulsao=false;
            }
            mudansaDelta();
            guardarMemoria();
            escrivan.novoRegistro(this.largura,this.mediasRGB ,this.variacao ,this.altura);
        }
    }
    public File[] capiturarPastaImagem(String caminho_pasta){
            File pasta =new File(caminho_pasta);
            File[] arquivos = pasta.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
            return arquivos;
    }
    public BufferedImage definicaoDeMatriz(File imagem, int matriz) throws IOException {
        this.matriz=matriz;
        BufferedImage img = ImageIO.read(imagem);
        this.largura = img.getWidth();
        this.altura = img.getHeight();
        this.blocoLargura = largura / this.matriz;
        this.blocoAltura = altura / this.matriz;

        matriz=this.matriz*this.matriz;
        this.mediasRGB = new double[matriz][3];

        this.variacao= new double[matriz];

        return img;
    }
    public void preenchendoMediasRGB(BufferedImage img){
        int blocoIndex = 0;
        for (int by = 0; by < this.matriz; by++) {//linhas da da matriz
            for (int bx = 0; bx < this.matriz; bx++) {//coluna da matriz
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
    }

    public void anterioresVazios(){
        if(mediasRGB_Anteriores==null) {
            this.mediasRGB_Anteriores = new double[this.matriz * this.matriz][3];
            for (int i = 0; i < mediasRGB.length; i++) {
                for (int j = 0; j < mediasRGB[0].length; j++) {
                    mediasRGB_Anteriores[i][j] = mediasRGB[i][j];
                }
            }
            System.out.println("Passamos aqui já");
        }
    }

    public void mudansaDelta(){
        for (int i = 0; i < variacao.length; i++) {
            double deltaR = mediasRGB[i][0] - mediasRGB_Anteriores[i][0];
            double deltaG = mediasRGB[i][1] - mediasRGB_Anteriores[i][1];
            double deltaB = mediasRGB[i][2] - mediasRGB_Anteriores[i][2];
            //variacao[i] = Math.abs(deltaR) + Math.abs(deltaG) + Math.abs(deltaB);
            variacao[i]=deltaR + deltaG + deltaB;
        }
    }

    public void guardarMemoria(){
        for (int i = 0; i < mediasRGB.length; i++) {
            for (int j = 0; j < mediasRGB[0].length; j++) {
                mediasRGB_Anteriores[i][j] = mediasRGB[i][j];
            }
        }
    }


    public double[][] getMediasRGB() {
        return mediasRGB;
    }

    public void setMediasRGB(double[][] mediasRGB) {
        this.mediasRGB = mediasRGB;
    }

    public double[][] getMediasRGB_Anteriores() {
        return mediasRGB_Anteriores;
    }

    public void setMediasRGB_Anteriores(double[][] mediasRGB_Anteriores) {
        this.mediasRGB_Anteriores = mediasRGB_Anteriores;
    }

    public double[] getVariacao() {
        return variacao;
    }

    public void setVariacao(double[] variacao) {
        this.variacao = variacao;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getBlocoLargura() {
        return blocoLargura;
    }

    public void setBlocoLargura(int blocoLargura) {
        this.blocoLargura = blocoLargura;
    }

    public int getBlocoAltura() {
        return blocoAltura;
    }

    public void setBlocoAltura(int blocoAltura) {
        this.blocoAltura = blocoAltura;
    }

    public boolean isPrimeira_execulsao() {
        return primeira_execulsao;
    }

    public void setPrimeira_execulsao(boolean primeira_execulsao) {
        this.primeira_execulsao = primeira_execulsao;
    }

    public int getMatriz() {
        return matriz;
    }

    public void setMatriz(int matriz) {
        this.matriz = matriz;
    }

    public Escrivan getEscrivan() {
        return escrivan;
    }

    public void setEscrivan(Escrivan escrivan) {
        this.escrivan = escrivan;
    }
}
