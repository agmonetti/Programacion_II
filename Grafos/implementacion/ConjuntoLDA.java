package implementacion;

import interfaz.ConjuntoTDAD;

public class ConjuntoLDA implements ConjuntoTDAD {
	
	class Nodo{
		int valor;
		Nodo sig;
	}

	Nodo primero;
	
	
	@Override
	public void InicializarConjunto() {
		primero = null;
	}

	@Override
	public void Agregar(int x) {
		if(!Pertenece(x)) {
			Nodo nuevo = new Nodo();
			nuevo.valor = x;
			//ahora el nuevo apunta al primero
			nuevo.sig = primero;
			//el nuevo pasa a ser el primero
			primero = nuevo;
		}
	}

	@Override
	public void Sacar(int x) {
		//no puede estar vacio el conjunto
		if(primero != null) {
			
			//caso 1: El numero a sacar esta en el primer nodo
			if(primero.valor == x)
				primero= primero.sig;
			
			//caso 2: debo recorrer el conjunto
			else {
				Nodo viajero = primero;
				while(viajero.sig != null && viajero.sig.valor != x) 
						viajero= viajero.sig;
				
				//si llego aca es porque o encontre el valor, o recorri todo el conjunto y el valor no existia
				//entonces pregunto, ¿es el nodo sig nulo? -> si no, es porque el .sig tiene el valor a sacar -> salteo la posicion
				if(viajero.sig != null) {
					viajero.sig = viajero.sig.sig;
				}
			}
			
		}
		
	}

	@Override
	public int Elegir() {
		return primero.valor;
	}

	@Override
	public boolean Pertenece(int x) {

		Nodo viajero = primero;
		
		while(viajero != null) {
			
			if(viajero.valor == x)
				return true;
			
			//si no es el valor, sigo avanzando
			viajero = viajero.sig;
		}
		//si sali del while es porque no estaba dentro.
		return false;
		
	}

	@Override
	public boolean ConjuntoVacio() {
		return (primero == null);
	}

}
