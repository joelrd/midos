# Compiladores
## Author Henry Rojas Douglas
### MIDOS
#### Comandos
Para utilizar los nuevos comandos, se debe escribir donde está el path de MIDOS, en el caso de CD, tiene que existir el directorio y estar en la ubicación requerida, se escribe un espacio y luego el nombre del directorio.
##### CD
	Se utiliza para llamar directorios y también para salir de los mismo. Para llamar un directorio se escribe el comando seguido del nombre del directorio. Para ir a un directorio padre, se utiliza .. ya se sea seguidos o con un espacio entre CD y los dos puntos. Vara volver al directorio raíz se utiliza el “\”.
##### RD

	Para el caso de RD, se debe ir a la ubicación del archivo con MD y después de escribir rd se escribe el nombre del archivo, este no debe contener ningún contenido para poder borrarlo. 

##### Prompt

	Le da el diseño necesario a la línea de comando de MIDOS si se escribe el comando o con un espacio le va a dar el orden de M:\> de caso contrario con $P solo muestra el M:\ con $G solo muestra el > y el caso de $G $P muestra el orden inverso. 

##### Dir

	Muestra los archivos de en orden alfabético. Si no tiene archivos muestra el mensaje de error. 

##### CLS
	Se encarga de limpiar parte de la pantalla de los comandos anteriores, para ello no permite comandos adicionales. 

##### VER

	Se encarga de mostrar la memoria y también el encabezado de MIDOS no recibe parámetros ni opciones adicionales. 

##### COPY
	Se encarga de crear archivos de texto y también agregarle el contenido al mismo. Se utiliza escribiendo un espacio poniendo “CON” y seguido con el nombre del archivo, se preciona enter, y automáticamente puede escribir el contenido del archivo para salvar el contenido se escribe “^Z” y se presiona enter, todo escrito después de ese comando, se va a borrar y no se va a agregar en el archivo del texto. 

##### TYPE
	Se encarga de mostrar el contenido del archivo para ello se escribe el comando seguido con un espacio y el nombre del archivo. 

##### DEL

	Se encarga de borrar archivos, para ello se escribe el comando seguido por el archivo de texto que se desea borrar, el archivo debe estar en la ubicación que se encuentre. 

##### REN

	Permite cambiarle el nombre a un archivo de texto, para ello se debe encontrar en la ubicación actual del archivo y se escribe el comando, seguido por un espacio el nombre del archivo, seguido por el nuevo nombre del archivo. 

##### TREE
	Muestra la estructura interna de todos los archivos del programa, se utiliza escribiendo solamente el comando seguido por la tecla enter. 

#### Manejo de errores
| Error | Commando           											|
|:---:|:---------------------------------------------------------------:|
| 001 | Comando invalido 												|
| 002 | No hay memoria 													|
| 003 | Directorios deben tener nombre									|
| 004 | No se pueden agregar mas de 8 directorios  						|
| 005 | Los directorios no pueden exceder los 8 caracteres 				|
| 006 | Primer caracter no puede ser un numero							|
| 007 | No se permiten caracteres especiales							|
| 008 | No se pueden agregar mas de 8 directorios						|
| 009 | Ya se agrego el directorio										|
| 010 | Solo puede ser S o N											|
| 011 | Se encuentra en el directorio raiz								|
| 012 |El archivo que trata de accesar no es un directory				|
| 013 |No existe un directorio con ese nombre en ese directorio			|
| 014 | Caracter invalido												|
| 015 | No se encuentra el archivo deseado								|
| 016 | No se puede borrar carpetas con contenido						|
| 017 | No se pueden agregar mas de 8 archivos							|
| 018 | Favor ingresar un nombre										|
| 019 | El archivo esta vacio											|
| 020 | El archivo es un directory										|
| 021 | No existe un archivo de texto con ese nombre en ese directorio	|
| 022 | No se puede borrar directorios									|
| 023 | No puede empezar con espacio									|

