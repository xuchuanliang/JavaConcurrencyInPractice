package ConcurrencyPractice.capter02.code;

public class LazyInitRace {

    private LazyInitRace lazyInitRace = null;

    public synchronized   LazyInitRace getInstatnce(){
        if(null==lazyInitRace){
            lazyInitRace = new LazyInitRace();
        }
        return lazyInitRace;
    }

    private LazyInitRace(){}
}
