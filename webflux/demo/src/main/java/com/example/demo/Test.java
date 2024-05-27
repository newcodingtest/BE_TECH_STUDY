package com.example.demo;

import java.util.*;

class Test {
    static int[] enemies;
    public static void main(String[] args){
        int n1 = 7;
        int k1 = 3;
        int[] enemy1 = {4, 2, 4, 5, 3, 3, 1};//5

        int n2 = 2;
        int k2 = 4;
        int[] enemy2 = {3, 3, 3, 3}; //4

        int n3 = 2;
        int k3 = 1;
        int[] enemy3 = {2,3,3}; //2

        int n4 = 3;
        int k4 = 1;
        int[] enemy4 = {2,1,5}; //3

        System.out.println(solution(n1,k1,enemy1));

    }
    public static int solution(int n, int k, int[] enemy) {
        int numOfSoldier = n;
        int godModCnt = k;
        for (int i=1; i<=enemy.length-1; i++){
            //1. 무적권은 있고 현재 병사로 다음level 못갈 경우

            //2. 무적권과 현재 병사 모두 모자른 경우

            //3. 현재 병사로 커버 가능
            if (godModCnt<0 && numOfSoldier<0){
                return i+1;
            }

            if (godModCnt<0 && numOfSoldier<0){
                return i+1;
            }

            if (i==enemy.length-1 && enemy[i]<=numOfSoldier){
                return i+1;
            }

            if (enemy[i-1]+enemy[i]>numOfSoldier){
                if (enemy[i-1]>enemy[i]){
                    godModCnt--;
                } else {
                    numOfSoldier-=enemy[i-1];
                }
            } else {
                numOfSoldier-=enemy[i-1];
            }
      }
        return enemy.length;
    }

}
