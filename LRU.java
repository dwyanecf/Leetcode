public class LRUCache {
    public int capacity=0;
    public int count=0;
    public Map<Integer,Node> map;
    public Node head,tail;
    
    public class Node{
        int key,value;
        Node pre,next;
        Node(int key,int value){
            this.key=key;
            this.value=value;
        }
        
    }
    
    public LRUCache(int capacity) {
        this.capacity=capacity;
        map=new HashMap<>();
        head=new Node(0,0);
        tail=new Node(0,0);
        head.next=tail;
        tail.pre=head;
    }
    
    public int get(int key) {
        Node n=map.get(key);
        if(n==null){
            return -1;
        }
        else{
            update(n);
            return n.value;
        }
    }
    public void add(Node n){
        Node after=head.next;
        n.pre=head;
        n.next=after;
        after.pre=n;
        head.next=n;
    }
    public void remove(Node n){
        Node after=n.next;
        Node before=n.pre;
        before.next=after;
        after.pre=before;
    }
    public void update(Node n){
        remove(n);
        add(n);
    }
    public void put(int key, int value) {
        Node n=map.get(key);
        if(n!=null){
            update(n);
            n.value=value;
        }else{
            Node node=new Node(key,value);
            add(node);
            count++;
            map.put(key,node);
            
            if(count>capacity){
                Node toDel=tail.pre;
                remove(toDel);
                count--;
                map.remove(toDel.key);
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */