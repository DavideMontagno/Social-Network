public class MyEdge<E> implements Edge<E>{
	//Overview: MyEdge<E> è un oggetto di tipo E; E' utilizzato per gestire l'arco tra la sorgente e la destinazione.
	//INV: { sorgente !=null && destinazione!=null && sorgente!=destinazione}
	//AF: <sorgente,destinazione> dove sorgente e destinazione sono di tipo Node<E>
    private Node<E> sorgente;

    private Node<E> destinazione;
    
    
    //Costruttore
    public MyEdge(Node<E> sorgente, Node<E> destinazione) {
    	//Modify: This;
    	//Effects: Setta l'arco tra sorgente e destinazione ( l'arco è unidirezionale)
        this.sorgente = sorgente;
        this.destinazione = destinazione;
    }
    
    public Node<E> sorgente() {
    	//Effects: Restituisce il sorgente sorgente
        return this.sorgente;
    }

    public Node<E> destinazione() {
    	//Effects: Restituisce il sorgente di arrivo
        return this.destinazione;
    }
    
    public boolean esisteArco(Node<E> sorgente, Node<E> node2) {
    	//Effects: Controlla se corrispondono i valori restituendone il valore di verità.
      //  return (this.sorgente.equals(sorgente) && this.destinazione.equals(destinazione));
        return (this.sorgente.equals(sorgente) && this.destinazione.equals(destinazione));
    }
}