import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<String> inputsrc = new ArrayList<>();
    static ProcessTable processTable = new ProcessTable();
    static PageTable currentPageTable;
    static Memory memory = new Memory();
    static Disk disk = new Disk();

    static int miss = 0;
    static int access = 0;
    static int hit = 0;
    static int compulsory = 0;

    public static void parseCommand(String command){
        String[] parsed = command.split(" ");
        if(parsed[0].equals("new")){
            int pid = Integer.parseInt(parsed[1]) - 1;
            processTable.setProcPageTable(pid, new PageTable());
        } else if(parsed[0].equals("switch")){
            int pid = Integer.parseInt(parsed[1]) - 1;
            currentPageTable = processTable.getProcPageTable(pid);
        } else {
            int accessid = Integer.parseInt(parsed[1]);
            int pid = accessid >> 10;
            if(currentPageTable.getPageTableEntry(pid) == null) {
                miss++;
                compulsory++;
                access++;
                currentPageTable.setPageTableEntry(pid, new PageTableEntry());
                memory.setFrames(currentPageTable.getPageTableEntry(pid));
                // If kicked out of disk, and not in the disk, move it to the disk.
                int[] checklist = currentPageTable.requestMoveDisk();
                for(int i : checklist) {
                    if(!(disk.isInDisk(currentPageTable.getPageTableEntry(i)))) {
                        disk.addFrame(currentPageTable.getPageTableEntry(i));
                    }
                }
            } else {
                if(currentPageTable.getPageTableEntry(pid).isInMemory()) {
                    hit++;
                    access++;
                } else {
                    memory.setFrames(currentPageTable.getPageTableEntry(pid));
                    disk.removeFrame(currentPageTable.getPageTableEntry(pid));
                    miss++;
                    access++;
                }
            }
        }
    }

    public static void main(String[] args) {
        long runtime = System.currentTimeMillis();
        File f = new File("src/VMInput.txt");
        try {
            FileInputStream inputstr = new FileInputStream(f);
            Scanner sc = new Scanner(inputstr);
            while(sc.hasNextLine()) {
                inputsrc.add(sc.nextLine());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for(String s : inputsrc) {
            parseCommand(s);
        }
        System.out.printf("Access: %d\n", access);
        System.out.printf("Hit: %d\n", hit);
        System.out.printf("Miss: %d\n", miss);
        System.out.printf("Compulsory: %d", compulsory);
    }
}
