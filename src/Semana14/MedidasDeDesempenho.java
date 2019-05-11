package Semana14;

import weka.classifiers.lazy.IBk;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class MedidasDeDesempenho {
  
  public static void main(String[] args) throws Exception {
    //DEFINE FONTE DE DADOS
    DataSource arff = new DataSource("diabetes.arff");
    
    //INSTANCIA CONJUNTO A PARTIR DA FONTE DEFINIDA
    Instances diabetes = arff.getDataSet();
    
    //DEFINE ÍNDICE DO ATRIBUTO CLASSE
    diabetes.setClassIndex(8);
    
    //DEFINE PARÂMETROS DA VALIDAÇÃO CRUZADA: leave one out
    int k = diabetes.numInstances();
    System.out.println("real,vizinho,knn3,knn7");
    for (int i = 0; i < k; i++){
      //DEFINE A PARTIÇÃO DE TREINO E A PARTIÇÃO DE TESTE
      Instances diabetesTreino = diabetes.trainCV(k, i);
      Instances diabetesTeste = diabetes.testCV(k, i);

      //INSTANCIA OS CLASSIFICADORES QUE SERÃO AVALIADOS
      IBk vizinho = new IBk();
      IBk knn3 = new IBk(3);
      IBk knn7 = new IBk(7);

      //TREINA OS CLASSIFICADORES
      vizinho.buildClassifier(diabetesTreino);
      knn3.buildClassifier(diabetesTreino);
      knn7.buildClassifier(diabetesTreino);

      //TESTA OS CLASSIFICADORES
      for (int j = 0; j < diabetesTeste.numInstances(); j++){
        Instance teste = diabetesTeste.instance(j);
        
        // - Exibe a classe real do exemplo
        System.out.print(teste.value(8));

        // - Remove o valor do atributo classe
        teste.setClassMissing();

        // - Classifica o exemplo usando cada um dos classificadores
        double cVizinho = vizinho.classifyInstance(teste);
        double cKnn3 = knn3.classifyInstance(teste);
        double cKnn7 = knn7.classifyInstance(teste);

        // - Exibe a resposta de cada um dos classificadores
        System.out.println("," + cVizinho + "," + cKnn3 + "," + cKnn7);
      }
    }
  }
}
