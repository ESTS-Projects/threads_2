class Exercice2 extends Thread {
    public static String sharedString = "";
    private String toAppend;

    Exercice2(String toAppend) {
        this.toAppend = toAppend;
    }

    public void run() {
        for (String charString : toAppend.split("")) {
            if (!Thread.currentThread().isInterrupted()) {
                    sharedString += charString;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Exercice2 ta = new Exercice2("ABC");
        Exercice2 tb = new Exercice2("DEF");

        ta.start();
        tb.start();

        Thread.sleep(1000);
        System.out.println(sharedString);
    }
}
