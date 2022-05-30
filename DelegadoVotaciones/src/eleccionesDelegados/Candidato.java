package eleccionesDelegados;

public class Candidato extends Alumno{

    private int votos;

    //Constructor
    public Candidato(String name) {
        super(name);
        this.votos = 0;
    }

    public void setValidVote() {
        this.votos++;
    }

    public int getVotes(){
        return this.votos;
    }

    @Override
    public String toString() {

        return "'" + "\033[0;32m" + this.getName() + "\033[0m" + '\'' + "\t";
    }

}
