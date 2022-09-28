# Langton_Ant
Programa que hice para un examen sobre la hormiga de langton

--PORFAVOR LEER Y RELLENAR ANTES EL "archivoDeConfiguracion.txt"--

Este ejercicio genera un fichero txt llamado hormiga.txt en el que si se cuadra el tablero con el tamaño del ejecutable con el que se le abre manteniendo el botón avanzar
página se vé todos los movimientos de las hormigas.

El juevo se basa en: se le da una anchura y longitud al tablero, posición inicial a todal las hormigas (X,Y) y horientación de cada una de ellas.

Ahora bién, cada casilla se rellena con 0 inicialmente (Luego en el tablero final cada numero representa un simbolo), si la hormiga pasa por una casilla se le aumenta
el valor de esa casilla de 0 a 3 es decir si una casilla es 0 y la hormiga pasa por ella se le aumentara a 1 asi hasta 3 y después a 0 de nuevo.

En el fichero de configuración se le indica si queremos que cuando pase por la casilla 0 gire a la izquierda o a la derecha con la 1,2,3 igual por ejemplo; Si yo en 
el fichero de configuración escribo RRLL significará quie en la casilla 0 girara a la derecha, en la 1 derecha en la 2 izquierda y en la 3 izquierda.

Si la hormiga gira a la izquierda su horientación sera a la izquierda esto quiere decír que mirara a la izquierda por lo tanto si ahora tiene que girar a la derecha
en el tablero se representara como que gira hacia arriba y asi con todos los movimientos.

Tambien el tablero es romboédico esto quiere decir que si una hormiga está a la derecha del tablero y tiene que avanzar a la derecha de nuevo, aparecerá por el lado 
izquierdo.

Este ejercicio tiene como finalidad ver como con una sola hormiga completa un patrón hacia bajo creando una linea recta (Se le añaden mas hormigas para aumentaf la 
dificultad)

Lo he intentado explicar lo mas brevemente posible ya que es un ejercicio complicado y con muchas normas.
