package capter04.com.snail;

import capter03.anno.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

/**
 * 封闭机制来确保线程安全
 */
@ThreadSafe
public class PersonSet {

    private final Set<Person> mySet = new HashSet<>();

    public synchronized void addPerson(Person p){
        mySet.add(p);
    }

    public synchronized boolean containsPerson(Person p){
        return mySet.contains(p);
    }
}
