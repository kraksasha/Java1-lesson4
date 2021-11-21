package com.geekbrains;

import java.util.Random;
import java.util.Scanner;

public class KreNu {
    public static final int SIZE = 4;
    public static final int DOTS_TO_WIN = 3;
    public static char[][] map;
    public static final char DOT_EMPTY = '.';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static Scanner SCANNER = new Scanner(System.in);
    public static Random random = new Random();

    public static void main(String[] args) {
            initMap();
            printMap();
            while (true){
                humanTurn();
                printMap();
                if (checkWin(DOT_X)){
                    System.out.println("Победил человек");
                    break;
                }
                if (isMapFull()){
                    System.out.println("Ничья");
                    break;
                }
                aiTurn();
                printMap();
                if (checkWin(DOT_O)){
                    System.out.println("Победил Искуственный интелект");
                     break;
                }
                if (isMapFull()){
                    System.out.println("Ничья");
                    break;
                }

            }

        System.out.println("Игра окончена");
    }

    public static void initMap(){
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap(){
        for (int i = 0; i <= SIZE; i++){
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++){
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void humanTurn(){
        int x;
        int y;

        do {
            System.out.println("Введите координаты в формате X Y");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;

        } while (!isCellVallid(x,y));
        map[y][x] = DOT_X;

    }

    public static boolean isCellVallid(int x, int y){
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }

    public static void aiTurn(){
        int x;
        int y;

        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellVallid(x,y));
        System.out.println("Компьютер походил в точку " +  (x + 1) + " " +  (y + 1));
        map[y][x] = DOT_O;
    }

    public static boolean isMapFull(){
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    public static boolean checkWin(char symb){
        int n = 0;
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                if (map[i][j] == symb){
                    n = n + 1;
                }
            }
            if (n == SIZE){
                return true;
            } else {
                n = 0;
            }
        }

        for (int j = 0; j < SIZE; j++){
            for (int i = 0; i < SIZE; i++){
                if (map[i][j] == symb){
                    n = n + 1;
                }
            }
            if (n == SIZE){
                return true;
            } else {
                n = 0;
            }
        }

        int k = 0;
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                n = n + 1;
                if (((n == 1) || (n == 6) || (n == 11) || (n == 16)) && (map[i][j] == symb)){
                    k = k + 1;
                }
            }
        }
        if (k == SIZE){
            return true;
        } else {
            k = 0;
            n = 0;
        }

        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                n = n + 1;
                if (((n == 4) || (n == 7) || (n == 10) || (n == 13)) && (map[i][j] == symb)){
                    k = k + 1;
                }
            }
        }
        if (k == SIZE){
            return true;
        }
        return false;
    }
}
