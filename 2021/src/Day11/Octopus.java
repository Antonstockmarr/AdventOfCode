package Day11;

import java.util.List;

public class Octopus {
    public int energy;
    private Flasher flasher;
    private List<Octopus> neighbours;

    public Octopus(int energy, Flasher flasher) {
        this.energy = energy;
        this.flasher = flasher;
    }

    public void setNeighbours(List<Octopus> neighbours) {
        this.neighbours = neighbours;
    }

    public void increaseEnergy() {
        if (energy > 0) {
            energy++;
        }
        if (energy > 9) {
            flash();
            neighbours.forEach(Octopus::increaseEnergy);
        }
    }

    public void flash() {
        energy = 0;
        flasher.flash();
    }
}
