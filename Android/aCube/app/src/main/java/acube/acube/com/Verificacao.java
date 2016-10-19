package acube.acube.com;

public class Verificacao
{
    float celsius;
    float lux;
    float humidity;
    float decibels;
    int score =7;

  Verificacao(String[] args)
  {
      this.celsius = Float.valueOf(args[1]);
      this.lux = Float.valueOf(args[3]);
      this.humidity = Float.valueOf(args[5]);
      this.decibels = Float.valueOf(args[7]);
  }
    public String Status()
    {
        this.score=7;
        if((decibels>50)&&(decibels<60))
        {
            score-=1;
        }
        if(decibels>60)
        {
            score-=2;
        }
        if(celsius>33)
        {
            score-=1;
        }
        if(celsius<19)
        {
            score-=1;
        }
        if(lux>lux_ideal(1,40,1)+100)
        {
            score-=1;
        }
        if(lux<lux_ideal(1,40,1)-100)
        {
            score-=1;
        }
        if(lux<lux_ideal(1,40,1)-1000)
        {
            score-=5;
        }
        if(lux>lux_ideal(1,40,1)+1000)
        {
            score-=5;
        }
        if(humidity<12)
        {
            score-=3;
        }
        if((humidity>12)&&(humidity<22))
        {
            score-=-2;
        }
        if((humidity>22)&&(humidity<30))
        {
            score-=-1;
        }
        switch (score)
        {
            case 0:
                return "Morte certa";
            case 1:
                return "Péssimo";
            case 2:
                return "Muito ruim";
            case 3:
                return "Ruim";
            case 4:
                return "Regular";
            case 5:
                return "Bom";
            case 6:
                return "Muito Bom";
            case 7:
                return "Excelente";
        }
        return "";
    }

    public static int lux_ideal(int amb,int idade,int importancia){
        int [][] a={
           /*0*/{100,150,200},
           /*1*/{75,100,150},
           /*2*/{300,500,750},
           /*3*/{150,200,300},
           /*4*/{200,300,500},
           /*5*/{100,150,200},
           /*6*/{150,200,300},
           /*7*/{200,300,500},
           /*8*/{300,500,700},
           /*9*/{300,500,700},
           /*10*/{100,150,200},
           /*11*/{150,200,300},
           /*12*/{750,1000,1500},
           /*13*/{30,50,75}};
        int ideal,aux=0;

        if(idade<40){
            aux-=1;
        }
        else{
            if(idade>55){
                aux+=1;
            }
        }
        if(importancia==0){
            aux-=1;
        }
        else{
            if(importancia==2){
                aux+=1;
            }
        }
        if(aux <= -2){
            aux=0;
        }
        else{
            if(aux>=2){
                aux=2;
            }
            else{
                aux=1;
            }
        }
        return a[amb][aux];
    }




}