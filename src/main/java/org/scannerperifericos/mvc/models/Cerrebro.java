package org.scannerperifericos.mvc.models;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Cerrebro {
    private Instances dados;

    public void carregarDados(String caminhoArff) throws Exception {

        DataSource source = new DataSource(caminhoArff);

        dados = source.getDataSet();

// última coluna como classe
        if (dados.classIndex() == -1) {
            dados.setClassIndex(dados.numAttributes() - 1);
        }

        System.out.println("Dados carregados.");
        System.out.println("Instâncias: " + dados.numInstances());
        System.out.println("Atributos: " + dados.numAttributes());
        System.out.println("Classe: " + dados.classAttribute().name());
    }

    public Instances getDados() {
        return dados;
    }
}

