public class Semaphore
{
    private int valeur;
    
    public Semaphore(int valeur)
    {
        this.valeur=valeur;
    }
    
    public int getValeur()
    {
        return valeur;
    }
    
    synchronized public void P()
    {
        while (valeur<1)
        {
            try 
            {
                wait();
            }
            catch(InterruptedException e)
            {
                System.out.println("Erreur de Sémaphore");
            }
        }
        valeur=valeur-1;
    }
    
    synchronized public void V()
    {
        valeur=valeur+1;
        notify();
    }
}