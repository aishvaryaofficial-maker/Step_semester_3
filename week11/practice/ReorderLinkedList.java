package week11.practice;

public class ReorderLinkedList {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static void reorderList(Node head) {
        if (head == null || head.next == null)
            return;

        Node slow = head, fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node second = slow.next;
        slow.next = null;
        Node prev = null;

        while (second != null) {
            Node next = second.next;
            second.next = prev;
            prev = second;
            second = next;
        }

        Node first = head;
        second = prev;

        while (second != null) {
            Node next1 = first.next;
            Node next2 = second.next;

            first.next = second;
            second.next = next1;

            first = next1;
            second = next2;
        }
    }
}
