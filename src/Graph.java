import java.util.Vector;

public interface Graph<E> {
	 //Aggiunge un vertice a this
	 public void aggiungiVertice(E vertice) throws AlreadyPresentException;
	 //Aggiunge un arco tra il vertice1 e il vertice 2
	 public void aggiungiArco(E vertice1, E vertice2) throws NotPresentException, AlreadyPresentException;
	 //Rimuove il vertice da this;
	 public void rimuoviVertice(E vertice) throws NotPresentException;
	 //Rimuove l'arco tra il vertice1 e il vertice2
	 public void rimuoviArco(E vertice1, E vertice2) throws NotPresentException;
	 //Restituisce il numero di vertici in this
	 public int numberOfVertici();
	 //Controlla se vertice è presente in this e ne restituisce il valore di verità
	 public boolean isInVertice(E vertice);
	 //Controlla se esiste un arco dal vertice1 al vertice2 e ne restituisce il valore di verità
	 public boolean isThereArco(E vertice1, E vertice2);
	 //Restituisce il numero di archi di un determinato vertice
	 public int numberArchiOf(E vertice) throws NotPresentException;
	 //Calcola il cammino minimo tra il vertice1 e il vertice2
	 public int camminoMinimo(E vertice1, E vertice2);
	 //Metodo ausiliario per il camminoMinimo();
	 void BFS(E inizio);
	 //resetta i valori dopo la BFS 
	 void inizialize();
	 //Restituisce la lunghezza dei più lunghi tra i cammini minimi.
	 public int diametro();
	 //Restituisco la lista degli amici di vertice
	 public Vector<Node<E>> getArchiUscenti(E vertice) throws NotPresentException;
	 //Restituisco la lista degli amici in comune tra vertice1 e vertice2
	 public Vector<Node<E>> getCommonArchiUscenti(E vertice1, E vertice2) throws NotPresentException;
	 
}
