import java.util.*;
import java.util.Map.Entry;

public class MyGraph<E> extends MyPerson implements Graph<E>{
	//Overview: MyGraph<E> è una collezione di oggetti omogenei	generici di	tipo E organizzati come un grafo.	
	//INV: { Per ogni coppia presente all'interno di list i valori sono diversi da null e prese due coppie qualsiasi
	// queste sono diverse tra di loro.
	
	//}
	//AF: { <vertice"i",list.get(i)> | 0<=i<list.size(); &&
	//		<vertice"j",list.get(j)> | 0<=j<list.size(); &&
	// Per ogni i!=j le coppie sono differenti.
	//}
	
	private Map<E, MyNode<E>> list;
	
    public MyGraph() {
    	super();
        list = new HashMap<E, MyNode<E>>();
    }

    
    public void aggiungiVertice(E vertice) throws AlreadyPresentException {
    	//Modify: This;
    	//Effects:  vertice==NULL sollevo NullPointerException (Unchecked) 
    	//Se vertice è già presente sollevo AlreadyPresentException (Checked)
    	//Altrimento lo aggiungo alla lista "list"
        if( vertice==null) throw new NullPointerException();
        if((isInVertice(vertice))) throw new AlreadyPresentException();
        MyNode<E> tmp = new MyNode<E>(vertice);
         list.put(vertice, tmp);
        
    }

  

    
    public void aggiungiArco(E vertice1, E vertice2) throws NotPresentException{
    	//Effects:  vertice1 || vertice2==NULL sollevo NullPointerException (Unchecked)
    	//Se vertice è già presente sollevo AlreadyPresentException (Checked)
    	//Altrimento lo aggiungo alla lista "list"
    	if( vertice1==null || vertice2==null) throw new NullPointerException();
        if (!isInVertice(vertice1) || !isInVertice(vertice2)) throw new NotPresentException();
        Node<E> nodo1 = getNode(vertice1);
        Node<E> nodo2 = getNode(vertice2);
        try {
			nodo1.aggiungiArco(nodo2);
		} catch (AlreadyPresentException e) {
			e.printStackTrace();
		}
    }

    
    public void rimuoviVertice(E vertice) throws NotPresentException {
    	//Modify: This;
    	//Effects:  vertice == null sollevo NullPointerException (Unchecked)
    	//Se vertice non appartiene a list sollevo NotPresentException (Checked)
    	//Altrimenti rimuovo il vertice con i suoi relativi archi.
    	//Oss: Utilizzo list.values poichè ritorna una vista di tutti i valori presenti nella mappa.
    	if( vertice==null) throw new NullPointerException();
    	if(!(isInVertice(vertice))) throw new NotPresentException();
        Node<E> rimuovere = getNode(vertice);
         list.values().forEach(nodo -> {
			try {
				nodo.rimuoviArco(rimuovere);
			} catch (NotPresentException e) {
			}
		});
        list.remove(vertice);
        
    }
    
    public void rimuoviArco(E vertice1, E vertice2) throws NotPresentException {
    	//Effects:  vertice1 || vertice2 == null sollevo NullPointerException(Unchecked)
    	//Se vertice1 || vertice2 non appartengono a this sollevo NotPresentException (Checked) 
    	//Altrimenti rimuovo l'arco controllando che esista.
    	if( vertice1==null || vertice2==null) throw new NullPointerException();
    	if(!isInVertice(vertice1) || !isInVertice(vertice2)) throw new NotPresentException();
    	getNode(vertice1).rimuoviArco(getNode(vertice2));
        
    }

   
    public int numberOfVertici() {
    	//Restituisce il numero di vertici presenti in this
    	return list.size();
       
    }

   

    public boolean isInVertice(E vertice) {
    	//Effects: Se vertice == null sollevo NullPointerException (Unchecked)
    	//Se vertice non appartiene restituisco false; true altrimenti;
    		if(vertice==null) throw new NullPointerException();
        	return list.keySet().contains(vertice);
    }

    public boolean isThereArco(E vertice1, E vertice2) {
    	//Effects:  Se vertice1 || vertice2 == null sollevo NullPointerException (Unchecked)
    	//Se vertice1 || vertice2 non appartengono a list restituisco false
    	//Altrimenti controllo se c'è effettivamente l'arco tra vertice1 e vertice2.
    	if( vertice1==null || vertice2==null) throw new NullPointerException();
        if (!isInVertice(vertice1) || !isInVertice(vertice2)) {
            return false;
        }
        if(getNode(vertice1).isThereEdge(getNode(vertice2))!=null) return true;
        else return false;
    }
	
	
	public int diametro(){
		//Effects: Restituisce il più grande tra i cammini minimi presenti all'interno di this.
		int key=0;
		int diametro=0;
		for(Entry<E, MyNode<E>> vertice: list.entrySet()){
			for(Entry<E, MyNode<E>> vertice2: list.entrySet()){
				if(!(vertice.equals(vertice2))){
					key=camminoMinimo(vertice.getKey(),vertice2.getKey());
					if(key>diametro) diametro=key;
				}
			}
		}
		return diametro;
	}
	
	
	
	
	
