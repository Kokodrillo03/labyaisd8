

import java.util.NoSuchElementException;

public class BST<T> {
	String retstr = null;
	private class Node{
		T value;
		Node left,right,parent;
		public Node(T v) {
			value=v;
		}
		public Node(T value, Node left, Node right, Node parent) {
			super();
			this.value = value;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}
	}		
	private Node root=null;

	public BST() {
	}

	public Node getNode(T toFind){
		return getNode((Comparable<T>) toFind,root);
	}

	private Node getNode(Comparable<T> tofind, Node n){
		if(n == null || n.value.equals(tofind))return n;
		if(tofind.compareTo(n.value)<0){
			return getNode(tofind,n.left);
		}else{
			return getNode(tofind,n.right);
		}
	}

	public T getElement(T toFind) {
		if(toFind==null || root == null)return null;
		Node actnode = root;
		Link findl = (Link) toFind;
		Link actlink = (Link) actnode.value;
		while(actnode!=null && findl!=actlink){
			actlink = (Link) actnode.value;
			if(findl.compareTo(actlink)>=0){
				actnode = actnode.right;
			}else{
				actnode = actnode.left;
			}
		}
		if(actlink==findl)return actnode.value;
		return null;
	}

	public T successor(T elem) {
		Node actnode = getNode(elem);
		if(actnode.right!=null){
			Node nextnode = actnode.right;
			while(nextnode.left!=null){
				nextnode = nextnode.left;
			}
			return nextnode.value;
		}else{
			Node parentnode = actnode.parent;
			while(actnode!=parentnode.left&&parentnode!=root){
				actnode = actnode.parent;
				parentnode = actnode.parent;
			}
			if(parentnode.left==actnode)return parentnode.value;
			return null;
		}
	}

	public void inorder(Node n){
		if(n == null) return;
		inorder(n.left);
		retstr += n.value.toString() + ", ";
		inorder(n.right);
	}

	public void preorder(Node n){
		if(n == null) return;
		retstr += n.value.toString() + ", ";
		preorder(n.left);
		preorder(n.right);
	}

	public void postorder(Node n){
		if(n == null) return;
		postorder(n.left);
		postorder(n.right);
		retstr += n.value.toString()  + ", ";
	}

	public String toStringInOrder() {
		if(root == null){
		return null;
		}
		retstr = "";
		inorder(root);
		retstr = retstr.substring(0, retstr.length()-2);
		return retstr;
	}

	public String toStringPreOrder() {
		if(root == null){
			return null;
		}
		retstr = "";
		preorder(root);
		retstr = retstr.substring(0, retstr.length()-2);
		return retstr;
	}

	public String toStringPostOrder() {
		if(root == null){
			return null;
		}
		retstr = "";
		postorder(root);
		retstr = retstr.substring(0, retstr.length()-2);
		return retstr;
	}


	public boolean add(T elem) {
		Node newnode = new Node(elem);
		if (root == null){
			root = newnode;
			return true;
		}
		Node actnode = root;
		Link newlink = (Link) newnode.value;
		while(true){
			Link actLink = (Link) actnode.value;
			if(newlink.compareTo(actLink) >= 0){
				if(actnode.right!=null) {
					actnode = actnode.right;
				}else{
					newnode.parent = actnode;
					actnode.right = newnode;
					return true;
				}
			}else{
				if(actnode.left!=null) {
					actnode = actnode.left;
				}else{
					newnode.parent = actnode;
					actnode.left = newnode;
					return true;
				}
			}
		}
	}


	public T remove(T value) {
		Node torem = getNode(value);
		if(root==null)return null;
		if(torem==null)return null;
		if(torem.left==null || torem.right==null){
			removes(torem);
			return value;
		}
		Node suc = getNode(successor(value));
		torem.value = suc.value;
		removes(suc);
		return value;
	}

	public void removes(Node torem){
		if(torem.left==null&&torem.right==null){
			Node temp = torem.parent;
			if(temp.left.equals(torem))temp.left=null;
			if(temp.right.equals(torem))temp.right=null;
			return;
		}
		if (torem.left != null && torem.right == null) {
			Node temp = torem.parent;
			if(temp.left.equals(torem))temp.left=torem.left;
			if(temp.right.equals(torem))temp.right=torem.left;
			return;
		}
		if(torem.left == null && torem.right != null){
			Node temp = torem.parent;
			if(temp.left.equals(torem))temp.left=torem.right;
			if(temp.right.equals(torem))temp.right=torem.right;
			return;
		}
	}
	
	public void clear() {
		// TODO
		root = null;
	}

	public int size() {
		return size(root);
	}

	private int size(Node node) {
		if (node == null) return(0);
		else {
			return(size(node.left) + 1 + size(node.right));
		}
	}

}
