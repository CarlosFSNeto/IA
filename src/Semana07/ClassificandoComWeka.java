package Semana07;

import weka.classifiers.lazy.IBk;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class ClassificandoComWeka {
  public static void main(String[] args) throws Exception {
    
    //DEFINIR FONTE DE DADOS
    DataSource arff = new DataSource("iris.arff");
    
    //INSTANCIAR UM CONJUNTO A PARTIR DA FONTE DEFINIDA
    Instances iris = arff.getDataSet();
    
    //DEFINIR O ÍNDICE DO ATRIBUTO CLASSE
    iris.setClassIndex(4);
    
    //DEFINIR EXEMPLO A SER CLASSIFICADO
    Instance plantaDesconhecida = new DenseInstance(5);
    
    //DEFINIR COMO SÃO OS ATRIBUTOS DESSE EXEMPLO
    plantaDesconhecida.setDataset(iris);
    
    //DEFINIR QUAIS SÃO OS VALORES DESSES ATRIBUTOS
    plantaDesconhecida.setValue(0, 5.1);
    plantaDesconhecida.setValue(1, 3.5);
    plantaDesconhecida.setValue(2, 1.4);
    plantaDesconhecida.setValue(3, 0.2);
    
    //INSTANCIAR ALGORITMO DE CLASSIFICAÇÃO
    IBk vizinho = new IBk();
    
    //TREINAR O ALGORITMO
    vizinho.buildClassifier(iris);
    
    //IDENTIFICAR O GRUPO PARA O EXEMPLO DESCONHECIDO
    double grupo = vizinho.classifyInstance(plantaDesconhecida);
    
    //MOSTRAR O GRUPO IDENTIFICADO
    System.out.println("Grupo: " + grupo);
    plantaDesconhecida.setClassValue(grupo);
    System.out.println("Rótulo: " + plantaDesconhecida.stringValue(4));
    
    
  }
}
