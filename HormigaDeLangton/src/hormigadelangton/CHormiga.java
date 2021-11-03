package hormigadelangton;

import java.util.Scanner;
public class CHormiga {
    static Scanner poTeclado = new Scanner(System.in);

    public static void main(String[] args) {
        
        MovimientoHormigas();
    }// main
    
    
    private static void MovimientoHormigas(){
        //En estas primeras lineas guardo toda la configuracion en distintas variables, hago un split Para guardar cada hormiga con toda su configuracion en un arrayd.
        String[] longitud_YX_Tablero =poTeclado.nextLine().split(" ");
        int longitudYTablero = Integer.parseInt(longitud_YX_Tablero[0]);
        int longitudXTablero = Integer.parseInt(longitud_YX_Tablero[1]);
        String[] hormigas = poTeclado.nextLine().split(";");
        int[] posicionY = new int[hormigas.length];
        int[] posicionX = new int[hormigas.length];
        int contador = 0,colorPosicion;
        String debeContinuar ="",datosHormigaSegunElColor;
        char[] horientacionInicialHormiga =new char[hormigas.length];
        boolean continuar = true; 
        //Este bucle wile se podria decir que se ejecuta una vez por movimiento de todas las hormigas y el for de dentro se ejecuta tantas veces como hormigas haya
        while (continuar) { 
            debeContinuar = poTeclado.nextLine();
            if (debeContinuar == "no"){
                continuar = false;
            } else{
                ++contador;
                for(int i = 0;i<hormigas.length;++i){
                    String[] configuracionHormiga = hormigas[i].split(" ");
                    if (contador==1){//Este if se ejcuta una vez por hormiga en su primer movimiento para guardar las configuraciones iniciales
                        posicionY[i] = Integer.parseInt(configuracionHormiga[0]);
                        posicionX[i] = Integer.parseInt(configuracionHormiga[1]);
                        horientacionInicialHormiga[i] = configuracionHormiga[2].charAt(0);
                    }
                    char[] normas = GuardarNormas(configuracionHormiga);
                    System.out.println(posicionY[i]);
                    System.out.println(posicionX[i]);
                    System.out.println(normas.length);
                    colorPosicion = Integer.parseInt(poTeclado.nextLine());
                    //Del char de normas voy a comparar segun el color del tablero(0,1,2,3...) y si en el array de ese color hay una R va al metodo de derecha y ejecuta el switch,si no al de la izquierda
                    String enviarDatosDeHormiga = horientacionInicialHormiga[i]+" "+posicionY[i]+" "+posicionX[i];
                    if (normas[colorPosicion] == 'R')
                        datosHormigaSegunElColor = IrALaDerecha(enviarDatosDeHormiga);
                    else
                       datosHormigaSegunElColor = IrALaIzquierda(enviarDatosDeHormiga);                                          
                    String[] datosSeparados = datosHormigaSegunElColor.split(" ");
                    horientacionInicialHormiga[i] = datosSeparados[0].charAt(0);
                    posicionY[i] = Integer.parseInt(datosSeparados[1]);
                    posicionX[i] = Integer.parseInt(datosSeparados[2]);
                    //Estas dos lineas comprueban mediante los dos metodos si se han salido del tablero.
                    posicionY[i] = FuncionamientoToroPosicionY(posicionY[i], longitudYTablero);
                    posicionX[i] = FuncionamientoToroPosicionX(posicionX[i], longitudXTablero);
                }        
            }
        }       
    }// MvimientoHormigas
    private static String IrALaDerecha(String datos){
        String[] datosSeparados = datos.split(" ");
        char horientacionHormiga = datosSeparados[0].charAt(0);
        int posicionY = Integer.parseInt(datosSeparados[1]);
        int posicionX = Integer.parseInt(datosSeparados[2]);
        switch(horientacionHormiga){
            case 'U'://Arriba
                horientacionHormiga = 'R';
                posicionX++;
                break;
            case 'D'://Abajo
                horientacionHormiga = 'L';
                posicionX--;
                break;
            case 'R'://Derecha
                horientacionHormiga ='D';
                posicionY++;
                break;
            case 'L'://Izquierda
                horientacionHormiga = 'U';
                posicionY--;
                break;
            }
        datos = horientacionHormiga+" "+posicionY+" "+posicionX;
        return datos;
    }// IrALaDerecha()
    private static String IrALaIzquierda(String datos){
        String[] datosSeparados = datos.split(" ");
        char horientacionHormiga = datosSeparados[0].charAt(0);
        int posicionY = Integer.parseInt(datosSeparados[1]);
        int posicionX = Integer.parseInt(datosSeparados[2]);
        switch(horientacionHormiga){
            case 'U'://Arriba
                horientacionHormiga = 'L';
                posicionX--;
                break;
            case 'D'://Abajo
                horientacionHormiga = 'R';
                posicionX++;
                break;
            case 'R'://Derecha
                horientacionHormiga ='U';
                posicionY--;
                break;
            case 'L'://Izquierda
                horientacionHormiga = 'D';
                posicionY++;
                break;
        }
        datos = horientacionHormiga+" "+posicionY+" "+posicionX;
        return datos;
    }// IrALaIzquierda
    
    private static char[] GuardarNormas(String[] configuracionHormigas){
        /*Este metodo lo que hace es recibir el array que contiene toda la configuracion inicial, y guardar en un array de char
         *Las normas de la hormiga
        */
        String todasLasNormas = "";
        for (int i=3;i<configuracionHormigas.length;++i){
            todasLasNormas += configuracionHormigas[i];
        }
        char[] normas = todasLasNormas.toCharArray();
        return normas;
    }// GuardarNormas()
    
    private static int FuncionamientoToroPosicionY(int posicionY, int longitudYTablero){
        if(posicionY >= longitudYTablero)
            posicionY -=longitudYTablero;
        else if(posicionY<0)
            posicionY+=longitudYTablero;
        return posicionY;
    }// FuncionamientoToroPosicionY()
    private static int FuncionamientoToroPosicionX(int posicionX, int longitudXTablero){
            if(posicionX >= longitudXTablero)
                posicionX -=longitudXTablero;
            else if(posicionX<0)
                posicionX+=longitudXTablero;
        return posicionX;
    }// FuncionamientoToroPosicionY()

}// CHormiga
