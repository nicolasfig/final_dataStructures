public class AVLtree<T extends Comparable<? super T>>
{

    private AVLnode<T> root;

    public AVLtree( )
    {
        root = null;
    }

    public boolean isEmpty( )
    {
        return root == null;
    }

    public void insert( T x )
    {
        root = insert(root, x);
    }

    private AVLnode<T> insert(AVLnode<T> nodo, T x){
        if(nodo == null){
            return new AVLnode<T>(x);
        }
        int comparation = x.compareTo(nodo.getElement());
        if(comparation < 0 ){
            nodo.setLeftChild(insert(nodo.getLeftChild(), x));
        }
        else if(comparation > 0){
            nodo.setRightChild(insert(nodo.getRightChild(), x));
        }
        return balance(nodo);
    }

    public void remove( T x )
    {
        root = remove(root, x);
    }

    private AVLnode<T> remove(AVLnode<T> nodo, T x)
    {
        if(nodo == null)
            return null;
        int comparation = x.compareTo(nodo.getElement());
        if( comparation < 0){
            nodo.setLeftChild(remove(nodo.getLeftChild(), x));
        }
        else if(comparation > 0){
            nodo.setRightChild(remove(nodo.getRightChild(), x));
        }
        else{
            if(nodo.getLeftChild() != null && nodo.getRightChild() != null){
                nodo.setElement(findMin(nodo.getRightChild()));
                nodo.setRightChild(remove(nodo.getRightChild(), nodo.getElement()));
            }
            else{
                nodo = (nodo.getLeftChild() != null) ? nodo.getLeftChild() : nodo.getRightChild();
            }
        }
        return balance( nodo );
    }

    public T findMin()
    {
        return findMin(root);
    }

    public T findMin(AVLnode<T> nodoInicial)
    {
        AVLnode<T> nodo = nodoInicial;
        T elemento = null;
        while(nodo != null){
            elemento = nodo.getElement();
            nodo = nodo.getLeftChild();
        }
        return elemento;
    }

    public AVLnode<T> search(T x){
        AVLnode<T> nodo = root;
        while(nodo != null){
            int comparation = x.compareTo(nodo.getElement());
            if(comparation == 0) return nodo;
            if(comparation < 0){
                nodo = nodo.getLeftChild();
            }
            else{
                nodo = nodo.getRightChild();
            }
        }
        return nodo;
    }

    public boolean contains( T x )
    {
        return !(search(x) == null);
    }

    private int height(){
        return height(root);
    }

    private int height( AVLnode<T> nodo )
    {
        return nodo == null ? -1 : nodo.getHeight();
    }

    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    private void printTree( AVLnode<T> nodo )
    {
        if( nodo != null )
        {
            printTree( nodo.getLeftChild() );
            System.out.println( nodo );
            printTree( nodo.getRightChild() );
        }
    }

    private AVLnode<T> rotateWithLeftChild( AVLnode<T> k2 )
    {
        AVLnode<T> k1 = k2.getLeftChild();
        k2.setLeftChild(k1.getRightChild());
        k1.setRightChild(k2);
        k2.setHeight(Math.max( height( k2.getLeftChild() ), height( k2.getRightChild() ) ) + 1);
        k1.setHeight(Math.max( height( k1.getLeftChild() ), k2.getHeight() ) + 1);
        return k1;
    }

    private AVLnode<T> rotateWithRightChild( AVLnode<T> k1 )
    {
        AVLnode<T> k2 = k1.getRightChild();
        k1.setRightChild(k2.getLeftChild());
        k2.setLeftChild(k1);
        k1.setHeight(Math.max( height( k1.getLeftChild() ), height( k1.getRightChild() ) ) + 1);
        k2.setHeight(Math.max( height( k2.getRightChild() ), k1.getHeight() ) + 1);
        return k2;
    }

    private AVLnode<T> doubleWithLeftChild( AVLnode<T> k3 )
    {
        k3.setLeftChild(rotateWithRightChild( k3.getLeftChild() ));
        return rotateWithLeftChild( k3 );
    }

    private AVLnode<T> doubleWithRightChild( AVLnode<T> k1 )
    {
        k1.setRightChild(rotateWithLeftChild( k1.getRightChild() ));
        return rotateWithRightChild( k1 );
    }

    private static final int ALLOWED_IMBALANCE = 1;

    private AVLnode<T> balance( AVLnode<T> nodo )
    {
        if( nodo == null )
            return nodo;

        if( height( nodo.getLeftChild() ) - height( nodo.getRightChild() ) > ALLOWED_IMBALANCE )
            if( height( nodo.getLeftChild().getLeftChild() ) >= height( nodo.getLeftChild().getRightChild() ) )
                nodo = rotateWithLeftChild( nodo );
            else
                nodo = doubleWithLeftChild( nodo );
        else
        if( height( nodo.getRightChild() ) - height( nodo.getLeftChild() ) > ALLOWED_IMBALANCE )
            if( height( nodo.getRightChild().getRightChild() ) >= height( nodo.getRightChild().getLeftChild() ) )
                nodo = rotateWithRightChild( nodo );
            else
                nodo = doubleWithRightChild( nodo );

        nodo.setHeight(Math.max( height( nodo.getLeftChild() ), height( nodo.getRightChild() ) ) + 1);
        return nodo;
    }

    public void checkBalance( )
    {
        checkBalance( root );
    }

    private int checkBalance( AVLnode<T> nodo) {
        if (nodo == null)
            return -1;

        if (nodo != null) {
            int hl = checkBalance(nodo.getLeftChild());
            int hr = checkBalance(nodo.getRightChild());
            if (Math.abs(height(nodo.getLeftChild()) - height(nodo.getRightChild())) > 1 ||
                    height(nodo.getLeftChild()) != hl || height(nodo.getRightChild()) != hr)
                System.out.println("Arbol no balanceado :(");
        }
        return height(nodo);
    }

    // Test program
    public static void main( String [ ] args ) throws Exception
    {
        AVLtree<String> usuarios = new AVLtree<>();
        System.out.println("Vacio: " + usuarios.isEmpty());
        usuarios.insert("hola");
        usuarios.insert("esta");
        usuarios.insert("es");
        System.out.println("hola " + usuarios.contains("hola"));
        System.out.println("prueba " + usuarios.contains("prueba"));
        System.out.println("arboles " + usuarios.contains("arboles"));
        usuarios.insert("una");
        usuarios.insert("prueba");
        usuarios.insert("de");
        usuarios.insert("arboles");
        usuarios.insert("binarios");
        usuarios.insert("de");
        usuarios.insert("busqueda");

        System.out.println("Vacio: " + usuarios.isEmpty());
        System.out.println("hola " + usuarios.contains("hola"));
        System.out.println("prueba " + usuarios.contains("prueba"));
        System.out.println("arboles " + usuarios.contains("arboles"));
        System.out.println("minimo: " + usuarios.findMin());
        System.out.println("minimo desde prueba: " + usuarios.findMin(usuarios.search("prueba")));
        System.out.println("minimo desde es: " + usuarios.findMin(usuarios.search("es")));
        System.out.println("altura: " + usuarios.height());
        System.out.println("altura desde prueba: " + usuarios.height(usuarios.search("prueba")));
        System.out.println("altura desde es: " + usuarios.height(usuarios.search("es")));

        usuarios.remove("ahora");
        usuarios.remove("haremos");
        usuarios.remove("la");
        usuarios.remove("prueba");
        usuarios.remove("de");
        usuarios.remove("eliminacion");
        usuarios.remove("en");
        usuarios.remove("arboles");
        System.out.println("hola " + usuarios.contains("hola"));
        System.out.println("prueba " + usuarios.contains("prueba"));
        System.out.println("arboles " + usuarios.contains("arboles"));
        System.out.println("minimo: " + usuarios.findMin());
        System.out.println("altura: " + usuarios.height());

        AVLtree<Integer> test = new AVLtree<>();

        for(int i=0;i<=20;i++){
            test.insert(new Integer(i));
        }

        System.out.println("solucion: " + (test.height() + 1));

    }
}


