package Semana05;

public class ExercicioExtra01 {
  public static void main(String[] args) {
    double[][] simpsons = {
      {0, 250, 36, 1}, //Homer
      {10, 150, 34, 0}, //Marge
      {2, 90, 10, 1},   //Bart
      {6, 78, 8, 0},
      {4, 20, 1, 0},
      {1, 170, 70, 1},
      {8, 160, 41, 0},
      {10, 180, 38, 1},
      {6, 200, 45, 1}
    };
    
    double[] comic = {8, 290, 38, -1};
    
    //System.out.println("Distância: " + getDistanciaEuclidiana(simpsons[2], comic));
    System.out.println("Classe: " + getVizinhoMaisProximo(simpsons, comic));
  }
  
  public static double getDistanciaEuclidiana(double[] c1, double[] c2){
    
    double soma = 0;
    
    //Ignora o último (dado a ser descoberto)
    for(int i = 0; i < c1.length - 1; i++){
      soma += Math.pow(c1[i] - c2[i], 2);
    }
    
    return Math.sqrt(soma);
  }
  
  public static double getVizinhoMaisProximo(double[][] conjunto, double[] desconhecido){
    double menorDistancia = getDistanciaEuclidiana(conjunto[0], desconhecido);
    int indiceVizinhoMaisProximo = 0;
    
    
    for (int i=1; i< conjunto.length; i++){
      double distancia = getDistanciaEuclidiana(conjunto[i], desconhecido);
      
      if (distancia < menorDistancia){
        menorDistancia = distancia;
        indiceVizinhoMaisProximo = i;
      }
    }
    
    return conjunto[indiceVizinhoMaisProximo][conjunto[indiceVizinhoMaisProximo].length-1];
  }
}
