/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio1;

/**
 *
 * @author Pedro
 */
public class HiloProductor extends Thread{
    private Buffer buffer;
    private int producido;

    private final String caracteres = "ABCDEFGHIJKLMNOPQRSTUWXYZ";
    
    public HiloProductor(Buffer buffer){
        this.buffer = buffer;
        this.producido = 0;
    }
    
    @Override
    public void run() {
        while(producido < 15){
            char c = caracteres.charAt((int) (Math.random() * caracteres.length()));
            this.buffer.producir(c);
            this.producido++;
            System.out.println("Depositado el caracter " + c + " en el buffer");
        }
    }
    

}
