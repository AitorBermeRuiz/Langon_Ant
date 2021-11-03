package hormigadelangton;

import java.util.Scanner;
import java.io.*;
public class CDirector {
    static Scanner poTeclado = new Scanner(System.in);
    public static void main(String[] args) {
        
        /*E implementado las dos maneras que pedias en la practica Preguntando por pantalla o bien
        a traves del fichero de configuracion, decidiendo si utilizar una u otra.
        */
        System.out.println("Como quieres Guardar la configuracion: Fichero = F teclado = T");
        String decision = poTeclado.nextLine();
        if (decision.equals("F"))
            GuardarConfiguracionInicialFichero();
        else
            PedirConfiguracionTablero();
    }//main
    
    private static void PedirConfiguracionTablero(){
        System.out.println("Dame la longitud Y del tablero: ");
        int longYTablero = poTeclado.nextInt(); poTeclado.nextLine();
        System.out.println("Dame la longitud X del tablero: "); 
        int longXTablero = poTeclado.nextInt(); poTeclado.nextLine();
                
        ConfiguracionInicialHormigas(longYTablero, longXTablero);
    }// PedirConfiguracionTablero()

    private static void ConfiguracionInicialHormigas(int longYTablero, int longXTablero){
        String configuracionInicialHormigas = "";
        System.out.println("Con cuantas hormigas quieres jugar?:");
        int cantidadHormigas = poTeclado.nextInt(); poTeclado.nextLine();
        for(int i =0;i<cantidadHormigas;++i){
            //protocolo de la poscion = Y X
            System.out.println("Posicion inicial de la hormiga: (PosicionY PosicionX)");
            String PosicionInicialHormiga = poTeclado.nextLine();
            //Protocolo horientacion = U: Arriba D: Abajo R Derecha L Izquierda
            System.out.println("Horientacion de la hormiga: (U o D o R o L)");
            String HorientacionInicialHormiga = poTeclado.nextLine();
            //La recla se identifica con R y L. poniendolas en el orden que quieras con un espacio de por medio
            System.out.println("Regla a seguir: (R R L L, R L etc)");
            String ReglaHormiga = poTeclado.nextLine();
            configuracionInicialHormigas += PosicionInicialHormiga+" "+HorientacionInicialHormiga+" "+ReglaHormiga+";";
        }
        int numMovimientosHormiga = NumeroDeMovimientos();
       
        Principal(longYTablero, longXTablero, configuracionInicialHormigas,cantidadHormigas,numMovimientosHormiga);
    }// ConfiguracionInicialHormigas()
    
