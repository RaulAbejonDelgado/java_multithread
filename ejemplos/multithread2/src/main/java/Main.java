public class Main {

    public static void main(String[] args){

        System.out.println("Iep");

        Thread hilo1 = new HIloEjemplo("hilo -> [hilo-1]******");
        Thread hilo2 = new HIloEjemplo("hilo -> [hilo-2]***************");
        Thread hilo3 = new HIloEjemplo("hilo -> [hilo-3]*****************************");

        hilo1.start();
        hilo2.start();
        hilo3.start();

    }
}
