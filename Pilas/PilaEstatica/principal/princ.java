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
	
	public static pilaTDA copiarPila(pilaTDA pilaOriginal) {
		pilaTDA pilaAux = new pilaTF();
		pilaTDA pilaCopia = new pilaTF();
		
		pilaAux.InicializarPila();
		pilaCopia.InicializarPila();
		
		int x;
		
		while(!pilaOriginal.PilaVacia()) {
			x = pilaOriginal.Tope();
			pilaAux.Apilar(x);
			pilaOriginal.Desapilar();
		}
		
		while(!pilaAux.PilaVacia()) {
			x = pilaAux.Tope();
			pilaCopia.Apilar(x);
			pilaOriginal.Apilar(x);       //TENGO QUE APILAR DE NUEVO A LA ORIGINAL PORQUE SINO ROMPO EL PARAMETRO QUE LE PASE
			pilaAux.Desapilar();
		}
		
		
		return pilaCopia;
		
		
		
	}

	public static void main(String[] args) {
		//hola
		pilaTDA pila = new pilaTF();
		pila.InicializarPila();
		pila.Apilar(1);
		pila.Apilar(2);
		pila.Apilar(3);
		pila.Apilar(4);
		System.out.println("Procedemos a copiar la pila");
		pilaTDA copiaPila = copiarPila(pila);
		//copio antes de mostrar, porque el metodo de mostrar la pila la borra.
		System.out.println("pila1: ");
		System.out.println();
		mostrarPila(pila);
		System.out.println();
		System.out.println("pila2: ");

		mostrarPila(copiaPila);
		
	}

}
