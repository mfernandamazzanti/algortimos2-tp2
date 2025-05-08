package aed;

import java.util.ArrayList;

public class Arbolito<T> { 

    // Clase interna Nodo
    public class Nodo {

        // Invariante de representación: 
        // Hijos != null 
        // Todos los nodos hijos de un mismo padre deben tener flags distintas y dichas flags deben estar en orden lexicográfico según el alfabeto ASCII.
        // Valor puede ser null 
        
        private ArrayList<Nodo> Hijos;
        private Nodo Padre;
        private char flag;
        private T Valor;

        public Nodo(char flag) {
            this.flag = flag;
            this.Hijos = new ArrayList<>();
        }

        public ArrayList<Nodo> getHijos() {
            return Hijos;
        }

        public Nodo getPadre() {
            return Padre;
        }

        public void setPadre(Nodo Padre) {
            this.Padre = Padre;
        }

        public char getFlag() {
            return flag;
        }

        public T getValor() {
            return Valor;
        }

        public void setValor(T Valor) {
            this.Valor = Valor;
        }
    }   
    // Invariante de representación:
    // Cantidad >= 0 
    // Raiz != null (una vez inicializado el trie)
    // El flag de Raiz es nulo y Raiz no tiene un padre (Raiz.Padre = null) 
    private int Cantidad; 
    private Nodo Raiz;

    public Arbolito() {
        this.Cantidad = 0;
        this.Raiz = new Nodo('\0'); // Nodo raíz con flag nulo
        this.Raiz.setPadre(null);
        this.Raiz.setValor(null);
    }

    public int cardinal() {
        return this.Cantidad;
    }

    private Nodo BuscarNodoAyuda(ArrayList<Nodo> nodos, char bu) {
        for (Nodo nodo : nodos) {
            if (nodo.getFlag() == bu)
                return nodo;
        }
        return null;
    }

    private void AgregarHijoOrdenado(Nodo nuevoHijo, Nodo Padre) {
        int indice = 0;
        while (indice < Padre.getHijos().size() && Padre.getHijos().get(indice).getFlag() < nuevoHijo.getFlag()) {
            indice++;
        }
        Padre.getHijos().add(indice, nuevoHijo);
    }

    public void insertar(T elem, String Pala) {
        Nodo ayuda = this.Raiz;
        int i = 0;
        while (ayuda != null && i < Pala.length()) {
            Nodo hel = BuscarNodoAyuda(ayuda.getHijos(), Pala.charAt(i));
            if (hel != null) {
                ayuda = hel;
                i++;
            } else {
                break;
            }
        }

        for (int k = i; k < Pala.length(); k++) {
            Nodo nuevo = new Nodo(Pala.charAt(k));
            nuevo.setPadre(ayuda);
            AgregarHijoOrdenado(nuevo, ayuda);
            ayuda = nuevo;
        }

        if (i == Pala.length()) {
            ayuda.setValor(elem);
            this.Cantidad++;
        } else {
            this.Cantidad++;
            ayuda.setValor(elem);
        }
    }

    public boolean pertenece(String pala) {
        Nodo ayuda = this.Raiz;
        int i = 0;
        while (ayuda != null && i < pala.length()) {
            Nodo help = BuscarNodoAyuda(ayuda.getHijos(), pala.charAt(i));
            if (help != null) {
                i++;
                ayuda = help;
            } else {
                return false;
            }
        }
        return ayuda != null && ayuda.getValor() != null;
    }

    public void eliminar(String pala) {
        Nodo ayuda = this.Raiz;
        int i = 0;
        while (ayuda != null && i < pala.length()) {
            Nodo hel = BuscarNodoAyuda(ayuda.getHijos(), pala.charAt(i));
            if (hel != null) {
                ayuda = hel;
                i++;
            } else {
                return;
            }
        }
        if (i == pala.length()) {
            ayuda.setValor(null);
            this.Cantidad--;
        }
    }

    public Nodo BuscarNodo(String pala) {
        Nodo ayuda = this.Raiz;
        int i = 0;
        while (ayuda != null && i < pala.length()) {
            Nodo hel = BuscarNodoAyuda(ayuda.getHijos(), pala.charAt(i));
            if (hel != null) {
                ayuda = hel;
                i++;
            } else {
                return null;
            }
        }
        if (i == pala.length()) {
            return ayuda;
        }
        return null;
    }

    public Nodo getRaiz() {
        return Raiz;
    }
}
