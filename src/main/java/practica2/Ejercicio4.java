package practica2;

import java.util.Scanner;

/**
 * Programa que simula una app de un parque de atracciones, contiene menú de elección de opciones
 * @author Piera Merlo
 * @author Javier Viloria
 */

public class Ejercicio4 {

    /**
     * Función que comprueba que usuario y contraseña sean correctas
     * @param usuarioReal primer usuario introducido por teclado
     * @param userIntroducido repetición de usuario introducido por teclado
     * @param passwReal primera contraseña introducida por teclado
     * @param psswwIntroducido repetición de la contraseña introducida por teclado
     * @return devuelve true si el acceso es denegado y false si es correcto
     */

    public static boolean login (String usuarioReal, String userIntroducido,
                                 String passwReal, String psswwIntroducido){
        boolean accesoDenegado;
        if (usuarioReal.equals(userIntroducido) && passwReal.equals(psswwIntroducido)){
            accesoDenegado = false;
        }else{
            accesoDenegado = true;
        }
        return accesoDenegado;
    }

    /**
     * Función que muestra el menú de opciones al loguearte de forma correcta
     */

    public static void mostrarMenuOpciones(){

        System.out.println("******** Menú Opciones ************");
        System.out.println("1. Atracciones del parque\n" +
                "2. Restauración \n" +
                "3. Días con entradas disponibles\n" +
                "4. Entradas y precios\n" +
                "5. ¡Participa en el sorteo!\n" +
                "6. Salir de la aplicación");
    }

    /**
     * Función de la opción 1 que muestra las atracciones
     */

    public static void mostrarAtracciones(){

        System.out.println("****** Nuestras atracciones *******");
        System.out.println("1- Los rápidos. Intensidad: suave\n" +
                "2- Sillas voladoras. Intensidad: moderada\n" +
                "3- La lanzadera. Intensidad: alta\n" +
                "4- Star Flyer. Intensidad: alta\n" +
                "5- Vértigo. Intensidad: moderada\n" +
                "6- Los Fiordos. Intensidad: moderada");
    }

    /**
     * Función de la opción dos que muestra los restaurantes
     */

    public static void mostrarRestaurantes(){

        System.out.println("- La Super plaza\n" +
                "   ¡En La Plaza disfrutarás los mejores Hot Dogs!\n" +
                "- FoodTruck Adrenalina\n" +
                "   El yougurt helado con los toppings más deliciosos y frescos.\n" +
                "- Burger Alegría\n" +
                "   Si pruebas nuestras increíbles hamburguesas, repetirás en cada visita.\n" +
                "-Freeze Brain\n" +
                "   ¡Disfruta de un rico granizado!");
    }

    /**
     * Función llamada al introducir la opción 3, que según el més, muestra la cantidad de días que tiene dicho mes
     * @param mes mes introducido en formato de número por el usuario
     * @return devuelve el número de días que tiene el mes escogido
     */

    public static int cantidadDeDias(int mes){
        int dias = 0;

        switch(mes){
            case 1, 3, 5, 7, 8, 10, 12 -> dias = 31;
            case 2 -> dias = 28;
            case 4, 6, 9, 11 -> dias = 30;
        }
        return dias;
    }

    /**
     * Función que saca un número aleatorio dependiendo del máximo que corresponde a la
     * cantidad de días que tenga el mes
     * cantidadDeDias para establecer el máximo
     * @param maximo número máximo para generar el número aleatorio, en este caso a través de la función cantidadDeDias
     * @return retorna número aleatorio
     */

    public static int numeroAleatorio(int maximo){
        int fechaDisponible =(int) (Math.random() * cantidadDeDias(maximo) + 1);
        return fechaDisponible;
    }

    /**
     *Función que imprime números aleatorios (serán los días disponibles) las veces que se imprimen los números
     * dependen del número aleatorio que nos da la función numeroAleatorio.
     * @param mes mes que escogió el usuario por teclado, este se imprimirá.
     */

    public static void diasDisponibles(int mes){
        int disponible =  (int) (Math.random() * 10) + 1;
        System.out.println("Días disponibles del mes " + mes);
        for (int i = 1; i <= disponible; i++){
            System.out.print(numeroAleatorio(disponible) + " ");
        }
        System.out.println();
    }

    /**
     *Función que muestra los precios de las entradas al seleccionar la opción 4
     */

    public static void mostrarPrecios(){
        System.out.println("- Entrada reducida (<140cm) = 29,90€\n" +
                "- Entrada general: 39,90€");
    }

    /**
     *Función que pide cantidad de entradas y verifica que la compra sea correcta
     */

