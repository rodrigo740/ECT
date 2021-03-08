
void torresDeHanoi(char origem, char auxiliar, char destino, int n){

    if(n == 1){


        contadorGlobalMovs++;
        moverDisco(origem, destino);    //Imprime o movimento

        return;
    }

    //Divide-and-Conquer(2 chamadas recursivas, 2 problemas do mesmo tipo menores)
    torresDeHanoi(origem,destino,auxiliar,n-1);

    contadorGlobalMovs++;
    moverDisco(destino,destino);
    torresDeHanoi(auxiliar,origem,destino,n-1);
}