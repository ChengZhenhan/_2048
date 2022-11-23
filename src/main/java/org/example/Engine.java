package org.example;

import java.io.*;
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

	public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
		List<T> dest = (List<T>) in.readObject();
        return dest;
    }

	public List<Integer> merge(List<Integer> l, boolean ud){
		List<Integer> n;
		try {
			n = deepCopy(l);
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		if(!ud) {
            for (int j = 0; j < n.size(); j++) {
                if (j+1 < n.size() && Objects.equals(n.get(j), n.get(j + 1))) {
                    int m = n.get(j) + n.get(j+1);
                    score += Math.log(m) / Math.log(2);
                    n.remove(j);
                    n.add(j,m);
                    n.remove(j+1);
                    j--;
                }
                if (j-1 >= 0 && Objects.equals(n.get(j), n.get(j - 1))) {
                    int m = n.get(j) + n.get(j-1);
                    score += Math.log(m) / Math.log(2);
                    n.remove(j-1);
                    n.add(j-1,m);
                    n.remove(j);
                }
            }
        } else {
            for (int j = 0; j < n.size(); j++) {
                if (j-1 >= 0 && Objects.equals(n.get(j), n.get(j - 1))) {
                    int m = n.get(j) + n.get(j-1);
                    score += Math.log(m) / Math.log(2);
                    n.remove(j-1);
                    n.add(j-1,m);
                    n.remove(j);
                }
				if (j+1 < n.size() && Objects.equals(n.get(j), n.get(j + 1))) {
                    int m = n.get(j) + n.get(j+1);
                    score += Math.log(m) / Math.log(2);
                    n.remove(j);
                    n.add(j,m);
                    n.remove(j+1);
                    j--;
                }
            }
        }

		while(n.size() < 4) {
            n.add(0);
        }
		return n;
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
			l = merge(l, true);
            for(int j = 4; j > 4-l.size(); j--) {
                t[5-j][i] = l.get(l.size()-j);
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
            l = merge(l, false);
            for(int j = 4; j > 4-l.size(); j--) {
                t[j][i] = l.get(l.size()-j);
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
            l = merge(l, true);
            for(int j = 4; j > 4-l.size(); j--) {
                t[i][5-j] = l.get(l.size()-j);
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
            l = merge(l, false);
            for(int j = 4; j > 4-l.size(); j--) {
                t[i][j] = l.get(l.size()-j);
            }
        }
        return t;
    }

    public void addNum() {
        int i = 1;
        while (i > 0 && !iffull()) {
            int x = Math.abs(random.nextInt() % 4) + 1;
            int y = Math.abs(random.nextInt() % 4) + 1;
            if(map[x][y] == 0) {
                i--;
                if(Math.abs(random.nextInt() % 10) == 0) {
                    map[x][y] = 4;
                } else {
                    map[x][y] = 2;
                }
                // map[x][y] = (Math.abs(random.nextInt() % 2) + 1) * 2;
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

    public void CopyArray(int[][] src, int[][] dist) {
        for(int i = 0; i < Math.min(src.length,dist.length); i++) {
            System.arraycopy(src[i], 0, dist[i], 0, Math.min(src[i].length, dist[i].length));
        }
    }
}
