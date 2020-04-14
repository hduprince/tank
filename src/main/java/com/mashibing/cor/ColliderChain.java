package com.mashibing.cor;

import com.mashibing.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * @create: 2020-04-14 10:53
 **/
public class ColliderChain implements Collider{

    List<Collider> chain = new LinkedList<>();

    public void add(Collider collider){
        chain.add(collider);
    }

    public void add(List<Collider> colliders){
        chain.addAll(colliders);
    }


    public void remove(Collider collider){
        chain.remove(collider);
    }


    @Override
    public boolean collide(GameObject o1, GameObject o2) {

        for (int i = 0; i < chain.size(); i++) {
            if(!chain.get(i).collide(o1, o2)){
                return false;
            }
        }
        return true;
    }
}
