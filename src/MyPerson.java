
public class MyPerson implements Person {
	//Overview: MyPerson è un tipo di dato modificabile organizzato per gestire una persona tramite nome e cognome
	//INV: { nome!=null && cognome!=null}
	//AF: {<nome,cognome> && nome e cognome sono entrambi di tipo String }

	String nome;
	String cognome;
	public MyPerson(){};
	public MyPerson(String nome, String cognome){
		super();
		this.nome=nome;
		this.cognome=cognome;
	}
	public String getInformation() {
		//Modify: This;
		//Effects: Restituisce il nome e cognome di this, e lo rende inacessibile.
		String s = new String();
		s = " "+ nome.toUpperCase()+ " "+ cognome.toUpperCase();
		return s;
	}
	public void setNome(String nome) {
		//Modify: This;
		//Effects: Se nome==null sollevo NullPointerException (Unchecked)
		//Altrimenti modifico il nome di this con il parametro passato.
		if(nome==null) throw new NullPointerException();
		this.nome=nome;
		
	}
	public void setCognome(String cognome) {
		//Modify: This;
		//Effects: Se cognome==null sollevo NullPointerException (Unchecked)
		//Altrimenti modifico il cognome di this con il parametro passato.
		if(cognome==null) throw new NullPointerException();
		this.cognome=cognome;
	}

	
	

}
