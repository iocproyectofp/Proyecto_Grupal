//////////////////////////////////////////////////////////////////////
// Autor: Pedro A. Ruiz                                             //    
// Versión: 3.0                                                     //        
// Modificaciones (ENUNCIADO):                                      //
//     - Opciones seleccionadas almacenadas en arrays.              //
//     - Ordenado opciones seleccionadas por experiencia.           //
//                                                                  //
// Modificaciones própias (control duplicaciones y buffer):         //            
//     - Impide que se dupliquen códigos de registro.               //    
//     - Impide que se dupliquen ID Sesión.                         //
//     - Resuelto problema buffer valores NO ENTERO con espacios.   //
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
    public static final int MAX_REGISTROS = 10;
    public static final int MIN_REGISTROS = 1;
    public static final int MAX_IMPRIMIR = 2;
    public static final int MIN_IMPRIMIR = 1;
    public static final int REGISTRO_NULO = 0;
    public static final int OP_IMPRIMIR_MIN = 0;
    public static final int OP_IMPRIMIR_MAX = 1;
    public static final int DOS_DIGITOS = 10;
    public static final int PRIMER_REGISTRO = 0;
    
    //Mensaje de error
    public static final String FUERA_RANGO = "El dato debe estar comprendido entre: ";
    public static final String NO_ENTERO = "El dato no es un entero.\n";
    public static final String REG_DUPLICADO = "Error, registro duplicado.\n";
    
    //Cabecera
    public static final String CABECERA = "Código\t\tGénero\t\tTipo\t\tSesión\t\tExperiencia";

    //FIN DECLARACIÓN CONSTANTES //
    

    public static void main(String[] args){
        
        //Declaración de variables //
        
        //int
        int seguir, imprimir, i, j, contImprimir, contRegistros, intentos, aux;
        seguir = imprimir = i = j = contImprimir = contRegistros = intentos = aux = 0;
        
        //String
        String generoTipo, opcionParticipante, sesion, buffer;
        generoTipo = opcionParticipante = sesion = buffer = "";
        
        //boolean
        boolean tipoCorrecto = true, introducirMas, duplicado = false;     
        
        //Fin declaración variables //
        
        // Declaración de arrays //
        int [] codigos = new int[MAX_REGISTROS];
        int [] generos = new int[MAX_REGISTROS];
        int [] participantes = new int[MAX_REGISTROS];
        int [] daSesiones = new int[MAX_REGISTROS];
        int [] idSesiones = new int[MAX_REGISTROS];
        int [] anosExperiencia = new int[MAX_REGISTROS];
        // Fin declaración Arrays //
        
        //Lector Entrada de datos
        Scanner entrada = new Scanner(System.in);
        
        
        // INICIAMOS LA EJECUCIÓN //        
        
        do //Se repetirá mientras "introducirMas" sea true y no superemos el maximo de registros
        {
            introducirMas = false; //Por defecto no queremos introducir más registros
            
            // BLOQUE DE PREGUNTAS //  
            do //Repite mientras tipoCorrecto = false e intentos < MAX_INTENTOS
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
                    
                    // CONTTROL REGISTROS DUPLICADOS                    
                    if(contRegistros > PRIMER_REGISTRO) //Evaluamos a partir del segundo registro.
                    {
                        //Recorremos el array desde el principio hasta el último valor entrado
                        for(i = 0; i < contRegistros; i++)
                        {
                            //Si encontramos coincidencia evaluamos 'duplicado' a true
                            if(codigos[i] == codigos[contRegistros])
                            {
                                duplicado =  true;
                            }
                        }
                    }
                    // FIN CONTROL DUPLICADOS
                    
                    //Si el valor no esta duplicado, evaluamos rango
                    if(!duplicado)
                    {
                        if((codigos[contRegistros] < COD_MIN) || (codigos[contRegistros] > COD_MAX))
                        {
                            //Si no cumple el rango 
                            tipoCorrecto = false;
                            System.out.println(FUERA_RANGO + COD_MIN + " y " + COD_MAX + "\n");
                            entrada.nextLine(); // liberamos buffer
                            intentos++; //incrementamos intentos por cada error
                        }
                    }else //Si está duplicado mostramos mensaje y reseteamos boolean
                    {
                        System.out.println(REG_DUPLICADO);
                        tipoCorrecto = false;
                        duplicado = false;
                        intentos++; //incrementamos intentos por cada error
                    }
                }else //si el valor no es entero
                {                      
                    buffer = entrada.nextLine(); //liberamos buffer
                    System.out.println(NO_ENTERO); //Mensaje error                   
                    intentos++; //incrementamos intentos por cada error
                }                
                
            }while((!tipoCorrecto) && (intentos < MAX_INTENTOS));
            
            //Si cumple el rango mostramos el Menu Género
            if(tipoCorrecto)
            {                   
                entrada.nextLine(); //liberamos buffer
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
                     
                    tipoCorrecto = entrada.hasNextInt();
                    //Evaluamos entero y rango
                    if(tipoCorrecto)
                    {
                        generos[contRegistros] = entrada.nextInt();
                        if((generos[contRegistros] < GENERO_MIN) || 
                           (generos[contRegistros] > GENERO_MAX))
                        {
                            tipoCorrecto = false;
                            System.out.println(FUERA_RANGO + GENERO_MIN + 
                                               " y " + GENERO_MAX + "\n");
                            entrada.nextLine(); //liberamos buffer
                            intentos++; //incrementamos intentos por cada error
                        }
                    }else
                    {
                        buffer = entrada.nextLine(); //liberamos buffer
                        System.out.println(NO_ENTERO); //Mensaje error                        
                        intentos++; //incrementamos intentos por cada error
                    }
                    
                }while((!tipoCorrecto) && (intentos < MAX_INTENTOS));
            }
            
            //Si cumple el rango mostramos el Menu Participante
            if(tipoCorrecto)
            {
                entrada.nextLine(); //liberamos buffer
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
                        if((participantes[contRegistros] < TIPO_MIN) || 
                           (participantes[contRegistros] > TIPO_MAX))
                        {
                            tipoCorrecto = false;
                            System.out.println(FUERA_RANGO + TIPO_MIN + 
                                               " y " + TIPO_MAX + "\n");
                            entrada.nextLine(); //liberamos buffer
                            intentos++; //incrementamos intentos por cada error
                        }
                    }else
                    {
                        buffer = entrada.nextLine(); //liberamos buffer
                        System.out.println(NO_ENTERO); //Mensaje error                        
                        intentos++; //incrementamos intentos por cada error
                    }
                    
                }while((!tipoCorrecto) && (intentos < MAX_INTENTOS));
            }   
            
            //Si cumple el rango mostramos el Menu Sesión
            if(tipoCorrecto)
            { 
                entrada.nextLine(); //liberamos buffer
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
                    
                    tipoCorrecto = entrada.hasNextInt();
                    //Evaluamos entero y rango
                    if(tipoCorrecto)
                    {                    
                        daSesiones[contRegistros] = entrada.nextInt();
                        if((daSesiones[contRegistros] < SESION_MIN) || 
                           (daSesiones[contRegistros] > SESION_MAX))
                        {
                            tipoCorrecto = false;
                            System.out.println(FUERA_RANGO + SESION_MIN + 
                                               " y " + SESION_MAX + "\n");
                            entrada.nextLine(); //liberamos buffer
                            intentos++; //incrementamos intentos por cada error
                        }
                    }else
                    {
                        buffer = entrada.nextLine(); //liberamos buffer
                        System.out.println(NO_ENTERO); //Mensaje error                        
                        intentos++; //incrementamos intentos por cada error
                    }
                    
                }while((!tipoCorrecto) && (intentos < MAX_INTENTOS));
            }
            
            //Si cumple el rango y da sesión, Mostramos Menú ID Sesión
            if(tipoCorrecto)
            {                
                intentos = 0; //Reseteamos el valor de "intentos"
                if(daSesiones[contRegistros] == SESION_MAX) 
                {
                    entrada.nextLine(); //liberamos buffer
                    //Repite mientras tipoCorrecto = false e intentos < MAX_INTENTOS
                    do
                    {
                        //Menu 4 - ID Sesión
                        System.out.print("\nID Sesión (" + ID_MIN + "-" + ID_MAX + "):");
                        //Evaluamos entero y rango
                        tipoCorrecto = entrada.hasNextInt();
                        if(tipoCorrecto)
                        {
                            idSesiones[contRegistros] = entrada.nextInt();
                            
                            //CONTROL DUPLICADOS
                            if(contRegistros > PRIMER_REGISTRO) //Evaluamos a partir del segundo registro
                            {
                                //Recorremos el array desde el principio hasta el último valor entrado
                                for(i = 0; i < contRegistros; i++)
                                {
                                    //Si encontramos coincidencia evaluamos 'duplicado' a true
                                    if(idSesiones[i] == idSesiones[contRegistros])
                                    {
                                        duplicado =  true;
                                    }
                                }
                            }
                            //FIN CONTROL DUPLICADOS
                            
                            //Si el valor no está duplicado evaluamos rango.
                            if(!duplicado)
                            {
                                if((idSesiones[contRegistros] < ID_MIN) || 
                                   (idSesiones[contRegistros] > ID_MAX))
                                {
                                    tipoCorrecto = false;
                                    System.out.println(FUERA_RANGO + ID_MIN + 
                                                       " y " + ID_MAX + "\n");
                                    entrada.nextLine(); // liberamos buffer
                                    intentos++; //incrementamos intentos por cada error
                                }
                            }else //Si está duplicado, mostramos mensaje de error
                            {
                                System.out.println(REG_DUPLICADO);
                                tipoCorrecto = false;
                                duplicado = false;
                                intentos++; //incrementamos intentos por cada error
                            }
                        }else //Mensaje error, NO ENTERO.
                        {
                            buffer = entrada.nextLine(); //liberamos buffer
                            System.out.println(NO_ENTERO); //Mensaje error                            
                            intentos++; //incrementamos intentos por cada error
                        }

                    }while((!tipoCorrecto) && (intentos < MAX_INTENTOS));
                      
                }

            }
            
            //Si cumple el rango mostramos menú experiencia
            if(tipoCorrecto)
            {
                entrada.nextLine(); //liberamos buffer
                intentos = 0; //Reseteamos el valor de "intentos"
                //Repite mientras tipoCorrecto = false e intentos < MAX_INTENTOS
                do
                {
                    //Menú 5 - Años Experiencia
                    System.out.print("\nAños de experiencia (0-30):");
                    tipoCorrecto = entrada.hasNextInt();
                    //Evaluamos entero y rango
                    if(tipoCorrecto)
                    {
                        anosExperiencia[contRegistros] = entrada.nextInt();
                        if((anosExperiencia[contRegistros] < EXPERIENCIA_MIN) || 
                           (anosExperiencia[contRegistros] > EXPERIENCIA_MAX))
                        {
                            tipoCorrecto = false;
                            System.out.println(FUERA_RANGO + EXPERIENCIA_MIN + 
                                               " y " + EXPERIENCIA_MAX + "\n");
                            entrada.nextLine(); //liberamos buffer
                            intentos++; //incrementamos el valor de intentos por cada error
                        }
                    }else
                    {
                        buffer = entrada.nextLine(); //liberamos buffer
                        System.out.println(NO_ENTERO); //Mensaje error                        
                        intentos++; //incrementamos el valor de intentos por cada error
                    }                    
                    // FIN BLOQUE PREGUNTAS //
                    
                    //Se incrementará 'contRegistros' si TODAS  opciones han sido correctas.
                    if(tipoCorrecto)
                    {
                        contRegistros++;
                    }                
                    
                }while((!tipoCorrecto) && (intentos < MAX_INTENTOS));
                
            }
            
            //Si se han superado los intentos (3) por pregunta, mostramos mensaje error
            if(intentos >= MAX_INTENTOS)
            {
                System.out.println("\nDato Incorrecto. Has superado " + intentos + " intentos");
            }
            
            //Preguntamos introducir más registros si 'contRegistros < MAXREGISTROS'.
            if(contRegistros < MAX_REGISTROS)
            {                 
                do //Repite mientras tipoCorrecto = false (en este punto, no evaluamos intentos).
                {
                    System.out.print("\nIntroducir más registros SI(1) - NO(0):");
                    tipoCorrecto = entrada.hasNextInt();
                    //Evaluamos ENTERO y RANGO
                    if(tipoCorrecto)
                    {
                        seguir = entrada.nextInt();                        
                        if((seguir < SEGUIR_MIN) || seguir > SEGUIR_MAX)
                        {
                            tipoCorrecto = false;
                            System.out.println(FUERA_RANGO + SEGUIR_MIN + 
                                               " y " + SEGUIR_MAX + "\n"); 
                            entrada.nextLine(); //liberamos buffer
                        }          
                    }else
                    {
                        buffer = entrada.nextLine(); //liberamos buffer
                        System.out.println(NO_ENTERO); //Mensaje error                                        
                    }       
                    
                    //Evaluamos la respuesta del usuario sobre un nuevo registro
                    if(seguir == SEGUIR_MAX) //Si "seguir" es = 1 iniciamos nuevo registro
                    {
                        introducirMas = true;                        
                        intentos = 0; //Reseteamos "intentos" para el siguiente registro"
                        entrada.nextLine();
                        System.out.println(); //salto de línea
                    }

                }while(!tipoCorrecto);
                
            }                
           
        }while((introducirMas) && (contRegistros < MAX_REGISTROS));                
        
        
        // BLOQUE DE IMPRESIÓN //
        
        //Evaluamos cuantos registros se han completado y mostramos un mensaje al finalizar el programa.
        if(contRegistros > REGISTRO_NULO) //Si al menos hemos completado un registro...
        {   
            do
            {
                //Según el número de vuelta mostramos una cabecera distinta.
                if(contImprimir < MIN_IMPRIMIR) //Vuelta 1 - Sin ordenar
                {
                    System.out.println("\n\t\t\tREGISTROS ENTRADOS");
                    System.out.println("---------------------------------------"+
                                       "------------------------------------\n" +
                                       CABECERA + "\n" +
                                       "---------------------------------------"+
                                       "------------------------------------");
                }else //Vuelta 2 - Ordenamos por experiencia.
                {
                    System.out.println("\n\t\tREGISTROS ORDENADOS POR EXPERIENCIA");
                    System.out.println("---------------------------------------"+
                                       "------------------------------------\n" +
                                       CABECERA + "\n" +
                                       "---------------------------------------"+
                                       "------------------------------------");
                }                    
                
                //Recorremos opciones elegidas usuario y asignamos valor.
                for(i = 0; i < contRegistros; i++)
                {
                    //Asignamos valor array 'generos' a 'generoTipo'.
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
   
                    //Asignamos valor array 'participantes' a 'opcionParticipante'.
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

                    //Asignamos valor array 'daSesiones' a 'sesion'.
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
                    if(sesion == SI_SESION)//Si da sesión, mostramos los datos CON ID sesión
                    {              
                        // Si idSesión es < 10 añadimos un "0" delante para que muestre 2 dígitos
                        if(idSesiones[i] < DOS_DIGITOS)
                        {
                            System.out.println(codigos[i] + "\t\t" + generoTipo + 
                                               opcionParticipante + sesion + "0" + idSesiones[i] + 
                                               "\t\t" + anosExperiencia[i] + "\n"); 
                        }else //si >10 no añadimos nada...
                        {                            
                            System.out.println(codigos[i] + "\t\t" + generoTipo + 
                                               opcionParticipante + sesion + idSesiones[i] + 
                                               "\t\t" + anosExperiencia[i] + "\n");
                        }                    
                    }else //Si NO da sesión, mostramos los datos SIN ID sesión
                    {                        
                        System.out.println(codigos[i] + "\t\t" + generoTipo + 
                                           opcionParticipante + sesion + "\t\t" + 
                                           anosExperiencia[i] + "\n");                     
                    }
                    
                }
                
                System.out.println(); //salto de línea
                
                //Mostramos mensaje una vez y si hay + de un registro
                if((contImprimir < MIN_IMPRIMIR) && (contRegistros > MIN_REGISTROS))
                {
                    do
                    {
                        //Preguntamos si quiere que se muestre los valores ordenador por experiencia <>
                        System.out.print("¿Quieres mostrar los datos ordenados " +
                                         "por experiencia de <>? Si(1)-No(0): ");
                        tipoCorrecto = entrada.hasNextInt();
                        //Evaluamos Entero y Rango.
                        if(!tipoCorrecto)
                        {
                            buffer = entrada.nextLine(); //liberamos buffer
                            System.out.println("Error, el dato debe ser un entero.\n");
                            
                        }else
                        {
                            imprimir = entrada.nextInt();                            
                            if((imprimir < OP_IMPRIMIR_MIN) || (imprimir > OP_IMPRIMIR_MAX))
                            {
                                System.out.println("Error, el valor de estran entre " + 
                                                   OP_IMPRIMIR_MIN + "-" + OP_IMPRIMIR_MAX + "\n");
                                entrada.nextLine(); //Liberamos buffer
                                tipoCorrecto = false;
                            }
                        }
                        
                    }while(!tipoCorrecto);
                    
                }   

                //Evaluamos la respuesta del usuario en el ordenamiento de los registros
                if(imprimir == OP_IMPRIMIR_MIN) // si no quiere ordenarlos, mostramos SIN ORDENAR y salimos bucle
                {
                   tipoCorrecto = false; 
                }
                else if(imprimir == OP_IMPRIMIR_MAX) //Si quiere ordenarlos, mostramos registros ordenados por experiencia.
                {
                    contImprimir++; //Incrementamos 'contImprimir' para ejecución segunda vuelta.
                    
                    
                    // ORDENAMOS POR EXPERIENCIA 
                    
                    //Recorremos array y comparamos 'anosExperiencia[i]' con 'anosExperiencia[j]'.  
                    for(i = 0; i < contRegistros; i++)
                    {
                        for(j = i + 1; j < contRegistros; j++)
                        {
                            //Evaluamos si 'anosExperiencia[i]' es > 'anosExperiencia[j].
                            if(anosExperiencia[i] > anosExperiencia[j])
                            {
                                //Ordenamos Código
                                aux = codigos[i];
                                codigos[i] = codigos[j];
                                codigos[j] = aux;
                                
                                //Ordenamos Género
                                aux = generos[i];
                                generos[i] = generos[j];
                                generos[j] = aux;
                                
                                //Ordenamos Tipo
                                aux = participantes[i];
                                participantes[i] = participantes[j];
                                participantes[j] = aux;
                                
                                //Ordenamos da Sesión
                                aux = daSesiones[i];
                                daSesiones[i] = daSesiones[j];
                                daSesiones[j] = aux;
                                
                                //Ordenamos ID Sesión
                                aux = idSesiones[i];
                                idSesiones[i] = idSesiones[j];
                                idSesiones[j] = aux;
                                                                
                                //Ordenamos Experiencia                                
                                aux = anosExperiencia[i];
                                anosExperiencia[i] = anosExperiencia[j];
                                anosExperiencia[j] = aux;                            
                            }
                        }
                    }
                } 
                
                // FIN ORDENAMIENTO 
                
            }while((contImprimir < MAX_IMPRIMIR) && (tipoCorrecto)); 
            
            // FIN BLOQUE DE IMPRESIÓN //
            
                      
            //Mostramos los registros entrados
            System.out.println("\nSe han incrito: " + contRegistros + 
                               " participante/s nuev@/s");
            
        }else //Si no se finaliza ningún registro, mostramos el mensaje
        {
           System.out.println("\nNo se ha registrado ningún participante."); 
        } 
        
        // FIN DE LA EJECUCIÓN //
        
    }

}
