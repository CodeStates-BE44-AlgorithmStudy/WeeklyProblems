import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public int solution(int n, int[][] wires) {

        initialize(wires);

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < wires.length; i++) {
            int[][] network = makeNetwork(i, n, wires);
            Set<Integer> set = makeSet(n);
            countNode(set, network, 0);

            int firstNetworkNodes = set.size();
            int secondNetworkNodes = n- set.size();
            
            list.add(Math.abs(firstNetworkNodes - secondNetworkNodes));
        }

        return list.stream().mapToInt(num -> num).min().getAsInt();
    }

    private void countNode(Set<Integer> set, int[][] network, int startingPoint) {
        for (int i = 0; i < network[startingPoint].length; i++) {
            if(set.contains(i) && network[startingPoint][i] == 1){
                set.remove(i);
                countNode(set, network, i);
            }
        }
    }

    private Set<Integer> makeSet(int n) {
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i < n; i++) {
            set.add(i);
        }
        return set;
    }

    private int[][] makeNetwork(int order, int n, int[][] wires) {
        int[][] network = new int[n][n];

        for (int i = 0; i < wires.length; i++) {
            if (i == order) {
                continue;
            }
            int col = wires[i][0];
            int row = wires[i][1];
            network[col][row] = 1;
            network[row][col] = 1;
        }

        return network;
    }

    private void initialize(int[][] wires) {
        for (int[] wire : wires) {
            wire[0] = wire[0] - 1;
            wire[1] = wire[1] - 1;
        }
    }
}
