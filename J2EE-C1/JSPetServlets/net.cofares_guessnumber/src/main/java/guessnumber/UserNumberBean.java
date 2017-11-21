package guessnumber;

import java.io.Serializable;
import java.util.Random;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;

/**
 *
 * @author Pascal Fares (adapté de la version guessnumber de SUN)
 */
@SessionScoped
@Named
public class UserNumberBean implements Serializable {
    Integer randomInt = null;
    Integer userNumber = null;
    String response = null;
    private long maximum = 5;
    private long minimum = 0;

    public UserNumberBean() {
        Random randomGR = new Random();
        randomInt = randomGR.nextInt((int)maximum+1);
        System.out.println("Le nombre de Duke: " + randomInt);
    }

    public void setUserNumber(Integer user_number) {
        userNumber = user_number;
    }

    public Integer getUserNumber() {
        return userNumber;
    }

    public String getResponse() {
        if ((userNumber != null) && (userNumber.compareTo(randomInt) == 0)) {
            return "Bravo! Vous l'avez!";
        } else {
            return "Heh non!, " + userNumber + " est incorrecte.";
        }
    }

    public long getMaximum() {
        return (this.maximum);
    }

    public void setMaximum(long maximum) {
        this.maximum = maximum;
    }

    public long getMinimum() {
        return (this.minimum);
    }

    public void setMinimum(long minimum) {
        this.minimum = minimum;
    }
}
