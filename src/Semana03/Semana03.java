package Semana03;

import java.util.Scanner;

public class Semana03 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Digite a palavra de consulta: ");
        String consulta = sc.next();
        
        System.out.println("Digite as palavras do dicionário: ");
        String[] dicionario = sc.next().split(" ");
        
        System.out.println("Palavra mais similar: " + getPalavraParecida(dicionario, consulta));
    }
    
    private static String getPalavraParecida(String[] palavras, String consulta){
        String ret = palavras[0];
        double maiorSimilaridade = Semana02.Exercicio02_2.getSimilaridade(consulta, palavras[0]);
        
        for (int i = 1; i < palavras.length; i++){
            double similaridade = Semana02.Exercicio02_2.getSimilaridade(consulta, palavras[i]);
            
            if (similaridade > maiorSimilaridade){
                maiorSimilaridade = similaridade;
                ret = palavras[i];
            }
        }
        
        return ret;
    }
}
