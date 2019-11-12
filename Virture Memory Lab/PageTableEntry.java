public class PageTableEntry {
    private boolean inMemory;
    private int frame;

    public PageTableEntry() {
        inMemory = false;
        frame = -1;
    }

    public int getFrame() {
        return frame;
    }

    public boolean isInMemory() {
        return inMemory;
    }

    public void swapToDisk() {
        inMemory = false;
    }

    public void swapToMemory() {
        inMemory = true;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof PageTableEntry)) {
            return false;
        }
        return ((PageTableEntry) obj).frame == this.frame;
    }
}
