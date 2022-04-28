
package TestMain;


import Controllers.DemandeDietController;
import Models.DemandeDiet;
import java.io.IOException;


import tools.MaConnexion;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
       MaConnexion m = MaConnexion.getInstance();
       DemandeDiet d = new DemandeDiet(1,5,"xxx","sssxx");
       DemandeDietController ds = new DemandeDietController();
       //ds.ajouterDiet(d);
//       ds.supprimerDemandeDiet(d);
       // ds.updateDemandeDiet(d);
          System.out.println(ds.displayDemandeDiets());


       
       

  }
      }

