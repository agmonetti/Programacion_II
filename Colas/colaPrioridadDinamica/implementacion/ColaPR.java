package implementacion;

import interfaz.ColaPrioridadTDA;

public class ColaPR implements ColaPrioridadTDA {
	
	public Nodo mayorPrioridad;
	
	
	@Override
	public void InicializarCola() {
		mayorPrioridad = null;

	}

	@Override
	public void AcolarPrioridad(int x, int prioridad) {
		//se crea el nodo que se quiere agregar
		Nodo nuevo = new Nodo();
		nuevo.valor = x;
		nuevo.prioridad = prioridad;
		
		//Ahora vemos si la cola esta vacia o es mas prioritario que el primero, se agrega al principio
		if(mayorPrioridad == null || mayorPrioridad.prioridad < prioridad) {
			nuevo.sig = mayorPrioridad;
			mayorPrioridad = nuevo;
		}
		
		//en este caso, sabemos que la cola no esta vacia
		else {
			//viajero
			Nodo viajero = mayorPrioridad;
			
			while(viajero.sig != null && viajero.sig.prioridad >= prioridad) {
				viajero = viajero.sig;
			}
			
			// aca ya encontramos la posicion en la que debe ir el nodo nuevo
			nuevo.sig = viajero.sig;
			viajero.sig = nuevo;
		}
		
		
	}

	@Override
	public void Desacolar() {
		mayorPrioridad = mayorPrioridad.sig;

	}

	@Override
	public int Primero() {
		return mayorPrioridad.valor;
	}

	@Override
	public int Prioridad() {
		return mayorPrioridad.prioridad;
	}

	@Override
	public boolean ColaVacia() {
		return (mayorPrioridad == null);
	}

}
