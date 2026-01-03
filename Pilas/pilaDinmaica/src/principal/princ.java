package principal;

import implementacion.pilaTF; 
import interfaz.pilaTDA;


public class princ {

	//metodo para copiar la pila tal cual esta
	public static pilaTDA copiarPila(pilaTDA pila) {
		pilaTDA pilaAux = new pilaTF();
		pilaTDA pilaCopia = new pilaTF();
		
		pilaAux.InicializarPila();
		pilaCopia.InicializarPila();
		
		int x;
		
		while(!pila.PilaVacia()) {
			x = pila.Tope();
			pilaAux.Apilar(x);
			pila.Desapilar();
		}
		
		while(!pilaAux.PilaVacia()) {
			x = pilaAux.Tope();
			pilaCopia.Apilar(x);
			pila.Apilar(x);       //TENGO QUE APILAR DE NUEVO A LA ORIGINAL PORQUE SINO ROMPO EL PARAMETRO QUE LE PASE
			pilaAux.Desapilar();
		}
		
		
		return pilaCopia;
		
		
	}
	
	//metodo para mostrar una pila
	public static void mostrarPila(pilaTDA pila) {
		int valor;
		while(!pila.PilaVacia()) {
			valor = pila.Tope();
			System.out.println("valor: " + valor);
			pila.Desapilar();
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
