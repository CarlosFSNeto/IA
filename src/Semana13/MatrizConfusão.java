package Semana13;

import java.util.Arrays;
import java.util.Random;
import weka.classifiers.lazy.IBk;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

// CÓPIA DO CÓDIGO Exemplo10_AvaliaClassificador2
public class MatrizConfusão {

    public static void main(String[] args) throws Exception {

        kfolds(5);

    }

    /**
     * Apresenta os resultados dos testes de três diferentes classificadores,
     * utilizando a validação cruzada 'hold out', ou seja, dois terços da base
     * são usados para treinar o classificador e o um terço restante é usado
     * para testá-lo.
     *
     * @throws Exception
     */
    public static void holdOut() throws Exception {
        // CRIAR OBJETO Instances BASEADO NESSE ARFF
        DataSource arff = new DataSource("iris.arff");
        Instances iris = arff.getDataSet();
        iris.setClassIndex(4);
        iris = iris.resample(new Random());
        //System.out.println(iris);

        Instances baseTreino = iris.trainCV(3, 0);
        Instances baseTeste = iris.testCV(3, 0);

        // INSTANCIAR kNN(3) PARA CLASSIFICAR O COMIC
        IBk knn = new IBk(); // k=1
        IBk knn3 = new IBk(3); // k = 3
        IBk knn7 = new IBk(7); // k = 7

        knn.buildClassifier(baseTreino);
        knn3.buildClassifier(baseTreino);
        knn7.buildClassifier(baseTreino);

        System.out.println("real;knn;knn(3);knn(7)");
        for (int i = 0; i < baseTeste.numInstances(); i++) {
            Instance teste = baseTeste.instance(i);
            System.out.print(teste.classValue() + ";");
            teste.setClassMissing();
            double classe = knn.classifyInstance(teste);
            double classe3 = knn3.classifyInstance(teste);
            double classe7 = knn7.classifyInstance(teste);
            teste.setClassValue(classe);
            System.out.print(teste.classValue() + ";");
            teste.setClassValue(classe3);
            System.out.print(teste.classValue() + ";");
            teste.setClassValue(classe7);
            System.out.println(teste.classValue());
        }
    }

    /**
     * Apresenta os resultados dos testes de três diferentes classificadores,
     * utilizando a validação cruzada 'k-folds', ou seja, a quantidade de
     * iterações/partições é determinada pelo parâmetro 'k'.
     *
     * @param k O número de iterações/partições usada na validação cruzada.
     * @throws Exception
     */
    public static void kfolds(int k) throws Exception {
        // CRIAR OBJETO Instances BASEADO NESSE ARFF
        DataSource arff = new DataSource("iris.arff");
        Instances iris = arff.getDataSet();
        iris.setClassIndex(4);
        iris = iris.resample(new Random());
        //System.out.println(iris);
        int[][] matriz = new int[iris.numClasses()][iris.numClasses()];

        System.out.println("real;knn;knn(3);knn(7)");
        for (int j = 0; j < k; j++) {
            Instances baseTreino = iris.trainCV(k, j);
            Instances baseTeste = iris.testCV(k, j);

            // INSTANCIAR kNN(3) PARA CLASSIFICAR O COMIC
            IBk knn = new IBk(); // k=1
            IBk knn3 = new IBk(3); // k = 3
            IBk knn7 = new IBk(7); // k = 7

            knn.buildClassifier(baseTreino);
            knn3.buildClassifier(baseTreino);
            knn7.buildClassifier(baseTreino);

            for (int i = 0; i < baseTeste.numInstances(); i++) {
                Instance teste = baseTeste.instance(i);
                double real = teste.classValue();
                teste.setClassMissing();
                double classe = knn.classifyInstance(teste);
                double classe3 = knn3.classifyInstance(teste);
                double classe7 = knn7.classifyInstance(teste);
                String s = real + ";" + classe + ";" + classe3 + ";" + classe7;
                System.out.println(s);
                matriz[(int)real][(int)classe7]++;
            }
        }
        System.out.println("Matriz confusão: Knn7");
        for(int[] linha: matriz) {
            System.out.println(Arrays.toString(linha));
        }
    }

    /**
     * Apresenta os resultados dos testes de três diferentes classificadores,
     * utilizando a validação cruzada 'leave-one-out', ou seja, a quantidade de
     * iterações/partições é determinada pela quantidade de exemplos na base.
     *
     * @throws Exception
     */
    public static void leaveOneOut() throws Exception {
        // CRIAR OBJETO Instances BASEADO NESSE ARFF
        DataSource arff = new DataSource("iris.arff");
        Instances iris = arff.getDataSet();
        iris.setClassIndex(4);
        //iris = iris.resample(new Random());
        //System.out.println(iris);

        int k = iris.numInstances();

        System.out.println("real;knn;knn(3);knn(7)");
        for (int j = 0; j < k; j++) {
            Instances baseTreino = iris.trainCV(k, j);
            Instances baseTeste = iris.testCV(k, j);

            // INSTANCIAR kNN(3) PARA CLASSIFICAR O COMIC
            IBk knn = new IBk(); // k=1
            IBk knn3 = new IBk(3); // k = 3
            IBk knn7 = new IBk(7); // k = 7

            knn.buildClassifier(baseTreino);
            knn3.buildClassifier(baseTreino);
            knn7.buildClassifier(baseTreino);

            for (int i = 0; i < baseTeste.numInstances(); i++) {
                Instance teste = baseTeste.instance(i);
                System.out.print(teste.stringValue(4) + ";");
                teste.setClassMissing();
                double classe = knn.classifyInstance(teste);
                double classe3 = knn3.classifyInstance(teste);
                double classe7 = knn7.classifyInstance(teste);
                teste.setClassValue(classe);
                System.out.print(teste.stringValue(4) + ";");
                teste.setClassValue(classe3);
                System.out.print(teste.stringValue(4) + ";");
                teste.setClassValue(classe7);
                System.out.println(teste.stringValue(4));
            }
        }

    }
}

