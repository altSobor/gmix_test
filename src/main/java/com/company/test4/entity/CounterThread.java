package com.company.test4.entity;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CounterThread implements Runnable {
    int strSum;
    public boolean isActive;
    ArrayList<int[][]> magQuard = new ArrayList<int[][]>();
    public CounterThread(int strSum){
        isActive = true;
        this.strSum=strSum;
    }
    public ArrayList<int[][]> countMagicQuard(){
        for(int i = 123456789; i <= 987654321; i++){
            ArrayList<Integer> intlist = new ArrayList<Integer>();
            intlist.add(i/100000000);
            intlist.add((i/10000000)%10);
            intlist.add((i/1000000)%10);
            intlist.add((i/100000)%10);
            intlist.add((i/10000)%10);
            intlist.add((i/1000)%10);
            intlist.add((i/100)%10);
            intlist.add((i/10)%10);
            intlist.add(i%10);
            if(intlist.contains(1)&&intlist.contains(2)&&intlist.contains(3)&&intlist.contains(4)&&intlist.contains(5)
                    &&intlist.contains(6)&&intlist.contains(7)&&intlist.contains(8)&&intlist.contains(9)&&
                    (intlist.get(0)+intlist.get(1)+intlist.get(2))==strSum&&
                    (intlist.get(3)+intlist.get(4)+intlist.get(5))==strSum&&
                    (intlist.get(6)+intlist.get(7)+intlist.get(8))==strSum&&
                    (intlist.get(0)+intlist.get(3)+intlist.get(6))==strSum&&
                    (intlist.get(1)+intlist.get(4)+intlist.get(7))==strSum&&
                    (intlist.get(2)+intlist.get(5)+intlist.get(8))==strSum){
                    int[][] mQ = new int[3][3];
                    mQ[0][0] = intlist.get(0);
                    mQ[0][1] = intlist.get(1);
                    mQ[0][2] = intlist.get(2);
                    mQ[1][0] = intlist.get(3);
                    mQ[1][1] = intlist.get(4);
                    mQ[1][2] = intlist.get(5);
                    mQ[2][0] = intlist.get(6);
                    mQ[2][1] = intlist.get(7);
                    mQ[2][2] = intlist.get(8);
                    magQuard.add(mQ);
            }
        }
        return magQuard;
    }

    @Override
    public void run() {
        File file = new File("magNums.txt");
        if(file.exists()){
            magQuard = importData();
        }
        else{
            countMagicQuard();
            saveData(magQuard);
        }
        isActive = false;
    }

    public ArrayList<int[][]> getMagQuard() {
        return magQuard;
    }

    public void saveData(ArrayList<int[][]> arr){
        try(FileWriter writer = new FileWriter("magNums.txt", false))
        {
            for(int i = 0; i < arr.size(); i++){
                for(int j = 0; j < 3; j++){
                    for(int k = 0; k < 3; k++){
                        writer.write(arr.get(i)[j][k]);
                    }
                }
                writer.append('\n');
            }
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    public ArrayList<int[][]> importData(){
        //magQuard
        try(FileReader reader = new FileReader("magNums.txt"))
        {
            int c;
            while((c=reader.read())!=-1){
                int[][] mQ = new int[3][3];
                for(int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        mQ[j][k]=c;
                        c=reader.read();
                    }
                }
                magQuard.add(mQ);
            }
        }
        catch(IOException ex){
            return magQuard;
        }

        return magQuard;
    }
}