    public static void compraValida(){
        Scanner sc = new Scanner(System.in);
        char seguir;
        boolean compraValida = false;
        double total = 0, aplicarDescuento = 0;
        int descuento = 0;

        boolean verImporte = consultarImporte();

        do{
            System.out.println("Dime la cantidad de entradas reducidas: ");
            int reducida = sc.nextInt();
            System.out.println("Dime la cantidad de entradas generales: ");
            int generales = sc.nextInt();

            if (reducida < 0 || generales < 0 || reducida == 0 && generales ==0 ) {
                System.out.println("Datos no válidos. No has realizado ninguna compra.");

                do {
                    System.out.println("¿Desea seguir comprando? (S/N)");
                    seguir = sc.next().charAt(0);
                    seguir = Character.toUpperCase(seguir);
                } while (seguir != 'S' && seguir != 'N'); //solo saldremos del bucle cuando se escriba 's' o 'n'

                if (seguir == 'N') { //si es 'N', saldremos del bucle sin hacer cálculos
                    System.out.println("No has realizado ninguna compra.");
                    compraValida = true; // Salimos del bucle
                } //no pongo else, ya que la otra opción sí o sí será 's' y compraValida seguirá en false y
                // el codigo seguirá a las siguientes líneas

            }else{
                //Calculos de importe y descuentos a través de las funciones
                total = calcularImporte(reducida, generales);
                descuento = descuentoAsociado(total);
                aplicarDescuento = aplicarDescuento(total, descuento);
                if (verImporte){
                    //Si la respuesta es true ('s'), entonces imprimimos el importe total, sino, no se imprime
                    System.out.printf("Importe total: %.2f€\n", total);
                }
                System.out.println("Aplicable descuento del " + descuento + "%");
                System.out.printf("Importe Final: %.2f€\n",aplicarDescuento);
                compraValida = true; //salimos del bucle
            }
        }while (!compraValida);

    }

    /**
     * Función que calcula el importe total de todas las entradas escogidas
     * @param entradaReducida cantidad de entradas reducidas escogidas
     * @param entradaGeneral cantidad de entradas generales escogidas
     * @return devuelve el total de la operación de calcular todas las entradas
     */
    //Corregir error si se introduce números negativos de entradas o no se escoge ninguna
    public static double calcularImporte(int entradaReducida, int entradaGeneral){
        final double GENERAL = 39.90, REDUCIDA = 29.90;
        double importeTotal = (entradaReducida * REDUCIDA) + (entradaGeneral * GENERAL);

        return importeTotal;
    }

    /**
     * Función que según la respuesta que de el usuario se visualizará o no el importe
     * @return devuelve true si decide que si o false si decide que no
     */

    public static boolean consultarImporte(){
        Scanner sc = new Scanner(System.in);

        boolean respuestaIncorrecta = true;
        boolean verDescuento = true;
        do {
            System.out.println("Deseas consultar cuál será el importe en función " +
                    "del número de entradas (S/N): ");
            char respuesta = sc.next().toUpperCase().charAt(0);

            if (respuesta == 'S') {
                respuestaIncorrecta = false;
                verDescuento = true;

            } else if (respuesta == 'N') {
                respuestaIncorrecta = false;
                verDescuento = false;

            }else {
                System.out.println("Introduce S/N, por favor");
            }

        }while(respuestaIncorrecta);

        return verDescuento;
    }



    /**
     * Función que da un descuento al usuario dependiendo del importe total que han de pagar
     * @param importe total del importe de las entradas
     * @return devuelve el número que corresponde al descuento
     */

    public static int descuentoAsociado(double importe){
        int total;

        if(importe > 50 && importe < 100){
            total = 5;
        } else if (importe > 100 && importe < 200) {
            total = 10;
        } else if (importe > 200) {
            total = 30;
        }else {
            total = 0;
        }
        return total;
    }

    /**
     * Función que aplica el descuento al importe final
     * @param importe total del importe
     * @param descuento numero del descuento
     * @return devuelve la operación del importe final menos el descuento aplicado
     */

    public static double aplicarDescuento(double importe, int descuento){
        double importeTotal = 0;
        importeTotal = importe - (importe * descuento / 100);
        return importeTotal;

    }
    /**
     * Función que dice si el numero introducido es primo o no
     * @param numero numero introducido por consola
     * @return devuelve true si es primo y false si no lo es
     */

    public static boolean esPrimo(int numero){
        boolean primo = true; // Iniciamos diciendo que si es primo

        if (numero <= 1){
            primo = false; // Los números menor o igual a 1 no son primos
        }else{
            //Sacamos la raíz cuadrada del número para que el código sea más eficiente
            // y no recorra números de más, solo el número de su raíz cuadrada y así acortamos el rango del bucle.
            for( int i = 2; i <= Math.sqrt(numero); i++){
                if (numero % i == 0){ // Si el numero tiene divisor no es primo
                    primo = false;
                }
            }
        }
        return primo;
    }

    /**
     * Funcion que suma los divisores de un número si son primos, si no son primos no los suma
     * @param numero numero de sorteo introducido
     * @return devuelve la suma de los divisores primos
     */

    public static int sumaDivisoresPrimos(int numero){
        int suma = 0;

        for (int i = 1; i <= numero; i++){
            if (numero % i == 0){ //si número introducido es divisible por i (número que itera)
                if (esPrimo(i)){ //se compara si ese número por el que es divisible es primo
                    suma+= i;// si es primo se suma
                }
            }
        }
        return suma;
    }

