package principal;

import implementacion.ColaPU;
import interfaz.ColaTDA;
import implementacion.pilaTF;
import interfaz.pilaTDA;

public class princ {

	//metodo para mostrar una cola
	public static void mostrarCola(ColaTDA cola) {
		int valor;
		while(!cola.ColaVacia()) {
			valor = cola.Primero();
			System.out.println("Valor: " + valor);
			cola.Desacolar();
		}
	}
	
	//metodo para mostrar cola sin perderla
	public static void mostrarColaClean(ColaTDA cola) {
		ColaTDA copia = copiarCola(cola);
		int valor;
		while(!copia.ColaVacia()) {
			valor = copia.Primero();
			System.out.println("Valor: " + valor);
			copia.Desacolar();
		}
	}
	
	//metodo para copiar una cola
	public static ColaTDA copiarCola(ColaTDA cola) {
		ColaTDA colaCopia = new ColaPU();
		ColaTDA aux = new ColaPU();
		colaCopia.InicializarCola();
		aux.InicializarCola();
		
		int valor;
		
		while(!cola.ColaVacia()) {
			valor = cola.Primero();
			cola.Desacolar();
			aux.Acolar(valor);
		}
		
		while(!aux.ColaVacia()) {
			valor = aux.Primero();
			aux.Desacolar();
			cola.Acolar(valor);
			colaCopia.Acolar(valor);
		}
		
		return colaCopia;
	}
	
	//metodo para invertir una cola, usando una pila
	public static ColaTDA invertirCola(ColaTDA cola) {
		ColaTDA copia = copiarCola(cola);
		ColaTDA colaInvertida = new ColaPU();
		colaInvertida.InicializarCola();
		
		pilaTDA pila = new pilaTF();
		pila.InicializarPila();
		
		int valor;
		
		while(!copia.ColaVacia()) {
			valor = copia.Primero();
			copia.Desacolar();
			pila.Apilar(valor);
		}
		
		while(!pila.PilaVacia()) {
			valor = pila.Tope();
			pila.Desapilar();
			colaInvertida.Acolar(valor);
		}
		
		return colaInvertida;
	}
	
	//metodo para invertir una cola, sin pila aux
	public static ColaTDA invertirColaPro(ColaTDA cola) {
		ColaTDA colaInvertida = new ColaPU();
		colaInvertida.InicializarCola();
		ColaTDA aux = copiarCola(cola);
 		
		int cant = 0;
		int valor;
		int [] copia = new int [20];
		
		
		while(!aux.ColaVacia()) {
			copia[cant] = aux.Primero();
			aux.Desacolar();
			cant ++;
			
		}
		
		for(int i = cant -1; i >=0; i --) {
			valor = copia[i];
			colaInvertida.Acolar(valor); 	
		}
		
		return colaInvertida;
	}
	
	//metodo para ver si el final de cola C1 es igual al final de la cola C2
	public static boolean esFinalIgual(ColaTDA C1, ColaTDA C2) {
		ColaTDA copia1 = copiarCola(C1);
		ColaTDA aux1 = copiarCola(C1);
		ColaTDA copia2 = copiarCola(C2);
		
		int valor1 = 0;
		int valor2 = 1;
		int cant = 0;
		
		while(!aux1.ColaVacia()) {
			cant++;
			aux1.Desacolar();
		}
		
		while(!copia1.ColaVacia()) {
			valor1 = copia1.Primero();
			copia1.Desacolar();
		}
		while(!copia2.ColaVacia()) {
			valor2 = copia2.Primero();
			copia2.Desacolar();
		}
		return(valor1 == valor2);
	}
	
	//metodo para ver si una cola es capicua o no, usando pila aux
	public static boolean esCapicua(ColaTDA cola) {
		ColaTDA copia = copiarCola(cola);
		ColaTDA aux = copiarCola(cola);
		
		pilaTDA pila = new pilaTF();
		pila.InicializarPila();
		
		int valor;

		while(!copia.ColaVacia()) {
			valor = copia.Primero();
			copia.Desacolar();
			pila.Apilar(valor);
		}
		
		while(!aux.ColaVacia() && !pila.PilaVacia()) {
	        if(aux.Primero() != pila.Tope()) {
	            return false; 
	        }
	        
	        aux.Desacolar();
	        pila.Desapilar();
	    }
	    
	    return true;
		
	}
	
	
	
