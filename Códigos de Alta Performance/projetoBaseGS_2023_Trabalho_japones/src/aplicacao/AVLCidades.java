package aplicacao;

import java.util.ArrayList;
import java.util.List;

class AVLCidades {

    private class NoAVL {
        Cidade cidade;
        NoAVL esq, dir;
        int altura;

        public NoAVL(Cidade cidade) {
            this.cidade = cidade;
            this.esq = null;
            this.dir = null;
            this.altura = 1;
        }
    }

    private NoAVL raiz;

    public void inserir(String nome, double taxaVacinacao, int numeroCasos) {
        Cidade cidade = new Cidade(nome, taxaVacinacao, numeroCasos);
        raiz = inserir(raiz, cidade);
    }
    
    private NoAVL inserir(NoAVL no, Cidade cidade) {
        if (no == null) {
        	comparacoesInsercaoAVL++;
            return new NoAVL(cidade);
        }
        
        comparacoesInsercaoAVL++;
        
        if (cidade.nome.compareToIgnoreCase(no.cidade.nome) < 0) {
            no.esq = inserir(no.esq, cidade);
        } else if (cidade.nome.compareToIgnoreCase(no.cidade.nome) > 0) {
            no.dir = inserir(no.dir, cidade);
        } else {
            // Caso a cidade já exista (aqui você pode decidir como lidar com isso)
            return no;
        }

        // Atualiza a altura do nó atual
        no.altura = 1 + Math.max(altura(no.esq), altura(no.dir));

        // Realiza o balanceamento após a inserção
        return balancear(no, cidade);
    }

    private int altura(NoAVL no) {
        if (no == null) {
            return 0;
        }
        return no.altura;
    }

    private int fatorBalanceamento(NoAVL no) {
        if (no == null) {
            return 0;
        }
        return altura(no.esq) - altura(no.dir);
    }

    private NoAVL rotacaoDireita(NoAVL y) {
        NoAVL x = y.esq;
        NoAVL T2 = x.dir;

        x.dir = y;
        y.esq = T2;

        y.altura = Math.max(altura(y.esq), altura(y.dir)) + 1;
        x.altura = Math.max(altura(x.esq), altura(x.dir)) + 1;

        return x;
    }

    private NoAVL rotacaoEsquerda(NoAVL x) {
        NoAVL y = x.dir;
        NoAVL T2 = y.esq;

        y.esq = x;
        x.dir = T2;

        x.altura = Math.max(altura(x.esq), altura(x.dir)) + 1;
        y.altura = Math.max(altura(y.esq), altura(y.dir)) + 1;

        return y;
    }

    private NoAVL balancear(NoAVL no, Cidade cidade) {
        int fatorBalanceamento = fatorBalanceamento(no);

        // Caso de desequilíbrio à esquerda
        if (fatorBalanceamento > 1) {
            // Caso de desequilíbrio à esquerda-direita
            if (cidade.nome.compareToIgnoreCase(no.esq.cidade.nome) > 0) {
                no.esq = rotacaoEsquerda(no.esq);
            }
            return rotacaoDireita(no);
        }

        // Caso de desequilíbrio à direita
        if (fatorBalanceamento < -1) {
            // Caso de desequilíbrio à direita-esquerda
            if (cidade.nome.compareToIgnoreCase(no.dir.cidade.nome) < 0) {
                no.dir = rotacaoDireita(no.dir);
            }
            return rotacaoEsquerda(no);
        }

        return no;
    }

    public void imprimirEmOrdem() {
        imprimirEmOrdem(raiz);
    }

    private void imprimirEmOrdem(NoAVL no) {
        if (no != null) {
            imprimirEmOrdem(no.esq);
            System.out.println(no.cidade.nome);
            imprimirEmOrdem(no.dir);
        }
    }
    
    public List<Cidade> gerarVetorAlerta() {
        List<Cidade> vetorAlerta = new ArrayList<>();
        gerarVetorAlerta(raiz, vetorAlerta);
        return vetorAlerta;
    }

