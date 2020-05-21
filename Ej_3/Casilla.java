package Ej_3;

public class Casilla {
	
	//Atributos
	private int indice;
	private String nombre;
	private boolean comprada;
	private int precio_compra;
	private int precio_alquiler;
	private Jugador due�o;
	
	//Constructor
	public Casilla(int indice,String nombre,int precio_compra,int precio_alquiler) {
		
		this.indice=indice;
		this.nombre=nombre;
		comprada=false;
		this.precio_compra=precio_compra;
		this.precio_alquiler=precio_alquiler;
		
	}

	//Getters y setters
	
	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isComprada() {
		return comprada;
	}

	public void setComprada(boolean comprada) {
		this.comprada = comprada;
	}

	public int getPrecio_compra() {
		return precio_compra;
	}

	public void setPrecio_compra(int precio_compra) {
		this.precio_compra = precio_compra;
	}

	public int getPrecio_alquiler() {
		return precio_alquiler;
	}

	public void setPrecio_alquiler(int precio_alquiler) {
		this.precio_alquiler = precio_alquiler;
	}
	
	public Jugador getDue�o() {
		return due�o;
	}

	public void setDue�o(Jugador due�o) {
		this.due�o = due�o;
	}

	//ToString bonico 
	
	public String toString() {
		
		String comprada2;
		int precio_actual;
		
		if(comprada==true) {
			
			precio_actual=precio_alquiler;
			comprada2="�Esta casilla ya tiene due�o!\nPrecio por caer aqu�: "+precio_actual;
			
		}else {
			
			precio_actual=precio_compra;
			comprada2="�Esta casilla est� libre!\nPrecio de compra: "+precio_actual;
			
			
		}
		
		return "Nombre de a casilla: "+nombre+"\nN�mero de la casilla: "+indice+"\n"+comprada2;
		
	}

}
