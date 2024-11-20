import java.util.ArrayList;
public class Reporte {
    //Procesos terminados
    private ArrayList<Integer> a=new ArrayList<Integer>();
    //Procesos que nunca entraron en ejecucion
    private ArrayList<Integer> b=new ArrayList<Integer>();
    //procesos que siguen en procesamiento
    private ArrayList<Integer> c=new ArrayList<Integer>();
    //cantidad de cabios de procesos
    private int d;

    public Reporte() {
    }
    
    public void imprimirReporte() {
        System.out.println("================================================");
        System.out.print("Qué procesos terminaron: ");
        if(a.size()==3){System.out.println(a.get(0)+", "+a.get(1)+" y "+a.get(2));}
        if(a.size()==2){System.out.println(a.get(0)+" y "+a.get(1));}
        if(a.size()==1){System.out.println(a.get(0));}
        if(a.size()==0){System.out.println("ninguno");}
        if(a.size()>3){
        for(int i=0;i<a.size();i++){
                if(i==a.size()-1){
                    System.out.println(a.get(i));
                }
                if(i==a.size()-2){
                    System.out.print(a.get(i));
                System.out.print(" y ");
                }else{
                    System.out.print(a.get(i));
                System.out.print(", ");
                }
            }  
        }  

        System.out.print("Qué procesos No entraron nunca en ejecucion: ");
        if(b.size()==3){System.out.println(b.get(0)+", "+b.get(1)+" y "+b.get(2));}
        if(b.size()==2){System.out.println(b.get(0)+" y "+b.get(1));}
        if(b.size()==1){System.out.println(b.get(0));}
        if(b.size()==0){System.out.println("ninguno");}
        if(b.size()>3){
            for(int i=0;i<b.size();i++){
                if(i==b.size()-1){
                    System.out.println(b.get(i));
                }
                if(i==b.size()-2){
                    System.out.print(b.get(i));
                System.out.print(" y ");
                }else{
                    System.out.print(b.get(i));
                System.out.print(", ");
                }
            }  
        }

    System.out.print("Qué procesos siguen en procesamiento: ");
    if(c.size()==3){System.out.println(c.get(0)+", "+c.get(1)+" y "+c.get(2));}
    if(c.size()==2){System.out.println(c.get(0)+" y "+c.get(1));}
    if(c.size()==1){System.out.println(c.get(0));}
    if(c.size()==0){System.out.println("ninguno");}
    if(c.size()>3){
        for(int i=0;i<c.size();i++){
            if(i==c.size()-1){
                System.out.println(c.get(i));
            }
            if(i==c.size()-2){
                System.out.print(c.get(i));
            System.out.print(" y ");
            }
            if(i<c.size()-2){
                System.out.print(c.get(i));
            System.out.print(", ");
            }
        }  
    }

    System.out.println("Cantidad de cambios de procesos: "+d);
    System.out.println("================================================");
    for(int i=0;i<a.size();i++){
        a.remove(0);
    } 
    for(int i=0;i<b.size();i++){
        b.remove(0);
    }   
    for(int i=0;i<c.size();i++){
        c.remove(0);
    }  
    this.d=this.d-this.d; 
    }

    //getter and setter
    public void setA(int a) {
        this.a.add(a);
    }

    public void setB(int b) {
        this.b.add(b);
    }

    public void setC(int c) {
        this.c.add(c);
    }

    public void setD(int d) {
        this.d=this.d+d;
    }
    
    
}
