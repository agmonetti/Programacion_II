package principal;

import implementacion.ConjuntoLDA;
import implementacion.GrafoMA;
import interfaz.GrafoTDA;
import interfaz.ConjuntoTDAD;

public class princ {
	//metodo para copiar un conjunto
	public static ConjuntoTDAD copiarConjunto(ConjuntoTDAD cj) {
		ConjuntoTDAD aux = new ConjuntoLDA();
		ConjuntoTDAD copia = new ConjuntoLDA();
		
		aux.InicializarConjunto();
		copia.InicializarConjunto();
		int valor;
		
		while(!cj.ConjuntoVacio()) {
			valor = cj.Elegir();
			aux.Agregar(valor);
			cj.Sacar(valor);
		}
		
		while(!aux.ConjuntoVacio()) {
			valor = aux.Elegir();
			copia.Agregar(valor);
			cj.Agregar(valor);
			aux.Sacar(valor);
		}
		
		return copia;
		
	}
	
	//metodo para mostrar un conjunto sin perderlo
	public static void mostrarConjuntoCopia(ConjuntoTDAD cj) {
		ConjuntoTDAD copia = copiarConjunto(cj);
		int valor;
		
		while(!copia.ConjuntoVacio()) {
			valor = copia.Elegir();
			System.out.println("Valor: "+ valor);
			copia.Sacar(valor);
		}
		
	}

	//Metodo para mostrar un GRAFO
	public static void mostrarGrafo(GrafoTDA g) {
	    ConjuntoTDAD vertices = g.Vertices();
	    int x, y;
	    
	    while(!vertices.ConjuntoVacio()) {
	        x = vertices.Elegir();
	        vertices.Sacar(x); 
	        
	        System.out.println("Vertice: " + x); 
	        
	        ConjuntoTDAD verticesDestino = g.Vertices();

	        while(!verticesDestino.ConjuntoVacio()) {
	            y = verticesDestino.Elegir();
	            verticesDestino.Sacar(y);

	            if(g.ExisteArista(x, y)) {
	                System.out.println("\t--> Conecta con: " + y + " (Peso: " + g.PesoArista(x, y) + ")");
	            }
	        }
	        
	        System.out.println("--------------------------------"); 
	    }
	}
	
	//4- metodo para obtener el conjunto de vertices adyacentes de un vertice
	public static ConjuntoTDAD verticesAdyacentesDobles(GrafoTDA g, int v) {
		ConjuntoTDAD conjResultante = new ConjuntoLDA();
		conjResultante.InicializarConjunto();
		
		System.out.println("Se buscaran los vertices adyacentes dobles de: "+v);
		//IDEA: Bucle doble, 
		//1) busco adyacente a vertice (v). Lo llamamos (x)
		//2) recorro los vertices nuevamente, busco adyacente a (x). Llamado (w)
		
		ConjuntoTDAD verticesX = g.Vertices();
		int x;
		int w;
		
		while(!verticesX.ConjuntoVacio()) {
			//un vertice es adyacente si una arista los conecta
			x= verticesX.Elegir();
			if(g.ExisteArista(v, x)) { 
				//tenemos el primer candidato.
				ConjuntoTDAD verticesW = g.Vertices();
				while(!verticesW.ConjuntoVacio()) {
					w = verticesW.Elegir();
					if(g.ExisteArista(x, w))// a veces los profesores piden "adyacentes dobles que no sean el propio vértice. (if(g.ExisteArista(x, w) && w != v))
						conjResultante.Agregar(w);
					verticesW.Sacar(w);
				}
				
			}
			verticesX.Sacar(x);
		}
		return conjResultante;
	}
	
	//5b- metodo para calcular el mayor de los costos de las aristas salientes.
	//en otras palabras, el peso con mayor valor
	public static int mayorCostoAristas(GrafoTDA g) {
		//IDEA: recorrer todos los vertices, si existe arista, guardo el peso
		ConjuntoTDAD vertices=g.Vertices();
		int x, y;
		int mayorCosto = 0;
		
	    while(!vertices.ConjuntoVacio()) {
	        x = vertices.Elegir();
	        vertices.Sacar(x); 
	        ConjuntoTDAD verticesDestino = g.Vertices();

	        while(!verticesDestino.ConjuntoVacio()) {
	            y = verticesDestino.Elegir();
	            verticesDestino.Sacar(y);
	            if(g.ExisteArista(x, y)) {
	            	int peso = g.PesoArista(x, y);
	            	if(peso > mayorCosto)
	            		mayorCosto=peso;
	            }
	        }
	        
	    }
	    return mayorCosto;
		
	}
	
	//5- metodo que dado un vértice v de un grafo, calcular el mayor de los costos de las aristas salientes
	public static int mayorCostoAristaDeV(GrafoTDA g, int v) {
		System.out.print("peso de mayor valor de la arista "+ v + " --> ");
		ConjuntoTDAD vertices=g.Vertices();
		int mayorCosto = 0;
		int x;
		
		while(!vertices.ConjuntoVacio()) {
			x = vertices.Elegir();
			vertices.Sacar(x);
			//Aca pregunto si existe la arista entre v y x
			
			if(g.ExisteArista(v, x)) {
				int peso = g.PesoArista(v, x);
				if(peso > mayorCosto)
					mayorCosto = peso;
			}
				
		}
	
		return mayorCosto;
	}
	
