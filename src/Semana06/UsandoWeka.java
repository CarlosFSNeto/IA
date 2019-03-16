package Semana06;

import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class UsandoWeka {

  public static void main(String[] args) throws Exception {
    DataSource arff = new DataSource("iris.arff");
    Instances iris = arff.getDataSet();
    
    System.out.println("Exemplo 2: " + iris.instance(1));
    
    Instance exemplo2 = iris.instance(1);
    System.out.println("Atributo 1: " + exemplo2.value(0));
    System.out.println("Classe: " + exemplo2.value(4));
    System.out.println("Rótulo: " + exemplo2.stringValue(4));
    
    
  }
  
}
