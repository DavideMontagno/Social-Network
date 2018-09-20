
public interface Edge<E> {

	//Restituisce il nodo corrente
	public Node<E> sorgente();
	//Restituisce il nodo successivo
    public Node<E> destinazione();
	//Controlla se esiste l'arco tra il nodo e il nodo2 e ne restituisce il valore di verità;
    public boolean esisteArco(Node<E> node1, Node<E> node2);

}