	 public int camminoMinimo(E vertice1, E vertice2){
		 	//Effects: Se inizio || fine ==null sollevo NullPointerException (Unchecked)
		 	//Se inizio || vertice non appartengono a list restituisco 0;
		 	//Altrimenti restituisco il cammino minimo tra i due nodi.
	        if (!isInVertice(vertice1) || !isInVertice(vertice2)) {
	           return 0;
	        }
	        //Eseguo la BFS
	        BFS(vertice1);
	        
	        List<E> cammino = new ArrayList<>();
	        Node<E> end = getNode(vertice2);
	        //Il ciclo smette sse end è uguale a null (nessun cammino trovato) oppure siamo tornati al vertice1
	        while (end != null && end != getNode(vertice1)) {
	            cammino.add(end.getVertice());
	            end = end.getPadre();
	        }
	        //Se end è nullo nessun cammino è stato trovato.
	        if (end == null) {
	            return 0;
	        }
	        
	        return cammino.size();
	    }
		public int numberArchiOf(E vertice) throws NotPresentException {
			//Effects: Se vertice non è presente, sollevo NotPresentException (Checked)
			//Altrimenti restituisco il numero di archi uscenti di vertice;
			if(!(isInVertice(vertice))) throw new NotPresentException();
			return this.getNode(vertice).numberOfArchi();
		}
	 
	 	public void BFS(E vertice1) {
	        //Effects: Resetto i nodi con inizialize() ed
	 		//effettuo una BFS e ritorno alla chiamata camminoMinimo
	       //avendo così i nodi segnati dalla chiamata della BFS
	        inizialize();
	        
	        Queue<Node<E>> coda = new LinkedList<>();
	        Node<E> start = getNode(vertice1);
	        coda.add(start);

	        
				        while (!coda.isEmpty()) {
				            Node<E> unqueued = coda.remove();
				            unqueued.setVisited("grigio");
				            for(int i=0;i<unqueued.listOfArchi().size();i++){
								Node<E> successivo = unqueued.listOfArchi().get(i).destinazione();
							    		if(!successivo.isVisited()){
							    			successivo.setPadre(unqueued);
							    			coda.add(successivo);
							    		}
							}
				        }
	 	}
	        

	    private Node<E> getNode(E vertice) {
	    	//Effects: Restituisce il nodo di vertice;
	        return list.get(vertice);
	    }

	    public void inizialize() {
	    	//Modify: This;
	    	//Effects: setta il padre a null e setta la visita del nodo a "bianco" (mai toccato)
	    	//Poichè resetto dopo la BFS
	        list.keySet().forEach(key -> {
	            Node<E> nodo = getNode(key);
	            nodo.setPadre(null);
	            nodo.setVisited("bianco");
	        });
	 }
	    
	 public Vector<Node<E>> getArchiUscenti(E vertice) throws NotPresentException{
		 //Effects: Se vertice == null sollevo NullPointerException(Unchecked)
		 //Se vertice non è presente in list sollevo NotPresentException( Checked)
		 //Altrimenti restituisco la lista degli amici di vertice
		 if(vertice==null) throw new NullPointerException();
		 if(this.isInVertice(vertice)==false) throw new NotPresentException();
		 Vector<Node<E>> tmp = new Vector<Node<E>>();
		 for(int i=0;i<getNode(vertice).listOfArchi().size();i++){
			 tmp.add((Node<E>) getNode(vertice).listOfArchi().get(i).destinazione());
		 }
		 return new Vector<Node<E>>(tmp);
	 }


	@Override
	public Vector<Node<E>> getCommonArchiUscenti(E vertice1, E vertice2) throws NotPresentException {
		//Effects: Se vertice1 || vertice2 ==null sollevo NullPointerException (Unchecked)
		//Se vertice1 || vertice2 non sono presenti in this sollevo NotPresentException (Checked)
		//Se vertice1 == vertice2 sollevo IllegalArgumentException (Unchecked)
		//Altrimenti restituisco la lista degli amici in comune tra vertice1 e vertice2
		if(vertice1==null || vertice2==null) throw new NullPointerException();
		if(this.isInVertice(vertice1)==false || this.isInVertice(vertice2)==false) throw new NotPresentException();
		if(vertice1.equals(vertice2)) throw new IllegalArgumentException();
		List<MyEdge<E>> listatmp1 = getNode(vertice1).listOfArchi();
		List<MyEdge<E>> listatmp2 = getNode(vertice2).listOfArchi();
		Vector<Node<E>> tmp = new Vector<Node<E>>();
		for(int i=0;i<listatmp1.size();i++){
			for(int j=0;j<listatmp2.size();j++){
				if(listatmp1.get(i).destinazione().equals(listatmp2.get(j).destinazione())){
					tmp.add(listatmp1.get(i).destinazione());
				}
			}
		}
		return tmp;
	}


}






// Di seguito ci sono tutte le eccezioni checked Gestite all'interno del progetto!


@SuppressWarnings("serial")
class AlreadyPresentException extends Exception{
    public AlreadyPresentException(){
	super();
    }   
}

@SuppressWarnings("serial")
class NotPresentException extends Exception{
    public NotPresentException(){
	super();
    }   
}



	

