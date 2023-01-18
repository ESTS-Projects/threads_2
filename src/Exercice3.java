class CompteBancaire {
    private int solde;

    public CompteBancaire() {
        this.solde = 0;
    }

    public synchronized void deposer(int montant) {
        this.solde += montant;
    }

    public synchronized void retirer(int montant) {
        this.solde -= montant;
    }

    public synchronized int getSolde() {
        return this.solde;
    }
}

class Employe extends Thread {
    private CompteBancaire compte;

    public Employe(CompteBancaire compte) {
        this.compte = compte;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            compte.deposer(100);
            System.out.println(this.getName() + " a déposé 100 dollars. Solde : " + compte.getSolde());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Exercice3 {
    public static void main(String[] args) {
        CompteBancaire compte = new CompteBancaire();
        Employe employe1 = new Employe(compte);
        Employe employe2 = new Employe(compte);

        employe1.start();
        employe2.start();
    }
}