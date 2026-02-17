package implementacion;

import interfaz.ConjuntoTDAD;
import interfaz.GrafoTDA;

public class GrafoMA implements GrafoTDA {
	
	//Necesito dos clases aux
	// 1- Para los vertices
	class NodoVertice{
		int nodo;    //valor del vertice
		NodoVertice sig;
		NodoArista arista;
	}
	
	// 2- Para las artistas
	class NodoArista {
		int peso;
		NodoVertice nodoDestino;  // como una arista une dos vertices. La arista apunta al segundo nodo, que es el destino
		NodoArista sig;
	}
	
	NodoVertice origen;

	@Override
	public void InicializarGrafo() {
		origen = null;

	}

	@Override
	public void AgregarVertice(int x) {
		// Un nuevo nodo del grafo se agrega siempre al inicio.   
//		 Como lo creamos al inicio, le tenemos que apuntar origen a este
		
		NodoVertice nuevo =  new NodoVertice();
		nuevo.nodo = x;
		nuevo.arista= null;
		nuevo.sig = origen;
		origen = nuevo;
	}


	@Override
	public void AgregarArista(int o, int d, int p) {
		/* Debo encontrar los dos nodos que quiero conectar. 
		Tambien necesito obtener el indice:
		   Para eso vamos a usar Vert2Nodo -> recorre todos los vertices y busca el 
		   nodo origen y destino 
		 */
		NodoVertice nodoPartida = Vert2Nodo(o);
		NodoVertice nodoLlegada = Vert2Nodo(d);
		// ahora necesito un nodoArista para vincularlos. 
		//Los voy agregando al inicio
		
		NodoArista nuevo = new NodoArista();
		nuevo.peso = p;
		nuevo.sig = nodoPartida.arista; //apunta a la arista, del nodo partida
		nodoPartida.arista = nuevo; //cambiamos la referencia del nodoPartida
		nuevo.nodoDestino = nodoLlegada; //conectamos al nuevo nodo que contiene la arista al nodo llegada
	
	}
	
	

	@Override
	public void EliminarVertice(int v) {
		/* Si elimino un vertice, debo eliminar todo, es decir, todas las aristas que 
		van a ese vertice
		   Para eso vamos a usar un metodo auxiliar que elimine las aristas del nodo y
		   sus conexiones
		Verifico si el primer nodo es el vertice que quiero eliminar
		*/
		if(origen.nodo == v) 
			origen = origen.sig;
		
		// si no es el primero, creo un viajero para recorrer los vertices. 
		//Debo eliminar todas las aristas que vayan al nodo a eliminar
		NodoVertice viajero = origen; 
		
		while(viajero.sig != null && viajero != null) {
			//Primero: eliminamos las aristas del nodo
			eliminarAristaNodo(viajero, v);
			if(viajero.sig.nodo == v)
				viajero.sig = viajero.sig.sig;
			viajero = viajero.sig;
		}
	}

	@Override
	public void EliminarArista(int o, int d) {
		// Primero obtengo los nodos de los int que me pasan
		NodoVertice nodo = Vert2Nodo(o);
		//eliminamos la arista que conecta ambos nodos
		eliminarAristaNodo(nodo, d);
	
	}
 

	@Override
	public int PesoArista(int o, int d) {
		NodoVertice nodo = Vert2Nodo(o);
		NodoArista viajero = nodo.arista;
		while(viajero.nodoDestino.nodo != d )
			viajero = viajero.sig;
		return viajero.peso;
	}

	@Override
	public boolean ExisteArista(int o, int d) {
		/* Tengo que encontrar si tengo en el nodo o una arista que vaya al nodo d
		Una forma seria
		Me paro en el nodo origen. Luego recorro las aristas y reviso si el destino es igual a d
		*/
		NodoVertice nodo = Vert2Nodo(o);
		NodoArista viajero = nodo.arista;
		//no hace falta mirar la siguiente ya que no tengo que eliminar nada. 
		// La recorrida para cuando destino sea igual a D o cuando no encuentre ningun noodo destino igual a d
		while(viajero != null && viajero.nodoDestino.nodo != d) {
			viajero = viajero.sig;
		}
		//si es distinto a null quiere decir que no encontro
		return(viajero != null);
	}

	@Override
	public ConjuntoTDAD Vertices() {
		ConjuntoTDAD c = new ConjuntoLDA();
		c.InicializarConjunto();
		//recorremos los nodos y los vamos metiendo en el conjunto
		NodoVertice viajero = origen;
		while(viajero != null) {
			c.Agregar(viajero.nodo);
			viajero = viajero.sig;
		}	
		return c;
	}
	
	//Metodos auxiliares
	// 1- METODO PARA BUSCAR UN VERTICE
	private NodoVertice Vert2Nodo(int v) { 
		NodoVertice viajero = origen;
		//Mientras que no haya encontrado el vertice que busco o hasta recorrer todos los nodos y no encontrarlo
		while(viajero != null && viajero.nodo != v) {
			viajero = viajero.sig;	
		}
		//o lo encontre o es null;
		return viajero;
	}
	 
	//2- METODO QUE ELIMINA TODAS LAS ARISTAS DE UN VERTICE
	private void eliminarAristaNodo(NodoVertice nodo, int v) { //v es nodo destino al cual quiero matar
		// Recorro las aristas, debo asegurarme que tenga aristas. como siempre, recorremos con un viajero
		NodoArista viajero = nodo.arista;
		if (viajero != null)  {
			// debo tratar el caso especial de que la primer arista este vinculada al nodo a eliminar
			if (viajero.nodoDestino.nodo == v) 
				// si no le pongo el.nodo no puedo hacerlo porque comparo un numero con un nodo 
				//El .nodo es para obtener el valor
				nodo.arista = nodo.arista.sig;
			else {
				while(viajero.sig != null && viajero.sig.nodoDestino.nodo != v) {
					viajero = viajero.sig;
				}
				//Averiguo porque termino el if:
//				1- Llegó al final de la lista (viajero.sig == null) y nunca encontró a v. No hay nada que borrar.
//				2- Encontró a v en el siguiente nodo.
				if(viajero.sig != null)
					viajero.sig = viajero.sig.sig;
			}
		}

	}
}
