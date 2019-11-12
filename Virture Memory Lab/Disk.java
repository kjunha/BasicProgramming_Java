import java.util.ArrayList;

public class Disk {
    private ArrayList<PageTableEntry> frames;

    public Disk(){
        frames = new ArrayList<>();
    }

    public void addFrame(PageTableEntry pte) {
        frames.add(pte);
    }

    public void removeFrame(PageTableEntry pte) {
        frames.remove(pte);
    }

    public boolean isInDisk(PageTableEntry pte){

        return (frames.indexOf(pte) == -1) ? false : true;
    }
}
