package Semana09;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Exercicio07 {
    public static void main(String[] args) throws Exception{
        //DEFINIR ORIGEM DOS DADOS
        DataSource arff = new DataSource("breast-cancer.arff");
        
        //INSTANCIAR CONJUNTO DE DADOS A PARTIR DESSA ORIGEM
        Instances bc = arff.getDataSet();
        
        //DEFINIR O ÍNDICE DO ATRIBUTO CLASSE NESSE CONJUNTO
        bc.setClassIndex(9);
        
        //INSTANCIAR CLASSIFICADOR Naive Bayes
        NaiveBayes bayes = new NaiveBayes();
        
        //TREINAR CLASSIFICADOR
        bayes.buildClassifier(bc);
        
        //EXIBIR O MODELO APRENDIDO
        //System.out.println(bayes);
        
        //DEFININDO UM EXEMPLO SEM GRUPO
        Instance desconhecido = new DenseInstance(10);
        
        //DEFININDO COMO SERÃO OS ATRIBUTOS DESSE EXEMPLO
        desconhecido.setDataset(bc);
        
        //DEFININDO OS VALORES DESSES ATRIBUTOS
        desconhecido.setValue(0, "50-59");
        desconhecido.setValue(1, "premeno");
        desconhecido.setValue(2, "25-29");
        desconhecido.setValue(3, "0-2");
        desconhecido.setValue(4, "yes");
        desconhecido.setValue(5, "1");
        desconhecido.setValue(6, "right");
        desconhecido.setValue(7, "left_low");
        desconhecido.setValue(8, "no");
        
        //IDENTIFICANDO O GRUPO AO QUAL ESSE EXEMPLO PERTENCE
        double classe = bayes.classifyInstance(desconhecido);
        
        //EXIBINDO O GRUPO IDENTIFICADO
        System.out.println("Grupo: " + classe);
        desconhecido.setClassValue(classe);
        System.out.println("Rótulo: " + desconhecido.stringValue(9));
        
        /*
        
        	50-59	premeno	25-29	0-2	yes	1	right  left_low no	
recorrente											
	85	25	48	18	46	31	12	36	35	54	3,227018007040412E-05
	286	85	85	85	85	85	85	85	85	85	
											
não recorrente											
	201	71	102	36	167	25	59	98	75	164	0,00010159356664082831
	286	201	201	201	201	201	201	201	201	201	
        */
    }
}
