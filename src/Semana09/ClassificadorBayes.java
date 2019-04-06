package Semana09;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class ClassificadorBayes{
    public static void main(String[] args) throws Exception {
        
        //DEFINIR ORIGEM DOS DADOS
        DataSource arff = new DataSource("contact-lenses.arff");
        
        //INSTANCIAR CONJUNTO DE DADOS A PARTIR DESSA ORIGEM
        Instances lenses = arff.getDataSet();
        
        //DEFINIR O ÍNDICE DO ATRIBUTO CLASSE NESSE CONJUNTO
        lenses.setClassIndex(4);
        
        //INSTANCIAR CLASSIFICADOR Naive Bayes
        NaiveBayes bayes = new NaiveBayes();
        
        //TREINAR CLASSIFICADOR
        bayes.buildClassifier(lenses);
        
        //EXIBIR O MODELO APRENDIDO
        System.out.println(bayes);
        
        //DEFININDO UM EXEMPLO SEM GRUPO
        Instance desconhecido = new DenseInstance(5);
        
        //DEFININDO COMO SERÃO OS ATRIBUTOS DESSE EXEMPLO
        desconhecido.setDataset(lenses);
        
        //DEFININDO OS VALORES DESSES ATRIBUTOS
        desconhecido.setValue(0, "presbyopic");
        desconhecido.setValue(1, "myope");
        desconhecido.setValue(2, "no");
        desconhecido.setValue(3, "reduced");
        
        //IDENTIFICANDO O GRUPO AO QUAL ESSE EXEMPLO PERTENCE
        double classe = bayes.classifyInstance(desconhecido);
        
        //EXIBINDO O GRUPO IDENTIFICADO
        System.out.println("Grupo: " + classe);
        desconhecido.setClassValue(classe);
        System.out.println("Rótulo: " + desconhecido.stringValue(4));
    }
}
