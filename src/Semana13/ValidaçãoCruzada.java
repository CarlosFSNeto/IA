package Semana13;

import java.util.Random;
import weka.classifiers.lazy.IBk;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;



public class ValidaçãoCruzada {
  
  public static void main(String[] args) throws Exception {
        //Pega a base
        DataSource arff = new DataSource("iris.arff");
        //Instancia e reordena
        Instances iris = arff.getDataSet().resample(new Random());
        //Define indice da classe
        iris.setClassIndex(4);
        int k = 3; // Quantidade de partições
        System.out.println("real;vizinho;knn3;knn7");
        for (int i = 0; i < k; i++) {
            Instances irisTreino = iris.trainCV(k, i); // Treina
            Instances irisTeste = iris.testCV(k, i); // Testa
            
            //Instanciando classificadores
            IBk vizinho = new IBk(); // Vizinho mais proximo
            IBk knn3 = new IBk(3); // 3 Vizinhos
            IBk knn7 = new IBk(7); // 7 Vizinhos
            
            //Treinando os classificadores
            vizinho.buildClassifier(irisTreino);
            knn3.buildClassifier(irisTreino);
            knn7.buildClassifier(irisTreino);
            
            for (int j = 0; j < irisTreino.numInstances(); j++) {
                Instance teste = irisTreino.instance(j);
                System.out.print(teste.value(4));
                teste.setClassMissing();
                double cVizinho = vizinho.classifyInstance(teste);
                double cKnn3 = knn3.classifyInstance(teste);
                double cKnn7 = knn7.classifyInstance(teste);
                System.out.println(";" + cVizinho + ";" + cKnn3 + ";" + cKnn7);
            }
        }
    }

}
