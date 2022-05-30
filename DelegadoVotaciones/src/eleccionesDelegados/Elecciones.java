package eleccionesDelegados;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Elecciones {

    public static int numVotantes;

    private static String okCandidate;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Votacion votacion = new Votacion();
        int numCandidates = 2;
        int validCandidate, isNotRepeated = -1;
        String voteAlumno;

        Set<Alumno> votantes = new HashSet<>(List.of(
                new Alumno("Lluís")
                , new Alumno("María")
                , new Alumno("Jordi")
                , new Alumno("Oriol")
                , new Alumno("Marc")
                , new Alumno("James")
                , new Alumno("Anna")
                , new Alumno("Rajoy")
        ));

        numVotantes = votantes.size();

        /* En caso de ser un número deseable
        do {
            System.out.print("Número de candidatos: ");
            numCandidates = scanner.nextInt();

        } while (numCandidates < 0
                || numCandidates > numVotantes);

        scanner.nextLine();
        */

        System.out.println("\n╔═╗┬  ┌─┐┌─┐┌─┐┬┌─┐┌┐┌┌─┐┌─┐\n" +
                "║╣ │  ├┤ │  │  ││ ││││├┤ └─┐\n" +
                "╚═╝┴─┘└─┘└─┘└─┘┴└─┘┘└┘└─┘└─┘ PARA\n" +
                "┌┬┐┌─┐┬  ┌─┐┌─┐┌─┐┌┬┐╔═╗    \n" +
                " ││├┤ │  ├┤ │ ┬├─┤ ││║ ║    \n" +
                "─┴┘└─┘┴─┘└─┘└─┘┴ ┴─┴┘╚═╝   \n" +
                "───────────────────────────────────────────────────────────────");
        votantes.forEach(s -> System.out.print("\033[0;33m"+ s.getName() + "\t" + "\033[0m"));
        System.out.print( " <── Delegables\n───────────────────────────────────────────────────────────────\n");

        for (int i = 0; i < numCandidates; i++) {

            do {

                System.out.print("Nombre del candidato " + (i + 1) + ": ");
                validCandidate = isInClassroomList(votantes, votacion.camelCase(scanner.nextLine()));

                if (validCandidate == -1) {
                    System.out.println("Introducir un nombre correcto de candidato");
                    votantes.forEach(s -> System.out.print(s.getName() + "\t"));
                    System.out.println();

                } else {
                    Candidato aceptado = new Candidato(okCandidate);
                    isNotRepeated = votacion.checkIfInList(aceptado);
                }

            } while (validCandidate == -1 || isNotRepeated == -1);

        }

        System.out.print("\nLos candidatos válidos que se presentan son: ");
        votacion.getCandidatoArrayList().forEach(System.out::print);
        System.out.println();


        // Election's day
        for (Alumno x : votantes) {
            System.out.print("Emite su voto " + x.getName() + ": ");
            votacion.countingVotes(new Candidato(scanner.nextLine()));
        }

        System.out.println(votacion);
        scanner.close();


    } // End main


    private static int isInClassroomList(Set<Alumno> votantes, String candidate) {

        Alumno alumno = votantes.stream()
                .filter(student -> candidate.equalsIgnoreCase(student.getName()))
                .findAny()
                .orElse(null);

        if (alumno != null) {

            okCandidate = candidate;
            return 1;

        } else {

            return -1;
        }
    }


}// end Elecciones class
