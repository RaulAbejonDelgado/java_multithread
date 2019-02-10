/**
 * Extendemos de Thread
 */
public class HIloEjemplo extends Thread {
    private String msg;
    public HIloEjemplo(String msg){
        super(msg);


    }

    public void run(){

        for(int i=0 ; i < 5000; i++ ){
            System.out.println(this.getName() + " iteracion nº -> "+ i);
        }
    }
}
