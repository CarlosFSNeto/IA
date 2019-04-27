package Semana12;

import static Semana05.ExercicioExtra01.getDistanciaEuclidiana;
import java.util.ArrayList;
import java.util.List;

public class Semana12 {
  public static double kNN(double[][] conjunto, double[] desconhecido, int k){
    //double menorDistancia = getDistanciaEuclidiana(conjunto[0], desconhecido);
    //int indiceVizinhoMaisProximo = 0;
    
    int atributoClasse = desconhecido.length-1;
    
    
    List menorDistancia = new ArrayList();
    List indice = new ArrayList();
    double distancia = 0d;
    
    for (int i=0;i<k;i++){
      boolean flag = false;
      for(int j=0;j<i;j++){
        distancia = getDistanciaEuclidiana(conjunto[i], desconhecido);
        if (distancia < (double)menorDistancia.get(j)){
          menorDistancia.add(j, distancia);
          indice.add(j, i);
          flag=true;
          break;
        }
      }
      if (!flag){
          menorDistancia.add(distancia);
          indice.add(i);
      }
    }
    
    
    
    for (int i=k; i< conjunto.length; i++){
      
      for (int j = 0; j<k; j++){
        
        distancia = getDistanciaEuclidiana(conjunto[i], desconhecido);
        
        if (distancia < (double)menorDistancia.get(j)){
          
          menorDistancia.add(j, distancia);
          indice.add(j, i);
        }
      }
    }
    
    int[] votos = new int[2];
    
    for (int i = 0; i<k; i++){
      
      votos[(int)conjunto[(int)indice.get(i)][atributoClasse]]++;
    }
    
    return votos[0] > votos[1] ? 0 : 1;
  }
  
  public static void main(String[] args) throws Exception{
    // Definindo o conjunto de treinamentos
        double[][] simpsons = {
            {0, 250, 36, 1}, // Homer
            {10, 150, 34, 0}, // Marge
            {2, 90, 10, 1}, // Bart
            {6, 78, 8, 0}, // Lisa
            {4, 20, 1, 0}, // Maggie
            {1, 170, 70, 1}, // Abe
            {8, 160, 41, 0}, // Selma
            {10, 180, 38, 1}, // Otto
            {6, 200, 45, 1} // Krusty
        };

        // Definindo o exemplo desconhecido
        double[] comic = {8, 140, 38, -1};

        // Passando como parâmetros a base de conhecimento e o problema para ser solucionado
        double resposta = kNN(simpsons, comic, 3);

        // Exibindo a resposta encontrada
System.out.println("Resposta: " + resposta);
  }
}
