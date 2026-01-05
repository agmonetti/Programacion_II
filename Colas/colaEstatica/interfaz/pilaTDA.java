package interfaz;

public interface pilaTDA {
    void InicializarPila();     // Sin precondiciones. Crea pila vacia
    void Apilar(int x);         // Pila inicializada. Agrega un numero a la pila, acordarse de que lo va agregando arriba y solo ten
    void Desapilar();           // Pila inicializada y no vacía. Elimina el de arriba del todo, el que esta en el tope
    int Tope();                 // Pila inicializada y no vacía.Me muestra el numero que esta arriba del todo.
    							// Debo asegurarme que la pila no este vacia
    boolean PilaVacia();        // Pila inicializada. Me dice si la pila esta vacia o no
}