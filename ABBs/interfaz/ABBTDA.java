package interfaz;

public interface ABBTDA {
	void InicializarArbol();				// sin precondiciones
	int Raiz();								// Ã¡rbol inicializado y no vacÃ­o
	ABBTDA HijoIzq();						// Ã¡rbol inicializado y no vacÃ­o
	ABBTDA HijoDer();						// Ã¡rbol inicializado y no vacÃ­o
	void AgregarElem(int x);				// Ã¡rbol inicializado
	void EliminarElem(int x);				// Ã¡rbol inicializado
	boolean ArbolVacio();					// Ã¡rbol inicializado
}