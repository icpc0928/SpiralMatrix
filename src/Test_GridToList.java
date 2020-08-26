import java.util.ArrayList;
import java.util.List;

public class Test_GridToList {
    public static void main(String[] args){
        int row = 5;
        int col = 5;
        int[][] matrix = {{0,1,2,3,4,},{5,6,7,8,9},{10,11,12,13,14},{15,16,17,18,19},{20,21,22,23,24}};
        printGrid(matrix);
        Solution solution = new Solution();
        List<Integer> list = solution.spiralOrder(matrix);
        System.out.println(list);

        int[][] newMatrix = solution.reSpiral(list, row , col);
        printGrid(newMatrix);

        ArrayList<Integer> list2 = new ArrayList<>();
        for(int i = 0; i < 25; i++){
            list2.add(i);
        }
        System.out.println(list2);
        int[][] matrix2 = solution.reSpiral(list2, row, col);
        printGrid(matrix2);


    }

    private static void printGrid(int[][] maxSpinWinGrid) {

        for(int i = 0; i < maxSpinWinGrid[0].length; i++){
            for(int j = 0; j < maxSpinWinGrid.length; j++){
                String result = (maxSpinWinGrid[j][i] < 10) ? "  "+maxSpinWinGrid[j][i] : " "+maxSpinWinGrid[j][i] ;
                System.out.print(result);
            }
            System.out.println();
        }
    }
}




class Solution{

    public List<Integer> spiralOrder(int[][] matrix){
        List ans = new ArrayList();
        if (matrix.length == 0) return ans;
        int R = matrix.length, C = matrix[0].length;
        boolean[][] seen = new boolean[R][C];
        int[] dr = {0, 1, 0, -1};               //縱向(dr dc同時使用同一個index值去判斷要往哪走 Ex: index0 dr=0 dc=1 往橫向右邊走 dr1 dc0 往縱向下面走 dr0 dc-1 往橫向左邊走 dr-1 dc0 往縱向上面走
        int[] dc = {1, 0, -1, 0};               //橫向
//        int[] dr = {1, 0, -1, 0};
//        int[] dc = {0, 1, 0, -1};
        int r = 0, c = 0, di = 0;
        for (int i = 0; i < R * C; i++) {       //總共有幾格就跑幾次
            ans.add(matrix[r][c]);              //將List新增
            seen[r][c] = true;                  //代表這格已經走過了
            int cr = r + dr[di];                //0
            int cc = c + dc[di];                //1
            if (0 <= cr && cr < R && 0 <= cc && cc < C && !seen[cr][cc]){   //判斷是否繼續相同走向
                r = cr;
                c = cc;
            } else {                                                        //更改走向 (順序為 右下左上)
                di = (di + 1) % 4;
                r += dr[di];
                c += dc[di];
            }
        }
        return ans;
    }

    public int[][] reSpiral(List<Integer> list, int row , int col){
        int[][] ans = new int[row][col];
        boolean[][] seen = new boolean[row][col];
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};

        int r = 0, c = 0, di = 0;
        for(int i = 0 ; i < list.size(); i++){
            ans[r][c] = list.get(i);
            seen[r][c] = true;
            int cr = r + dr[di];
            int cc = c + dc[di];
            if(0 <= cr && cr < row && 0 <= cc && cc < col && !seen[cr][cc]){
                r = cr;
                c = cc;
            }else{
                di = (di + 1) % 4;
                r += dr[di];
                c += dc[di];
            }
        }
        return ans;
    }




}