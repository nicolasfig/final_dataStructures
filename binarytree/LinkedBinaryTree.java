
import java.lang.reflect.Method;

public class LinkedBinaryTree implements BinaryTree{

    BinaryTreeNode root;
    
    // class data members
    
    static Method visit; // for traversal
    // parameters for the visit method
    static Object [] visitArgs = new Object [1];
    static int count; // counter
    // type of parameter for visit
    static Class [] paramType = {BinaryTreeNode.class};
    static Method theAdd1; // Method to increment count by 1
    static Method theOutput; // Method to output a node
    
    // method to initialize class data members
    static{
        try{
            Class linkedBinaryTree = LinkedBinaryTree.class;
            theAdd1 = linkedBinaryTree.getMethod("add1", paramType);
            theOutput = linkedBinaryTree.getMethod("output",paramType);
        }catch(Exception e){}
    }
    
    // class methods
    public static void output(BinaryTreeNode node){
        System.out.println(node.element + " ");
    }
    
    public static void add1(BinaryTreeNode node){
        count++;
    }
    
    /**
     * 
     * @return true if tree is empty 
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }
    /**
     * 
     * @return root if tree is not empty null otherwise
     */
    @Override
    public Object root() {
        return (root == null) ? null: root.element;
    }

    @Override
    public void makeTree(Object root, Object left, Object right) {
        this.root = new BinaryTreeNode(root,
        ((LinkedBinaryTree) left).root, 
        ((LinkedBinaryTree) right).root);
    }

    @Override
    public BinaryTree removeLeftSubTree() {
        if(root == null)
            throw new IllegalArgumentException("tree is empty");
        
        // detach left subtree and save in leftSubTree
        LinkedBinaryTree leftSubTree = new LinkedBinaryTree();
        leftSubTree.root = root.leftChild;
        root.leftChild = null;
        return (BinaryTree) leftSubTree;
    }

    @Override
    public BinaryTree removeRightSubTree() {
        if(root == null)
            throw new IllegalArgumentException("tree is empty");
        
        // detach right subtree and save in rightSubTree
        LinkedBinaryTree rightSubTree = new LinkedBinaryTree();
        rightSubTree.root = root.rightChild;
        root.rightChild = null;
        return (BinaryTree) rightSubTree;
    }

    @Override
    public void preOrder(Method visit) {
        this.visit = visit;
        thePreOrder(root);
    }
    
    static void thePreOrder(BinaryTreeNode node){
        if(node != null){
            visitArgs[0] = node;
            // visit tree root
            try{
                visit.invoke(null, visitArgs);
            }catch(Exception e){
                System.out.println(e);
            }
            thePreOrder(node.leftChild); // do left subtree 
            thePreOrder(node.rightChild); // do right subtree
        }
    }

    // Output the elements in preorder 
    public void preOrderOutput(){
        preOrder(theOutput);
    }
    
    @Override
    public void inOrder(Method visit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postOrder(Method visit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void levelOrder(Method visit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
