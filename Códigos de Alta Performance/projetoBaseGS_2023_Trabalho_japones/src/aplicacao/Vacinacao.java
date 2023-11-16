package aplicacao;

import java.util.Random;
import java.util.List;

class Cidade {
    String nome;
    double taxaVacinacao;
    int numeroCasos;

    public Cidade(String nome, double taxaVacinacao, int numeroCasos) {
        this.nome = nome;
        this.taxaVacinacao = taxaVacinacao;
        this.numeroCasos = numeroCasos;
    }
}


public class Vacinacao {

	
public static void main(String[] args) {
		 AVLCidades avlCidades = new AVLCidades();
		 	
	        String cidades[] = {"Analandia", "Araraquara", "Dourado", "Ibitinga", "Matao", "Sao Carlos", "Tabatinga"};
	        double vacinacao[] = {72.5, 88.4, 71.9, 76, 78.8, 96.7, 66};
	        int nCasos[] = {1, 0, 2, 0, 0, 1, 1};

	    
	        for (int i = 0; i < cidades.length; i++) {
	            avlCidades.inserir(cidades[i], vacinacao[i], nCasos[i]);
	        }
	        System.out.println("1° Parte.\n" );
	        avlCidades.resgataAVL();
		
	        System.out.println("\n2° Parte.\n" );
            Random random = new Random();

            adicionarCidades(avlCidades, random);
     
            avlCidades.resgataAVL();
    }

	private static void adicionarCidades(AVLCidades avlCidades, Random random) {
        int tamanho = 100;

        // Inserção na AVL
        for (int i = 0; i < tamanho; i++) {
            String nomeCidade = "Cidade" + i;
            double taxaVacinacao = random.nextDouble() * 100;
            int numeroCasos = random.nextInt(100);
            avlCidades.inserir(nomeCidade, taxaVacinacao, numeroCasos);
        }

        // Imprimir comparações durante a inserção
        System.out.println("Número de comparações durante a inserção na AVL: \n" + avlCidades.getComparacoesInsercaoAVL());

        Compara compara = new Compara();
        List<Cidade> vetorAlerta = avlCidades.gerarVetorAlerta();
        Cidade[] vetorCidades = vetorAlerta.toArray(new Cidade[0]);

        int[] indices = new int[vetorCidades.length];
        for (int i = 0; i < indices.length; i++) {
            indices[i] = i;
        }

        int comparacoesQuicksort = compara.quicksort(indices, vetorCidades, 0, indices.length - 1);
        System.out.println("Número de comparações no quicksort: \n" + comparacoesQuicksort);
    }
   }

