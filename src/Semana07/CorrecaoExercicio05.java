package Semana07;

import java.util.Arrays;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class CorrecaoExercicio05 {
  public static void main(String[] args) throws Exception {
    DataSource arff = new DataSource("iris.arff");
    
    Instances iris = arff.getDataSet();
    
    int qtExemplos = iris.numInstances();
    int qtAtributos = iris.numAttributes();
    double[][] matrizIris = new double[qtExemplos][qtAtributos];
    
    for (int i=0;i<qtExemplos;i++){
      matrizIris[i] = iris.instance(i).toDoubleArray();
      System.out.println(Arrays.toString(matrizIris[i]));
    }
    
    double[] plantaDesconhecida = {5.1, 3.5, 3.5, 1.1, -1};
    
    double grupo = Semana05.ExercicioExtra01.getVizinhoMaisProximo(matrizIris, plantaDesconhecida);
    
    System.out.println("Grupo: " + grupo);
  }
  
  
}
