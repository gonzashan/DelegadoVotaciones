    package eleccionesDelegados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Votacion {

    private List<Candidato> candidatoArrayList = new ArrayList<>();
    private int votoEnBlanco = 0;
    private int votoNulo = 0;

    //Setters
    public void setVotoEnBlanco() {
        this.votoEnBlanco++;
    }

    public void setVotoNulo() {
        this.votoNulo++;
    }

    //Getters
    public int getVotoEnBlanco() {
        return votoEnBlanco;
    }

    public int getVotoNulo() {
        return votoNulo;
    }

    public List<Candidato> getCandidatoArrayList() {
        return candidatoArrayList;
    }

    public void countingVotes(Candidato vote) {

        if (candidatoArrayList.contains(vote)) {
            candidatoArrayList.get(candidatoArrayList.indexOf(vote)).setValidVote();

        } else if (vote.getName().isEmpty()) {

            this.setVotoEnBlanco();

        } else {

            this.setVotoNulo();
        }
    }

    public void setCandidatos(Candidato candidato) {
        candidatoArrayList.add(candidato);
    }

    public int checkIfInList(Candidato preCandidate) {

        int i = 0;

        if (candidatoArrayList.size() > 0) {


            while (i < candidatoArrayList.size()) {

                if (candidatoArrayList.get(i).getName().equalsIgnoreCase(preCandidate.getName())) {
                    System.out.println("¡Este alumno ya es candidato!. Elija otro.");
                    return -1;
                }
                i++;
            }

            System.out.println("Nuevo candidato añadido: " + preCandidate.getName());
            setCandidatos(preCandidate);
            return 1;

        } else {

            System.out.println("Nuevo candidato añadido: " + preCandidate.getName());
            setCandidatos(preCandidate);
        }

        return 1;
    }


    public String getElectionResults() {

        // Getting results for voting
        Candidato mayor = Collections.max(candidatoArrayList, Comparator.comparing(Candidato::getVotes));
        // To know if the voting was a tie
        boolean result = candidatoArrayList.stream().map(Candidato::getVotes).distinct().count() == 1;

        if (mayor.getVotes() == 0) {

            return "Estas elecciones se tienen que repetir. Y mejor escoger otros candidatos, ¿no?.";

        } else if (result) {

            return "Ha habido un EMPATE en la votación. Por eso el candidato ganador será quién primero " +
                    "se inscribió, y ese fue, " + mayor.getName() + ".";
        } else {

            return "El candidato ganador y nuevo delegado es, " + mayor.getName() + ". ¡Enhorabuena!";
        }
    }

    @Override
    public String toString() {

        return String.format(
                "\n\n Resultado de las elecciones a delegado de clase 2022\n".toUpperCase() +
                        "─────────────────────────────────────────────────────\n" +
                        candidatesVotes() +
                        "%-20s: %s\n%-20s: %s\n%s\n%-20s: %s\n\n%s",
                "Votos en blanco", getVotoEnBlanco(),
                "Votos nulos", getVotoNulo(),
                "─────────────────────────────────────────────────────",
                "Total votos emitidos",
                Elecciones.numVotantes,
                getElectionResults()
        );
    }

    public String candidatesVotes() {
        String formato = "";
        for (Candidato c : getCandidatoArrayList()) {
            formato = formato.concat(String.format(
                    "%-20s: %s\n", c.getName(),
                    c.getVotes()));
        }
        return formato;
    }

    public String camelCase(String text) {

        StringBuilder builder = new StringBuilder(text);
        // Flag to keep track if last visited character is a
        // white space or not
        boolean isLastSpace = true;

        // Iterate String from beginning to end.
        for (int i = 0; i < builder.length(); i++) {
            char ch = builder.charAt(i);

            if (isLastSpace && ch >= 'a' && ch <= 'z') {
                // Character need to be converted to uppercase
                builder.setCharAt(i, (char) (ch + ('A' - 'a')));
                isLastSpace = false;
            } else if (ch != ' ')
                isLastSpace = false;
            else
                isLastSpace = true;
        }

        return builder.toString();
    }
}// end class Votacion
