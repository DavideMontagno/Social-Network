import java.util.*;

public class MyNode<E> implements Node<E>{
	//Overview: MyNode<E> è un oggetto di tipo E; Viene utilizzato per gestire gli archi di un Vertice.
	//INV: { vertice!=null 
	//	archi.get(i)!=null | 0<=i<archi.size();
	// archi.get(j) != archi.get(i) | 0<=i<j<list.size();
	// colore="Bianco" || colore="Grigio"
	//}
	//AF:{
	// <vertice,{archi.get(i) || 0<=i<archi.size() },padre,colore> 
	//}
    private E vertice;

    private List<MyEdge<E>> archi;

    private Node<E> padre;

    private String colore; //Bianco se non è stato visitato, grigio altrimenti.
    
    
    
    
    //Costruttore;
    public MyNode(E vertice) {
    	//Modify: This;
    	//Effects: Inizializza this a vuoto;
        this.vertice = vertice;
        this.archi = new ArrayList<>();
        this.padre=null;
        this.colore="Bianco";
        
    }

   

    public void aggiungiArco(Node<E> nodo) throws AlreadyPresentException {
    	//Modify: This;
    	//Effects: Se l'arco un arco di nodo this è già stato inserito sollevo AlreadyPresentException (Checked)
    	//Altrimenti aggiungo l'arco tra this e nodo.
        if (isThereEdge(nodo)!=null) {
            throw new AlreadyPresentException();
        }
        MyEdge<E> newArco = new MyEdge<E>((Node<E>) this, nodo);
        archi.add(newArco);
    }

    public void rimuoviArco(Node<E> node) throws NotPresentException {
    	//Modify: This;
    	//Effects:  l'arco un arco di nodo this to nodo non esiste sollevo NotPresentException (Checked)
    	//Altrimenti rimuovo l'arco tra this e nodo.
        MyEdge<E> tmp = this.isThereEdge(node);
        if (tmp!=null) {
           archi.remove(tmp);
        }
        else{
        	throw new NotPresentException();
        }
    }

    public MyEdge<E> isThereEdge(Node<E> node) {
    	//Cerca l'arco all'interno di tutti gli archi e controlla se è presente restituendone il valore di verità.
    	//return findEdge(node).isPresent();
    	for(int i=0;i<archi.size();i++){
    		if(archi.get(i).destinazione().equals(node)) return archi.get(i);
    	}
    	return null;
    }

    
    public List<MyEdge<E>> listOfArchi() {
    	//Altrimenti restituisce la lista degli archi di this.
        return archi;
    }

    public int numberOfArchi() {
    	//Effects: Restituisce il numero di archi presenti in this
        return archi.size();
    }


    public boolean isVisited() {
    	//Effects: Restituisce true se this è stato visitato, false altrimenti
    	return this.colore.equalsIgnoreCase("grigio");
    }
    
    public Node<E> getPadre() {
    	//Restituisce il padre di this
        return padre;
    }
    
   

    public void setVisited(String colore) {
    	//Modify: This;
    	//Effects: Cambia il valore di colore;
        this.colore = colore;
    }

    public void setPadre(Node<E> padre) {
    	//Modify: This;
    	//Effects: Setto il padre di this con il nodo passato per parametro.
        this.padre = padre;
    }

	@Override
	public E getVertice() {
		//Restituisce il vertice di this;
		return vertice;
	}

	
	
}