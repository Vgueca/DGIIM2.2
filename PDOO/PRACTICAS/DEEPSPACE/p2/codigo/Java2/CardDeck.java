/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Profe
 * @param <T>
 */
class CardDeck<T extends Copyable<T>> {    
    private ArrayList<T> cards=new ArrayList<>();
    private boolean ready;
    private int count;
    
    CardDeck() {
        ready=false;
        count=0;
    }
    
    public void add(T t) {
        if (!ready)
            cards.add(t);
    }
    
    public T next() {
        if (!ready) {
            ready=true;
            shuffle();
        }

        T card=cards.remove(0);
        cards.add(card);
        
        count++;
        if (count==cards.size()) {
            shuffle();
            count=0;
        }
        
        return card.copy();
    }
    
    private void shuffle() {
        Collections.shuffle(cards);
    }
    
    boolean justShuffled() {
        return (count==0);
    }
}

/*
class SafeHangarCardDeck extends CardDeck<Hangar> {
    @Override
    public Hangar next() {
        Hangar h=(Hangar)(super.next());
        return new Hangar(h) ;
    }
}
*/