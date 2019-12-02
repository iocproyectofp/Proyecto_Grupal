//////////////////////////////////////////////////////////////////////
// Autor: Pedro A. Ruiz                                             //    
// Versión: 2.0                                                     //        
// Modificaciones (ENUNCIADO):                                      //
//     - Intentos por ERROR preguntas -> 3                          //
//     - Entrada de registros ilimitado.                            //
//                                                                  //
// Modificaciones própias (muestra de datos):                       //            
//     - Cambio muestra la opción "No responde" -> "-".             //    
//     - Cambio muestra la opción "idSesión<10" ->   2 digitos.     //
//////////////////////////////////////////////////////////////////////

package proyecto;

import java.util.Scanner;

public class Proyecto_JBCN {

    //DECLARACIÓN DE CONSTANTES//

    //Género
    public static final int MUJER = 1;
    public static final int HOMBRE = 2;
    public static final int NO_RESPONDE = 3;
    public static final String OP_MUJER = "Mujer\t\t";
    public static final String OP_HOMBRE = "Hombre\t\t";
    public static final String OP_NO_RESPONDE = "-\t\t";

    //Tipo de participante
    public static final int GENERAL = 0;
    public static final int EDUCACION = 1;
    public static final int EMPRESA = 2;
    public static final int PRENSA = 3;
    public static final String OP_GENERAL = "General\t\t";
    public static final String OP_EDUCACION = "Educación\t";
    public static final String OP_EMPRESA = "Empresa\t\t";
    public static final String OP_PRENSA = "Prensa\t\t";

    //Sesión
    public static final String SI_SESION = "SI:";
    public static final String NO_SESION = "NO";

    //Mínimos y Máximos (control de errores)
    public static final int COD_MIN = 100;
    public static final int COD_MAX = 2100;
    public static final int GENERO_MIN = 1;
    public static final int GENERO_MAX = 3;
    public static final int TIPO_MIN = 0;
    public static final int TIPO_MAX = 3;
    public static final int SESION_MIN = 0;
    public static final int SESION_MAX = 1;
    public static final int ID_MIN = 4;
    public static final int ID_MAX = 27;
    public static final int EXPERIENCIA_MIN = 0;
    public static final int EXPERIENCIA_MAX = 30;
    public static final int MAX_INTENTOS = 3;
    public static final int SEGUIR_MIN = 0;
    public static final int SEGUIR_MAX = 1;
    public static final int MAX_REGISTROS = 2;
    
    //Mensaje de error
    public static final String FUERA_RANGO = "El dato debe estar comprendido entre: ";
    public static final String NO_ENTERO = "El dato no es un entero.\n";
    
    //Cabecera
    public static final String CABECERA = "Código\t\tGénero\t\tTipo\t\tSesión\t\tExperiencia\n";

    //FIN DECLARACIÓN CONSTANTES //

