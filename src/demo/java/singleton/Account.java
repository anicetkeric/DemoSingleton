package demo.java.singleton;

public class Account {

    private int numero;// Account number.
    private double solde;
    
  
    public Account(int numero)
    {
            this.numero=numero;
            this.solde=0.0;
    }
    
    
    public void deposerArgent(double depot)
    {
            if(depot>0.0)
            {       
                    solde+=depot;// The sum deposited is added to the balance.
                    Logging.getInstance().addLog("Add "+depot+"€ to the account "+numero+".");
            }
            else
            {
                    Logging.getInstance().addLog("/!\\ Error: Negative value for amount ("+numero+").");
            }
    }
    
   
    public void retirerArgent(double retrait)
    {
            if(retrait>0.0)
            {
                    if(solde>=retrait)
                    {
                            solde-=retrait;
                            Logging.getInstance().addLog("Withdrawal of "+retrait+"€ from the account "+numero+".");
                    }
                    else
                    {
                            Logging.getInstance().addLog("/!\\ The bank does not authorize overdraft ("+numero+").");
                    }
            }
            else
            {
                    Logging.getInstance().addLog("/!\\ Error: Negative value for amount ("+numero+").");
            }
    }
}
