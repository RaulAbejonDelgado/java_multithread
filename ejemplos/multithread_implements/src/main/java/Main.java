public class Main {
    public static void main(String[] args){
        System.out.println("Ieeep");

        Runnable rTest1 = new HiloEjemplo("Hilo1");
        Runnable rTest2 = new HiloEjemplo("Hilo2");
        Runnable rTest3 = new HiloEjemplo("Hilo3");

        Thread hilo1 = new Thread(rTest1,((HiloEjemplo) rTest1).getNombre());
        Thread hilo2 = new Thread(rTest2,((HiloEjemplo) rTest2).getNombre());
        Thread hilo3 = new Thread(rTest3,((HiloEjemplo) rTest3).getNombre());

        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
}