	// 6- metodo para obtener los vertices predecesores de un vertice V
	public static ConjuntoTDAD conjuntoPredecesores(GrafoTDA g, int v) {
		//Idea: la misma logica de recorrer los vertices buscando sucesores lo unico es
		//que cambia la pregunta de si existe arista
		
		ConjuntoTDAD conjResultante = new ConjuntoLDA();
		conjResultante.InicializarConjunto();
		
		ConjuntoTDAD vertices = g.Vertices();
		int x;
		
		while(!vertices.ConjuntoVacio()) {
			x = vertices.Elegir();
			vertices.Sacar(x);
			if(g.ExisteArista(x, v))
				conjResultante.Agregar(x);
		}
		
		return conjResultante;
	}
	
	// 7- método que permita obtener el conjunto de los vértices aislados en G.
	public static ConjuntoTDAD conjuntoVerticesAislados(GrafoTDA g) {
	    ConjuntoTDAD conjResultante = new ConjuntoLDA();
	    conjResultante.InicializarConjunto();
	    
	    // primero recorro los candidatos
	    ConjuntoTDAD candidatos = g.Vertices();
	    while(!candidatos.ConjuntoVacio()) {
	        int candidato = candidatos.Elegir();
	        candidatos.Sacar(candidato); 
	        
	        // asumo que esta aislado
	        boolean esAislado = true; 
	        
	        // busco evidencia de que no es aislado
	        ConjuntoTDAD testigos = g.Vertices();
	        while(!testigos.ConjuntoVacio()) {
	            int testigo = testigos.Elegir();
	            testigos.Sacar(testigo);
	            
	            // Si el candidato toca al testigo O el testigo toca al candidato entonces no esta aislado
	            if(g.ExisteArista(candidato, testigo) || g.ExisteArista(testigo, candidato)) {
	                esAislado = false;
	            }
	        }
	        
	        // si es true lo agrego
	        if(esAislado == true) {
	            conjResultante.Agregar(candidato);
	        }
	    }
	    
	    return conjResultante;
	}
	
	// 8- Dado un grafo y dos vertices v1 y v2.
	//método que permita obtener el conjunto de todos los vértices puente entre v1 y v2.
	//Basicamente es el "intermediario" que completa el camino de dos pasos.
	public static ConjuntoTDAD verticesPuente(GrafoTDA g, int v1, int v2) {
		//un vértice p es puente entre dos vértices o y d, si hay una arista que
		//comienza en o y termina en p y otra que comienza en p y termina en d.
		//o: v1
		//d: v2
	    ConjuntoTDAD conjResultante = new ConjuntoLDA();
	    conjResultante.InicializarConjunto();
	    int p,y;
	    
	    ConjuntoTDAD verticesO = g.Vertices();
	    while(!verticesO.ConjuntoVacio()) {
	    	p = verticesO.Elegir();
	    	verticesO.Sacar(p);
	    	//reviso si existe una arista que comience en o y termine en p
	    	if(g.ExisteArista(v1, p) && g.ExisteArista(p, v2)) {
	    		//ultima validacion, que comience en p y termine en d
    			conjResultante.Agregar(p);
	    				
	    	}
	    }
	    return conjResultante;
	}
	
	//9- dado un grafo y un vertice. metodo para obtener el grado del vertice.
	// grado = aristas salientes - aristas entrantes
	public static int gradoVertice(GrafoTDA g, int v) {
		int salientes = 0, entrantes = 0;
		int vertice;
		ConjuntoTDAD vertices = g.Vertices();
		while(!vertices.ConjuntoVacio()) {
			vertice = vertices.Elegir();
			vertices.Sacar(vertice);
			// si conecta a alguien: ++ saliente
			if(g.ExisteArista(v, vertice))
				salientes++;
			if(g.ExisteArista(vertice, v))
				entrantes++;
		}
		return(salientes-entrantes);
	}
	
	public static void main(String[] args) {
		GrafoTDA grafo = new GrafoMA();
		grafo.InicializarGrafo();
		
		grafo.AgregarVertice(6);
		grafo.AgregarVertice(5);
		grafo.AgregarVertice(4);
		grafo.AgregarVertice(3);
		grafo.AgregarVertice(2);
		grafo.AgregarVertice(1);
		
		//Aristas Vertice(1)
		grafo.AgregarArista(1, 6, 6);
		grafo.AgregarArista(1, 4, 2);
		grafo.AgregarArista(1, 2, 7);
		
		//Aristas Vertice(4)
		grafo.AgregarArista(4, 4, 3);
		
		mostrarGrafo(grafo);

		ConjuntoTDAD adyacentesDobles = verticesAdyacentesDobles(grafo,1);
		System.out.println();
		mostrarConjuntoCopia(adyacentesDobles);
		System.out.println("peso de mayor valor: "+mayorCostoAristas(grafo));
		System.out.println(mayorCostoAristaDeV(grafo,4));
		ConjuntoTDAD conjPredecesores =  conjuntoPredecesores(grafo,2);
		mostrarConjuntoCopia(conjPredecesores);
		ConjuntoTDAD conjVerticesAislados =  conjuntoVerticesAislados(grafo);
		System.out.println();
		System.out.println("Vertices aislados");
		mostrarConjuntoCopia(conjVerticesAislados);
		System.out.println();
		System.out.println("conjunto puente entre dos vertices");
		ConjuntoTDAD verticesPuente =  verticesPuente(grafo,1,4);
		mostrarConjuntoCopia(verticesPuente);
		System.out.println("grado del vertice (3) -> "+ gradoVertice(grafo,3));
	
	}

}
