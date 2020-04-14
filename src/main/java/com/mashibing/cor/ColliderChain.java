package com.mashibing.cor;

import com.mashibing.ConfigMgr;
import com.mashibing.GameObject;

import java.util.Arrays;
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


    public ColliderChain() {
        String colliders = ConfigMgr.getString("colliders");
        Arrays.stream(colliders.split(",")).forEach(s -> {
            try {
                Collider collider = (Collider) Class.forName(s).newInstance();
                chain.add(collider);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        chain.add(new TankBulletCollider());
        chain.add(new TankTankCollider());
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
