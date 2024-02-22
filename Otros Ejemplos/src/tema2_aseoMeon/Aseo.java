/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema2_aseoMeon;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Pedro
 */
public class Aseo {
    private Semaphore semaforoAforo;
    private Semaphore semaforoWC;
    
    public Aseo(int aforo, int wc){
        this.semaforoAforo  = new Semaphore(aforo);
        this.semaforoWC     = new Semaphore(wc);
    }
    
    public void entrarAseo(Persona p) throws InterruptedException{
       semaforoAforo.acquire();
       System.out.println("" + p.getNombre()+" entra aseo");
    }
    
    public void ocuparWC(Persona p) throws InterruptedException{
        semaforoWC.acquire();
        System.out.println("" + p.getNombre()+" entra wc");
        if(p.getAccion()){
                System.out.println(""+ p.getNombre() + " meando");
                Thread.sleep(1000);
            }else{
                System.out.println(""+ p.getNombre() + " cagando");
                Thread.sleep(5000);
        }
    }
    
    public void dejarWC(Persona p){
        System.out.println("" + p.getNombre()+" sale wc");
        semaforoWC.release();
        
    }
    
    public void salirAseo(Persona p){
        System.out.println("" + p.getNombre()+" sale aseo");
        semaforoAforo.release();
        
    }
}
