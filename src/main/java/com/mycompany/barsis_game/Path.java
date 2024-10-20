/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barsis_game;

/**
 *
 * @author Csmooo
 */
public class Path {
    public Position[] path;

    public Path(String playerType) {
        
        path = new Position[75];
        for(int i=0; i<75; i++){
            path[i] = new Position();
        }

        if("H".equals(playerType)){
            setPath2();
        }
        else{
            setPath1();
        }
    }

    public void initialize(){
        for(int k=0; k<74; k++){
            path[k] = new Position();
        }
    }

    public Position getPosition(int index){
        if(index <= 73){
        return path[index];
        }
        else{
            return path[73];
        }
    }

    public void setPath1() {
        path[0].i = 6; path[0].j = 8; 
        path[1].i = 5; path[1].j = 8; 
        path[2].i = 4; path[2].j = 8; 
        path[3].i = 3; path[3].j = 8; 
        path[4].i = 2; path[4].j = 8; 
        path[5].i = 1; path[5].j = 8;
        path[6].i = 0; path[6].j = 8;
        path[7].i = 0; path[7].j = 7;
        path[8].i = 1; path[8].j = 7;
        path[9].i = 2; path[9].j = 7;
        path[10].i = 3; path[10].j = 7; 
        path[11].i = 4; path[11].j = 7; 
        path[12].i = 5; path[12].j = 7; 
        path[13].i = 6; path[13].j = 7; 
        path[14].i = 7; path[14].j = 6; 
        path[15].i = 7; path[15].j = 5;
        path[16].i = 7; path[16].j = 4;
        path[17].i = 7; path[17].j = 3;
        path[18].i = 7; path[18].j = 2;
        path[19].i = 7; path[19].j = 1; 
        path[20].i = 7; path[20].j = 0; 
        path[21].i = 8; path[21].j = 0; 
        path[22].i = 9; path[22].j = 0; 
        path[23].i = 9; path[23].j = 1; 
        path[24].i = 9; path[24].j = 2; 
        path[25].i = 9; path[25].j = 3;
        path[26].i = 9; path[26].j = 4;
        path[27].i = 9; path[27].j = 5;
        path[28].i = 9; path[28].j = 6;
        path[29].i = 10; path[29].j = 7; 
        path[30].i = 11; path[30].j = 7; 
        path[31].i = 12; path[31].j = 7; 
        path[32].i = 13; path[32].j = 7; 
        path[33].i = 14; path[33].j = 7; 
        path[34].i = 15; path[34].j = 7; 
        path[35].i = 16; path[35].j = 7;
        path[36].i = 16; path[36].j = 8;
        path[37].i = 16; path[37].j = 9;
        path[38].i = 15; path[38].j = 9;
        path[39].i = 14; path[39].j = 9; 
        path[40].i = 13; path[40].j = 9; 
        path[41].i = 12; path[41].j = 9; 
        path[42].i = 11; path[42].j = 9; 
        path[43].i = 10; path[43].j = 9; 
        path[44].i = 9; path[44].j = 10; 
        path[45].i = 9; path[45].j = 11;
        path[46].i = 9; path[46].j = 12;
        path[47].i = 9; path[47].j = 13;
        path[48].i = 9; path[48].j = 14;
        path[49].i = 9; path[49].j = 15; 
        path[50].i = 9; path[50].j = 16; 
        path[51].i = 8; path[51].j = 16; 
        path[52].i = 7; path[52].j = 16; 
        path[53].i = 7; path[53].j = 15; 
        path[54].i = 7; path[54].j = 14; 
        path[55].i = 7; path[55].j = 13;
        path[56].i = 7; path[56].j = 12;
        path[57].i = 7; path[57].j = 11;
        path[58].i = 7; path[58].j = 10;
        path[59].i = 6; path[59].j = 9; 
        path[60].i = 5; path[60].j = 9; 
        path[61].i = 4; path[61].j = 9; 
        path[62].i = 3; path[62].j = 9; 
        path[63].i = 2; path[63].j = 9; 
        path[64].i = 1; path[64].j = 9; 
        path[65].i = 0; path[65].j = 9;
        path[66].i = 0; path[66].j = 8;
        path[67].i = 1; path[67].j = 8;
        path[68].i = 2; path[68].j = 8;
        path[69].i = 3; path[69].j = 8; 
        path[70].i = 4; path[70].j = 8;
        path[71].i = 5; path[71].j = 8;
        path[72].i = 6; path[72].j = 8;
        path[73].i = 7; path[73].j = 8;
    }

