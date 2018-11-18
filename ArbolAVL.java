package tdavideo;


public class ArbolAVL {
    private NodoArbolAVL raiz;
    public ArbolAVL(){
        raiz=null;
    }
    public NodoArbolAVL obtenerRaiz(){
        return raiz;
    }
    
    //Buscar el factor de equilibrio
    public int obtenerFe(NodoArbolAVL nodo){
        if(nodo==null){
            return -1;
        }else{
            return nodo.fe;
        }
    }
    //Rotacion simple izquierda
    public NodoArbolAVL rotacionIzquierda(NodoArbolAVL nodo){
        NodoArbolAVL auxiliar = nodo.hijoIzquierdo;
        nodo.hijoIzquierdo = auxiliar.hijoDerecho;
        auxiliar.hijoDerecho=nodo;
        nodo.fe=Math.max(obtenerFe(nodo.hijoIzquierdo), obtenerFe(nodo.hijoDerecho))+1;
        auxiliar.fe = Math.max(obtenerFe(auxiliar.hijoIzquierdo),obtenerFe(auxiliar.hijoDerecho))+1;
        return auxiliar;
    }
    //Rotacion simple derecha
    public NodoArbolAVL rotacionDerecha(NodoArbolAVL nodo){
        NodoArbolAVL auxiliar = nodo.hijoDerecho;
        nodo.hijoDerecho = auxiliar.hijoIzquierdo;
        auxiliar.hijoIzquierdo=nodo;
        nodo.fe=Math.max(obtenerFe(nodo.hijoIzquierdo), obtenerFe(nodo.hijoDerecho))+1;
        auxiliar.fe = Math.max(obtenerFe(auxiliar.hijoIzquierdo),obtenerFe(auxiliar.hijoDerecho))+1;
        return auxiliar;
    }
    //Rotacion Doble a la derecha
    public NodoArbolAVL rotacionDobleIzquierda(NodoArbolAVL nodo){
        NodoArbolAVL temporal;
        nodo.hijoIzquierdo=rotacionDerecha(nodo.hijoIzquierdo);
        temporal=rotacionIzquierda(nodo);
        return temporal;
    }
    //Rotacion Doble a la Izquierda
    public NodoArbolAVL rotacionDobleDerecha(NodoArbolAVL nodo){
        NodoArbolAVL temporal;
        nodo.hijoDerecho=rotacionIzquierda(nodo.hijoDerecho);
        temporal=rotacionDerecha(nodo);
        return temporal;
    }
    //Metodo para Insertar AVL
    public NodoArbolAVL insertarAVL(NodoArbolAVL nuevo, NodoArbolAVL subAr){
        NodoArbolAVL nuevoPadre = subAr;
        if(nuevo.dato<subAr.dato){
            if(subAr.hijoIzquierdo==null){
                subAr.hijoIzquierdo=nuevo;
            }else{
                subAr.hijoIzquierdo=insertarAVL(nuevo,subAr.hijoIzquierdo);
                if((obtenerFe(subAr.hijoIzquierdo)-obtenerFe(subAr.hijoDerecho)==2)){
                    if(nuevo.dato<subAr.hijoIzquierdo.dato){
                        nuevoPadre=rotacionIzquierda(subAr);
                    }else{
                        nuevoPadre = rotacionDobleIzquierda(subAr);
                    }
                }
            }
        }else if(nuevo.dato> subAr.dato){
            if(subAr.hijoDerecho==null){
                subAr.hijoDerecho=nuevo;
            }else{
                subAr.hijoDerecho=insertarAVL(nuevo,subAr.hijoDerecho);
                if((obtenerFe(subAr.hijoDerecho)-obtenerFe(subAr.hijoIzquierdo)==2)){
                    if(nuevo.dato>subAr.hijoDerecho.dato){
                        nuevoPadre = rotacionDerecha(subAr);
                    }else{
                        nuevoPadre = rotacionDobleDerecha(subAr);
                    }
                }
            }
        }else{
            System.out.println("Palabra Duplicada");
        }
        //ACtualizando la altura
        if((subAr.hijoIzquierdo==null) && (subAr.hijoDerecho!=null)){
            subAr.fe=subAr.hijoDerecho.fe+1;
        }else if((subAr.hijoDerecho==null)&& (subAr.hijoIzquierdo!=null)){
            subAr.fe=subAr.hijoIzquierdo.fe+1;
        }else{
            subAr.fe=Math.max(obtenerFe(subAr.hijoIzquierdo), obtenerFe(subAr.hijoDerecho))+1;
        }
        return nuevoPadre;
    }
    //Metodo para insertar
    public void insertar(int value){
        NodoArbolAVL nuevo = new NodoArbolAVL(value);
        if(raiz==null){
            raiz=nuevo;
        }else{
            raiz=insertarAVL(nuevo, raiz);
        }
    }
    //Arbol in order
    public void inOrden(NodoArbolAVL nodo){
        if(nodo!=null){
            inOrden(nodo.hijoIzquierdo);
            System.out.print(nodo.dato+", ");
            inOrden(nodo.hijoDerecho);
        }
    }
    // Arbol preorden
    public void preOrden(NodoArbolAVL nodo){
        if(nodo!=null){
            System.out.print(nodo.dato+", ");
            preOrden(nodo.hijoIzquierdo);
            preOrden(nodo.hijoDerecho);
        }
    }
    //Arbol postOrden
    public void postOrden(NodoArbolAVL nodo){
        if(nodo!=null){
            
            postOrden(nodo.hijoIzquierdo);
            postOrden(nodo.hijoDerecho);
            System.out.print(nodo.dato+", ");
        }
    }
}
