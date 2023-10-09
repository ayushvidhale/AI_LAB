package Astar;
import java.util.*;

class Node {
    Node parent;
    Node child;
    ArrayList<ArrayList<Integer>> board;
    int x, y; // board position
    int cost;
    int Hval;
    int F_score;
}

class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(Node lhs, Node rhs) {
        return (lhs.Hval + lhs.cost) - (rhs.Hval + rhs.cost);
    }
}

public class Main {
    static ArrayList<ArrayList<Integer>> goal = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(1, 2, 3)),
            new ArrayList<>(Arrays.asList(4, 5, 6)),
            new ArrayList<>(Arrays.asList(7, 8, 0))
    ));

    public static int H(ArrayList<ArrayList<Integer>> a, ArrayList<ArrayList<Integer>> b) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (a.get(i).get(j) != 0 && !a.get(i).get(j).equals(b.get(i).get(j))) {
                    count++;
                }
            }
        }
        return count;
    }

    public static boolean safe(int x, int y) {
        return (x >= 0 && x < 3) && (y >= 0 && y < 3);
    }

    public static void printBoard(Node head) {
        for (ArrayList<Integer> row : head.board) {
            for (int i : row) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println("Cost: " + head.F_score);
    }

    public static Node createNode(int oldx, int oldy, int X, int Y, Node parent, int level) {
        Node newNode = new Node();
        newNode.parent = parent;
        newNode.cost = level;
        newNode.board = new ArrayList<>();
        for (ArrayList<Integer> row : parent.board) {
            newNode.board.add(new ArrayList<>(row));
        }
        Collections.swap(newNode.board.get(parent.x), parent.y, newNode.board.get(X).get(Y));
        newNode.Hval = H(newNode.board, goal);
        newNode.x = X;
        newNode.y = Y;
        newNode.F_score = newNode.cost + newNode.Hval;
        return newNode;
    }
    

    public static void printVector(ArrayList<Node> head) {
        System.out.print("Close: ");
        for (Node x : head) {
            System.out.print(x.F_score + " ");
        }
        System.out.println();
    }

    public static void printQueue(PriorityQueue<Node> open) {
        System.out.print("Open: ");
        for (Node node : open) {
            System.out.print(node.F_score + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node();
        head.parent = null;
        head.board = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(1, 3, 6)),
                new ArrayList<>(Arrays.asList(4, 2, 0)),
                new ArrayList<>(Arrays.asList(7, 5, 8))
        ));
        head.Hval = H(head.board, goal);
        head.x = 1;
        head.y = 2;
        head.cost = 0;
        head.F_score = head.Hval + head.cost;

        PriorityQueue<Node> open = new PriorityQueue<>(new NodeComparator());
        ArrayList<Node> close = new ArrayList<>();
        ArrayList<Integer> row = new ArrayList<>(Arrays.asList(1, 0, -1, 0));
        ArrayList<Integer> col = new ArrayList<>(Arrays.asList(0, -1, 0, 1));

        Node root = createNode(0, 2, 0, 2, head, 0);
        open.add(root);
        printBoard(head);

        while (!open.isEmpty()) {
            Node temp = open.poll();
            close.add(temp);

            if (temp.Hval == 0) {
                printBoard(temp);
                printQueue(open);
                printVector(close);
                break;
            }

            for (int i = 0; i < 4; i++) {
                if (safe(temp.x + row.get(i), temp.y + col.get(i))) {
                    Node child = createNode(temp.x, temp.y, temp.x + row.get(i), temp.y + col.get(i), temp, temp.cost + 1);
                    open.add(child);
                }
            }

            printBoard(temp);
            printQueue(open);
            printVector(close);
            System.out.println("+++++++++++++++++++");
        }
    }
}
