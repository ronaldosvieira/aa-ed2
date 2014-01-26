package br.ufrrj.im.cc.ed2.arquivos;

/*************************************************************************
 *
 *  Limitações
 *  -----------
 *   -  Assume que M é even and M >= 4
 *   -  b é um array de filhos ou lista
 *
 *************************************************************************/

public class BTree<Key extends Comparable<Key>, Value>  {
    private static final int M = 4;    //maximo de filhos por no = M-1

    private No raiz;             // raiz da arvoreB
    private int HT;                // altura da arvore B
    private int N;                 // numero de chaves na arvore B

    // helper B-tree node data type
    private static final class No {
        private int m;                             //numero de filhos
        private Entry[] filho = new Entry[M];   // array de filhos
        private No(int k) { m = k; }            //cria um no com k filhos
    }

    // no interno:apenas usar chave e next
    // no externo: apenas usar chave e valor
    private static class Entry {
        private Comparable chave;
        private Object valor;
        private No next;     
        public Entry(Comparable chave, Object valor, No next) {
            this.chave   = chave;
            this.valor = valor;
            this.next  = next;
        }
    }

    // constructor
    public BTree() { raiz = new No(0); }
 
    // retorna o numero de chave-valor que tem na arvore
    public int size() { return N; }

    // retorna a altura da arvore
    public int height() { return HT; }


    // procura a chave e retorna null se nao a encontrar
    public Value get(Key chave) { return search(raiz, chave, HT); }
    private Value search(No x, Key chave, int ht) {
        Entry[] filho = x.filho;

        // external node
        if (ht == 0) {
            for (int j = 0; j < x.m; j++) {
                if (eq(chave, filho[j].chave)) return (Value) filho[j].valor;
            }
        }

        // internal node
        else {
            for (int j = 0; j < x.m; j++) {
                if (j+1 == x.m || less(chave, filho[j+1].chave))
                    return search(filho[j].next, chave, ht-1);
            }
        }
        return null;
    }


    // insere o par chave-valor
    // codigo que checa se variaveis duplicadas
    public void put(Key chave, Value valor) {
        No u = insert(raiz, chave, valor, HT); 
        N++;
        if (u == null) return;

        // 
        No t = new No(2);
        t.filho[0] = new Entry(raiz.filho[0].chave, null, raiz);
        t.filho[1] = new Entry(u.filho[0].chave, null, u);
        raiz = t;
        HT++;
    }


    private No insert(No h, Key chave, Value valor, int ht) {
        int j;
        Entry t = new Entry(chave, valor, null);

        // no externo
        if (ht == 0) {
            for (j = 0; j < h.m; j++) {
                if (less(chave, h.filho[j].chave)) break;
            }
        }

        // no interno
        else {
            for (j = 0; j < h.m; j++) {
                if ((j+1 == h.m) || less(chave, h.filho[j+1].chave)) {
                    No u = insert(h.filho[j++].next, chave, valor, ht-1);
                    if (u == null) return null;
                    t.chave = u.filho[0].chave;
                    t.next = u;
                    break;
                }
            }
        }

        for (int i = h.m; i > j; i--) h.filho[i] = h.filho[i-1];
        h.filho[j] = t;
        h.m++;
        if (h.m < M) return null;
        else         return split(h);
    }

    // divisao no nó do meio
    private No split(No h) {
        No t = new No(M/2);
        h.m = M/2;
        for (int j = 0; j < M/2; j++)
            t.filho[j] = h.filho[M/2+j]; 
        return t;    
    }

    // for debugging
    public String toString() {
        return toString(raiz, HT, "") + "\n";
    }
    
    private String toString(No h, int ht, String indent) {
        String s = "";
        Entry[] filho = h.filho;

        if (ht == 0) {
            for (int j = 0; j < h.m; j++) {
                s += indent + filho[j].chave + " " + filho[j].valor + "\n";
            }
        }
        else {
            for (int j = 0; j < h.m; j++) {
                if (j > 0) s += indent + "(" + filho[j].chave + ")\n";
                s += toString(filho[j].next, ht-1, indent + "     ");
            }
        }
        return s;
    }


    // compara funcoes 
    private boolean less(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) < 0;
    }

    private boolean eq(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) == 0;
    }


   /*************************************************************************
    *  teste
    *************************************************************************/
    public static void main(String[] args) {
        BTree<String, String> st = new BTree<String, String>();

        st.put("www.cs.princeton.edu", "128.112.136.12");
        st.put("www.cs.princeton.edu", "128.112.136.11");
        st.put("www.princeton.edu",    "128.112.128.15");
        st.put("www.yale.edu",         "130.132.143.21");
        st.put("www.simpsons.com",     "209.052.165.60");
        st.put("www.apple.com",        "17.112.152.32");
        st.put("www.amazon.com",       "207.171.182.16");
        st.put("www.ebay.com",         "66.135.192.87");
        st.put("www.cnn.com",          "64.236.16.20");
        st.put("www.google.com",       "216.239.41.99");
        st.put("www.microsoft.com",    "207.126.99.140");
        st.put("www.dell.com",         "143.166.224.230");
        st.put("www.espn.com",         "199.181.135.201");
        st.put("www.yahoo.com",        "216.109.118.65");
        st.put("www.ufrrj.br",		   "200.11.2.11");
        st.put("aaaasda", "13543");


        System.out.println("princeton.edu:  " + st.get("www.cs.princeton.edu"));
        System.out.println("ufrrj.br:	" + st.get("www.ufrrj.br"));
        System.out.println("simpsons.com:      " + st.get("www.simpsons.com"));
        System.out.println("apple.com:         " + st.get("www.apple.com"));
        System.out.println("ebay.com:          " + st.get("www.ebay.com"));
        System.out.println("dell.com:          " + st.get("www.dell.com"));
        
        System.out.println();

        System.out.println("size:    " + st.size());
        System.out.println("height:  " + st.height());
       //System.out.println(st);// escreve a árvore
        System.out.println();
    }

}