    /**
     * Funcion que dice si ha tocado un premio u otro o ninguno
     * @param numero numero del sorteo
     * @return devuelve true si es premiado o false si no es premiado
     */

    public static boolean premio(int numero){
        boolean esPremio;

        if (esPrimo(numero)){
            esPremio = true;
            System.out.println("Enhorabuena, has ganado un granizado gratis en Freeze Brain.");
        }else if (sumaDivisoresPrimos(numero) > 10) {
            esPremio = true;
            System.out.println("Felicidades, has ganado dos menús de La Super Plaza");
        }else{
            esPremio = false;
        }

        return esPremio;
    }

    /**
     * Función que pregunta si se desea volver al menú principal o salir de la app
     * @return devuelve true si se quiere seguir o false si no se quiere.
     */
    public static boolean seguirEnPrograma() {
        Scanner sc = new Scanner(System.in);
        char continuar;
        boolean seguirBucle = false;
        boolean seguir = false;

        do {
            System.out.println("Desea volver al menú de opciones (S) o salir (N)");
            continuar = sc.next().toUpperCase().charAt(0);

            if (continuar == 'S') {
                seguir = true;
                seguirBucle = false;
            } else if (continuar == 'N') {
                System.out.println("Desconectando...");
                System.out.println("      |\\      _,,,---,,_\n" +
                        "ZZZzz /,`.-'`'    -.  ;-;;,_\n" +
                        "     |,4-  ) )-,_. ,\\ (  `'-'\n" +
                        "    '---''(_/--'  `-'\\_)  ...nos vemos pronto :D ");
                seguirBucle = false;
            } else {
                System.out.println("Dame una respuesta correcta (S/N)");
                seguirBucle = true;
            }

        } while (seguirBucle);

        return seguir;
    }

    public static void diasDisponibles(){
        Scanner sc = new Scanner(System.in);
        int mes;
        // bucle para introducir los meses de forma correcta
        do {
            System.out.println("Introduce el mes: ");
            mes = sc.nextInt();
            if (mes < 1 || mes > 12) {
                System.out.println("Mes introducido incorrecto. Intentalo de nuevo.");
            } else {
                numeroAleatorio(cantidadDeDias(mes));
                diasDisponibles(mes);
            }

        } while (mes < 1 || mes > 12);
    }




    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("¡Bienvenido/a a nuestra aplicación del parque de atracciones!");
        System.out.println("  /\\_/\\  (\n" +
                " ( ^.^ ) _)\n" +
                "   \\\"/  (\n" +
                " ( | | )\n" +
                "(__d b__)");
        System.out.println("Regístrate en nuestra aplicación para acceder al parque de atracciones y " +
                "descubrir todo lo que ofrecemos");

        System.out.println("Establece un usuario: ");
        String usuario = sc.nextLine();
        System.out.println("Establece una contraseña: ");
        String password = sc.nextLine();
        System.out.println("Registro realizado :D");

        String user;
        String pssww;
        do {
            System.out.println("Ingresa tus credenciales: ");
            System.out.println("Usuario: ");
            user = sc.nextLine();
            System.out.println("Contraseña: ");
            pssww = sc.nextLine();

        } while (login(usuario, user, password, pssww));

        int opcion;
        boolean continuar = false;
        boolean opcionCinco = false;
        do {
            mostrarMenuOpciones();
            System.out.println("Escribe la opción que deseas: ");
            opcion = sc.nextInt();


            switch (opcion) {
                case 1 -> {
                    mostrarAtracciones();
                    continuar = seguirEnPrograma();
                }
                case 2 -> {
                    mostrarRestaurantes();
                    continuar = seguirEnPrograma();
                }
                case 3 -> {
                    diasDisponibles();
                    continuar = seguirEnPrograma();
                }
                case 4 -> {
                    mostrarPrecios();
                    compraValida();
                    continuar = seguirEnPrograma();
                }
                case 5 -> {
                    int numPremio;
                    if (!opcionCinco) {
                        for (int i = 1; i <= 3; i++) {
                            System.out.println("Introduce el número que quieras y podrás ganar un premio: ");
                            numPremio = sc.nextInt();

                            if (premio(numPremio)) {
                                i = 3;
                            } else if (i <= 2) {
                                System.out.println("Inténtalo de nuevo.");
                            }else{
                                System.out.println("Otra vez será.");
                            }
                        }
                        opcionCinco = true; // Opcion se vuelve true para no dejar participar al usuario de nuevo
                    } else {
                        System.out.println("Ya has participado en el sorteo.");
                    }
                    continuar = seguirEnPrograma();
                }
                case 6 -> { System.out.println("Desconectando...");
                    System.out.println("      |\\      _,,,---,,_\n" +
                            "ZZZzz /,`.-'`'    -.  ;-;;,_\n" +
                            "     |,4-  ) )-,_. ,\\ (  `'-'\n" +
                            "    '---''(_/--'  `-'\\_)  ...nos vemos pronto :D ");
                }
                default -> {
                    System.out.println("\nOpción no disponible... Vuelva a intentarlo.");
                    continuar = seguirEnPrograma();
                }
            }

        } while (opcion != 6 && continuar);


    }