    public void setPath2() {
        path[0].i = 10; path[0].j = 8; 
        path[1].i = 11; path[1].j = 8; 
        path[2].i = 12; path[2].j = 8; 
        path[3].i = 13; path[3].j = 8; 
        path[4].i = 14; path[4].j = 8; 
        path[5].i = 15; path[5].j = 8;
        path[6].i = 16; path[6].j = 8;
        path[7].i = 16; path[7].j = 9;
        path[8].i = 15; path[8].j = 9;
        path[9].i = 14; path[9].j = 9;
        path[10].i = 13; path[10].j = 9; 
        path[11].i = 12; path[11].j = 9; 
        path[12].i = 11; path[12].j = 9; 
        path[13].i = 10; path[13].j = 9; 
        path[14].i = 9; path[14].j = 10; 
        path[15].i = 9; path[15].j = 11;
        path[16].i = 9; path[16].j = 12;
        path[17].i = 9; path[17].j = 13;
        path[18].i = 9; path[18].j = 14;
        path[19].i = 9; path[19].j = 15; 
        path[20].i = 9; path[20].j = 16; 
        path[21].i = 8; path[21].j = 16; 
        path[22].i = 7; path[22].j = 16; 
        path[23].i = 7; path[23].j = 15; 
        path[24].i = 7; path[24].j = 14; 
        path[25].i = 7; path[25].j = 13;
        path[26].i = 7; path[26].j = 12;
        path[27].i = 7; path[27].j = 11;
        path[28].i = 7; path[28].j = 10;
        path[29].i = 6; path[29].j = 9; 
        path[30].i = 5; path[30].j = 9; 
        path[31].i = 4; path[31].j = 9; 
        path[32].i = 3; path[32].j = 9; 
        path[33].i = 2; path[33].j = 9; 
        path[34].i = 1; path[34].j = 9; 
        path[35].i = 0; path[35].j = 9;
        path[36].i = 0; path[36].j = 8;
        path[37].i = 0; path[37].j = 7;
        path[38].i = 1; path[38].j = 7;
        path[39].i = 2; path[39].j = 7; 
        path[40].i = 3; path[40].j = 7; 
        path[41].i = 4; path[41].j = 7; 
        path[42].i = 5; path[42].j = 7; 
        path[43].i = 6; path[43].j = 7; 
        path[44].i = 7; path[44].j = 6; 
        path[45].i = 7; path[45].j = 5;
        path[46].i = 7; path[46].j = 4;
        path[47].i = 7; path[47].j = 3;
        path[48].i = 7; path[48].j = 2;
        path[49].i = 7; path[49].j = 1; 
        path[50].i = 7; path[50].j = 0; 
        path[51].i = 8; path[51].j = 0; 
        path[52].i = 9; path[52].j = 0; 
        path[53].i = 9; path[53].j = 1; 
        path[54].i = 9; path[54].j = 2; 
        path[55].i = 9; path[55].j = 3;
        path[56].i = 9; path[56].j = 4;
        path[57].i = 9; path[57].j = 5;
        path[58].i = 9; path[58].j = 6;
        path[59].i = 10; path[59].j = 7; 
        path[60].i = 11; path[60].j = 7; 
        path[61].i = 12; path[61].j = 7; 
        path[62].i = 13; path[62].j = 7; 
        path[63].i = 14; path[63].j = 7; 
        path[64].i = 15; path[64].j = 7; 
        path[65].i = 16; path[65].j = 7;
        path[66].i = 16; path[66].j = 8;
        path[67].i = 15; path[67].j = 8;
        path[68].i = 14; path[68].j = 8;
        path[69].i = 13; path[69].j = 8; 
        path[70].i = 12; path[70].j = 8;
        path[71].i = 11; path[71].j = 8;
        path[72].i = 10; path[72].j = 8; 
        path[73].i = 9; path[73].j = 8;
    }
}
