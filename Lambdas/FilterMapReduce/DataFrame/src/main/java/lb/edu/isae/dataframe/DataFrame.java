package lb.edu.isae.dataframe;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Acer
 */
public final class DataFrame implements DataFrameIntf{
    private final List<String> _champs;
    private final List<List<String>> _lignes;
 
     /**
     * Création d'une DataFrame en définissant la liste des noms
     * de champs
     * exemple ['id', 'Nom', 'Prenom', 'Tel', 'Email']
     * @param champs : liste des noms de champs
     */
    public DataFrame(String... champs) {
         _champs=Arrays.asList(champs);
        _lignes = new ArrayList<>();
    }
    
    @Override
    public void insert(String... ligne) {
        _lignes.add(Arrays.asList(ligne));
    }

    private List<String> _projection(List<String> ligne, String ... chs) {
        Stream<Integer> sindex=Arrays.stream(chs).map((s) -> _champs.indexOf(s));
        return sindex.map((i)-> ligne.get(i)).collect(Collectors.toList());
    }
    private List<List<String>> projection(List<List<String>> lignes, String ... chs) {
        return lignes.stream().
                map((ls) -> _projection(ls, chs)).collect(Collectors.toList());
    }
    private Boolean _critere(List<String> ligne, HashMap<String, Predicate<String>> conds){
        return conds.keySet().stream().allMatch((k) -> conds.get(k).test(ligne.get(_champs.indexOf(k))));
    }
    private List<List<String>> where(HashMap<String, Predicate<String>> conds){
        return _lignes.stream().filter((l) -> _critere(l, conds)).collect(Collectors.toList());
    }
    @Override
    public List select(HashMap<String, Predicate<String>> criteres, String ... chs) {
         return projection(where(criteres), chs);  
    }

    String _afficheLigne(List<String> ligne) {
        return ligne.stream().reduce("", (acc, s) -> String.format("%s\t%s",acc,s));
    }
    
    String _afficheLignes(List<List<String>> lignes) {
        return lignes.stream().map((ls) -> _afficheLigne(ls)).
                reduce("",(acc,s)->String.format("%s\n%s",acc,s));
        
    }
    @Override
    public String toString(){
        return _afficheLigne(_champs)+_afficheLignes(_lignes);
    }
    
    public static void main(String... args){
        DataFrame df = new DataFrame("AAA","BBB", "CCC");
        System.out.printf("<%s>\n",df._afficheLigne(df._champs));
        df.insert("a","b", "c");
        df.insert("a","b", "c");
        df.insert("a","b", "c");
        System.out.printf("<%s>\n",df._afficheLignes(df._lignes));
        System.out.println(df);
    }
    
}
