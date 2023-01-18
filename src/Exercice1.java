class Compte {
    private int solde = 0;

    public void ajouter(int somme) {
        solde += somme;
    }

    public void retirer(int somme) {
        solde = Math.max(0, solde - somme);
    }

    public void operationNulle(int somme) {
        ajouter(somme);
        retirer(somme);
    }

    public int getSolde() {
        return solde;
    }
}

class Operation extends Thread {
    private String nomop;
    private Compte compte;

    public Operation(String nom, Compte compte) {
        this.nomop = nom;
        this.compte = compte;
    }

    public void run() {
        while (true) {
            int i = (int) (Math.random() * 10000);
            String nom = nomop;
            System.out.println("Nom opération : " + nom);
            compte.ajouter(i);
            compte.retirer(i);
            compte.operationNulle(i);
            int solde = compte.getSolde();
            System.out.println(solde);

            if (solde != 0) {
                System.out.println(nom + ":*** Solde=" + solde);
                System.exit(1);
            }
        }
    }
}

public class Exercice1 {
    public static void main(String[] args) throws Exception {
        Compte compte = new Compte();
        Operation operation = new Operation("Test", compte);

        operation.start();
    }
}
