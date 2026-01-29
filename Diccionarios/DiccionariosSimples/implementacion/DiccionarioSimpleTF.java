package implementacion;

import interfaz.ConjuntoTDAD;
import interfaz.DiccionarioSimpleTDA;


public class DiccionarioSimpleTF implements DiccionarioSimpleTDA {

	NodoDiccionario origen;
	
	@Override
	public void InicializarDiccionario() {
		origen = null;
	}

	@Override
	public void Agregar(int clave, int x) {
	    // usamos un nodo auxiliar para buscar la clave
		NodoDiccionario aux = Clave2NodoClave(clave);

	    // 2. Si nc es null, la clave es NUEVA
	    if (aux == null) {
	        aux = new NodoDiccionario();      
	        aux.clave = clave;          
	        aux.sigClave = origen;      // El nuevo apunta al antiguo primero
	        origen = aux;               // El nuevo ahora es el primero
	    }

	    // 3.se ejecuta siempre
	    // Si era nuevo, se guarda por primera vez.
	    // Si ya existía, se sobrescribe
	    aux.valor = x;
	}
	
	private NodoDiccionario Clave2NodoClave(int clave) {
		NodoDiccionario aux = origen;
	    // Mientras no se acabe la lista Y la clave actual no sea la que busco
	    while (aux != null && aux.clave != clave) {
	        aux = aux.sigClave; // Avanzo
	    }
	    return aux; // Devuelve el nodo encontrado o null
	}

	@Override
	public void Eliminar(int clave) {
		//Caso 1: La clave a eliminar esta en la primera posicion
		if(origen != null) {
			if(origen.clave == clave) {
				origen = origen.sigClave;
			}
			
			// Caso 2: La clave a eliminar no esta en la primera posicion
			else {
				NodoDiccionario viajero = origen;
				while(viajero.sigClave != null && viajero.sigClave.clave != clave)
					viajero = viajero.sigClave;
				
				//si la sig posicion del viajero no es nulo, encontro la clave a eliminar
				if(viajero.sigClave!=null)
					viajero.sigClave = viajero.sigClave.sigClave;
			}
		}
		
	}

	@Override
	public int Recuperar(int clave) {
		NodoDiccionario aux = Clave2NodoClave(clave);
		return(aux.valor);
			
		
	}

	@Override
	public ConjuntoTDAD Claves() {
		ConjuntoTDAD claves = new ConjuntoLDA();
		claves.InicializarConjunto();
		NodoDiccionario viajero = origen;
		
		while(viajero!= null) {
			claves.Agregar(viajero.clave);
			viajero = viajero.sigClave;
		}
		
		return claves;
	}

}