    private static void GuardarConfiguracionInicialFichero(){
        char caracter;
        String ficheroEntero="";
        try {
            FileReader Lector = new FileReader(new File("ArchivoDeConfiguracion.txt"));
            int salida;
            while((salida = Lector.read()) != -1){
                caracter = (char) salida;
                ficheroEntero +=caracter;
            }//while
        } catch (Exception e) {System.out.println("Error al leer el fichero");}
        String[] configuracionSeparada = ficheroEntero.split(":");
        int longYTablero = Integer.parseInt(configuracionSeparada[1]);
        int longXTablero = Integer.parseInt(configuracionSeparada[3]);
        int numMovimientosHormiga = Integer.parseInt(configuracionSeparada[5]);
        String configuracionInicialHormigas = configuracionSeparada[7];
        String[] hormigas = configuracionInicialHormigas.split(";");
        int numDeHormigas = hormigas.length;
        Principal(longYTablero, longXTablero, configuracionInicialHormigas, numDeHormigas,numMovimientosHormiga);
    }
    private static void Principal(int longYTablero, int longXTablero, String configuracionInicialHormigas,int numeroDeHormigas, int movimientosHormiga){
        int[][] tablero = CrearTablero(longYTablero,longXTablero);
        try {
            Process pHormiga = ProcesoHormiga();
            BufferedWriter bwHormiga = new BufferedWriter(new OutputStreamWriter(pHormiga.getOutputStream()));
            BufferedReader brHormiga = new BufferedReader(new InputStreamReader(pHormiga.getInputStream()));
            Process pGrabador = ProcesoGrabador();
            BufferedWriter bwGrabador = new BufferedWriter(new OutputStreamWriter(pGrabador.getOutputStream()));
            
            bwHormiga.write(longYTablero + " "+longXTablero+"\n");bwHormiga.flush();
            bwHormiga.write(configuracionInicialHormigas+"\n");bwHormiga.flush();
            bwGrabador.write(movimientosHormiga+"\n");bwGrabador.flush();
            int contador = 0, posicionY = 0, posicionX = 0;
             
            boolean continuar = true;
            while(continuar){
                String posicionYTodasHormigas = "", posicionXTodasHormigas ="";
                ++contador;
                if (contador > movimientosHormiga){
                    continuar = false;
                    bwHormiga.write("no\n");bwHormiga.flush();
                }else{
                    bwHormiga.write("si\n");bwHormiga.flush();
                    for (int i = 0;i<numeroDeHormigas;++i){
                        posicionY = Integer.parseInt(brHormiga.readLine());
                        posicionX = Integer.parseInt(brHormiga.readLine());
                        int numeroDeNormas = Integer.parseInt(brHormiga.readLine());
                        int colorPosicion = tablero[posicionY][posicionX];
                        bwHormiga.write(colorPosicion+"\n");bwHormiga.flush();
                        if(tablero[posicionY][posicionX] == numeroDeNormas -1){
                            tablero[posicionY][posicionX] = 0;
                        }else{
                            tablero[posicionY][posicionX]++;
                        }
                        posicionYTodasHormigas += posicionY +",";
                        posicionXTodasHormigas += posicionX +",";
                    }
                    String tableroParaGrabador = StringParaGrabador(tablero, longYTablero, longXTablero);
                    bwGrabador.write(longYTablero+" "+longXTablero+" "+posicionYTodasHormigas+" "+posicionXTodasHormigas+" "+tableroParaGrabador+"\n");bwGrabador.flush();
                }                            
            }
            bwHormiga.close();brHormiga.close();bwGrabador.close();
        } catch (Exception e) { System.out.println(e);}
    }//IniciarHormigas   
    private static Process ProcesoHormiga() throws IOException{
        ProcessBuilder pb = new ProcessBuilder("java",
                "-cp","C:\\Users\\aitor\\Documents\\NetBeansProjects\\HormigaDeLangton\\build\\classes",
                "hormigadelangton.CHormiga");
        Process p = pb.start();
        return p;
    }// ProcesoHormiga
        private static Process ProcesoGrabador() throws IOException{
        ProcessBuilder pb = new ProcessBuilder("java",
                "-cp","C:\\Users\\aitor\\Documents\\NetBeansProjects\\HormigaDeLangton\\build\\classes",
                "hormigadelangton.CGrabador");
        Process p = pb.start();
        return p;
    }// ProcesoGrabador

    private static int NumeroDeMovimientos(){
        System.out.println("Cuantas veces quieres que se mueva la Hormiga?");
        int movimientosHormiga = poTeclado.nextInt();
        return movimientosHormiga;
    }
     private static int[][] CrearTablero(int Y, int X){
        //Este metodo sirve para que: Segun la longitud del tablero que nos a dado el usuario, rellenamos todos a 0, lo que es lo mismo que a blanco.
        int[][] tablero = new int[Y][X];
                
        for(int i = 0; i<Y;++i){
            for (int e = 0; e<X;++e){
                tablero[i][e] = 0;
            }
        }
        return tablero;
        
    }// CrearTablero()
     private static String StringParaGrabador(int[][] tablero, int longitudYTablero, int longitudXTablero){
         //Lo que hago con este metodo Es guardar en un String Todos los caracteres de tablero separando cada linea con una,
       String tableroEntero ="";
       for(int i = 0;i<longitudYTablero;++i){
           for (int e = 0;e<longitudXTablero;++e){
              tableroEntero += tablero[i][e];
           }
           tableroEntero += ",";
       }
       return tableroEntero;
     }
}// CDirector
