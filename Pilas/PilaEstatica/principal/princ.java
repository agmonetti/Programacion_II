package principal;

import implementacion.pilaTF; 
import interfaz.pilaTDA;


public class princ {
	
	public static void mostrarPila(pilaTDA pila)  {
		int valor;
		while(!pila.PilaVacia()) {
			valor = pila.Tope();
			System.out.println("valor: " + valor);
			pila.Desapilar();
		}
		
	}

	public static void main(String[] args) {
		//hola
		pilaTDA pila = new pilaTF();
		pila.InicializarPila();
		pila.Apilar(1);
		pila.Apilar(2);
		pila.Apilar(3);
		pila.Apilar(4);
		
		mostrarPila(pila);
	}

}
