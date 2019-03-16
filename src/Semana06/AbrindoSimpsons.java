package Semana06;

import Semana05.ExercicioExtra01;
import java.lang.reflect.Array;
import java.util.Arrays;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class AbrindoSimpsons {
  public static void main(String[] args) throws Exception {
    
    //DEFINIR FONTE DE DADOS
    DataSource arff = new DataSource("simpsons.arff");
    
    //INSTANCIAR CONJUNTO A PARTIR DA FONTE DEFINIDA
    Instances simpsons = arff.getDataSet();
    
    //DEFINIR ÍNDICE DO ATRIBUTO CLASSE
    simpsons.setClassIndex(3);
    
    //OBTER O EXEMPLO 'selma' DO CONJUNTO SIMPSONS
    Instance selma = simpsons.instance(6);
    
    //MOSTRAR DADOS DO EXEMPLO OBTIDO
    System.out.println(selma);
    System.out.println("Peso: " + selma.value(1));
    
    //INSTANCIAR UM OBJETO DESSE CONJUNTO
    Instance comic = new DenseInstance(4); // QUANTIDADE DE ATRIBUTOS
    
    //DEFINIR COMO SERÃO OS ATRIBUTOS
    comic.setDataset(simpsons);
    
    //DEFINIR OS VALORES DOS ATRIBUTOS (NOMES E TIPOS)
    comic.setValue(0, 8); //cabelo
    comic.setValue(1, 290); //peso
    comic.setValue(2, 38); //idade
    comic.setClassMissing(); //deixando a classe indefinida
    
    System.out.println(comic);
    
    double[] vComic = comic.toDoubleArray();
    System.out.println("Cabelo: " + vComic[0]);
    System.out.println(Arrays.toString(vComic));
    
    double[][] arrSimpsons = new double[simpsons.numInstances()][simpsons.numAttributes()];
    for (int i=0;i<simpsons.numInstances();i++){
      arrSimpsons[i] = simpsons.get(i).toDoubleArray();
    }
    
    double classeComic = ExercicioExtra01.getVizinhoMaisProximo(arrSimpsons, vComic);
    
    System.out.println(classeComic);
  }
}
