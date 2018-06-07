import java.util.*;
public class Heap<T extends Comparable<? super T>> implements PriorityQueue<T> {

    T[] BinaryHeap;
    int size;

    public Heap(int initialCapacity){
        if(initialCapacity < 1)throw new IllegalArgumentException("initialCapacity must be >= 1");
        BinaryHeap = ( T[] ) new Comparable[ initialCapacity + 1];
        size = 0;
    }

    public Heap(){
        this(10);
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public T getMax(){
        return (size == 0) ? null : BinaryHeap[1];
    }

    public void put(T theObject ){
        if(size == BinaryHeap.length - 1){ //The array is full
            T[] old = BinaryHeap;
            BinaryHeap = (T[]) new Comparable[2 * (size+1)];
            System.arraycopy(old, 0, BinaryHeap, 0, size + 1);
        }
        int currentNode = ++size;
        while(currentNode != 1 && BinaryHeap[currentNode / 2].compareTo(theObject) < 0){
            BinaryHeap[currentNode] = BinaryHeap[currentNode / 2];
            currentNode /=2;
        }
        BinaryHeap [currentNode] = theObject;
    }

    public T removeMax(){
        if(size == 0) return null;
        T maxElement = BinaryHeap[1];
        T lastElement = BinaryHeap[size--];
        int currentNode = 1;
        int child = 2;
        while(child <= size){
            if(child < size && BinaryHeap[child].compareTo(BinaryHeap[child + 1]) < 0)
                child++;
            if(lastElement.compareTo(BinaryHeap[child]) >=0 )
                break;
            BinaryHeap[currentNode] = BinaryHeap[child];
            currentNode = child;
            child *= 2;
        }
        BinaryHeap[currentNode] = lastElement;
        return maxElement;
    }

    public void initialize(T[] theList) {
        int theSize = theList.length;
        BinaryHeap = (T[]) new Comparable [ theSize + 1];
        for(int i=1; i<BinaryHeap.length; i++)
            BinaryHeap[i] = theList[i - 1];
        size = theSize;
        for(int root = size / 2; root >= 1 ;root --) {
            T rootElement = BinaryHeap[root];
            int child = 2 * root;
            while (child <= size) {
                if (child < size && BinaryHeap[child].compareTo(BinaryHeap[child + 1]) < 0)
                    child++;
                if (rootElement.compareTo(BinaryHeap[child]) >= 0)
                    break;
                BinaryHeap[child / 2] = BinaryHeap[child];
                child *= 2;
            }
            BinaryHeap[child / 2] = rootElement;
        }
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("The " + size + " elements are [ ");
        if(size > 0){
            s.append(BinaryHeap[1]);
            for(int i = 2; i <= size ; i++){
                s.append(", " + Objects.toString(BinaryHeap[i]));
            }
        }
        s.append("]");
        return new String(s);
    }



    public static void main(String[] args){
        Heap<Integer> h = new Heap<Integer>(10);
        h.put(new Integer(10));
        h.put(new Integer(20));
        h.put(new Integer(5));
        System.out.println(h.removeMax());
        System.out.println(h.removeMax());
        h.put(new Integer(15));
        h.put(new Integer(0));
        System.out.println(h.removeMax());
        System.out.println(h.removeMax());
        System.out.println(h.removeMax());
        System.out.println(h.removeMax());

        Heap<Estudiante> hs = new Heap<>();
        hs.put(new Estudiante(13, "50", "Abelardo"));
        hs.put(new Estudiante(6, "27", "Benito"));
        hs.put(new Estudiante(2, "50", "Cesidia"));
        hs.put(new Estudiante(15, "1", "Dominga"));
        hs.put(new Estudiante(7, "100", "Epifanio"));
        hs.removeMax();
        hs.put(new Estudiante(8, "3", "Fulano"));
        hs.put(new Estudiante(34, "100", "Gregoria"));
        hs.put(new Estudiante(3, "270", "Hermelando"));
        hs.removeMax();
        hs.removeMax();
        hs.put(new Estudiante(28, "1", "Iscariote"));
        hs.put(new Estudiante(10, "28", "Jacinto"));
        System.out.println(hs);
    }

    static class Estudiante implements Comparable<Estudiante>{
        Integer id;
        String codigo;
        String nombre;

        public int compareTo(Estudiante x){
            if (codigo.compareTo(x.codigo) < 0 || (codigo.compareTo(x.codigo) == 0 && id > x.id)) return 1;
            if (codigo.equals(x.codigo) && id == x.id) return 0;
            return -1;
        }

        Estudiante(){
            codigo = null;
            nombre = null;
            id = 0;
        }

        Estudiante(int id, String codigo, String nombre){
            this.id = id;
            this.codigo = codigo;
            this.nombre = nombre;
        }

        public String toString(){
            return ((Character)nombre.charAt(0)).toString();
        }
    }
}