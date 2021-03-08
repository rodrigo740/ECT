

public class BloomFilter {
	
	private int k; // numero de hash functions
	private boolean ver []; //filtro de bloom (array de 0 (false) e de 1(true) a indicar a presença de strings)
	private static int code[];//codigos para usar com a hash function
	
	// n= tamanho do bloom filter (= ver.length) e k=nº de hash functions
	public BloomFilter(int n, int k) {
		this.k = k;
		ver = new boolean [n];//inicia o bloom filter com n elementos a false(0)
		code=new int[k];
		for(int i=0;i<k;i++) {
			code[i]=Math.abs((int)(Math.random()*Integer.MAX_VALUE));
		}
	} 
	
	/* Adicionar elementos ao bloom filter
     * hash code para string = numero positivo
	 * a funcao de dispersao(i) esta entre (1<i<=k) - slides do bloom filter
	 */
	public void addElement(String nova) {
		String s = nova;
		for(int i=1;i<=k;i++) {
			s += i; 
			int hashcode = s.hashCode()^code[i-1]; 
			hashcode = Math.abs(hashcode);
			hashcode = Math.abs(hashcode % ver.length);//funcao para strings - slides das hash function
			ver[hashcode]=true; //coloca-se a 1 na posicao hashcode (a posicao devolvida pela hash function)
		}
	} 
	
	//Verificar se e membro ou nao
	public boolean membro(String elemento) {
		boolean pertence=true;
		String s = elemento;
		for(int i=1;i<=k;i++) {
			s += i;
			int hashcode =Math.abs((s.hashCode()^code[i-1]) % ver.length);
			if(!ver[hashcode]) { // nao existe falsos negativos (se der um false entao ja nao faz parte)
				pertence=false;
				break;
			}
		}
		return pertence;
	}
}
