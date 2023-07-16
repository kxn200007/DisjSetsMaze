package Maze;

import java.util.Random;
import java.util.Arrays;
import java.lang.String;
import java.util.Scanner;


public class maze
{

    private static int design()
    {
        int wallCts = 2;
        int w;
        Random design = new Random();
        w = design.nextInt(wallCts);
        return w;
    }

    public static void main(String [] args)
    {
        Scanner srt = new Scanner(System.in);
        //checking user to start the program
        System.out.println("Press 0 to start or 1 to exit:");
        int start = srt.nextInt();
        if (start == 0)
        {
            //initializing variables
            int c1, c2, w;

            //setting design as random
            Random design = new Random();
            //setting mze as scanner
            Scanner mze = new Scanner(System.in);
            //enter rows
            System.out.println("Enter number of rows:");
            int row = mze.nextInt();
            //checking rows for a valid number
            while(row < 1)
            {
                System.out.println("Enter a number over 0");
                System.out.println("Enter number of rows:");
                row = mze.nextInt();
            }

            //enter columns
            System.out.println("Enter number of columns:");
            int col = mze.nextInt();
            //checking columns for a valid number
            while(col < 1)
            {
                System.out.println("Enter a number over 0");
                System.out.println("Enter number of columns:");
                col = mze.nextInt();
            }

            //initializing variables
            int total = row * col, ct = 1;
            DisjSets disJ = new DisjSets(total);
            //creating Array1
            boolean[] Array1 = new boolean[total];
            Arrays.fill(Array1, true);
            //creating Array2
            boolean[] Array2 = new boolean[total];
            Arrays.fill(Array2, true);


            while (ct < total)
            {
                //randomizing w
                w = design();

                if (w == 0)
                {
                    c1 = design.nextInt(total);
                    c2 = c1 - 1;
                    //finding remainder of c1 and col
                    if (c1 - col * (c1 / col) == 0)
                    {
                        continue;
                    }
                }

                else if (w == 1)
                {
                    c1 = design.nextInt(total);
                    c2 = c1 - col;
                    if (c1 < col)
                    {
                        continue;
                    }
                }

                else if (w == 2)
                {
                    c1 = design.nextInt(total);
                    c2 = c1 + 1;
                    if ((c1 + 1) - col * ((c1 + 1) / col) == 0)
                    {
                        continue;
                    }
                }

                else
                {
                    c1 = design.nextInt(total);
                    c2 = c1 + col;
                    if (c1 >= (total - col))
                    {
                        continue;
                    }
                }

                if (disJ.find(c1) != disJ.find(c2))
                {
                    disJ.union(disJ.find(c1), disJ.find(c2));

                    //setting arrays to false
                    if (w == 0)
                    {
                        Array1[Math.max(c1, c2)] = false;
                    }

                    if (w == 2)
                    {
                        Array1[Math.max(c1, c2)] = false;
                    }

                    if (w != 0)
                    {
                        if (w != 2)
                        {
                            Array2[Math.min(c1, c2)] = false;
                        }
                    }

                    ct++;
                }
            }

            //printing maze
            for (int k = 0; k < col; k++)
            {
                if (k == 0)
                {
                    System.out.print("  ");
                }

                while (k != 0)
                {
                    System.out.print("_ ");
                    break;
                }
            }

            System.out.println();
            for (int n = 0; n < total; n++)
            {
                if (Array1[n] && n != 0)
                {
                    System.out.print("|");
                }

                else
                {
                    System.out.print(" ");
                }

                if (Array2[n] && n < (total - 1))
                {
                    System.out.print("_");
                }

                else
                {
                    System.out.print(" ");
                }

                int bored = n - col * (n / col);
                if (bored == col - 1 && n < (total - 1))
                {
                    System.out.println("|");
                }

            }

            System.out.println();
        }

        else
        {
            //exit program
            System.exit(0);
        }
    }

}

