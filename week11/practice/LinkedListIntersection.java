package week11.practice;

public class LinkedListIntersection {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node findIntersection(Node a, Node b) {
        Node p = a, q = b;

        while (p != q) {
            p = (p == null) ? b : p.next;
            q = (q == null) ? a : q.next;
        }

        return p;
    }
}
