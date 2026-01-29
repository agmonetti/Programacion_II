package interfaz;

public interface DiccionarioSimpleTDA {
	void InicializarDiccionario(); 
	void Agregar(int clave, int x); //Si ya existe la misma clave con otro valor se sobreescribe el valor
	void Eliminar(int clave); //dada una clave, elimina el valor asociado a ella
	int Recuperar(int clave); //dada una clave, devuelve el valor asociado a ella.
	ConjuntoTDAD Claves(); //devuelve un conjunto con todas las claves del diccionario
}
