import java.util.ArrayList;

public class ProcessTable {
    PageTable[] procPageTable;

    public ProcessTable() {
        procPageTable = new PageTable[8];

    }
    public void setProcPageTable(int pid, PageTable pt) {
        procPageTable[pid] = pt;
    }

    public PageTable getProcPageTable(int pid) {
        return procPageTable[pid];
    }
}
