import java.util.List;

public class Main {
    public static void main(String[] args) {
        IElevadorService elevadorService = new ElevadorService();
        System.out.println(elevadorService.periodoMaiorUtilizacaoConjuntoElevadores());
    }

}