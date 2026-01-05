package implementacion;

import interfaz.pilaTDA;

public class pilaTF implements pilaTDA {
	int[] array;		// Aquí se guardan los números de la pila. El tope al final
	int cant;		// Cantidad de elementos en la pila
	
	@Override
	public void InicializarPila() {
		array = new int[20];
		cant = 0;
	}
	
	@Override
	public void Apilar(int x) {
		array[cant] = x;
		cant++;
	}
	
	@Override
	public void Desapilar() {
		cant--;
	}
	
	@Override
	public int Tope() {
		return array[cant-1];
	}
	
	@Override
	public boolean PilaVacia() {
		return (cant == 0);
	}
}