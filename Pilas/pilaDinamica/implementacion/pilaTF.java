package implementacion;

import interfaz.pilaTDA;

public class pilaTF implements pilaTDA {
	public Nodo topePila;		// Aquí se guardan los números de la pila. El tope al final
	
	@Override
	public void InicializarPila() {
		topePila = null;   //inicializamos la pila vacia
	}
	
	@Override
	public void Apilar(int num) {
		Nodo nuevo = new Nodo();   // creamos un nuevo nodo ya que estamos queriendo agregar otro valor a la pila
		nuevo.valor = num;      // asigna el valor al nuevo nodo
		nuevo.sig = topePila; // //  necesito enlazar el nuevo nodo a este nodo existente para que sea el nuevo último nodo de la lista
		topePila = nuevo; // actualiza el tope de la pila al nuevo nodo
	}
	
	@Override
	public void Desapilar() {
	    // Ajustamos el tope de la pila al siguiente nodo
	    //asi vamos a llegar al final
	    topePila = topePila.sig;
	}
	
	@Override
	public int Tope() {
		return topePila.valor;
	}
	
	@Override
	public boolean PilaVacia() {
		return (topePila == null);
	}
}

