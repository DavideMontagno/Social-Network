import java.util.List;

public interface Node<E> {
	 //Restituisce il vertice corrente
	 public E getVertice();
	 //Aggiunge un arco tra this e nodo
	 public void aggiungiArco(Node<E> nodo) throws AlreadyPresentException;
	 //Rimuove arco tra this e nodo
	 public void rimuoviArco(Node<E> nodo) throws NotPresentException;
	 // Controlla se è presente l'arco tra nodo e this e restituisce il valore di verità   
	 public MyEdge<E> isThereEdge(Node<E> nodo);
	 //Restituisce la lista di archi presente il this
	 public List<MyEdge<E>> listOfArchi();
	 //Restituisce il padre di this
	 public Node<E> getPadre();
	 //Controlla se è stato visitato e ne restituisce il valore di verità   
	 public boolean isVisited();
     //Setta il valore di colore
	 public void setVisited(String colore);
     //Setta il padre di this
	 public void setPadre(Node<E> padre) ;
	 //Restituisce il numero di archi uscenti presenti in this.
	 public int numberOfArchi();
}
