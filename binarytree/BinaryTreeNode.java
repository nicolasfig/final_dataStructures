
public class BinaryTreeNode {

    Object element;
    BinaryTreeNode leftChild;
    BinaryTreeNode rightChild;

    public BinaryTreeNode() {
    }

    public BinaryTreeNode(Object theElement) {
        element = theElement;
    }

    public BinaryTreeNode(Object theElement,
            BinaryTreeNode theleftChild,
            BinaryTreeNode therightChild) {
            
            element = theElement;
            leftChild = theleftChild;
            rightChild = therightChild;          
    }
    
    public String toString(){
        return element.toString();
    }
}
