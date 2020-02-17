/**
 * COMP 410
 *See inline comment descriptions for methods not described in interface.
 *
*/
package LinkedList_A1;


public class LinkedListImpl implements LIST_Interface {
  Node headCell; //this will be the entry point to your linked list (the head)
  Node lastCell; // this is the Node at the end of the list... the starting place
                 // if you wanted to traverse the list backwards
  int len;

  public LinkedListImpl(){//this constructor is needed for testing purposes. Please don't modify!
    headCell = null; //Note that the root's data is not a true part of your data set!
    lastCell = null;
  }
  
  //implement all methods in interface, and include the getRoot method we made for testing 
  // purposes. Feel free to implement private helper methods!

 // add the fields you need to add to make it work... like a 
  
  public Node getRoot(){ //leave this method as is, used by the grader to grab your linkedList easily.
    return headCell;
  }
  public Node getLast(){ //leave this method as is, used by the grader to grab your linkedList easily.
    return lastCell;
  }


public boolean insert(double elt, int index) {
	//edge cases
	Node c = new Node(elt);
	
	if (index<0 ||index>size()) {
		return false;
	}
	
	if (index == 0) {
		if (isEmpty()) {
		headCell = c;
		lastCell = c;
		len++;
		return true;
		}else {
			Node temp = headCell;
			headCell = c;
			c.next = temp;
			temp.prev = c;
			len++;
			if (size() == 2) {
				lastCell = temp;
			}
			return true;
		}
	}
	if (index == size()) {
		c.prev = lastCell;
		lastCell.next = c;
		lastCell = c;
		len++;
		return true;
	}

	
	Node curr = headCell;
	while(index>0) {
		  curr=curr.next;
		  index--;
	  }
	  Node tmp=curr.prev;
	  curr.prev = c;
	  tmp.next=c;
	  c.prev=tmp;
	  c.next=curr;
    
	
	len++;
	
	return true;
}



public boolean insort(double elt) {
	Node new_node = new Node(elt);

	if (isEmpty()) {
		headCell = new_node;
		lastCell = new_node;
		len++;
		return true;
		
	}
	if (headCell.next == null) {
		if (headCell.data>=new_node.data) {
			Node tmp = headCell;
			headCell = new_node;
			new_node.next = tmp;
			tmp.prev = new_node;
			lastCell = tmp;
			len++;
			return true;
			
		}else if(headCell.data<=new_node.data) {
			headCell.next = new_node;
			new_node.prev = headCell;
			lastCell = new_node;
			len++;
			return true;
		}
	}
	if (headCell.next != null) {
		if (headCell.data>= new_node.data) {
			Node temp = headCell;
			headCell = new_node;
			new_node.next = temp;
			temp.prev = new_node;
			len++;
			return true;
		}else {
			
			
			
			Node curr = headCell;
			while(curr.data<new_node.data&&curr.next !=null){
				curr = curr.next;
			}
			if(curr.next == null&&curr.data <=new_node.data) {
				curr.next = new_node;
				new_node.prev = curr;
				len++;
				lastCell = new_node;
				return true;
			}
			
			
			Node temp = curr.prev;
			curr.prev = new_node;
			temp.next = new_node;
			new_node.next = curr;
			new_node.prev = temp;
			len++;
			return true;
		}
	}
	return false;

	
	
}

@Override
public boolean remove(int index) {
	//edge case
	if(index<0 || index>size()) {
		return false;
	}
	
	if (index == 0) {
		if(isEmpty()) {
			return true;
		}else {
			Node tmp = headCell;
			headCell = headCell.next;
			tmp = null;
			len--;
			return true;
		}
	}
	
	if (index == size()) {
		lastCell = null;
		len--;
	}
	
	Node curr = headCell;

	while(index>0) {
		curr = curr.next;
		index--;
		
	}
	if(curr.next==null) {
		lastCell = curr.prev;
		curr.prev.next = null;
		curr = null;
		len--;
		return true;
	}
	Node temp1 = curr.prev;
	Node temp2 = curr.next;
	temp1.next = temp2;
	temp2.prev = temp1;
	curr = null;
	len--;
	
	return true;
}

@Override
public double get(int index) {
	if(index<0 || index>=size()) {
		return Double.NaN;
	}
	
	Node curr = headCell;
	while(index>0) {
		curr = curr.next;
		index--;	
	}
	
	return curr.data;
}

@Override
public int size() {

	return len;
}

@Override
public boolean isEmpty() {

	return size() == 0;
}

@Override
public void clear() {
	for(Node x=headCell;x!= null;) {
		Node next = x.next;
		x.prev = null;
		x.next = null;
		x = null;
		x = next;
	}
	headCell = lastCell = null;
	len = 0;
}
}