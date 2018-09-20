import java.util.*;

public class Run_Social {

	public static void main(String[] args) {
		//NON E' POSSIBILE LAVORARE IN NESSUN MODO CON OGGETTI NULL.
		//Per testare le eccezioni lavorare sull'oggetto f.
		//Creo il grafo delle persone con 4 persone.
		Graph<Person> p = new MyGraph<Person>();
		Person a = new MyPerson("Davide","Montagno");
		Person b = new MyPerson("Davide","Montagno");
		Person c = new MyPerson("Bill","Gates");
		Person d = new MyPerson("Mark","Zuckeberg");
		//Person e = null;
		Person f = new MyPerson("Salvatore", "Aranzulla");
		//Testo i set nella classe MyPerson
		System.out.println("Valori della persona" + a.getInformation() + " cambiati in");
		a.setNome("Franco");
		a.setCognome("Rossi");
		System.out.println(a.getInformation());
		//Aggiungo le persone al grafo, si nota che;
		//OSS: Aggiungendo l'oggetto e, sollevva NullPointerException (Unchecked) e l'esecuzione del programma termina.
		try {
			p.aggiungiVertice(a);
			p.aggiungiVertice(b);
			p.aggiungiVertice(c);
			p.aggiungiVertice(d);
		} catch (AlreadyPresentException e1) {
			e1.printStackTrace();
		}
		//Amici di a:b,c,d  --- Amici di b:nessuno --- Amici di c:b,d ---- Amici di d:nessuno
		try {
			p.aggiungiArco(a,b);
			p.aggiungiArco(a, c);
			p.aggiungiArco(a, d);
			p.aggiungiArco(c, b);
			p.aggiungiArco(c, d);
			p.aggiungiArco(d, a);
		} catch (NotPresentException | AlreadyPresentException e1) {
			e1.printStackTrace();
		}
		//Test delle persone inserite, e le relative "amicizie" (Archi). 
		//Test del get all'interno della classe MyPerson.
		try {
			System.out.println("Numero di persone presenti nella piattaforma: " + p.numberOfVertici());
			System.out.println("Amici di "+a.getInformation() +": " + p.numberArchiOf(a));
			System.out.println("Amici di "+b.getInformation() +": " + p.numberArchiOf(b));
			System.out.println("Amici di "+c.getInformation() +": " + p.numberArchiOf(c));
			System.out.println("Amici di "+d.getInformation() +": " + p.numberArchiOf(d));
		} catch (NotPresentException e1) {
			e1.printStackTrace();
		}
		//Controllo se una persona è presente nella piattaforma
		System.out.println("E' presente"+a.getInformation()+ " nella piattaforma? " + p.isInVertice(a));
		System.out.println("E' presente"+b.getInformation()+ " nella piattaforma? " + p.isInVertice(b));
		System.out.println("E' presente"+f.getInformation()+ " nella piattaforma? " + p.isInVertice(f));
		System.out.println("Amici di " + a.getInformation());
		Vector<Node<Person>> tmp = new Vector<Node<Person>>();
		//Vedo la lista degli amici di a;
		try {
			tmp = p.getArchiUscenti(a);
		} catch (NotPresentException e1) {
			e1.printStackTrace();
		}
		for(int i=0;i<tmp.size();i++){
			System.out.println(tmp.get(i).getVertice().getInformation());
			
		}
		//Vedo gli amici in comune tra a ed c.
		try {
			tmp = p.getCommonArchiUscenti(a, c);
		} catch (NotPresentException e1) {
			e1.printStackTrace();
		}
		System.out.println("Amici in comune tra: "+ a.getInformation() + " e"+ c.getInformation());
		for(int i=0;i<tmp.size();i++){
			System.out.println(tmp.get(i).getVertice().getInformation());
		}
		
		//Test sul diametro della rete e sui cammini minimi.
		System.out.println("Cammino minimo tra a ed b: " + p.camminoMinimo(a, b));
		System.out.println("Cammino minimo tra c ed a: " + p.camminoMinimo(c, a));
		System.out.println("Cammino minimo tra a ed f: " + p.camminoMinimo(f, a));
		System.out.println("Diametro della rete: " + p.diametro());
		
		
			//Rimuovo la persona b ... amico in comune tra a ed c.
			try {
					p.rimuoviVertice(b);
			} catch (NotPresentException e1) {
				e1.printStackTrace();
			}
			
			//Controllo di nuovo la lista degli amici in comune tra a ed c
			try {
				tmp = p.getCommonArchiUscenti(a, c);
			} catch (NotPresentException e1) {
				e1.printStackTrace();
			}
			System.out.println("Amici in comune tra: "+ a.getInformation() + " e"+ c.getInformation()+ " dopo la cancellazione dalla piattaforma di:" + b.getInformation());
			for(int i=0;i<tmp.size();i++){
				System.out.println(tmp.get(i).getVertice().getInformation());
			}
			//Controllo gli archi uscenti effettivi dopo la cancellazione di b
			try {
				System.out.println("Amici di "+a.getInformation() +": " + p.numberArchiOf(a));
				System.out.println("Amici di "+c.getInformation() +": " + p.numberArchiOf(c));
			} catch (NotPresentException e1) {
				e1.printStackTrace();
			}
			//Rimuovo d dalla lista degli amici di a
			try {
				p.rimuoviArco(a, d);
			} catch (NotPresentException e1) {
				e1.printStackTrace();
			}
			//Vedo la lista degli amici di a dopo la rimozione di un arco uscente;
			System.out.println("Lista degli amici di"+a.getInformation());
			try {
				tmp = p.getArchiUscenti(a);
			} catch (NotPresentException e1) {
				e1.printStackTrace();
			}
			for(int i=0;i<tmp.size();i++){
				System.out.println(tmp.get(i).getVertice().getInformation());
			}
	}

}
/*** La correttezza dell'implementazione è dimostrata attraverso i test fatti in precedenza*****/