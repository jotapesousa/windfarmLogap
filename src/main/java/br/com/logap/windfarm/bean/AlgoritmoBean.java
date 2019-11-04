package br.com.logap.windfarm.bean;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Named
@SessionScoped
public class AlgoritmoBean implements Serializable {

    private int num1;
    private int num2;
    private List<Integer> numeros;


    public AlgoritmoBean() {
        numeros = new ArrayList<>();
    }

    public String salvar() {
        numeros.add(num1);
        num1 = 0;
        return "/algoritmo?faces-redirect=true";
    }

    public void procurar() {
        if (numeros.size() < 2) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "A tabela não tem números suficientes para realizar a busca. Insira mais dados.", ""));
        } else {
            Collections.sort(numeros);

            int numAchado1 = -1;
            int numAchado2 = 0;
            for (int n : numeros) {
                int numBusca = num2 - n;
                numAchado1 = buscaBinaria(numeros, numBusca);
                if (numAchado1 != -1) {
                    numAchado2 = n;
                    break;
                }
            }

            if (numAchado1 == -1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Não existem dois números que somados dão igual a " + num2, ""));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("[ "+ numeros.get(numAchado1) + " + " + numAchado2 + " = "+ num2 +" ]"
                            + ". Num. Procurado: " + num2 + ". Valores Encontrados: " + numeros.get(numAchado1) + " e " + numAchado2));
            }
            num1 = 0;
            num2 = 0;
        }
    }

    private int buscaBinaria(List<Integer> nums, int x) {
        int pos = -1, posInicial = 0, posFinal = nums.size()-1;

        while(posInicial <= posFinal) {
            pos = (posFinal  + posInicial) / 2;
            if(nums.get(pos) == x) return pos;
            else if(nums.get(pos) > x) posFinal = pos - 1;
            else posInicial = pos + 1;
        };
        return -1;
    }


    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public List<Integer> getNumeros() {
        return numeros;
    }
}
