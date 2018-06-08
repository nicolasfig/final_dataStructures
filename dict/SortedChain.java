public class SortedChain implements Dictionary{
    
    protected static class SortedChainNode{
        protected Comparable key;
        protected Object element;
        protected SortedChainNode next;
    }

    // attributes

    protected SortedChainNode firstNode;
    protected int size;

    // methods 
    public boolean isEmpty(){return size == 0};
    public Object get(Object theKey){
        SortedChainNode currentNode = firstNode;
        // search for the key
        while(currentNode != null && currentNode.key.compareTo(theKey)< 0 ){
            currentNode = currentNode.next;
        }
        // verify match
        if(currentNode != null && currentNode.key.equals(theKey)){
            return currentNode.element;
        }
        return null;
    }
    // removes and returns the removed object
    public Object put(Object theKey, Object theElement){
        SortedChainNode p = firstNode, tp = null; // tp trails p
        while(p != null && p.key.compareTo(theKey) < 0){
            tp = p;
            p = p.next;
        }
        // check for matching element
        if(p != null p.key.equals(theKey)){
            // replace previous element
            Object elemetToReturn = p.element;
            p.element = theElement;
            return elemetToReturn;
        }
        // no match create a new node for theElement
        SortedChainNode q = new SortedChainNode(theKey, theElement, p);
        // instert node after tp
        if(tp == null){
            firstNode = q;
        }else{
            tp.next = q;
        }
        size++;
        return null;
    }
    public Object remove(Object theKey){
        SortedChainNode p = firstNode, tp = null; // tp trails p
        while(p != null && p.key.compareTo(theKey) < 0){
            tp = p;
            p = p.next;
        }
        // verify match
        if(p != null && p.key.equals(theKey)){
            Object e = p.element; // element
            // remove from the chain
            if(tp == null){
                firstNode = p.next;
            }else{
                tp.next = p.next;
            }
            size--;
            return e;
        }
        return null;
    }
    public String toString(){
        return "[" + theKey+ ":" + theElement+ "]";
    }

    public static void main(String [] args){

    }
}