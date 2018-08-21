package net.cofares;

import java.util.ArrayList;

/**
 *
 * @author pfares
 * @param <D>
 */
public class  Source<D> {
    ArrayList<GEventListener> listListener;
    
    public Source() {
         listListener=new ArrayList<>();
    }
    public void addGEventListener(GEventListener listener) {
        listListener.add(listener);
    }
    public void removeGEventListener(GEventListener listener) {
        listListener.add(listener);
    }
    
    public void dispatch(GEvent<Source<D>,D> ev) {
        listListener.forEach((gel) ->  gel.action(ev));
    }
    public GEvent<Source<D>,D> genEvent(D d) {
        return new GEvent<Source<D>,D>(this, d);
    }
}
