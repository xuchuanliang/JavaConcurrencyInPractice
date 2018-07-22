package capter10.com.snail.b1;

public class LeftRightDeadlock {
    private final Object left = new Object();
    private final Object right = new Object();
    public void leftRight(){
        synchronized (left){
            synchronized (right){
                System.out.println("left->right");
            }
        }
    }
    public void rightLeft(){
        synchronized (right){
            synchronized (left){
                System.out.println("right->left");
            }
        }
    }
    public static void main(String[] args){
        LeftRightDeadlock leftRightDeadlock = new LeftRightDeadlock();
        leftRightDeadlock.leftRight();
        leftRightDeadlock.rightLeft();
    }
}