    private void gerarVetorAlerta(NoAVL no, List<Cidade> vetorAlerta) {
        if (no != null) {
            gerarVetorAlerta(no.esq, vetorAlerta);

            if (no.cidade.taxaVacinacao < 80 && no.cidade.numeroCasos > 0) {
                vetorAlerta.add(no.cidade);
            }

            gerarVetorAlerta(no.dir, vetorAlerta);
        }
    }

    public void ordenarVetorAlerta() {
        List<Cidade> vetorAlerta = gerarVetorAlerta();
        Cidade[] vetorCidades = vetorAlerta.toArray(new Cidade[0]);

        int[] indices = new int[vetorCidades.length];
        for (int i = 0; i < indices.length; i++) {
            indices[i] = i;
        }
    }
    
    
   


    public void resgataAVL() {
    	ABBCidades abbCidades = new ABBCidades();
    	resgataAVL(raiz, abbCidades);
    	
    	abbCidades.imprimirEmOrdem();
    	
    }
    
    public void resgataAVL(NoAVL no, ABBCidades abbCidades) {
    	if (no != null) {
    		resgataAVL(no.esq,abbCidades);
    		
    		if (no.cidade.taxaVacinacao < 80 && no.cidade.numeroCasos > 0) {
    				abbCidades.inserir(no.cidade);
    			}
    				resgataAVL(no.dir,abbCidades);
    		}
    	}
    	
    class ABBCidades {
        private class NoABB {
            Cidade cidade;
            NoABB esq, dir;

            public NoABB(Cidade cidade) {
                this.cidade = cidade;
                this.esq = null;
                this.dir = null;
            }
        }

        private NoABB raiz;

        public void inserir(Cidade cidade) {
            raiz = inserir(raiz, cidade);
        }

        public void imprimirEmOrdem() {
            imprimirEmOrdem(raiz);
        }

        private void imprimirEmOrdem(NoABB no) {
            if (no != null) {
                imprimirEmOrdem(no.esq);
                System.out.println(no.cidade.nome + " - Taxa de Vacinação: " + no.cidade.taxaVacinacao);
                imprimirEmOrdem(no.dir);
            }
        }

		private NoABB inserir(NoABB no, Cidade cidade) {
            if (no == null) {
                return new NoABB(cidade);
            }

            if (cidade.taxaVacinacao < no.cidade.taxaVacinacao) {
                no.esq = inserir(no.esq, cidade);
            } else {
                no.dir = inserir(no.dir, cidade);
            }

            return no;
        }
 
    }
    
    private int comparacoesInsercaoAVL;

    public AVLCidades() {
        this.comparacoesInsercaoAVL = 0;
    }
    
    public int getComparacoesInsercaoAVL() {
        return comparacoesInsercaoAVL;
    }
    
    }


class Compara {
    private int comparacoes = 0;

    public int quicksort(int indices[], Cidade vetorCidades[], int li, int ls) {
        
        if (li < ls) {
            int j = particiona(indices, vetorCidades, li, ls);
            quicksort(indices, vetorCidades, li, j - 1);
            quicksort(indices, vetorCidades, j + 1, ls);
        }
        return comparacoes;
    }

    private int particiona(int indices[], Cidade vetorCidades[], int li, int ls) {
        int abaixo = li;
        int acima = ls - 1;
        int pivo = indices[ls];

        while (abaixo <= acima) {
            while (abaixo <= acima && vetorCidades[indices[abaixo]].taxaVacinacao < vetorCidades[pivo].taxaVacinacao) {
                abaixo++;
                comparacoes++; // Incrementa a contagem de comparações
            }

            while (acima >= abaixo && vetorCidades[indices[acima]].taxaVacinacao >= vetorCidades[pivo].taxaVacinacao) {
                acima--;
                comparacoes++; // Incrementa a contagem de comparações
            }

            if (abaixo < acima) {
                int temp = indices[abaixo];
                indices[abaixo] = indices[acima];
                indices[acima] = temp;
            }
        }

        int temp = indices[abaixo];
        indices[abaixo] = indices[ls];
        indices[ls] = temp;

        comparacoes++; // Incrementa a contagem de comparações

        return abaixo;
    }
}
   