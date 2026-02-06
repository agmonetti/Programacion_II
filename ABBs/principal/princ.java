package principal;

import implementacion.ABB;
import implementacion.ConjuntoLDA;
import interfaz.ABBTDA;
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
	
	

	//metodo para mostrar un arbol - recorrido in order: hijoIzq - raiz -hijoDer
	//metodo in order sirve para recuperar todos los elementos del arbol ordenados
	public static void mostrarArbolIn(ABBTDA arbol) {
		if(!arbol.ArbolVacio()) {
			//entro al metodo con el hijo izquierdo
			mostrarArbolIn(arbol.HijoIzq());
			System.out.println(arbol.Raiz());
			mostrarArbolIn(arbol.HijoDer());
		}
	}
	//metodo para mostrar un arbol - recorrido pre order: raiz -hijoIzq - hijoDer
	public static void mostrarArbolPre(ABBTDA arbol) {
		if(!arbol.ArbolVacio()) {
			System.out.println(arbol.Raiz());
			mostrarArbolPre(arbol.HijoIzq());
			mostrarArbolPre(arbol.HijoDer());
		}
	}
	
	//metodo para mostrar un arbol - recorrido pre order: raiz -hijoIzq - hijoDer
	public static void mostrarArbolVertical(ABBTDA a, String espacio, String etiqueta) {
	    if (!a.ArbolVacio()) {
	        // primero, nodo raiz
	        System.out.println(espacio + "|__ " + etiqueta + ": " + a.Raiz());
	        
	        // segundo, hijo izq
	        mostrarArbolVertical(a.HijoIzq(), espacio + "    ", "Izq");
	        
	        // tercero hijo der
	        mostrarArbolVertical(a.HijoDer(), espacio + "    ", "Der");
	    }
	}
	
	//metodo para mostrar un arbol - recorrido post order: -hijoIzq - hijoDer -raiz
	public static void mostrarArbolPost(ABBTDA arbol) {
		if(!arbol.ArbolVacio()) {
			//entro al metodo con el hijo izquierdo
			mostrarArbolPost(arbol.HijoIzq());
			mostrarArbolPost(arbol.HijoDer());
			System.out.println(arbol.Raiz());
		}
	}
	
	// Método para imprimir el árbol de forma acostado
	// es como un reverse-in-order
	public static void ImprimirVisual(ABBTDA a, String espacio) {
	    
	    if (!a.ArbolVacio()) {
	        //a la derecha primero
	        // Al llamar, le agrego más espacios a la sangría para que el hijo salga más a la derecha.
	        ImprimirVisual(a.HijoDer(), espacio + "      "); // Uso 6 espacios para que se note bien
	        
	        // Imprimo los espacios acumulados + el valor + un salto de línea (println)
	        System.out.println(espacio + "[" + a.Raiz() + "]");
	        
	        // izq al final
	        ImprimirVisual(a.HijoIzq(), espacio + "      ");
	    }
	}
	
	
	//metodo para determinar si un elemento esta en el ABB
	//iterativo
	public static boolean perteneceIterativo(ABBTDA arbol, int elemento) {
		ABBTDA viajero = arbol;
		while(!viajero.ArbolVacio()) {
			//si es el que busco, true de una
			if(viajero.Raiz() ==elemento )
				return true;
			
			//si es mayor al elemento, me voy para la izquierda
			if(viajero.Raiz() > elemento)
				viajero = viajero.HijoIzq();
			//si es menor al elemento, me voy para la derecha
			else
				viajero = viajero.HijoDer();
		}
		// si sali del while es poorque quedo vacio, es decir, no lo encontre
		return false;
	}
	
	//metodo para determinar si un elemento esta en el ABB
	//recursivo
	public static boolean perteneceRecursivo(ABBTDA a,int elemento) {
		if(!a.ArbolVacio()) {
			if(a.Raiz() == elemento) 
				return true;
			
			else if(a.Raiz() > elemento)
				return perteneceRecursivo(a.HijoIzq(),elemento);
			
			else
				return perteneceRecursivo(a.HijoDer(),elemento);
		}	
		return false;
//		El General (Raíz) le pregunta al Coronel (Hijo).
//		Si no esta el return antes de la llamada, es como si el General le preguntara al Coronel, el Coronel respondiera "SÍ", y el General se quedara callado mirando a la nada.
//		Con el return, el General dice: "Mi respuesta será lo que responda el Coronel".
	}
	
	
	//metodo para determinar si un elemento es hoja
	//recursivo
	//un nodo es hoja si no tiene otros nodos debajo
	public static boolean esHojaRecursivo(ABBTDA a, int elemento) {
		//Ya sabemos que esta en el arbol, debemos ver si es hoja o no
		if(!a.ArbolVacio()) {
			//condicion corte: a.raiz == elemento, a.HijoIzq.ArbolVacio == true ,  a.HijoDer.ArbolVacio == true
			if(a.Raiz() == elemento &&  (a.HijoDer().ArbolVacio() && a.HijoIzq().ArbolVacio()) )
				return true;
			
			//PASO RECURSIVO
			else if(a.Raiz() > elemento)
				return esHojaRecursivo(a.HijoIzq(), elemento);
			else
				return esHojaRecursivo(a.HijoDer(), elemento);
		
		
		}
		return false;
	}
	
	//version optimizada
	public static boolean esHojaRecursivoV2(ABBTDA a, int elemento) {
	    if (a.ArbolVacio()) {
	        return false;
	    }

	    //condicion de corte
	    if (a.Raiz() == elemento) {
	    	 // es true si ambos estan vacios
	        return (a.HijoIzq().ArbolVacio() && a.HijoDer().ArbolVacio());
	    }

	    //PASO RECURSIVO
	    
	    // si la raiz es mayor al elem, busco a la izquierda
	    else if (a.Raiz() > elemento) {
	        return esHojaRecursivo(a.HijoIzq(), elemento);
	    }
	    
	    // y si es menor, busco a la derecha
	    else {
	        return esHojaRecursivo(a.HijoDer(), elemento);
	    }
	}
	
	//metodo para determinar si un elemento es hoja
	//interativo
	public static boolean esHojaIterativo(ABBTDA a, int elemento) {
		ABBTDA viajero = a;
		
		//Ya sabemos que esta en el arbol, debemos ver si es hoja o no
		while(!viajero.ArbolVacio()) {
			//condicion exito: a.raiz == elemento, a.HijoIzq.ArbolVacio == true ,  a.HijoDer.ArbolVacio == true
			if(viajero.Raiz() == elemento &&  (viajero.HijoDer().ArbolVacio() && viajero.HijoIzq().ArbolVacio()) )
				return true;
			
			else if(viajero.Raiz() > elemento)
				viajero= viajero.HijoIzq();
			else
				viajero= viajero.HijoDer();
		
		
		}
		return false;
	}
	
	//version optimizada
	public static boolean esHojaIterativoV2(ABBTDA a, int elemento) {
	    ABBTDA viajero = a;

	    while (!viajero.ArbolVacio()) {
	        
	        // condicion de corte
	        if (viajero.Raiz() == elemento) {
	            // es true si ambos estan vacios
	            return (viajero.HijoIzq().ArbolVacio() && viajero.HijoDer().ArbolVacio());
	        }

	        // movemos el puntero
	        if (viajero.Raiz() > elemento) {
	            viajero = viajero.HijoIzq();
	        } else {
	            viajero = viajero.HijoDer();
	        }
	    }
	    
	    return false;
	}
	
	//metodo para obtener la profundida de un elemento
	//recursivo
	public static int calcProfundidadRecursivo(ABBTDA a, int elemento) {
		if(a.ArbolVacio())
			return -1;
		
		//condicion de corte
		if(a.Raiz() == elemento)
			return 0;
		
		//PASO RECURSIVO
		if (a.Raiz() > elemento) {
	        // Le pregunto al hijo izquierdo
	        int respuestaHijo = calcProfundidadRecursivo(a.HijoIzq(), elemento);
	        // si el hijo no lo encontro, yo también devuelvo -1.
	        if (respuestaHijo == -1) return -1;
	        // Si lo encontro, sumo 1 a su respuesta
	        return 1 + respuestaHijo;
	    } 
	    else {
	        // Mismo proceso para la derecha
	        int respuestaHijo = calcProfundidadRecursivo(a.HijoDer(), elemento);
	        if (respuestaHijo == -1) return -1;
	        return 1 + respuestaHijo;
	    }
	}
		
	//metodo para obtener la profundida de un elemento
	//iterativo
	public static int calcProfundidadIterativa(ABBTDA a, int elemento) {
		int cont = 0;
		//recorremos el arbol con un viajero
		ABBTDA viajero = a;
		while(!viajero.ArbolVacio()) {
			//si encontramos el elemento, devuelvo la profundidad
			if(viajero.Raiz() == elemento)
				return cont;
			//sino, avanzo, para izq si es > al elemento
			else if(viajero.Raiz() > elemento) {
				viajero = viajero.HijoIzq();
				cont++;
			}
			//derecha si es menor
			else {
				viajero = viajero.HijoDer();
				cont++;
			}
		}
		
		return -1;
		
	}
	
	//metodo para obtener el elemento de menor valor de un ABB
	//recursivo
	public static int menorElemento(ABBTDA a) {
		if (a.ArbolVacio()) {
	        return -1; 
	    }
		
		//condicion de corte
		if(a.HijoIzq().ArbolVacio())
			return a.Raiz();
		
		//paso recursivo
		else
			return menorElemento(a.HijoIzq());
		

	}
	
	//metodo para obtener el elemento de menor valor en un ABB
	//iterativo
	public static int menorElementoIterativo(ABBTDA a) {
		if (a.ArbolVacio()) {
	        return -1;
	    }
		ABBTDA viajero = a;
		while(!viajero.HijoIzq().ArbolVacio()) {
			viajero = viajero.HijoIzq();
		}
		
		return viajero.Raiz();
		
	}
	
	//meotdo para contar la cant de elmeentos
	//recursivo
	public static int contarElementosRecursivo(ABBTDA a) {
		//condicion de corte
		if(a.ArbolVacio())
			return 0;
		
		//PASO RECURSIVO
		else  {
	        // Le pregunto al hijo izquierdo + el hijo der + la raiz
	        return 1 + contarElementosRecursivo(a.HijoIzq()) + contarElementosRecursivo(a.HijoDer());
	    }
	}

	//metodo para sumar elementos
	public static int sumarElementosRecursivo(ABBTDA a) {
		//condicion de corte
		if(a.ArbolVacio())
			return 0;
		
		//PASO RECURSIVO
		else  {
	        // Le pregunto al hijo izquierdo + el hijo der + la raiz
	        return a.Raiz() + sumarElementosRecursivo(a.HijoIzq()) + sumarElementosRecursivo(a.HijoDer());
	    }
	}
	
	//metodo para calcular la cantidad de hojas
	public static int cantHojas(ABBTDA a) {
		if(a.ArbolVacio()) return 0;
		
		//condicion de corte
		if(a.HijoDer().ArbolVacio() && a.HijoIzq().ArbolVacio())
			return 1;
		
		else
			return cantHojas(a.HijoDer()) + cantHojas(a.HijoIzq());
		
	}
	
	
	//metodo para calcular la altura de un arbol(max distancia desde la raiz)
	public static int calcAltura(ABBTDA a) {
		if(a.ArbolVacio()) return -1; //Porque si solo tenemos la Raíz, sus hijos son vacíos.
		//la altura de un solo nodo (sin aristas) es 0.
		//Matematicamente: 
		//Altura = 1 + Max(-1, -1) -> 1 + (-1) = 0
		//Estrategia: Recursivamente, preguntar altura izq y der a hijos y sumarle 1, ya que es la arista que lo conecta con dichos hijos
		//Es una comparacion de alturas, mas facil, una competencia. El hijo con mayor distancia, es la altura
		
		int altIzq = calcAltura(a.HijoIzq());
		int altDer = calcAltura(a.HijoDer());
		
		//PASO RECURSIVO
		if(altIzq > altDer)
			return 1+ altIzq; //no llamo a la funcion, la variable ya calcula esa altura.
		else
			return 1 +altDer;
		
	}
	
	//metodo para ver si dos arboles tienen la misma forma
	//MISMA FORMA -> misma estructura, pero pueden tener valores distintos
	public static boolean sonMismaForma(ABBTDA a, ABBTDA b) {
		
		//condicion de corte
		if(a.ArbolVacio() && b.ArbolVacio()) return true; // si ambos arboles pasados por parametros estan vacios -> misma forma
		
		else if( (a.ArbolVacio() && !b.ArbolVacio()) || (!a.ArbolVacio() && b.ArbolVacio()) ) return false;
		// si alguno no esta vacio pero el otro si -> distinta forma
		
		else {
			return sonMismaForma(a.HijoIzq(),b.HijoIzq()) &&
					sonMismaForma(a.HijoDer(),b.HijoDer());
			//Necesito que la izquierda sea igual Y que la derecha sea igual.
		}
	}
	//metodo para ver si dos arboles son iguales
	//SON IGUALES -> misma estructura, valores iguales tambien
	public static boolean sonIguales(ABBTDA a, ABBTDA b) {
		
		//condicion de corte
		if(a.ArbolVacio() && b.ArbolVacio()) return true; // si ambos arboles pasados por parametros estan vacios -> misma forma
		
		else if( (a.ArbolVacio() && !b.ArbolVacio()) || (!a.ArbolVacio() && b.ArbolVacio()) ) return false;
		// si alguno no esta vacio pero el otro si -> distinta forma
		
		else {
			return sonIguales(a.HijoIzq(),b.HijoIzq()) &&
					sonIguales(a.HijoDer(),b.HijoDer()) &&
					(a.Raiz() == b.Raiz());
			//Necesito que la izquierda sea igual Y que la derecha sea igual.
			////Pero, ahora, tambien es necesario que esos valores sean iguales.
		}
	}
	
	//metodo para contar los elementos de un cirto nivel N
	public static int cantElemNivel(ABBTDA a, int nivel) {
		//Estrategia: No voy a llevar una variable extra que cuente "0, 1, 2, 3..." 
		//para compararla con N. Es más fácil hacerlo al revés: ir restando.
		if(a.ArbolVacio()) return 0; //nivel fuera de rango
		
		//condicion de corte -> voy restando los niveles, cuando sea el 0, es el nivel que quiero contar
		if(nivel == 0) 
			return 1;
		
		else//todavia falta bajar
			return cantElemNivel(a.HijoIzq(),nivel -1) + cantElemNivel(a.HijoDer(),nivel-1);
	}
	
	//metodo auxiliar para el conjElemMayores, me permite crear un solo conjunot y no uno cada vez que se llama a la recursion
	public static ConjuntoTDAD buscarElem(ABBTDA a, int k) {
        ConjuntoTDAD resultado = new ConjuntoLDA(); // creamos el conj
        resultado.InicializarConjunto();
        
        // Llamamos al metodo para que llene el conjunto
        conjElemMayores(a, k, resultado);
        
        return resultado;
    }
	
	//metodo para, dado un elemento k, guardar en un conjunto, todos los valores mayores a k
	public static void conjElemMayores(ABBTDA a, int k, ConjuntoTDAD c) {
		//Estrategia, si a.raiz es > k, voy para la izq, sino para la derecha
		
		//condicoin corte
		if(a.ArbolVacio())return;
	
		if (a.Raiz() > k){
            c.Agregar(a.Raiz()); 
            
            // hay que mirar a la izq porque puede haber valores como k+1
            conjElemMayores(a.HijoIzq(), k, c);
            
            // Y para la derecha, todos son mayores
            conjElemMayores(a.HijoDer(), k, c);
		}
            
		//PASO REcursivo -> me muevo para la derecha
		else
			conjElemMayores(a.HijoDer(),k,c);
		

	}
	
	//metodo para devovler el valor inmediatamente anterior (predecesor) de un valor n
	public static int elemPredecesor(ABBTDA a, int n) {
		//Estrategia:Utilizar una variable auxiliar que funcione como candidato a ser el predecesor
		//Lo hago iterativo, es mas facil. Me sirve porque solo tengo que recorrer un camino, no me hace falta comprobar ambos lados
		
		
		ABBTDA viajero = a;
		int candidato = -1;
		while(!viajero.ArbolVacio()) {
			if(viajero.Raiz() < n) {
				//me sirve, lo guardo como candidato
				candidato = viajero.Raiz();
				viajero= viajero.HijoDer();
			}
			else
				viajero = viajero.HijoIzq();
			
		}
		
		return candidato;
			
	}
	
	
	public static void main(String[] args) {
		ABBTDA arbol = new ABB();
		arbol.InicializarArbol();
		
		arbol.AgregarElem(50);
	    arbol.AgregarElem(30);
	    arbol.AgregarElem(70);
	    arbol.AgregarElem(20);
	    arbol.AgregarElem(40);
	    arbol.AgregarElem(60);
	    arbol.AgregarElem(80);
//		mostrarArbolIn(arbol);
//		ImprimirVisual(arbol,"");
//		mostrarArbolPost(arbol);
//		System.out.println(perteneceRecurs(arbol,1500));
		
		ABBTDA arbol2 = new ABB();
		arbol2.InicializarArbol();
		
		// Mismo orden de inserción, valores distintos pero con la misma relación relativa
		arbol2.AgregarElem(150); // Raíz
		arbol2.AgregarElem(130); // Izquierda de 150
		arbol2.AgregarElem(170); // Derecha de 150
		arbol2.AgregarElem(120); // Izquierda de 130
		arbol2.AgregarElem(140); // Derecha de 130
		arbol2.AgregarElem(160); // Izquierda de 170
		arbol2.AgregarElem(180); // Derecha de 170
		
		ABBTDA arbol3 = new ABB();
		arbol3.InicializarArbol();
		arbol3.AgregarElem(50);
	    arbol3.AgregarElem(30);
	    arbol3.AgregarElem(70);
	    arbol3.AgregarElem(20);
	    arbol3.AgregarElem(40);
	    arbol3.AgregarElem(60);
	    arbol3.AgregarElem(80);
	    arbol3.AgregarElem(45);
	    arbol3.AgregarElem(65);

		mostrarArbolVertical(arbol3, "", "Raiz");

//		ConjuntoTDAD conj = buscarElem(arbol,0);
//		mostrarConjuntoCopia(conj);
		System.out.println(elemPredecesor(arbol,40));
	}

}