	//metodo para ver si una cola es cpaicua o no, sin usar una pila aux
	public static boolean esCapicuaAtado(ColaTDA cola) {
		ColaTDA copiaCola = copiarCola(cola);
		ColaTDA aux = copiarCola(cola); //aux para obtener cant de elementos
		
		int cant = 0;
		int [] copia = new int [20];
		
		while(!aux.ColaVacia()) {
			copia[cant] = aux.Primero();
			aux.Desacolar();
			cant++;
		}
		
		for(int i = cant -1; i >= 0; i--) {
			if(copia[i] != copiaCola.Primero())
				return false;
			copiaCola.Desacolar();
		}
		
		return true;
		
	}
	
	//metodo para ver si la inversa de una cola es igual a otra cola2 - con pila
	public static boolean esInversaA(ColaTDA C1, ColaTDA C2) {
		ColaTDA copia1 = copiarCola(C1);
		ColaTDA copia2 = copiarCola(C2);
		
		ColaTDA copia1Invertida = invertirCola(copia1);
		
		while(!copia1Invertida.ColaVacia() && !copia2.ColaVacia()) {
			if(copia1Invertida.Primero() != copia2.Primero())
				return false;
			copia1Invertida.Desacolar();
			copia2.Desacolar();
		}
		// si alguna de las dos todaiva tiene algun elemento, se anula la comparacion
	    if (!copia1Invertida.ColaVacia() || !copia2.ColaVacia()) 
	        return false;
		
		return true;
		
	}
	
	
	public static void main(String[] args) {
	ColaTDA cola = new ColaPU();
	cola.InicializarCola();
	
	cola.Acolar(1);
	cola.Acolar(2);
	cola.Acolar(3);	
	cola.Acolar(4);
	cola.Acolar(5);
	System.out.println("Cola 1: ");
	mostrarColaClean(cola);
	System.out.println();
	System.out.println("Cola 2 [COPIA]: ");
	ColaTDA colaCopia = copiarCola(cola);
	mostrarColaClean(colaCopia);
	System.out.println();
	System.out.println("Cola Invertida:");
	ColaTDA colaInvertida = invertirCola(cola);
	mostrarColaClean(colaInvertida);
	System.out.println();
	System.out.println("Cola Invertida sin pilas:");
	ColaTDA colaInvertida2 = invertirColaPro(cola);
	mostrarColaClean(colaInvertida2);
	System.out.println();
	System.out.println("validamos si el final de cola 1 es igual al final de la cola invertida");
	System.out.println("deberia ser FALSE!");
	System.out.println("<--- Resultado ---> " +(esFinalIgual(cola,colaInvertida)));
	System.out.println();
	
	
	System.out.println("validamos si el final de cola 1 es igual al final de la cola 2");
	System.out.println("deberia ser TRUE!");
	System.out.println("<--- Resultado ---> " +(esFinalIgual(cola,colaCopia)));
	System.out.println();
	System.out.println("es la cola original capicua? " + (esCapicua(cola)));
	System.out.println("es la cola original capicua? [con metodo 2] " + (esCapicua(cola)));
	
	System.out.println();
	System.out.println("¡¡¡ se creo una nueva cola capicua !!!" );
	ColaTDA colaCapicua = new ColaPU();
	colaCapicua.InicializarCola();
	colaCapicua.Acolar(1);
	colaCapicua.Acolar(2);
	colaCapicua.Acolar(3);
	colaCapicua.Acolar(2);
	colaCapicua.Acolar(1);
	System.out.println();
	mostrarColaClean(colaCapicua);
	System.out.println("es capicua? " + esCapicua(colaCapicua) );
	System.out.println("es capicua? [con metodo 2] " + esCapicuaAtado(colaCapicua) );
	
	
	System.out.println();
	System.out.println("es la inversa de cola 1 igual a cola 2? [RTA: NO!] - Result: " + esInversaA(cola,colaCopia));
	System.out.println("es la inversa de cola 1 igual a la cola invertida? [RTA: Si!] - Result: " + esInversaA(cola,colaInvertida));
	}
}
