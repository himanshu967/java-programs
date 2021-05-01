import java.util.*;

import javax.lang.model.util.ElementScanner14;

public class lazer2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter size of matrix (x*y)");
        int x = sc.nextInt(), y = sc.nextInt();
        System.out.println("enter starting coordinates (i and j)");
        int i = sc.nextInt();
        int j = sc.nextInt();
        System.out.println("enter dirction (e/w/n/s)");
        char dir = sc.next().charAt(0);
        char[][] mir = new char[x][y];
        for (int i1 = 0; i1 < x; i1++) {
            for (int j1 = 0; j1 < y; j1++)
                mir[i1][j1] = 'E';
        }
        System.out.println("enter no of mirrors");
        int n = sc.nextInt();
        while (n-- > 0) {
            System.out.println("enter mirror coordinates and type('/' or '\\')");
            int axis1 = sc.nextInt();
            int axis2 = sc.nextInt();
            char c = sc.next().charAt(0);
            mir[axis1][axis2] = c;
        }
        int count = 0, flag = 0;
        HashSet<VisitData> h1=new HashSet<>();

        while (!wallhit(dir, i, j, x, y, mir)) {
            try {
                if (dir == 'e') {
                    if (mir[i + 1][j] == 'E') {
                        System.out.println("moved towards east:");
                    } else {
                        if (mir[i + 1][j] == '/') {
                            dir = 'n';
                            System.out.println("moved towards east,new dir:north");
                        } else {
                            dir = 's';
                            System.out.println("moved towards east,new dir:south ");
                        }
                    }
                    count++;
                    i++;
                    // c
                   
                    if(loop(i,j,'e',h1))
                    {flag=1;
                    break;
                    }
                }

                if (dir == 'w') 
                {
                    if (mir[i - 1][j] == 'E')
                     {
                         System.out.println("moved towards west");
                     } 
                    else 
                    {
                        if (mir[i - 1][j] == '/')
                         {
                            dir = 's';
                            System.out.println("moved towards west,new dir:south ");
                         } 
                        else
                         {
                            dir = 'n';
                            System.out.println("moved towards west,new dir:north ");
                         }
                    }
                    count++;
                    i--;
            
                    if(loop(i,j,'w',h1))
                   {  flag=1;
                        break;
                   }
                    // continue;
                }

                if (dir == 'n') {
                    if (mir[i][j + 1] == 'E') {
                        System.out.println("moved towards north");
                    } 
                    else {
                        if (mir[i][j + 1] == '/') {
                            dir = 'e';
                            System.out.println("moved towards north,new dir:east");
                        } else {
                            dir = 'w';
                            System.out.println("moved towards north,new dir:west");
                        }
                    }
                    count++;
                    j++;
                   
                    if(loop(i,j,'n',h1))
                   {  flag=1;
                        break;
                   }
                    // continue;
                }

                if (dir == 's') {
                    if (mir[i][j - 1] == 'E') {
                        System.out.println("moved towards south");
                    } else {
                        if (mir[i][j - 1] == '/') {
                            dir = 'w';
                            System.out.println("moved towards south,new dir:west");
                        } else {
                            dir = 'e';
                            System.out.println("moved towards south,new dir:east");
                        }
                    }
                    count++;
                    j--;
                  
                    if(loop(i,j,'s',h1))
                   { flag=1;
                       break;
                   }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                // System.out.println("count=" + count);
                break;
            }
        }
        if (flag == 0)
            System.out.println("count=" + count + ", final box coordinates:" + i + " " + j);
            else
            System.out.println("Infinite loop detected");
    }

    public static boolean wallhit(char dir, int i, int j, int x, int y, char[][] mir) {
        if (dir == 'e' && i == x - 1 && mir[i][j] == 'E')
            return true;
        if (dir == 'w' && i == 0 && mir[i][j] == 'E')
            return true;
        if (dir == 'n' && j == y - 1 && mir[i][j] == 'E')
            return true;
        if (dir == 's' && j == 0 && mir[i][j] == 'E')
            return true;
        return false;
    }

    public static boolean loop(int v, int w, char x, HashSet<VisitData> h2)
    {int flag=0;
       VisitData temp=new VisitData(v,w,x);
       for(VisitData a3:h2)
       {
           if(a3.a==v&&a3.b==w&&a3.c==x)
          { flag=1;
            break;
          }
       }
      if(flag==1)
      return true;
      else 
      { h2.add(temp);
        flag=0;
      return false;
      }
    }
}

class VisitData
{
  int a,b;
  char c;
  VisitData(int a1,int b1, char c1 )
  {
      a=a1;
      b=b1;
      c=c1;
  }
}
