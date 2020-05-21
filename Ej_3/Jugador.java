package Ej_3;

import java.util.ArrayList;

public class Jugador implements Comparable<Jugador>{
	
	//Atributos
	private String color_ficha;
	private String nombre;
	private int dinero;
	private int orden;
	private int posicion_tablero;
	private ArrayList<Casilla> propiedades;
	
	//Constructor
	public Jugador(String color_ficha,String nombre,int dinero) {
		
		this.color_ficha=color_ficha;
		this.nombre=nombre;
		this.dinero=dinero;
		propiedades=new ArrayList<Casilla>();
		orden=(int)(Math.random()*6+1);
		posicion_tablero=0;
		
	}

	//Getters y setters
	
	public String getColor_ficha() {
		return color_ficha;
	}

	public void setColor_ficha(String color_ficha) {
		this.color_ficha = color_ficha;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDinero() {
		return dinero;
	}

	public void setDinero(int dinero) {
		this.dinero = dinero;
	}

	public ArrayList<Casilla> getPropiedades() {
		return propiedades;
	}

	public void setPropiedades(ArrayList<Casilla> propiedades) {
		this.propiedades = propiedades;
	}
	
	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public int getPosicion_tablero() {
		return posicion_tablero;
	}

	public void setPosicion_tablero(int posicion_tablero) {
		this.posicion_tablero = posicion_tablero;
	}

	//Lanza una excepción si el precio de compra supera al dinero actual. Añadir en el main un try catch y un if si el jugador ya tiene 20 propiedades
	//Realiza una compra de la casilla que se le introduzca, marcándola como comprada y estableciéndole un dueño. Lanza una excepción si se ha alcanzado el límite de propiedades o si no se puede comprar.
	public void comprarCasilla(Casilla c) throws Exception {
		
		if(propiedades.size()>=20) {
			
			throw new Exception("Límite de propiedades alcanzado.");
			
		}else {
			
			if(c.getPrecio_compra()>dinero) {
				
				throw new Exception("No tienes suficiente dinero para comprar esta casilla");
				
			}else {
				
				dinero=dinero-c.getPrecio_compra();
				propiedades.add(c);
				c.setComprada(true);
				c.setDueño(this);
				
			}
			
		}
		
	}
	
	//Le resta el dinero del alquiler al jugador y se lo entrega a otro que se ha introducido por parámetro. Lanza una excepción si no tiene dinero para pagar
	public void pagarAlquiler(Casilla c, Jugador j) throws Exception{
		
		if(c.getPrecio_alquiler()>dinero) {
			
			throw new Exception("No tienes dinero para pagar el alquiler");
			
		}else {
			
			j.setDinero(j.getDinero()+c.getPrecio_alquiler());
			dinero-=c.getPrecio_alquiler();
			
		}
		
	}
	
	//Vende una propiedad a un jugador, sumándose a sí mismo el precio de compra 
	//y restándoselo al del jugador, quita tambíen a este objeto del arraylist de este objeto y añadiéndoselo al del parámetro.
	//Lanza una excepción si no es el dueño de esa casilla o si el jugador eleccionado no tiene suficiente dinero
	public void venderPropiedad(Casilla c, Jugador j) throws Exception{
		
		if(propiedades.contains(c)==false) {
			
			throw new Exception("No eres el dueño de la casilla introducida");
			
		}else {
			
			if(j.getDinero()<c.getPrecio_compra()) {
				
				throw new Exception("El jugador seleccionado no tiene suficiente dinero");
				
			}else {
				
				propiedades.remove(c);
				j.setDinero(j.getDinero()-c.getPrecio_compra());
				dinero+=c.getPrecio_compra();
				c.setDueño(j);
				j.getPropiedades().add(c);
				
			}
			
		}
		
	}
	
	//Se resta el dinero de la multa o lanza una excepción si no se tiene suficiente dinero
	public void pagarMulta() throws Exception{
		
		if(dinero>=250) {
			
			dinero-=250;
			
		}else {
			
			throw new Exception("No tienes suficiente dinero para pagar la multa.");
			
		}
		
	}
	
	//Borra al jugador del arraylist del main y libera todas sus propiedades
	public void perder(ArrayList<Jugador> jugadores) {
		
		for(Casilla c: propiedades) {
			
			c.setComprada(false);
			propiedades.remove(c);
			c.setDueño(null);
			
		}
		
		jugadores.remove(this);
		
	}

	//Compare to para ordenar
	@Override
	public int compareTo(Jugador e) {
		
		if(e.getOrden()>orden){
            return -1;
        }else if(e.getOrden()>orden){
            return 0;
        }else{
            return 1;
        }
	}

	//To string
	@Override
	public String toString() {
		return "Jugador [color_ficha=" + color_ficha + ", nombre=" + nombre + ", dinero=" + dinero + ", orden=" + orden
				+ ", posicion_tablero=" + posicion_tablero + ", propiedades=" + propiedades + "]";
	}
	
	

}
