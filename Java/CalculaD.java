/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Laplace;

/**
 *
 * @author diano
 */
public class CalculaD {

    public int calcularDeterminante(int[][] Matriz)
    {
        if (Matriz.length == 2) // Se matriz 2 por 2, faz sarrus
        {
            return ((Matriz[0][0] * Matriz[1][1]) - (Matriz[0][1] * Matriz[1][0]));
        }
        else
        {
            int Resultado = 0;
            for (int LinhaSelecionada = 0; LinhaSelecionada < Matriz.length; LinhaSelecionada++) //Laplace, linha selecionada 1
            {
				// Monto a submatriz para calcular laplace
                int tamanhoMatriz = Matriz.length - 1;
                int[][] NovaMatriz = new int[tamanhoMatriz][tamanhoMatriz];

                int LinhaNovaMatriz = 0;
                int ColunaNovaMatriz = 0;
				// Monto os elementos da nova matriz percorrendo a Matriz Atual
                for (int LinhaMatriz = 1; LinhaMatriz < Matriz.length; LinhaMatriz++) 
                {
                    for (int ColunaMatriz = 0; ColunaMatriz < Matriz.length; ColunaMatriz++)
                    {
                        if (ColunaMatriz != LinhaSelecionada)
                        {
                            NovaMatriz[LinhaNovaMatriz][ColunaNovaMatriz] = Matriz[LinhaMatriz][ColunaMatriz];
                            ColunaNovaMatriz++;
                        }
                    }
                    LinhaNovaMatriz++;
                    ColunaNovaMatriz = 0;
                }
				// Calculo o determinante
                Resultado = Resultado + ((int) (Math.pow(-1, LinhaSelecionada + 2)) * Matriz[0][LinhaSelecionada] * calcularDeterminante(NovaMatriz));
            }
            return Resultado;
        }
    }
}
