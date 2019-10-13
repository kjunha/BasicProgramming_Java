import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ArrayLab extends JPanel {
    int[][] map;

    public ArrayLab() {
        map = new int[200][200];
        for(int i = 0; i < 200; i++) {
            for(int j = 0; j < 200; j++) {
                map[i][j] = 0;
            }
        }
        String filename = "src/coordinates.txt";
        File file = new File(filename);
        try {
            Scanner s = new Scanner(file);
            while(s.hasNextLine()) {

                String line = s.nextLine();
                int x = Integer.parseInt(line.split(" ")[0]);
                int y = Integer.parseInt(line.split(" ")[1]);
                map[x][y] = 1;
                for(int i = -5; i <= 5; i++) {
                    map[x][y+i] = 1;
                    map[x+i][y] = 1;
                    map[x+i][y+i] = 1;
                    map[x+i][y-i] = 1;
                }
//                map[x-1][y-1] = 1;
//                map[x-2][y-2] = 1;
//                map[x-3][y-3] = 1;
//                map[x-1][y+1] = 1;
//                map[x-2][y+2] = 1;
//                map[x-3][y+3] = 1;
//                map[x+1][y-1] = 1;
//                map[x+2][y-2] = 1;
//                map[x+3][y-3] = 1;
//                map[x+1][y+1] = 1;
//                map[x+2][y+2] = 1;
//                map[x+3][y+3] = 1;

            }
        } catch (FileNotFoundException e) {
            System.out.println("File is not found.");
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        //Beginning Color RGB
        int red1 = 102;
        int blue1 = 135;
        int green1 = 35;
        for(int i = 0; i < 200; i++) {
            for(int j = 0; j < 200; j++) {
                if(map[i][j] == 1) {
                    g.setColor(Color.WHITE);
                    g.fillRect(i,j,1,1);
                    //g.setColor(new Color((int)(red1+(0.425)*i), (int)(green1 +(0.56)*i), (int)(blue1 + (0.36)*i)));
                } else {
                    //g.setColor(new Color((int)(red1+(0.43)*j), (int)(green1 +(0.43)*j), (int)(blue1 + (0.43)*j)));
                    g.setColor(new Color((int)(red1+(0.425)*j), (int)(green1 +(0.56)*j), (int)(blue1 + (0.36)*j)));
                    g.drawRect(i,j,1,1);
                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200,200);
        frame.setContentPane(new ArrayLab());
        frame.setVisible(true);
    }
}
