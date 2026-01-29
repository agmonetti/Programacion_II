package interfaz;
 
public interface DiccionarioMultipleTDA {
        void InicializarDiccionario();
        void Agregar(int clave, int valor);
        void Eliminar(int clave);         //elimino la clave junto a todos sus valores
        void EliminarValor(int clave, int valor); //elimino un valor de la clave
        ConjuntoTDAD Recuperar(int clave);      // me da los valores de tal clave
        ConjuntoTDAD Claves();
}