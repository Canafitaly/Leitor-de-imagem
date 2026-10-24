package org.scannerperifericos.mvc.models;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Debug;
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
    public void gerarArvore() throws Exception {

        J48 arvore = new J48();

        arvore.buildClassifier(dados);

        System.out.println(arvore);
    }

    public void testarArvore() throws Exception {

        J48 arvore = new J48();

        Evaluation avaliacao =
                new Evaluation(dados);

        avaliacao.crossValidateModel(
                arvore,
                dados,
                10,
                new Debug.Random(1)
        );

        System.out.println(
                avaliacao.toSummaryString()
        );

    }

    public Instances getDados() {
        return dados;
    }
}

