package acube.acube.com;

/**
 * Created by aluno on 14/04/2016.
 */
public class SaveandLoad {
    private static String nome;
    private static int idade;
    private static String all;
    private static String ambiente;
    public static int horas;

    SaveandLoad(String nome, int idade,String all,String ambiente,int horas)
    {
        this.nome=nome;
        this.idade=idade;
        this.all=all;
        this.ambiente=ambiente;
        this.horas=horas;
    }

    public String toString()
    {
        return ("Nome: "+nome+"\nIdade: "+idade+"\nAmbiente: "+ambiente+"\nHoras: "+horas);
    }



}
