package test_files;

import java.util.ArrayList;
import java.util.List;

public class Tree {

   private Node root;

   public Tree() {
      root = new Node();
   }

   public boolean insert(int x) {
      return root.insert(x);
   }

   public int size() {
      return root.size;
   }

   public int size(int x) {

      Node node = root.search(x);

      if (!node.data.contains(x)) // Check if the data is at the node
         return 0;

      // find size of subtree
      return node.size;
   }

   public int get(int x) {

      if (size() == 0 || x < 0 || x > size() - 1)
         throw new IndexOutOfBoundsException("x is out of bounds");

      return root.get(x);
   }

   /**
    * Method for debugging purposes, NOT used in get()
    *
    * @return
    */
   public List<Integer> inOrderList() {
      return root.inOrderArray(new ArrayList<>());
   }


   class Node implements Comparable<Node> {

      public static final int MAX_DATA = 2;

      public List<Integer> data;
      public Node parent;
      public List<Node> children;
      public int size;

      Node() {
         data = new ArrayList<>(3);
         parent = null;
         children = new ArrayList<>(4);
         size = 0;
      }

      Node(int data, Node parent) {
         this.data = new ArrayList<>(3);
         this.parent = parent;
         children = new ArrayList<>(4);
         this.data.add(data);
         size = 1;
      }

      Node(int nodeVal, Node parent, Node child1, Node child2) {
         data = new ArrayList<>(3);
         children = new ArrayList<>(4);
         this.parent = parent;
         size = child1.size + child2.size + 1;

         data.add(nodeVal); // Add child

         // Add grandchildren
         children.add(child1);
         child1.parent = this;
         children.add(child2);
         child2.parent = this;

         children.sort(Node::compareTo); // sort the children
      }

      private int get(int x) {

         int subTreeSize = 0;

         for (int i = 0; i < data.size(); i++) {

            if (!isLeaf())
               subTreeSize = children.get(i).size; // Get subSize of subtree

            if (subTreeSize > x)
               return children.get(i).get(x);      // Step in if within range

            if (subTreeSize - x == 0) // If subSize minus x is 0 found target
               return data.get(i);

            x -= subTreeSize + 1;     // subtract subSize and current
         }

         return children.get(children.size() - 1).get(x);
      }


      boolean insert(int x) {

         // Search for the node
         Node node = search(x);

         // Check if the value is a duplicate
         if (node.isDuplicate(x))
            return false;

         // Add data to node and then sort
         node.addVal(x);

         // Check if a split is needed
         if (node.data.size() > MAX_DATA)
            node.split();

         return true;
      }

      /**
       * Call on a node
       */
      void split() {

         // Check if this has a parent
         if (parent == null)
            addRoot();

         // Add median from child to parent
         parent.data.add(this.data.get(1));
         parent.data.sort(Integer::compareTo);

         // Remove child node
         parent.children.remove(this);

         // Add new subtrees
         parent.addChildren(this);

         // Check if another split is needed
         if (parent.data.size() > MAX_DATA)
            parent.split();
      }

      Node search(int x) {

         for (int i = 0; i < data.size(); i++) {
            int compare = data.get(i).compareTo(x); // Compare the data
            if (compare == 0)
               return this;
            if (compare > 0 && !isLeaf())
               return children.get(i).search(x);
         }

         if (isLeaf())
            return this;

         // If at end, no recursion
         return children.get(children.size() - 1).search(x);
      }

      private void addVal(int x) {
         data.add(x);
         data.sort(Integer::compareTo);
      }

      /**
       * Call on Parent
       *
       * @param dummyNode
       */
      private void addChildren(Node dummyNode) {
         if (!dummyNode.children.isEmpty()) {
            this.children.add(new Node(dummyNode.data.get(0), dummyNode.parent,
                    dummyNode.children.get(0), dummyNode.children.get(1)));
            this.children.add(new Node(dummyNode.data.get(2), dummyNode.parent,
                    dummyNode.children.get(2), dummyNode.children.get(3)));
         }
         else {
            this.children.add(new Node(dummyNode.data.get(0), this));
            this.children.add(new Node(dummyNode.data.get(2), this));
         }
         this.children.sort(Node::compareTo);
      }

      private void addRoot() {
         Node newParent = new Node();
         parent = newParent;
         newParent.size = size;
         root = newParent;
      }

      private boolean isLeaf() {
         return children.isEmpty();
      }

      private boolean isDuplicate(int x) {
         for (int i : data) // Check if any of the data are duplicates
            if (i == x)
               return true;
         incrementSizes();    // Change the subTree sizes in the path, if false
         return false;
      }

      private void incrementSizes() {
         Node node = this;
         while (node != null) {
             node.size++;
             node = node.parent;
         }
      }

      /**
       * NOT USED in get()
       *
       * @param list
       * @return
       */
      private ArrayList<Integer> inOrderArray(ArrayList<Integer> list) {

         for (int i = 0; i < data.size(); i++) {
            if (!isLeaf())
               children.get(i).inOrderArray(list);

            list.add(data.get(i));
         }

         if (!isLeaf())
            return children.get(children.size() - 1).inOrderArray(list);

         return list;
      }

      @Override
      public int compareTo(Node o) {
         return this.data.get(0).compareTo(o.data.get(0));
      }
   }
}
