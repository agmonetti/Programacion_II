package implementacion;
 
import interfaz.ConjuntoTDAD;
import interfaz.DiccionarioMultipleTDA;
 
public class DicMultipleL implements DiccionarioMultipleTDA {
 
	class NodoClave {
		int clave;
		NodoValor valores;
		NodoClave sigClave;
	}
	class NodoValor {
		int valor;
		NodoValor sigValor;
	}
	//creamos un primer nodo clave, y a partir de este vamos a ejecutar todo
	NodoClave origen;   //puerta de entrada a todas las estructura
	
	@Override
	public void InicializarDiccionario() {
		origen = null;
 
	}
 

	
	@Override
	public void Agregar(int clave, int valor) {
	    NodoClave nc = Clave2NodoClave(clave);

	    // PASO 1: Garantizar que la clave exista
	    if (nc == null) {
	        nc = new NodoClave();
	        nc.clave = clave;
	        nc.sigClave = origen;
	        origen = nc;
	    }

	    // PASO 2: Agregar el valor (Se ejecuta SIEMPRE, sea nueva o vieja la clave)
	    NodoValor viajero = nc.valores;
	    
	    // Verificamos que el valor no exista ya en esa clave
	    while(viajero != null && viajero.valor != valor) {
	        viajero = viajero.sigValor;
	    }

	    // Si viajero es null, significa que recorrimos todo y no encontramos el valor
	    if (viajero == null) {
	        NodoValor nv = new NodoValor();
	        nv.valor = valor;
	        nv.sigValor = nc.valores; // El nuevo apunta al antiguo primero
	        nc.valores = nv;          // El nuevo pasa a ser el primero
	    }
	}
 
	@Override
	public void Eliminar(int clave) {
		// elimina una clave con todos sus valores
		//no puedo usar nodo2nodoclave porque me da el nodo parado encima
		// tengo que pararme atras para eliminar el siguiente
		//entonce pasa como la otra vez, no puedo eliminar el primero
		if(origen.clave == clave) 
			origen = origen.sigClave; //el nodo origen es el que le sigue
		else {
			NodoClave viajero = origen; //para recorrer los nodos claves
			while(viajero.sigClave != null && viajero.sigClave.clave != clave) 
			//sigClave.clave(para evitar el error si el sig.clave es null) miro la clave y
			// tiene que ser distinto que la clave
				viajero = viajero.sigClave;
			//pregunto si el sig.clave no es null (significa que encontre al que quiero eliminar) entonces lo elimino
			if (viajero.sigClave != null)
				viajero.sigClave = viajero.sigClave.sigClave; 
				//entonces el siguiente va a ser el siguiente del que eliminamos
		}

 
	}
 
	@Override
	public void EliminarValor(int clave, int valor) {
		// elimina un valor de determinada clave
		//recaudos: debo encontrar el valor a buscar
		//dos opciones, primera, busco el valor, si la clave queda vacia, recorro todo, invoco a eliminar y elimino la clave tambien
		//la segundo, utilizar otro metodo privado, usamos esta:
		//Tengo que buscar la clave ahora, siempre me paro en la clave anterior, por el 3er //
		//pregunto si la primera clave es la que debo eliminar
		if (origen.clave == clave) {
			EliminarValorEnNodo(origen , valor);
			//ahora tenog que preguntar si origen sigue teniendo valor
			if (origen.valores == null)
				//elimino origen
				origen = origen.sigClave;
		}
		//ahora tenemos que recorrer los nodos claves
		else {
			//siempre utilizamos nodo viajero
			NodoClave viajero = origen;
			while (viajero.sigClave != null && viajero.sigClave.clave != clave)
				viajero = viajero.sigClave;
			if (viajero.sigClave != null) {
				//eliminamos
				EliminarValorEnNodo(viajero.sigClave , valor);
				//ahora debo asegurarme que el siguiente sigue teniendo valor, sino elimino el nodo lcave
				if (viajero.sigClave.valores == null)
					viajero.sigClave = viajero.sigClave.sigClave;
			}

		}
	}
 
	@Override
	public ConjuntoTDAD Recuperar(int clave) {
		// tengo que ir al nodo que tiene la clave, recorrerlo y meter todos sus valores en un conjunnto
		NodoClave nodo = Clave2NodoClave(clave);
		ConjuntoTDAD c = new ConjuntoLDA();
		c.InicializarConjunto();
		NodoValor viajero = nodo.valores; //nodo.valores contiene los nodos valores
		while(viajero != null) { 
			//debo agregar el valor al conjunto y mover un lugar
			c.Agregar(viajero.valor);
			viajero = viajero.sigValor;
		}
		return c;	
	}
 
	@Override
	public ConjuntoTDAD Claves() {
		ConjuntoTDAD c = new ConjuntoLDA();
		c.InicializarConjunto();
		//tengo que preguntar si origen es ! null porq si es null no hay ni una clave
		if(origen != null) {
			NodoClave viajero = origen;
			while (viajero != null) {
				c.Agregar(viajero.clave);
				viajero = viajero.sigClave; //porque sino siempre voy a estar agregando la misma clave
			}
		}
		return c;

	}
	private NodoClave Clave2NodoClave(int clave) { //me dan una clave y devuelvo 
	//la direccion del nodo correspondiente
		// tengeo que buscar la clave, recorreindo con un viajero los Nodo Claves
		// si lo encuentro, devuelvo lo que encontre, si no lo encuentro va a ser null
		NodoClave viajero = origen;
		while (viajero != null && viajero.clave != clave) 
			viajero = viajero.sigClave;
		return viajero; //no importa si es null o no, total devuelvo el valor
	}
	private void EliminarValorEnNodo(NodoClave nodo, int valor) {
	    if (nodo.valores != null) { // Protección extra por si la lista está vacía
	        if (nodo.valores.valor == valor) {
	            nodo.valores = nodo.valores.sigValor;
	        } else {
	            NodoValor viajero = nodo.valores;
	            while (viajero.sigValor != null && viajero.sigValor.valor != valor)
	                viajero = viajero.sigValor;
	            
	            // FALTABA ESTO:
	            if (viajero.sigValor != null) {
	                viajero.sigValor = viajero.sigValor.sigValor;
	            }
	        }
	    }
	}
}