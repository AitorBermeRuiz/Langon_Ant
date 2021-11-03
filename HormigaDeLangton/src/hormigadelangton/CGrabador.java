package hormigadelangton;

import java.util.Scanner;
import java.io.*;

public class CGrabador {
    static Scanner poTeclado = new Scanner(System.in);

    public static void main(String[] args) {
        int movimientosHormigas = Integer.parseInt(poTeclado.nextLine());
        for (int i =0;i<movimientosHormigas;++i){
            //TodosLos Datos Contiene:  LongYTablero LongXTablero PosicionYTodasHormigas PosicionXTodasHormigas TableroParaGrabador
            String todosLosDatos = poTeclado.nextLine();
            IniciarGrabadora(todosLosDatos);
        }
    }// main()
    private static void IniciarGrabadora(String TodosLosDatos){
        String[] datosSeparados = TodosLosDatos.split(" ");
        int longYTablero    = Integer.parseInt(datosSeparados[0]);
        int longXTablero    = Integer.parseInt(datosSeparados[1]);
        String[] posicionY  = datosSeparados[2].split(",");
        String[] posicionX  = datosSeparados[3].split(",");
        String[] tablaSinArray = datosSeparados[4].split(",");

        String[][] tablaEnArray =CrearTableroEnArray(longYTablero, longXTablero, tablaSinArray);
        for(int i = 0;i<posicionY.length;++i){
            tablaEnArray[Integer.parseInt(posicionY[i])][Integer.parseInt(posicionX[i])] = "¥";
        }
        
        GuardarTableroEnFichero(tablaEnArray, longYTablero, longXTablero);
    }// IniciarGrabadora()
    private static String[][] CrearTableroEnArray(int longYTablero, int longXTablero, String[] tablaSinArray){
        char caracterDeTabla;
        String[][] tablaEnArray = new String[longYTablero][longXTablero];
        for (int i = 0;i<longYTablero;++i){
            for (int e =0;e<longXTablero;++e){
               caracterDeTabla = tablaSinArray[i].charAt(e);
               switch(caracterDeTabla){
                   case '0':
                       caracterDeTabla = ' ';
                       break;
                   case '1':
                       caracterDeTabla = '#';
                       break;
                    case '2':
                       caracterDeTabla = '/';
                       break;
                    case '3':
                       caracterDeTabla = '&';
                       break;
                    case '4':
                       caracterDeTabla = '>';
                       break;
                    case '5':
                       caracterDeTabla = '?';
                       break;  
               }
               tablaEnArray[i][e] = String.valueOf(caracterDeTabla);
            }
        }
        return tablaEnArray;
    }// CrearTableroEnArray()
    
    private static void GuardarTableroEnFichero(String[][] tablaEnArray, int longYTablero, int longXTablero){
        try {
            FileWriter fw = new FileWriter(new File("Hormiga3.txt"),true);
            for (int i = 0;i<longYTablero;++i){
                for (int e =0;e<longXTablero;++e){
                    fw.write(tablaEnArray[i][e]);
                }
                fw.write("\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }// GuardarTableroEnFichero()
}// CGrabador
