public class Memory {
    private PageTableEntry[] frames;
    private int pointer;

    public Memory(){
        frames = new PageTableEntry[30];
        pointer = -1;
    }

    public void setFrames(PageTableEntry pte) {
        pte.swapToMemory();
        frames[getFreePage()] = pte;
    }

    public PageTableEntry getFrames(int frame) {
        return frames[frame];
    }

    private int getFreePage(){
        pointer++;
        pointer = (pointer >= 30) ? pointer - 30 : pointer;
        if(!(frames[pointer] == null)) {
            frames[pointer].swapToDisk();
        }
        return pointer;
    }

    public int findSwapPage(){
        return 0;
    }

    public int convert(int frame){
        return 0;
    }
}
