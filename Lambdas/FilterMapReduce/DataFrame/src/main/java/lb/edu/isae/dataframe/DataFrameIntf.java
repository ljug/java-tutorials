/*
 * Table de donées : exemple pour aspects fonctionels
 */
package lb.edu.isae.dataframe;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

/**
 * L'interface (contrat) des dataFrame
 * @author Acer
 */
public interface DataFrameIntf extends Serializable {

    /**
     * insertion d'une ligne dans le dataFrame
     * par simplification nous tranformons tous en String
     * pour ne pas se préocuper du typage des attributs
     * @param ligne : la ligne d'attributs correspondants aux champs
     */
    void insert(String... ligne);

    /**
     *
     * @param projection : les champs a récupérer
     * @param criteres : Les critère champs:fonction associé
     * @return la liste des ligne correspondante au critéres projeté
     */
    List select(List<String> projection, 
            HashMap<String, Function<?,Boolean>> criteres);
    
    /**
     * Affichage d'une dataFrame
     * @return la representation String
     */
    @Override
    String toString();
}
