package Semana16;

import weka.classifiers.trees.Id3;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class ArvoreDecisao {
  
  public static void main(String[] args) throws Exception {
    //DEFINIR ORIGEM DOS DADOS
    DataSource arff = new DataSource("contact-lenses.arff");
    DataSource arff2 = new DataSource("iris.arff");
    
    //INSTANCIAR CONJUNTO A PARTIR DA ORIGEM DEFINIDA
    Instances lenses = arff.getDataSet(4);
    Instances iris = arff2.getDataSet(4);
    
    //INSTANCIAR CLASSIFICADOR
    Id3 arvore = new Id3();
    J48 arvore2 = new J48();
    
    //TREINAR CLASSIFICADOR
    //arvore.buildClassifier(iris);
    arvore2.buildClassifier(iris);
    
    //IMPRIMIR O MODELO APRENDIDO
    System.out.println(arvore);
    System.out.println("=======================");
    System.out.println(arvore2);
  }
}
