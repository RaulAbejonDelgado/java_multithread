import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * 1 - Crear clase que implemente interfaz Runneable(metodo Run())
 * 2 - Escribir el codigo de la tarea dentro del metodo run
 * 3 - Instanciar la clase creada y guardarla en variable del tipo Runneable
 * 4 - Instancia la clase Thread pasandole como parametro la variable del tipo Runneable del punto 3
 * 5 - Poner en marcha el hilo de ejecucion con el metodo start() de clase thread
 */
public class MultiThreads {

    public static void main(String[] args) {


        JFrame marco = new MarcoReboteM();

        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        marco.setVisible(true);

    }

}

/**
 * 1 - Crear clase que implemente interfaz Runneable(metodo Run())
 */
class PelotaHilos implements Runnable {

    static private int numeroThread = 0;
    private PelotaM pelotaM;
    private Component componente;

    public PelotaHilos(PelotaM p, Component unComponent) {
        numeroThread +=1;
        this.pelotaM = p;
        this.componente = unComponent;

    }

    /**
     * 2 - Escribir el codigo de la tarea dentro del metodo run
     */
    public void run() {

        for (int i = 1; i <= 5000; i++) {

            pelotaM.mueve_pelota(componente.getBounds());


            componente.paint(componente.getGraphics());
            System.out.println("Hilo numero -> "+numeroThread +" / "+ this.pelotaM.getNumeroThread());
            try {
                Thread.sleep(5);
            } catch (InterruptedException ir) {
                System.out.println(ir);
            }

        }
    }
}


//Movimiento de la pelota-----------------------------------------------------------------------------------------

class PelotaM {

    static private int numeroThread = 0;

    public PelotaM(){
        this.numeroThread +=1 ;
    }

    public int getNumeroThread(){
        return this.numeroThread;
    }

    // Mueve la pelota invirtiendo posición si choca con límites

    public void mueve_pelota(Rectangle2D limites) {

        x += dx;

        y += dy;

        if (x < limites.getMinX()) {

            x = limites.getMinX();

            dx = -dx;
        }

        if (x + TAMX >= limites.getMaxX()) {

            x = limites.getMaxX() - TAMX;

            dx = -dx;
        }

        if (y < limites.getMinY()) {

            y = limites.getMinY();

            dy = -dy;
        }

        if (y + TAMY >= limites.getMaxY()) {

            y = limites.getMaxY() - TAMY;

            dy = -dy;

        }

    }

    //Forma de la pelota en su posición inicial

    public Ellipse2D getShape() {

        return new Ellipse2D.Double(x, y, TAMX, TAMY);

    }

    private static final int TAMX = 15;

    private static final int TAMY = 15;

    private double x = 0;

    private double y = 0;

    private double dx = 1;

    private double dy = 1;


}

// Lámina que dibuja las pelotas----------------------------------------------------------------------


class LaminaPelotaM extends JPanel {

    //Añadimos pelota a la lámina

    public void add(PelotaM b) {

        pelotas.add(b);
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        for (PelotaM b : pelotas) {

            g2.fill(b.getShape());
        }

    }

    private ArrayList<PelotaM> pelotas = new ArrayList<PelotaM>();
}


//Marco con lámina y botones------------------------------------------------------------------------------

class MarcoReboteM extends JFrame {

    public MarcoReboteM() {

        setBounds(600, 300, 400, 350);

        setTitle("MultiHilo");

        lamina = new LaminaPelotaM();

        add(lamina, BorderLayout.CENTER);

        JPanel laminaBotones = new JPanel();

        ponerBoton(laminaBotones, "Dale!", new ActionListener() {

            public void actionPerformed(ActionEvent evento) {

                comienza_el_juego();
            }

        });


        ponerBoton(laminaBotones, "Salir", new ActionListener() {

            public void actionPerformed(ActionEvent evento) {

                System.exit(0);

            }

        });

        add(laminaBotones, BorderLayout.SOUTH);
    }


    //Ponemos botones

    public void ponerBoton(Container c, String titulo, ActionListener oyente) {

        JButton boton = new JButton(titulo);

        c.add(boton);

        boton.addActionListener(oyente);

    }

    //Añade pelota y la bota 1000 veces

    public void comienza_el_juego() {


        PelotaM pelota = new PelotaM();

        lamina.add(pelota);

        /**
         * 3 - Instanciar la clase creada(punto 1) y guardarla en variable del tipo Runneable
         */
        Runnable r = new PelotaHilos(pelota, lamina);

        /**
         *  4 - Instancia la clase Thread pasandole como parametro la variable del tipo Runneable del punto 3
         */
        Thread t = new Thread(r);

        /**
         * 5 - Poner en marcha el hilo de ejecucion con el metodo start() de clase thread
         */
        t.start();

    }

    private LaminaPelotaM lamina;


}
