package com.scotiabankcolpatria.hiring;

/**
 * Una clase para representar c�rculos situados sobre el plano.
 * para evaluar el riesgo de incumplimiento de nuevos clientes;
 *  estos son la desviaci�n est�ndar de pagos atrasados, 
 *  agos tard�os inusuales y probabilidad de pagos tard�os
 * @version 1, 08/03/2022
 * @author Yeferson Ladino
 */

public class CreditRiskAssessment {

    /** 
        Calcula la desviaci�n estandar poblacional.
        *@param paymentDelays tama�o de la muestra.
        *@return  el total de la desviacion estandar poblacional
    **/
    public double standardDeviation(int[] paymentDelays) {

        double sumad = 0.0; // Sumatoria de los d�as del arreglo
        double desviacionEstandar = 0.0; //Variable de desviaci�n estandar
        double total; // Variable para calcular la desviaci�n estandar poblacional
        double media; // Variable para calcular la media

        /*
            Recorriendo el arreglo para calcular la sumatoria total de los dias.
        */
        for (int i = 0; i < paymentDelays.length; i++) {
            sumad += paymentDelays[i];
        }
        
        /* 
            Calculando la media de la sumatoria total del arreglo dividido por
            el tama�o de la muestra del arreglo.
        */
        media = sumad / paymentDelays.length;
        
        /*
            Recorriendo el arreglo y calculado la desviacion estandaar con la
            sumatoria de los elementos del arreglo menos la media calculad y 
            todo elevado al cuadrado.
        */
        for (int i = 0; i < paymentDelays.length; i++) {
            desviacionEstandar += Math.pow(paymentDelays[i] - media, 2);
        }
        
        /* 
            Calculando la desviaci�n estandar poblacional sacando la raiz  de
            la desviaci�n estandar dividido por el tama�o de la muestra del 
            arreglo
        */
        total = Math.sqrt(desviacionEstandar / paymentDelays.length);

        //Se retorna el total de la desviaci�n estandar poblacional.
        return total;
    }
    
    
    /** 
        Calcula la anomalia entre periodos de pagoas  .
        *@param paymentDelays contiene los periodos de pagos.
        *@return  el indice de la anomalia encontrada.
    **/
    public int paymentDelayMaxPeakIndex(int[] paymentDelays) {

        int anomalia = 0; // inicializar el indce de la anomalia
        int indiceInicial = paymentDelays[0]; // valor inicial de los periodos de pago
        int indiceFinal = paymentDelays[paymentDelays.length - 1]; //valor final de los periodos de pago
        

        /* 
            Se valida si el primier periodo es anomalo si  el valor del primer 
            periodo de pago es mayor con el siguiente perido y si el primer 
            perido es mayor con el ultimo periido. Si no es el primer perido de 
            pago se valida si el ultimo periodo de pago es anomalo si el valor 
            del perido del pago final es mayor al perido de pago antarior al 
            periodo de pago final y si el perido de pago final es mayor al 
            inicial. Si el perido inicial y final no son anomalos se recorre el
            arreglo para encontrar el indice anomalo.
        */
        
        if (indiceInicial > paymentDelays[1] && indiceInicial > paymentDelays[paymentDelays.length - 1]) {
            anomalia = 0; //capturando el indice anomalo
            /*
                Se valida si hay un valor mayor en el perido al indice inicial
                y as� capturar el valor anomalo del arreglo
            */
            
            if (max(paymentDelays) > indiceInicial) {
                anomalia = recorrerNumeros(paymentDelays); // Se captura el indice anomalo del arreglo
            }
        } else if (indiceFinal > paymentDelays[paymentDelays.length - 2] && indiceFinal >= indiceInicial) {
            anomalia = paymentDelays.length - 1; // se captura el indice final anomalo del arreglo

        } else {
            anomalia = recorrerNumeros(paymentDelays); // se captura el indice anomalo del arreglo
        }

        //Inidice del periodo anomalo
        return anomalia;
    }

    /** 
        Calcula la probabilidad de pago tardio de los periodos de un producto .
        *@param paymentDelays contiene los periodos de pagos de un producto.
        *@return un arreglo con los pagos tardios de un producto.
    **/
    
    public double[] latePaymentProbabilityByPeriod(int[][] paymentDelays) {
        
        //Arreglo de los pagos tradios de un producto, con el tama�o de las columnas del arreglo
        double[] resultado = new double[paymentDelays[0].length];
        
        /*
            Se recorre el tama�o del producto por columnas y filas para calcular
            el pago tardio de cada periodo.
        */
         for (int i = 0; i < paymentDelays[0].length; i++) {
            int cont =0; //Se inicializa el conador para calcular el pago tardio
            double pagoTardio = 0; // Se inicializa el pago tardio del perido
            
            /*
                Se recorre los peridos  del producto para contar que tantos 
                pagos tardios se registraron.
            */
            for (int j = 0; j < paymentDelays.length; j++) {
                
                /*
                    Valida si el periodo tiene un pago tard�o mayor a 0 que se
                    acumule al contador
                */
                if(paymentDelays[j][i]>0){
                     cont +=1;
                }
            }
             // Calcula el pago tardio entre el contador recorrido por los peridos dividido en 4 que son los peridos
              pagoTardio = cont/4.0; 
              //Se agrega el resultado al arreglo de pagos tardios de cada perido.
              resultado[i]= pagoTardio;
        }
         
         //Se retorna el arreglo de pagos tardis de cada perido.
        return resultado;
    }

    /** 
        Calcula el n�mero maximo de un arreglo .
        *@param lista arreglo de la lista n�mero enteros.
        *@return indica cual es el n�mero mayuor del arreglo.
    **/
    public int max(int[] lista) {
        
        int numeroMayor = lista[0]; // capturar el primer n�mero del arreglo
        
        /*
            Se recorre el arreglo para calcular el n�mero mayor.
        */
        for (int i = 0; i < lista.length; i++) {
            int numero = lista[i]; // se inicaliza la variable con el numero del arreglo
            
            /*
                Se valida si el numero del arreglo es mayor al numero mayor, si
                es as� se indica que el n�mero mayor a hora es el n�mero.
            */
            if (numero > numeroMayor) {
                
                numeroMayor = numero; // se captura el n�mero mayor
            }
        }
        
        //Se retorna un entero con el n�mero mayor del arreglo
        return numeroMayor;

    }
    
    
    /** 
        Recorre un arreglo para caputar el indice de un periodo anomalo  .
        *@param lista arreglo de la lista n�mero enteros.
        *@return indica cual es el indice con la anomalia.
    **/
    public static int recorrerNumeros(int[] lista) {
        int anomalia = 0; // Se inicializa el indice de la anomalia
        
        /*
            Se rcorree la lista para capturar el indice con la anomalia
        */
        for (int i = 0; i < lista.length; i++) {
           
            /*
                Se valida si el perido que se evalua es diferente de 0  O es igual al ultimo
                perido de la lista se captura el indice anomalo
            */
            if (i != 0 || i == lista.length - 1) {
            
                /*
                    Se valida si el perido que se evalua de la lista es mayor al
                    anterior periodo y si el perido que se evaalua es mayor al 
                    periodo si guiente se caputra el indice anomalo
                */
                if (lista[i] > lista[i - 1]
                        && lista[i] > lista[i + 1]) {
                    anomalia = i; // Indice anomalo del perido

                }
            }

        }
        
        //Retonra el indice del perido anomalo
        return anomalia;
    }
}
