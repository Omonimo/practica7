package mx.unam.ciencias.icc;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>Clase para listas genéricas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas implementan la interfaz {@link Iterable}, y por lo tanto se
 * pueden recorrer usando la estructura de control <em>for-each</em>. Las listas
 * no aceptan a <code>null</code> como elemento.</p>
 *
 * @param <T> El tipo de los elementos de la lista.
 */
public class Lista<T> implements Iterable<T> {

    /* Clase interna privada para nodos. */
    private class Nodo {
        /* El elemento del nodo. */
        private T elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nodo con un elemento. */
        private Nodo(T elemento) {
            // Aquí va su código.
	    this.elemento = elemento;
        }
    }

    /* Clase interna privada para iteradores. */
    private class Iterador implements IteradorLista<T> {
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nuevo iterador. */
        private Iterador() {
            // Aquí va su código.
	    
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
            // Aquí va su código.
	    return this.siguiente == null;
	    
        }

        /* Nos da el elemento siguiente. */
        @Override public T next() {
            // Aquí va su código.
	    return this.siguiente;
        }

        /* Nos dice si hay un elemento anterior. */
        @Override public boolean hasPrevious() {
            // Aquí va su código.
	    return this. anterior == null;
        }

        /* Nos da el elemento anterior. */
        @Override public T previous() {
            // Aquí va su código.
	    return this.anterior;
        }

        /* Mueve el iterador al inicio de la lista. */
        @Override public void start() {
            // Aquí va su código.
            siguiente = cabeza;
            anterior = null;
        }

        /* Mueve el iterador al final de la lista. */
        @Override public void end() {
            // Aquí va su código.
            siguiente = null;
            anterior = rabo;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        // Aquí va su código.
	return longitud;
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean esVacia() {
        // Aquí va su código.
	return this.cabeza == null && this.rabo == null;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(T elemento) {
        // Aquí va su código.
        if(elemento == null)
            throw new IllegalArgumentException();
        Nodo n = new Nodo(elemento);
        longitud++;
        if(esVacia()){
            cabeza = rabo = n;
        } else {
            rabo.siguiente = n;
            n.anterior = rabo;
            rabo = n;
        }
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(T elemento) {
        // Aquí va su código.
        if(elemento == null)
            throw new IllegalArgumentException();
        Nodo n = new Nodo(elemento);
        longitud++;
        if(esVacia()){
            cabeza = rabo = n;
        } else{
            cabeza.anterior = n;
            n.siguiente = cabeza;
            cabeza = n;
        }
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void inserta(int i, T elemento) {
        // Aquí va su código.
         if (elemento == null)
            throw new IllegalArgumentException();

        Nodo n = new Nodo(elemento);
        int tamanio = getLongitud();
        if (i <= 0) {
            agregaInicio(elemento);
            } else if (i >= tamanio) {
            agregaFinal(elemento);
            } else {
                int index = 0;
               for(T t : this){
                if(i == index){
                    n.anterior = anterior;
                    anterior.siguiente = n;

                    siguiente.anterior = n;
                    n.siguiente = siguiente;

                }

               }
            
        } 
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(T elemento) {
        // Aquí va su código.
         if (elemento == null) {
            return;
        }

        if (esVacia()) 
            return;
        
            for(T t : this){
                if(t.equals(elemento)){
                    anterior.siguiente = this.siguiente;
                    siguiente.anterior = this.antetior;
                }
            }
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaPrimero() {
        // Aquí va su código.
          if(esVacia())
            throw new NoSuchElementException();
        
        Nodo n = this.cabeza; 
        if (n == null) {
            return null;
        } else if (n != null && n.siguiente == null) {
            this.cabeza = null;
            this.rabo = null;
            longitud--;
            return n.elemento;
        } else {
            this.cabeza = this.cabeza.siguiente;
            this.cabeza.anterior = null;
            longitud--;
            return n.elemento;
        }
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaUltimo() {
        // Aquí va su código.
         if(esVacia())
            throw new NoSuchElementException();
        
	    Nodo n = this.rabo;
        if (n == null) {
            return null;
        } else if (n != null && n.anterior == null) {
            this.cabeza = null;
            this.rabo = null;
            longitud--;
            return n.elemento;
        } else {
            this.rabo = this.rabo.anterior;
            this.rabo.siguiente = null;
            longitud--;
            return n.elemento;
        }

    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <code>true</code> si <code>elemento</code> está en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean contiene(T elemento) {
        // Aquí va su código.
        for(T t : this){
            if(t.equals(elemento))
                return true; 
        } return false;

    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista<T> reversa() {
        // Aquí va su código.
         Lista<T> reversa = new Lista<>();
        for(T t : this)
            reversa.agregaInicio(t);
        return reversa;
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista<T> copia() {
        // Aquí va su código.
         Lista<T> copia = new Lista<>();
        for(T t : this) 
            copia.agregaFinal(t);
        return copia;
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    public void limpia() {
        // Aquí va su código.
        for(T t : this)
            elimina(t);;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getPrimero() {
        // Aquí va su código.
                if(esVacia())
            throw new NoSuchElementException();
        return cabeza.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getUltimo() {
        // Aquí va su código.
        if(esVacia())
            throw new NoSuchElementException();
        return rabo.elemento;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista.
     * @throws ExcepcionIndiceInvalido si <em>i</em> es menor que cero o mayor o
     *         igual que el número de elementos en la lista.
     */
    public T get(int i) {
        // Aquí va su código.
          if(i < 0 || i >= longitud){
            throw new ExcepcionIndiceInvalido();
        }
        Nodo n = cabeza;
        int contador = 0;
        if (i < 0 || i >= getLongitud()) {
            return null;
        } else {
            while (contador < i) {
                n = n.siguiente;
                contador++;
            }
            return n.elemento;
        }
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(T elemento) {
        // Aquí va su código.
         int contador = 0;
        Nodo n = cabeza;
        if (elemento == null) {
            return -1;
        }
        while (n != null) {
            if (n.elemento.equals(elemento)) {
                return contador;
            }
            n = n.siguiente;
            contador++;
        }
        return -1;
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
        // Aquí va su código.
                String s = "[";
        Nodo n = cabeza;
        while (n != null) {
            s += n.elemento;
            if (n.siguiente != null) {
                s += ", ";
            }
            n = n.siguiente;
        }
        s += "]";
        return s;
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param objeto el objeto con el que hay que comparar.
     * @return <code>true</code> si la lista es igual al objeto recibido;
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (objeto == null || getClass() != objeto.getClass())
            return false;
        @SuppressWarnings("unchecked") Lista<T> lista = (Lista<T>)objeto;
        // Aquí va su código.
          if(this.longitud != lista.longitud)
            return false;
        Nodo viajero = this.cabeza;;
        Nodo viajeroLista = lista.cabeza;
        while (viajero != null) {
            if(!(viajero.elemento.equals(viajeroLista.elemento))){
                return false;
            }
            viajero = viajero.getSiguiente();
            viajeroLista = viajeroLista.getSiguiente();
            
                
            
        } return true;
    }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * @return un iterador para recorrer la lista en una dirección.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }
}
