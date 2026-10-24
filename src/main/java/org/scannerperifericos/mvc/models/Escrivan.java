package org.scannerperifericos.mvc.models;

import java.io.FileWriter;
import java.io.IOException;


import static org.scannerperifericos.mvc.dataset.CriadorDeConteudo.tabelaDePossições;

public class Escrivan {
    String arquivo_arff="C:\\Users\\pedro\\OneDrive\\Área de Trabalho\\WorkSpace\\ScannerPerifericos\\src\\main\\java\\org\\scannerperifericos\\dados.arff";
    private static int contagem=0;
    public String getArquivo_arff() {
        return arquivo_arff;
    }

    public void setArquivo_arff(String arquivo_arff) {
        this.arquivo_arff = arquivo_arff;
    }

    public void cabesalho(){
        String pasta;
        try (FileWriter fw = new FileWriter(this.arquivo_arff)) {
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
            fw.write("@attribute origem {A,B,C,D,E,F,G,H,I}\n\n");
            fw.write("@data\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void novoRegistro(int largura, double[][] mediasRGB, double[] variacao, int altura){
        StringBuilder linha = new StringBuilder();
        linha.append(largura).append(",").append(altura);

        for (int i = 0; i < mediasRGB.length; i++) {
            linha.append(",").append(mediasRGB[i][0]); // R
            linha.append(",").append(mediasRGB[i][1]); // G
            linha.append(",").append(mediasRGB[i][2]); // B
        }

        for (int i = 0; i < mediasRGB.length; i++) {
            linha.append(",").append(variacao[i]);
        }

        // valores nominais fictícios (pode ajustar depois)

            linha.append(",").append(tabelaDePossições[this.contagem]);
            if (contagem==0){
                linha.append(",").append("?");
            }else{
                linha.append(",").append(tabelaDePossições[this.contagem-1]);
            }
            this.contagem++;

        try (FileWriter fw = new FileWriter(arquivo_arff, true)) {
            fw.write(linha.toString() + "\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
