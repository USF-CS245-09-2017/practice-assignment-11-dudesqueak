import java.lang.*;

public class Hashtable {
    HashNode[] buckets; // array of hash node buckets
    int num_buckets; // int value of the size of table
    int size;

    public Hashtable() {
        num_buckets = 250000;
        buckets = new HashNode[num_buckets];
        size = 0;
    }

    class HashNode {
        public String key;
        public String value;
        public HashNode next;

        public HashNode(String key, String value) {
            // declaring the Hash Node class with all the essential data
            this.key = key;
            this.value = value;
            this.next = null;
            size = 0;
        }
    }


    public int getBucket(String key) { //gets bucket
        int thehashCode = key.hashCode();
        return Math.abs((thehashCode % num_buckets));
    }

    boolean containsKey (String key){
        int bucketidfinder = getBucket(key);
        HashNode currentnode = buckets[bucketidfinder];
        while (currentnode != null) {
            if (currentnode.key.equals(key))
                return true;
            //node = node.next;
        }
        return false;

    }

    String get (String key){
        int bucketidfinder = getBucket(key);
        HashNode currentnode = buckets[bucketidfinder];
        while (currentnode != null) {
            if (currentnode.key.equals(key))
                return currentnode.value;
            currentnode = currentnode.next;
        }
        return null;

    }



    void put (String key, String value){
        int bucketidfinder = getBucket(key);
        HashNode currentnode = buckets[bucketidfinder];
        while (currentnode != null) {
            if (currentnode.key.equals(key)) {
                currentnode.key = key;
                currentnode.value = value;
                return;
            }
            currentnode = currentnode.next;
        }
        size++;
        // put new hash node into the buckets
        HashNode thenewnode = new HashNode(key, value);
        thenewnode.next = buckets[bucketidfinder];
        buckets[bucketidfinder] = thenewnode;



    }

    String remove (String key){
        // remove function that goes through and removes the specific key value
        // hash node pair
        int bucketidfinder = getBucket(key);
        HashNode currentnode = buckets[bucketidfinder];
        HashNode prevnode = null;
        while (currentnode.next != null && !currentnode.key.equals(key)) {
            prevnode = currentnode;
            currentnode = currentnode.next;
        }
        // conditions based on the location of the hashnode
        if (currentnode.key.equals(key))
            if (currentnode == null) {
                return null;
            }
        if (prevnode == null) {
            buckets[bucketidfinder] = currentnode.next;
        }
        else {
            prevnode.next = currentnode.next;
            size--;
        }
        return currentnode.value;
    }



}
