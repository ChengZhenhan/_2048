package org.example;

import java.util.*;


class Engine {
    private int[][] map = new int[5][5];
    private int score = 0;
    Random random = new Random(new Date().getTime());
    Engine() {
        for(int i = 0; i <= 4; i++) {
            for(int j = 0; j <= 4; j++) {
                map[i][j] = 0;
            }
        }
    }

    public int[][] getMap() {
        return map;
    }

    public int getScore() {
        return score;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean ifmove(int[][] map) {
        return this.map != map;
    }

    public int[][] up() {
        int[][] t = Arrays.copyOf(map,5);
        for (int i = 1; i <= 4; i++) {
            List<Integer> l = new LinkedList<>();
            for(int j = 1; j <= 4; j++) {
                if(map[j][i] == 0) {
                    continue;
                }
                l.add(map[j][i]);
            }
            for (int j = 0; j < l.size(); j++) {
                if (j-1 >= 0 && Objects.equals(l.get(j), l.get(j - 1))) {
                    int n = l.get(j) + l.get(j-1);
                    l.remove(j-1);
                    l.add(j-1,n);
                    l.remove(j);
                }
                if (j+1 < l.size() && Objects.equals(l.get(j), l.get(j + 1))) {
                    int n = l.get(j) + l.get(j+1);
                    l.remove(j);
                    l.add(j,n);
                    l.remove(j+1);
                    j--;
                }
            }
            while(l.size() < 4) {
                l.add(0);
            }
            for(int j = 4; j > 4-l.size(); j--) {
                map[5-j][i] = l.get(l.size()-j);
            }
        }
        return t;
    }

    public int[][] down() {
        int[][] t = Arrays.copyOf(map,5);
        for (int i = 1; i <= 4; i++) {
            List<Integer> l = new LinkedList<>();
            for(int j = 4; j >= 1; j--) {
                if(map[j][i] == 0) {
                    continue;
                }
                l.add(map[j][i]);
            }
            for (int j = 0; j < l.size(); j++) {
                if (j+1 < l.size() && Objects.equals(l.get(j), l.get(j + 1))) {
                    int n = l.get(j) + l.get(j+1);
                    l.remove(j);
                    l.add(j,n);
                    l.remove(j+1);
                    j--;
                }
                if (j-1 >= 0 && Objects.equals(l.get(j), l.get(j - 1))) {
                    int n = l.get(j) + l.get(j-1);
                    l.remove(j-1);
                    l.add(j-1,n);
                    l.remove(j);
                }
            }
            while(l.size() < 4) {
                l.add(0);
            }
            for(int j = 4; j > 4-l.size(); j--) {
                map[j][i] = l.get(l.size()-j);
            }
        }
        return t;
    }

    public int[][] left() {
        int[][] t = Arrays.copyOf(map,5);
        for (int i = 1; i <= 4; i++) {
            List<Integer> l = new LinkedList<>();
            for(int j = 1; j <= 4; j++) {
                if(map[i][j] == 0) {
                    continue;
                }
                l.add(map[i][j]);
            }
            for (int j = 0; j < l.size(); j++) {
                if (j-1 >= 0 && Objects.equals(l.get(j), l.get(j - 1))) {
                    int n = l.get(j) + l.get(j-1);
                    l.remove(j-1);
                    l.add(j-1,n);
                    l.remove(j);
                }
                if (j+1 < l.size() && Objects.equals(l.get(j), l.get(j + 1))) {
                    int n = l.get(j) + l.get(j+1);
                    l.remove(j);
                    l.add(j,n);
                    l.remove(j+1);
                    j--;
                }
            }
            while(l.size() < 4) {
                l.add(0);
            }
            for(int j = 4; j > 4-l.size(); j--) {
                map[i][5-j] = l.get(l.size()-j);
            }
        }
        return t;
    }

    public int[][] right() {
        int[][] t = Arrays.copyOf(map,5);
        for (int i = 1; i <= 4; i++) {
            List<Integer> l = new LinkedList<>();
            for(int j = 4; j >= 1; j--) {
                if(map[i][j] == 0) {
                    continue;
                }
                l.add(map[i][j]);
            }
            for (int j = 0; j < l.size(); j++) {
                if (j+1 < l.size() && Objects.equals(l.get(j), l.get(j + 1))) {
                    int n = l.get(j) + l.get(j+1);
                    l.remove(j);
                    l.add(j,n);
                    l.remove(j+1);
                    j--;
                }
                if (j-1 >= 0 && Objects.equals(l.get(j), l.get(j - 1))) {
                    int n = l.get(j) + l.get(j-1);
                    l.remove(j-1);
                    l.add(j-1,n);
                    l.remove(j);
                }
            }
            while(l.size() < 4) {
                l.add(0);
            }
            for(int j = 4; j > 4-l.size(); j--) {
                map[i][j] = l.get(l.size()-j);
            }
        }
        return t;
    }

    public void addNum() {
        int i = 2;
        while (i > 0 && !iffull()) {
            int x = Math.abs(random.nextInt() % 4) + 1;
            int y = Math.abs(random.nextInt() % 4) + 1;
            if(map[x][y] == 0) {
                i--;
                map[x][y] = (Math.abs(random.nextInt() % 2) + 1) * 2;
            }
        }
    }

    public void show() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void next() {
        addNum();
        show();
    }

    boolean iffull() {
        for(int i = 1; i <= 4; i++) {
            for(int j = 1; j <= 4; j++) {
                if(map[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public int WinorLose() {
        int[][] t = Arrays.copyOf(map, 5);
        if(iffull()) {
            if(Arrays.deepEquals(t,up()) && Arrays.deepEquals(t,down()) && Arrays.deepEquals(t,left()) && Arrays.deepEquals(t,right())) {
                return 2;
            }
        }
        for(int i = 1; i <= 4; i++) {
            for(int j = 1; j <= 4; j++) {
                if(map[i][j] >= 2048) {
                    return 1;
                }
            }
        }
        return 0;
    }
}
