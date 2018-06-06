package sequences;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class LinkedList {

    Sequence head = null;
    int size = 0;

    public boolean isEmpty() {
        return head == null;
    }

    public void addAtFront(Sequence newNode) {
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addAtIndex(Sequence newNode, int index) {
        if (!isEmpty() || index >= size) {
            Sequence temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            newNode.next = temp.next;
            temp.next = newNode;
            size++;
        } else {
            head = newNode;
        }
    }

    public void addAtBack(Sequence newNode) {
        if (isEmpty()) {
            head = newNode;
        } else {
            Sequence temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
            size++;
        }
    }

    public void deleteAtFront() {
        Sequence temp = head;
        head = head.next;
        temp = null;
        size--;
        System.gc();
    }

    public void deleteAtIndex(int index) {
        Sequence temp = head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        size--;
        System.gc();
    }

    public void deleteAtBack() {

        if (isEmpty()) {
            System.out.println("Empty List");
        } else {
            Sequence temp = head;
            while (temp.next.next != null) {
                temp = temp.next;
            }
            temp.next = null;
            size--;
            System.gc();
        }
    }

    public void printList() {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Sequence temp = head;
        try {
            while (temp != null) {
                bw.write(temp.toString());
                temp = temp.next;
            }
            bw.flush();
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) {

        LinkedList grades = new LinkedList();

        grades.printList();

    }

}
