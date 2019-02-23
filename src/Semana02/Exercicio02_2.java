package Semana02;

import java.util.Scanner;

public class Exercicio02_2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        /*System.out.println("Digite a primeira palavra: ");
        String p1 = sc.next();
        
        System.out.println("Digite a segunda palavra: ");
        String p2 = sc.next();
        System.out.println(getDistancia(p1, p2));*/
        
        
        
    }
    
    public static double getSimilaridade(String strPalavra1, String strPalavra2){
        String maior = strPalavra1;
        String menor = strPalavra2;
        
        if (strPalavra2.length() > strPalavra1.length()){
            maior = strPalavra2;
            menor = strPalavra1;
        }
        
        int PONTUACAO_MAXIMA = maior.length() * 6;
        int pontuacao = 0;
        
        for(int i = 0; i < maior.length(); i++){
            if (menor.length() > i && menor.charAt(i) == maior.charAt(i)){
                pontuacao += 4;
            } else if (menor.indexOf(maior.charAt(i)) > -1){
                pontuacao += 1;
            }
            
            if (getOcorrencias(maior.charAt(i), menor) == getOcorrencias(maior.charAt(i), maior)){
                pontuacao += 2;
            }
        }
        
        double indiceSimilaridade = (double)pontuacao / (double)PONTUACAO_MAXIMA;
        
        return indiceSimilaridade;
    }

    private static int getOcorrencias(char c, String s){
        return s.length() - s.replace(Character.toString(c), "").length();
    }
    
    public static double getDistancia(String strPalavra1, String strPalavra2){
        return 1 - getSimilaridade(strPalavra1, strPalavra2);
    }
    
    
}
