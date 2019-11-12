import java.util.ArrayList;

public class PageTable {
    private PageTableEntry[] entries;

    public PageTable(){
        entries = new PageTableEntry[64];
    }

    public void setPageTableEntry(int pt, PageTableEntry pte) {
        entries[pt] = pte;
    }

    public PageTableEntry getPageTableEntry(int page) {
        return entries[page];
    }

    public int[] requestMoveDisk(){
        ArrayList<Integer> inDisk = new ArrayList<>();
        for(int i = 0; i < entries.length; i++) {
            if(entries[i] != null &&!(entries[i].isInMemory())) {
                inDisk.add(i);
            }
        }
        int[] ls = new int[inDisk.size()];
        for(int i = 0; i < inDisk.size(); i++) {
            ls[i] = inDisk.get(i).intValue();
        }
        return ls;
    }
}
