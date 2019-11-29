package JavaCoreThreadPatten.capter03;

public class SafeCircularSeqGenerator {
    private short sequence = 1;
    public synchronized short nextSequen(){
        if(sequence >= 99){
            sequence = 0;
        }else {
            sequence++;
        }
        return sequence;
    }

    public short getSequence() {
        return sequence;
    }
}
