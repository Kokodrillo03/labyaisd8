

import java.util.LinkedList;

public class HashTable{
    LinkedList arr[]; // use pure array
    private final static int defaultInitSize=8;
    private final static double defaultMaxLoadFactor=0.7;
    private int size;
    private final double maxLoadFactor;

    public HashTable() {
        this(defaultInitSize);
    }
    public HashTable(int size) {
        this(size,defaultMaxLoadFactor);
    }


    public HashTable(int initCapacity, double maxLF) {
        //TODO
        if(initCapacity<2)
            initCapacity=2;
        this.maxLoadFactor=maxLF;
        this.size =0;
        arr = new LinkedList[initCapacity];
        for(int i = 0; i < arr.length; i++){
            arr[i] = new LinkedList();
        }
    }

    public boolean add(Object elem) {
        if(elem == null)return false;
        this.arr[elem.hashCode()%arr.length].add(elem);
        this.size+=1;
        if((float) this.size/(float) this.arr.length>this.maxLoadFactor)doubleArray();
        return true;
    }


    private void doubleArray() {
        LinkedList[] arrn = new LinkedList[this.arr.length*2];
        for(LinkedList l : arrn){
            l = new LinkedList();
        }
        for(int i = 0; i < arr.length; i++){
            for(Object o : arr[i]){
                arrn[o.hashCode()% arrn.length].add(o);
            }
        }
    }


    @Override
    public String toString() {
        //TODO
        // use	IWithName x=(IWithName)elem;
        int size;
        String retstr="";
        for(int i = 0; i < arr.length; i++){
            retstr += i + ":";
            size = arr[i].size();
            if(size!=0){
                retstr += " ";
                for(int j = 0; j < size-1;i++){
                    IWithName x = (IWithName) arr[i].get(j);
                    retstr += x.getName() + ", ";
                }
                IWithName y = (IWithName) arr[i].get(size-1);
                retstr += y.getName();
            }
            retstr += "\n";
        }


        return retstr;
    }

    public Object get(Object toFind) {
        int ind = toFind.hashCode()%this.arr.length;
        int j = this.arr[ind].indexOf(toFind);
        if(j==-1)return null;
        return this.arr[ind].get(j);
    }

}

