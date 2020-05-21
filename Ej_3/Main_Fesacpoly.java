package Ej_3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main_Fesacpoly {

	public static void main(String[] args) {
		
		//Variables necesarias para ejecutar el main
		Scanner teclado=new Scanner(System.in);
		Scanner teclado_texto=new Scanner(System.in);
		ArrayList<Casilla> tablero=new ArrayList<Casilla>();
		ArrayList<Jugador> jugadores=new ArrayList<Jugador>();
		int jugadores_iniciales=0;
		String color_ficha;
		String nombre;
		int dado;
		int opcion;
		int opcion2;
		boolean exito = false;
		String nombre_jugador;
		String nombre_casilla;
		Casilla venta = null;
		boolean casillas_especiales = false;
		
		//Inserci�n de objetos en el arrayList de tablero
		tablero.add(new Casilla(0,"SALIDA",0,0));
		tablero.add(new Casilla(1,"MELILLA",100,35));
		tablero.add(new Casilla(2,"CEUTA",100,50));
		tablero.add(new Casilla(3,"MURCIA",120,50));
		tablero.add(new Casilla(4,"ALICANTE",130,55));
		tablero.add(new Casilla(5,"C�RCEL",0,0));
		tablero.add(new Casilla(6,"VALENCIA",200,90));
		tablero.add(new Casilla(7,"CASTELL�N",180,80));
		tablero.add(new Casilla(8,"ZARAGOZA",170,70));
		tablero.add(new Casilla(9,"BARCELONA",240,140));
		tablero.add(new Casilla(10,"IR A SALIDA",0,0));
		tablero.add(new Casilla(11,"HUESCA",120,50));
		tablero.add(new Casilla(12,"BILBAO",200,75));
		tablero.add(new Casilla(13,"SANTANDER",150,60));
		tablero.add(new Casilla(14,"OVIEDO",160,60));
		tablero.add(new Casilla(15,"IR A C�RCEL",0,0));
		tablero.add(new Casilla(16,"LA CORU�A",160,70));
		tablero.add(new Casilla(17,"LE�N",150,0));
		tablero.add(new Casilla(18,"M�LAGA",200,80));
		tablero.add(new Casilla(19,"SEVILLA",240,140));
		
		//Inserci�n de jugadores
		do {
			System.out.println("�Cu�ntas personas van a jugar? (No m�s de 4 ni menos de 3)");
			jugadores_iniciales=teclado.nextInt();
		}while(jugadores_iniciales>4 || jugadores_iniciales<3);
		
		for(int x=0;x<jugadores_iniciales;x++) {
			
			System.out.println("\n�Qu� color de ficha quieres?");
			color_ficha=teclado_texto.nextLine();
			System.out.println("\n�C�mo te llamas?");
			nombre=teclado_texto.nextLine();
			jugadores.add(new Jugador(color_ficha,nombre,2100));
			
		}
		
		//Ordenaci�n
		Collections.sort(jugadores);
		
		//Bucle que se repetir� hasta que quede un s�lo jugador
		do {
			
			//Bucle que recorre jugadores
			for(Jugador j: jugadores) {
				
				//Dado aleatorio que mover� a los jugadores 
				dado=(int)(Math.random()*6+1);
				//Si la suma del dado y la posici�n es superior a 20, la nueva posici�n ser� la resta de la posici�n menos 20, para que vuelva a empezar el tablero
				if(j.getPosicion_tablero()+dado>=19) {
					
					j.setPosicion_tablero(j.getPosicion_tablero()-20);
					System.out.println("�Has pasado por la salida! Cobras 350�");
					j.setDinero(j.getDinero()+350);
					
				//Si no, todo ocurre con normalidad
				}else {
					
					j.setPosicion_tablero(j.getPosicion_tablero()+dado);
					
				}
				
				//Compensaci�n por pasar por la salida
				if(j.getPosicion_tablero()==0) {
					System.out.println("�Has pasado por la salida! Cobras 350�");
					j.setDinero(j.getDinero()+350);
					casillas_especiales=true;
					
				}else {
					
					//Te pasa a la casilla de salida directamente
					if(j.getPosicion_tablero()==10) {
						
						System.out.println("La persona que te gusta te manda un mensaje diciendo que te espera en la salida, �as� que te vas corriendo a la salida!");
						j.setPosicion_tablero(0);
						opcion=3;
						casillas_especiales=true;
						
					}else {
						
						//C�rcel. Se paga la multa tratando la excepci�n que lanza dicho m�todo ofreciendo la oportunidad de vender una propiedad o rendirse y perder
						if(j.getPosicion_tablero()==5) {
							
							System.out.println("Oh, no...Has ca�do en la c�rcel...");
							casillas_especiales=true;
							
							try {
								j.pagarMulta();
							} catch (Exception e) {
								System.out.println(e.getMessage());
								//Este bucle se repetir� hasta que el jugador tenga dinero para pagar la multa o se rinda
								do {
									
										System.out.println("�Qu� decides hacer?\n1 -> Vender una propiedad\n2 -> Aceptar la derrota\n>");
										opcion2=teclado.nextInt();
										switch(opcion2) {
										
											default:
												System.out.println("�Qu� propiedad quieres vender?\n");
												for(Casilla propiedad: j.getPropiedades()) {
													
													System.out.println("Nombre de la casilla: "+propiedad.getNombre()+", Precio de venta: "+propiedad.getPrecio_compra());
													
												}
												System.out.println("\n");
												nombre_casilla=teclado_texto.nextLine();
												for(Casilla casilla: tablero) {
													
													if(casilla.getNombre().equalsIgnoreCase(nombre_casilla)) {
														
														venta=casilla;
														
													}
													
												}
												for(Jugador comprador: jugadores) {
													
													if(comprador.getDinero()>=venta.getPrecio_compra()) {
														
														if(comprador.getNombre()!=j.getNombre()) {
															
															System.out.println("Posible comprador: "+comprador.getNombre());
															
														}
														
													}
													
												}
												System.out.println("\n�A qui�n se la quieres vender?");
												nombre_jugador=teclado_texto.nextLine();
												for(Jugador comprador: jugadores) {
													
													if(comprador.getNombre().equalsIgnoreCase(nombre_jugador)) {
														
														try {
															j.venderPropiedad(venta, comprador);
															exito=true;
														} catch (Exception e2) {
															System.out.println(e2.getMessage());
															exito=false;
														}
														
													}
													
												}
												break;
											case 2:
													j.perder(jugadores);
												break;
										
										}
										
								}while(j.getDinero()<250);
								
							}
							
							System.out.println("Has pagado la multa");
							
						}else {
							
							//Manda de cabeza a la c�rcel
							if(j.getPosicion_tablero()==15) {
								
								System.out.println("�Eh, oiga! Usted no me inspira confianza. �De cabeza a la c�rcel!");
								j.setPosicion_tablero(5);
								casillas_especiales=true;
								
							}
							
						}
						
					}
					
				}
				
				//Muestra el estado actual del jugador
				System.out.println("Es tu turno, "+j.getNombre()+"\n");
				System.out.println("Los dados han sacado un "+dado+"\n");
				System.out.println(tablero.get(j.getPosicion_tablero()).toString()+"\n");
				System.out.println("Tienes "+j.getDinero()+"�\n");
				
				//Trozo que se ejecuta si la casilla est� comprada (Pagar alquiler)
				if(tablero.get(j.getPosicion_tablero()).isComprada()==true) {
					
					//Se repetir� hasta que el jugador tenga suficiente dinero o se rinda
					do {
						
						//Se realiza el m�todo a la posici�n actual del tablero y a su due�o, atributo extra que he a�adido
						try {
							j.pagarAlquiler(tablero.get(j.getPosicion_tablero()), tablero.get(j.getPosicion_tablero()).getDue�o());
							System.out.println("Has pagado el alquiler");
						//Aqu� se trata la excepci�n que lanza el m�todo, que viene a ser la misma que la de la multa
						} catch (Exception e) {
							System.out.println(e.getMessage());
							System.out.println("�Qu� decides hacer?\n1 -> Vender una propiedad\n2 -> Aceptar la derrota\n>");
							opcion2=teclado.nextInt();
							switch(opcion2) {
							
								default:
									System.out.println("�Qu� propiedad quieres vender?\n");
									for(Casilla propiedad: j.getPropiedades()) {
										
										System.out.println("Nombre de la casilla: "+propiedad.getNombre()+", Precio de venta: "+propiedad.getPrecio_compra());
										
									}
									System.out.println("\n");
									nombre_casilla=teclado_texto.nextLine();
									for(Casilla casilla: tablero) {
										
										if(casilla.getNombre().equalsIgnoreCase(nombre_casilla)) {
											
											venta=casilla;
											
										}
										
									}
									for(Jugador comprador: jugadores) {
										
										if(comprador.getDinero()>=venta.getPrecio_compra()) {
											
											if(comprador.getNombre()!=j.getNombre()) {
												
												System.out.println("Posible comprador: "+comprador.getNombre());
												
											}
											
										}
										
									}
									System.out.println("\n�A qui�n se la quieres vender?");
									nombre_jugador=teclado_texto.nextLine();
									for(Jugador comprador: jugadores) {
										
										if(comprador.getNombre().equalsIgnoreCase(nombre_jugador)) {
											
											try {
												j.venderPropiedad(venta, comprador);
												exito=true;
											} catch (Exception e1) {
												System.out.println(e1.getMessage());
												exito=false;
											}
											
										}
										
									}
									break;
								case 2:
										j.perder(jugadores);
									break;
							
							}
							
						}
						
					}while(j.getDinero()<tablero.get(j.getPosicion_tablero()).getPrecio_alquiler());
					
				}
				
					//Aqu� empieza realmente el turno del jugador, que se repetir� hasta que se realice con �xito alguna acci�n
					//o se introduzca la opci�n de pasar el turno. Si el jugador ha ca�do en una casilla especial, pasar� el turno
					//autom�ticamente.
					do {
						
						if(casillas_especiales==true) {
							opcion=3;
							casillas_especiales=false;
						}else {
							System.out.println("�Qu� quieres hacer?\n1 -> Comprar propiedad\n2 -> Vender propiedad\n3 -> Pasar el turno\n>");
							opcion=teclado.nextInt();
						}
						//El jugador elige qu� va a hacer
						switch(opcion) {
						
							case 1:
									//Se hace la compra de una casilla, tratando tambi�n la excepci�n que lanza el m�todo
									try {
										j.comprarCasilla(tablero.get(j.getPosicion_tablero()));
										exito=true;
										System.out.println("Has comprado la casilla.");
									} catch (Exception e) {
										System.out.println(e.getMessage());
										exito=false;
									}
								break;
								
							//Se vende la propiedad elegida al jugador elegido tras haber mostrado todas las propiedades
							//obtenidas por pantalla y todos los jugadores que tienen el dinero suficiente para comprarla.
							//De momento se realiza la venta y ya est�, pero en un futuro cercano se dar� la opci�n de que el jugador
							//elegido rechace la venta
							case 2:
								System.out.println("�Qu� propiedad quieres vender?\n");
								for(Casilla propiedad: j.getPropiedades()) {
									
									System.out.println("Nombre de la casilla: "+propiedad.getNombre()+", Precio de venta: "+propiedad.getPrecio_compra());
									
								}
								System.out.println("\n");
								nombre_casilla=teclado_texto.nextLine();
								for(Casilla casilla: tablero) {
									
									if(casilla.getNombre().equalsIgnoreCase(nombre_casilla)) {
										
										venta=casilla;
										
									}
									
								}
								for(Jugador comprador: jugadores) {
									
									if(comprador.getDinero()>=venta.getPrecio_compra()) {
										
										if(comprador.getNombre()!=j.getNombre()) {
											
											System.out.println("Posible comprador: "+comprador.getNombre());
											
										}
										
									}
									
								}
								System.out.println("\n�A qui�n se la quieres vender?");
								nombre_jugador=teclado_texto.nextLine();
								for(Jugador e: jugadores) {
									
									if(e.getNombre().equalsIgnoreCase(nombre_jugador)) {
										
										//Se realiza el m�todo y se trata la excepci�n que lanza
										try {
											j.venderPropiedad(venta, e);
											exito=true;
										} catch (Exception e1) {
											System.out.println(e1.getMessage());
											exito=false;
										}
										
									}
									
								}
								System.out.println("Has vendido la casilla.");
								break;
							//No hace nada, as� que cuenta como saltar el turno
							case 3:
									exito=true;
								break;
						
						}
						}while(exito==false);
					
				}
				
				
		}while(jugadores.size()>1);
		
		//Una vez que quede s�lo un jugador, see declara como vencedor y se cierra el programa.
		for(Jugador ganador: jugadores) {
			
			System.out.println("�Has ganado, "+ganador.getNombre()+"!");
			
		}
		
		teclado.close();
		teclado_texto.close();
		
	}

}
