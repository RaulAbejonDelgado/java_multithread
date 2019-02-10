public class HiloEjemplo implements Runnable {
    private int numeroHilo = 1;
    private String nombre;



    public HiloEjemplo(String nombre){
        this.nombre = nombre;

    }

    public int getNumeroHilo(){
        return this.numeroHilo;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void run() {
        for (int i=0; i < 5000; i++){
            if(this.getNombre().equals("Hilo1")){
                System.out.println("Corriendo *****-> " +this.getNombre()  + " nº iteracion -> "+i);

            }

            if(this.getNombre().equals("Hilo2")){
                System.out.println("Corriendo **********-> " +this.getNombre()  + " nº iteracion -> "+i);

            }

            if(this.getNombre().equals("Hilo3")){
                System.out.println("Corriendo ***************-> " +this.getNombre()  + " nº iteracion -> "+i);

            }
        }

    }
}
