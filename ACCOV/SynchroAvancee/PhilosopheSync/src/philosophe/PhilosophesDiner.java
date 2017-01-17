package philosophe;

public class PhilosophesDiner {
  public static void main(String[] args)
  {
    int NbPhilo =5;
    Fourchettes fourchettes = new Fourchettes(NbPhilo);
    Philosophe[] mangeurs = new Philosophe[NbPhilo];
 
    for (int i =0; i<NbPhilo; i++)
      mangeurs[i] = new  Philosophe(i,3, fourchettes);
 
    for (int i =0; i<NbPhilo; i++)
      mangeurs[i].start();
 
  }
}
