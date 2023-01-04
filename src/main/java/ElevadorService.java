import org.json.JSONArray;
import org.json.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.System.out;

public class ElevadorService implements IElevadorService {
     List responsesList = new ArrayList<HashMap>();
    static HashMap andarCountList = new HashMap<String, Integer>();
    static HashMap elevadorCountList = new HashMap<String, Integer>();;
    static HashMap turnoCountList = new HashMap();
    public List<Integer> andarMenosUtilizado() {
        // number of andares
        int n = 15;
        // List of the least recurrence;
        ArrayList<Integer> leastUsedList = new ArrayList<Integer>();
        int menosUtilizado = (int) andarCountList.get(0);
        for (int i = 1; i <= n; i++) {
            int currentAndar = (int) andarCountList.get(i);
            if (currentAndar < menosUtilizado){
                menosUtilizado = currentAndar;
                leastUsedList = new ArrayList<Integer>();
                leastUsedList.add(i);
            }
            else if (currentAndar == menosUtilizado){
                leastUsedList.add(i);
            }
        }
        return leastUsedList;
    }

    public List<Character> elevadorMaisFrequentado() {
        // List of the most recurrence;
        ArrayList<Character> mostUsedList = new ArrayList<Character>();
        int MostUsedValue = 0;
        Iterator<String> iterator = elevadorCountList.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            int currentValue = (int) elevadorCountList.get(key);
            System.out.println(key + ":" + elevadorCountList.get(key));
            if (currentValue > MostUsedValue){
                MostUsedValue = currentValue;
                mostUsedList = new ArrayList<Character>();
                mostUsedList.add(key.toString().charAt(0));
            }
            else if (MostUsedValue == currentValue ){
                mostUsedList.add(key.toString().charAt(0));
            }
        }
        return mostUsedList;
    }

    public List<Character> periodoMaiorFluxoElevadorMaisFrequentado() {
        List<Character> listDeElevadores = this.elevadorMaisFrequentado();
        List<Character> listaDePeriodos = new ArrayList<Character>();
        for(int i = 0; i < responsesList.size(); i ++){
            HashMap response = (HashMap) responsesList.get(i);
            Character elevador = response.get("elevador").toString().charAt(0);
            if (listDeElevadores.contains(elevador)){
                Character turno = response.get("turno").toString().charAt(0);
                if (!listaDePeriodos.contains(turno)){
                    listaDePeriodos.add(turno);
                }
            };
        }
        return listaDePeriodos;
    }

    public List<Character> elevadorMenosFrequentado() {
        // List of the most recurrence;
        out.println("elevadorMenosFrequentado");
        ArrayList<Character> mostUsedList = new ArrayList<Character>();
        Iterator<String> iterator = elevadorCountList.keySet().iterator();
        int MostUsedValue = (int) elevadorCountList.get(iterator.next());
        while (iterator.hasNext()) {
            String key = iterator.next();
            int currentValue = (int) elevadorCountList.get(key);
            System.out.println(key + ":" + elevadorCountList.get(key));
            if (currentValue < MostUsedValue){
                MostUsedValue = currentValue;
                mostUsedList = new ArrayList<Character>();
                mostUsedList.add(key.toString().charAt(0));
            }
            else if (MostUsedValue == currentValue ){
                mostUsedList.add(key.toString().charAt(0));
            }
        }
        return mostUsedList;
    }

    public List<Character> periodoMenorFluxoElevadorMenosFrequentado() {
        List<Character> listDeElevadores = this.elevadorMenosFrequentado();
        List<Character> listaDePeriodos = new ArrayList<Character>();
        out.println(this.elevadorMenosFrequentado());
        for(int i = 0; i < responsesList.size(); i ++){
            HashMap response = (HashMap) responsesList.get(i);
            Character elevador = response.get("elevador").toString().charAt(0);
            out.println(elevador);
            if (listDeElevadores.contains(elevador)){
                Character turno = response.get("turno").toString().charAt(0);
                if (!listaDePeriodos.contains(turno)){
                    listaDePeriodos.add(turno);
                }
            };
        }
        return listaDePeriodos;
    }

    public List<Character> periodoMaiorUtilizacaoConjuntoElevadores() {
        // List of the most recurrence;
        ArrayList<Character> mostUsedList = new ArrayList<Character>();
        int MostUsedValue = 0;
        Iterator<String> iterator = turnoCountList.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            int currentValue = (int) turnoCountList.get(key);
            System.out.println(key + ":" + turnoCountList.get(key));
            if (currentValue > MostUsedValue){
                MostUsedValue = currentValue;
                mostUsedList = new ArrayList<Character>();
                mostUsedList.add(key.toString().charAt(0));
            }
            else if (MostUsedValue == currentValue ){
                mostUsedList.add(key.toString().charAt(0));
            }
        }
        return mostUsedList;
    }

    public float percentualDeUsoElevadorA() {
        float value = (int) elevadorCountList.get("A");
        float response = value / responsesList.size();
        return response;

    }

    public float percentualDeUsoElevadorB() {
        float value = (int) elevadorCountList.get("B");
        float response = value / responsesList.size();
        return response;
    }

    public float percentualDeUsoElevadorC() {
        float value = (int) elevadorCountList.get("C");
        float response = value / responsesList.size();
        return response;
    }

    public float percentualDeUsoElevadorD() {
        float value = (int) elevadorCountList.get("D");
        float response = value / responsesList.size();
        return response;
    }

    public float percentualDeUsoElevadorE() {
        float value = (int) elevadorCountList.get("A");
        float response = value / responsesList.size();
        return response;
    }
    private static void setInitialAndarCount() {
        int n = 10;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            andarCountList.put(i, 0);
        }
    }
    private static void setAndarCount(Integer andar) {
        int pastCount = 0;
        if (andarCountList.containsKey(andar)) {
            pastCount = (int) andarCountList.get(andar);
        }

        andarCountList.put(andar, pastCount + 1);
    }
    private static void setListCount(String value, HashMap countList) {
        int pastCount = 0;
        if (countList.containsKey(value)) {
            pastCount = (int) countList.get(value);
        }

        countList.put(value, pastCount + 1);
    }
    public ElevadorService() {
        setInitialAndarCount();

        try {
            // read data as string
            String data = new String(Files.readAllBytes(Paths.get("./input.json")));

            //read as json array
            JSONArray jsonArray = new JSONArray(data);
            for (int i = 0; i < jsonArray.length(); i++) {
                // parse in json object
                JSONObject jsonObjectRow = jsonArray.getJSONObject(i);
                // a. O elevador que utiliza com mais frequência (A, B, C, D ou E);
                String elevador = (String) jsonObjectRow.get("elevador");
                // b. O andar ao qual se dirigia (0 a 15);
                Integer andar = (Integer) jsonObjectRow.get("andar");
                // c. O período que utilizava o elevador – M: Matutino; V: Vespertino; N: Noturno.
                String turno = (String) jsonObjectRow.get("turno");

                // Create list of andar count
                setAndarCount(andar);
                // Create list of elevadoro count
                setListCount(elevador, elevadorCountList);;
                // Create list of turno count
                setListCount(turno, turnoCountList);;
                HashMap response = new HashMap();
                response.put("elevador", elevador);
                response.put("andar", andar);
                response.put("turno", turno);
                // ADD RAW RESPONSE LIST
                responsesList.add(response);
            }
        } catch (Exception e) {
            out.println("Error: "+e);
        }
    }
}