    public static void main(String[] args){
        
        //Declaración de variables //
        int codigo=0, genero=0, tipoParticipante=0, añosExperiencia=0, 
            daSesion=0, idSesion=0, intentos = 0, seguir=0, 
            registrosEntrados = 0, contRegistros = 0, auxExperiencia = 0;
        
        String generoTipo="", opcionParticipante="", sesion="";
        
        boolean tipoCorrecto = true, introducirMas;
        //Fin declaración variables //
        
        //Declaración de arrays
        int [] codigos = new int[MAX_REGISTROS];
        int [] generos = new int[MAX_REGISTROS];
        int [] participantes = new int[MAX_REGISTROS];
        int [] daSesiones = new int[MAX_REGISTROS];
        int [] idSesiones = new int[MAX_REGISTROS];
        int [] anosExperiencia = new int[MAX_REGISTROS];
        
        //Lector Entrada de datos
        Scanner entrada = new Scanner(System.in);
        
        // Se repetirá mientras "introducirMas" sea true
        do
        {
            introducirMas = false; //Por defecto no queremos introducir más registros
            
            //Repite mientras tipoCorrecto = false e intentos < MAX_INTENTOS
            do
            {
                //Ejecución del programa (Entrada Código incripción)
                System.out.print("Entrada de datos\n" +
                                 "----------------\n" +
                                 "Código (100-2100):");
                //Evaluamos si el código es entero y si cumple el rango
                tipoCorrecto = entrada.hasNextInt();
                if(tipoCorrecto)
                {           
                    codigos[contRegistros] = entrada.nextInt();
                    if((codigos[contRegistros] < COD_MIN) || (codigos[contRegistros] > COD_MAX))
                    {
                        //Si no cumple el rango 
                        tipoCorrecto = false;
                        System.out.println(FUERA_RANGO + COD_MIN + " y " + COD_MAX + "\n");
                        intentos++; //incrementamos intentos por cada error
                    }
                }else
                {
                    System.out.println(NO_ENTERO); //Mensaje error
                    entrada.next(); //liberamos buffer
                    intentos++; //incrementamos intentos por cada error
                }
                
                
            }while((!tipoCorrecto) && (intentos < MAX_INTENTOS));
            
            //Si cumple el rango mostramos el Menu Género
            if(tipoCorrecto)
            {                   
                intentos = 0; //Reseteamos el valor de "intentos"
                //Repite mientras tipoCorrecto = false e intentos < MAX_INTENTOS
                do
                {
                    //Menu 1 (Género)
                    System.out.print("\n" + 
                                     "Elige género\n" +
                                     "1 - Es Mujer.\n" +
                                     "2 - Es Hombre\n" + 
                                     "3 - No responde\n" + 
                                     "Elige una opción entre (1-2-3):");
                    //Evaluamos entero y rango 
                    tipoCorrecto = entrada.hasNextInt();
                    if(tipoCorrecto)
                    {
                        generos[contRegistros] = entrada.nextInt();
                        if((generos[contRegistros] < GENERO_MIN) || (generos[contRegistros] > GENERO_MAX))
                        {
                            tipoCorrecto = false;
                            System.out.println(FUERA_RANGO + GENERO_MIN + 
                                               " y " + GENERO_MAX + "\n");
                            intentos++; //incrementamos intentos por cada error
                        }
                    }else
                    {
                        System.out.println(NO_ENTERO); //Mensaje error
                        entrada.next(); //liberamos buffer
                        intentos++; //incrementamos intentos por cada error
                    }
                    
                }while((!tipoCorrecto) && (intentos < MAX_INTENTOS));
            }
            //Si cumple rango asignamos a la variable "generoTipo" la opción seleccionada
            if(tipoCorrecto)
            {
                intentos = 0; //Reseteamos el valor de "intentos"
                //Repite mientras tipoCorrecto = false e intentos < MAX_INTENTOS
                do
                {
                    //Menu 2 (Tipo Participante)
                    System.out.print("\n" + 
                                     "Tipo de participante\n" +
                                     "0 - General\n" +
                                     "1 - Educación\n" +
                                     "2 - Empresa\n" +
                                     "3 - Prensa\n" +
                                     "Elige una opción entre (0-1-2-3):");
                    //Evaluamos entero y rango
                    tipoCorrecto = entrada.hasNextInt();
                    if(tipoCorrecto)
                    {
                        participantes[contRegistros] = entrada.nextInt();
                        if((participantes[contRegistros] < TIPO_MIN) || (participantes[contRegistros] > TIPO_MAX))
                        {
                            tipoCorrecto = false;
                            System.out.println(FUERA_RANGO + TIPO_MIN + 
                                               " y " + TIPO_MAX + "\n");
                            intentos++; //incrementamos intentos por cada error
                        }
                    }else
                    {
                        System.out.println(NO_ENTERO); //Mensaje error
                        entrada.next(); //liberamos buffer
                        intentos++; //incrementamos intentos por cada error
                    }
                    
                }while((!tipoCorrecto) && (intentos < MAX_INTENTOS));
            }        
            //Si cumple el rango asignamos a opcionParticipante la opción seleccionada
            if(tipoCorrecto)
            { 
                intentos = 0; //Reseteamos el valor de "intentos"                
                //Repite mientras tipoCorrecto = false e intentos < MAX_INTENTOS
                do
                {
                    //Menu 3 (Sesión)
                    System.out.print("\n" +
                                     "¿Da sesión?\n" +
                                     "0 - No\n" +
                                     "1 - Si\n" +
                                     "Elige una opción entre (0-1):");
                    //Evaluamos entero y rango
                    tipoCorrecto = entrada.hasNextInt();
                    if(tipoCorrecto)
                    {                    
                        daSesiones[contRegistros] = entrada.nextInt();
                        if((daSesiones[contRegistros] < SESION_MIN) || (daSesiones[contRegistros] > SESION_MAX))
                        {
                            tipoCorrecto = false;
                            System.out.println(FUERA_RANGO + SESION_MIN + 
                                               " y " + SESION_MAX + "\n");
                            intentos++; //incrementamos intentos por cada error
                        }
                    }else
                    {
                        System.out.println(NO_ENTERO); //Mensaje error
                        entrada.next(); //liberamos buffer
                        intentos++; //incrementamos intentos por cada error
                    }
                    
                }while((!tipoCorrecto) && (intentos < MAX_INTENTOS));
            }
            //Si cumple el rango asignamos a sesion la opción seleccionada
            if(tipoCorrecto)
            {
                intentos = 0; //Reseteamos el valor de "intentos"
                if(daSesiones[contRegistros] == SESION_MAX) 
                {
                    //Repite mientras tipoCorrecto = false e intentos < MAX_INTENTOS
                    do
                    {
                        //Si elige dar sesión preguntamos ID
                        System.out.print("\nID Sesión (" + ID_MIN + "-" + ID_MAX + "):");
                        //Evaluamos entero y rango
                        tipoCorrecto = entrada.hasNextInt();
                        if(tipoCorrecto)
                        {
                            idSesiones[contRegistros] = entrada.nextInt();
                            if((idSesiones[contRegistros] < ID_MIN) || (idSesiones[contRegistros] > ID_MAX))
                            {
                                tipoCorrecto = false;
                                System.out.println(FUERA_RANGO + ID_MIN + 
                                                   " y " + ID_MAX + "\n");
                                intentos++; //incrementamos intentos por cada error
                            }
                        }else
                        {
                            System.out.println(NO_ENTERO); //Mensaje error
                            entrada.next(); //liberamos buffer
                            intentos++; //incrementamos intentos por cada error
                        }

                    }while((!tipoCorrecto) && (intentos < MAX_INTENTOS));
                      
                }

            }
            //Si cumple el rango preguntamos por los años de experiencia
            if(tipoCorrecto)
            {
                intentos = 0; //Reseteamos el valor de "intentos"
                //Repite mientras tipoCorrecto = false e intentos < MAX_INTENTOS
                do
                {
                    System.out.print("\nAños de experiencia (0-30):");
                    tipoCorrecto = entrada.hasNextInt();
                    //Evaluamos entero y rango
                    if(tipoCorrecto)
                    {
                        anosExperiencia[contRegistros] = entrada.nextInt();
                        if((anosExperiencia[contRegistros] < EXPERIENCIA_MIN) || (anosExperiencia[contRegistros] > EXPERIENCIA_MAX))
                        {
                            tipoCorrecto = false;
                            System.out.println(FUERA_RANGO + EXPERIENCIA_MIN + 
                                               " y " + EXPERIENCIA_MAX + "\n");
                            intentos++; //incrementamos el valor de intentos por cada error
                        }
                    }else
                    {
                        System.out.println(NO_ENTERO); //Mensaje error
                        entrada.next(); //liberamos buffer
                        intentos++; //incrementamos el valor de intentos por cada error
                    }
                    
                    if(tipoCorrecto)
                    {
                        contRegistros++;
                    }
                
                    
                }while((!tipoCorrecto) && (intentos < MAX_INTENTOS));
            }
            
            //Preguntamos si quiere introducir más siempre que no hallmos llegado a 'MAX_REGISTROS'
            if(contRegistros < MAX_REGISTROS)
            {
                //Repite mientras tipoCorrecto = false (en este punto, no evaluamos intentos).
                do
                {
                    System.out.print("\nIntroducir más registros SI(1) - NO(0):");
                    tipoCorrecto = entrada.hasNextInt();
                    if(tipoCorrecto)
                    {
                        seguir = entrada.nextInt();
                        if((seguir < SEGUIR_MIN) || seguir > SEGUIR_MAX)
                        {
                            tipoCorrecto = false;
                            System.out.println(FUERA_RANGO + SEGUIR_MIN + 
                                               " y " + SEGUIR_MAX + "\n");                       
                        }          
                    }else
                    {
                        System.out.println(NO_ENTERO); //Mensaje error
                        entrada.next(); //liberamos buffer                    
                    }       
                    //Si el valor de "seguir" es = 1 iniciamos nuevo el registro
                    if(seguir == 1)
                    {
                        introducirMas = true;                        
                        intentos = 0; //Reseteamos el valor de "intentos para el siguiente registro"
                        System.out.println(); //salto de línea
                    }                

                }while(!tipoCorrecto);
            }
           
        }while((introducirMas) && (contRegistros < MAX_REGISTROS));
        
        boolean repetir = false;
        int conRepetir = 0;
        int repetido = 0;
        while(!repetir){
            
            if(repetido == 1)
            {
                conRepetir++;  
                repetir = true;
            }
            
            //Evaluamos cuantos registros se han completado y mostramos un mensaje
            if(contRegistros > 0) //Si al menos hemos completado un registro...
            {
                //Evaluamos las opciones elegidas por el usuario e imprimismo
                for(int i = 0; i < contRegistros; i++)
                {
                    //Asignamos el valor del array 'generos'  a 'generoTipo' con la opción seleccionada por el usuario
                    switch(generos[i])
                    {
                        case MUJER:
                            generoTipo = OP_MUJER;
                            break;

                        case HOMBRE:
                            generoTipo = OP_HOMBRE;
                            break;

                        case NO_RESPONDE:
                            generoTipo = OP_NO_RESPONDE;
                            break;
                    } 
   
                    //Asignamos el valor del array 'participantes' a 'opcionParticipante' con la opción seleccionada por el usuario
                    switch(participantes[i])
                    {
                        case GENERAL:
                            opcionParticipante = OP_GENERAL;
                            break;

                        case EDUCACION:
                            opcionParticipante = OP_EDUCACION;
                            break;

                        case EMPRESA:
                            opcionParticipante = OP_EMPRESA;
                            break;

                        case PRENSA:
                            opcionParticipante = OP_PRENSA;
                    }

                    //Asignamos el valor del array 'participantes' a 'sesion' con la opción seleccionada por el usuario
                     switch(daSesiones[i])
                    {
                        case SESION_MIN:
                            sesion = NO_SESION;                      
                            break;

                        case SESION_MAX:
                            sesion = SI_SESION; 
                            break; 
                    }

                    //Imprimimos los resultados
                    if((tipoCorrecto) || (contRegistros < MAX_REGISTROS))
                    {                         
                        //registrosEntrados++; //Si llegamos a este punto, contabilizamos el registro                
                        if(sesion == SI_SESION)//Si da sesión, mostramos los datos CON el ID sesión
                        {              
                            // Si idSesión es < 10 añadimos un "0" delante para que muestre 2 dígitos
                            if(idSesiones[i]<10)
                            {
                                System.out.println(); //salto de línea
                                System.out.println("---------------------------------------"+
                                                   "------------------------------------\n" +
                                                   CABECERA + codigos[i] + "\t\t" + generoTipo + 
                                                   opcionParticipante + sesion + "0" + idSesiones[i] + 
                                                   "\t\t" + anosExperiencia[i] + "\n" +
                                                   "-------------------------------------"+
                                                   "--------------------------------------"); 
                            }else
                            {
                                System.out.println(); //salto de línea
                                System.out.println("---------------------------------------"+
                                                   "------------------------------------\n" +
                                                   CABECERA + codigos[i] + "\t\t" + generoTipo + 
                                                   opcionParticipante + sesion + idSesiones[i] + 
                                                   "\t\t" + anosExperiencia[i] + "\n" +
                                                   "-------------------------------------"+
                                                   "--------------------------------------");
                            }                    
                        }else //Si NO da sesión, mostramos los datos SIN el ID sesión
                        {
                            System.out.println(); //salto de línea
                            System.out.println("---------------------------------------" +
                                               "------------------------------------\n" +
                                               CABECERA + codigos[i] + "\t\t" + generoTipo + 
                                               opcionParticipante + sesion + "\t\t" + 
                                               anosExperiencia[i] + "\n" +
                                               "-------------------------------------"+
                                               "--------------------------------------");                     
                        }
                    }else //Mensaje dato incorrecto en cualquiera de las preguntas durante ejecución
                    {
                        System.out.println("\nDato Incorrecto. Has superado " + intentos + " intentos");
                    }
                    
                    System.out.println(); //salto de línea 
                    
                    if(conRepetir <1)
                    {
                        System.out.print("¿Quieres volver a imprimir? Si(1)-No(0): ");
                        repetido = entrada.nextInt();
                                                
                    }                    
                    
                }

                System.out.println("\nSe han incrito: " + contRegistros + " participante/s nuev@/s");
            }else //Si no se ha completado ningún registro completo...
            {
                System.out.println("\nNo se ha registrado ningún participante.");
            }
        }

    }

}
