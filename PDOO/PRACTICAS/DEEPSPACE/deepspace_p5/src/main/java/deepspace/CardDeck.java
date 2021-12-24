/**
 * @file CardDeck.java
 * @author Profesor
 * @version P-05
 * type : Java-Class
 */
package deepspace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * @brief Clase parametrizable que representa un mazo de cartas de objetos de
 * tipo T.
 * @param <T> : Objeto del que se realizara el mazo de cartas
 */
class CardDeck<T> {    
    private ArrayList<T> cards=new ArrayList<>();
    private boolean ready;
    private int count;
    
    /**
     * @brief Constructor por defecto de la clase
     */
    CardDeck() {
        ready=false;
        count=0;
    }
    
    /**
     * @brief Inserta una nueva carta en el mazo
     * @param t : Instancia a insertar
     */
    public void add(T t) {
        if (!ready)
            cards.add(t);
    }
    
    /**
     * @brief Devuelve la siguiente carta del mazo
     * @return Carta siguiente
     */
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
        return card;
    }
    
    /**
     * @brief Baraja la colección de cartas disponibles
     */
    private void shuffle() {
        Collections.shuffle(cards);
    }
    
    /**
     * @brief Comprueba si se han barajado las cartas
     * @return true si se han bajarado, false en otro caso
     */
    boolean justShuffled() {
        return (count==0);
    }
}

