
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Matrix  extends HttpServlet {

    public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.setContentType("text/html");
        int size = Integer.parseInt(req.getParameter("size"));
        String trans=(req.getParameter("trans"));
        String deter=(req.getParameter("deter"));

        size--;
        System.out.println("the size is " + size);
        int mat[][] = new int[size][size];
        int counter = 0;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                String value=(req.getParameter("input" + counter));
                if(value.equals(""))
                mat[i][j] = (0);
                else
                    mat[i][j]=Integer.parseInt(value);
                counter++;
            }
        int der=GFG.determinantOfMatrix(mat,size);

        String first = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\" />\n" +
                "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">" +

                "    <title>React App</title>\n" +
                "  </head>\n" +
                "  <body >\n" +
                "    <div id=\"root\" style='background-color:gray ; height:100vh' >" +
                "<div >";
                if(trans!=null)
                {
                    first+="<h1 class='text-center'> The Transpose of the matrix is </h1>";
                    for (int i = 0; i < size; i++)
                    {
                        first += "<ul style=\" display:flex; list-style:none; gap:20px; justify-content: center; \">";
                        for (int j = 0; j < size; j++) {
                            first+="<li>"+mat[j][i]+"</li>";
                        }
                        first += "</ul>";
                    }

                }

                first+="<h1 class='text-center'> The matrix </h1>";
                for (int i = 0; i < size; i++)
                {
                    first += "<ul style=\" display:flex; list-style:none; gap:20px; justify-content: center; \">";
                    for (int j = 0; j < size; j++) {
                        first+="<li>"+mat[i][j]+"</li>";
                    }
                    first += "</ul>";
                }
                if(deter!=null)
                {
                    first+="<h1 class='text-center'> The dertrimain of the matrix is  "+der+" </h1>";
                }
                String remain=
                "    <!--\n" +
                "      This HTML file is a template.\n" +
                "      If you open it directly in the browser, you will see an empty page.\n" +
                "\n" +
                "      You can add webfonts, meta tags, or analytics to this file.\n" +
                "      The build step will place the bundled scripts into the <body> tag.\n" +
                "\n" +
                "      To begin the development, run `npm start` or `yarn start`.\n" +
                "      To create a production bundle, use `npm run build` or `yarn build`.\n" +
                "    -->\n" +
                "  </body>\n" +
                "</html>\n";
                res.getWriter().println(first+remain);

    }
}


class GFG {

    // Dimension of input square matrix
    static final int N = 4;

    // Function to get cofactor of
    // mat[p][q] in temp[][]. n is
    // current dimension of mat[][]
    static void getCofactor(int mat[][], int temp[][],
                            int p, int q, int n)
    {
        int i = 0, j = 0;

        // Looping for each element of
        // the matrix
        for (int row = 0; row < n; row++)
        {
            for (int col = 0; col < n; col++)
            {
                // Copying into temporary matrix
                // only those element which are
                // not in given row and column
                if (row != p && col != q)
                {
                    temp[i][j++] = mat[row][col];
                    // Row is filled, so increase
                    // row index and reset col
                    // index
                    if (j == n - 1)
                    {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    /* Recursive function for finding determinant
    of matrix. n is current dimension of mat[][]. */
    static int determinantOfMatrix(int mat[][], int n)
    {
        int D = 0; // Initialize result

        // Base case : if matrix contains single
        // element
        if (n == 1)
            return mat[0][0];

        // To store cofactors
        int temp[][] = new int[mat.length][mat[0].length];

        // To store sign multiplier
        int sign = 1;

        // Iterate for each element of first row
        for (int f = 0; f < n; f++)
        {
            // Getting Cofactor of mat[0][f]
            getCofactor(mat, temp, 0, f, n);
            D += sign * mat[0][f]
                    * determinantOfMatrix(temp, n - 1);

            // terms are to be added with
            // alternate sign
            sign = -sign;
        }

        return D;
    }

    /* function for displaying the matrix */
    static void display(int mat[][], int row, int col)
    {
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
                System.out.print(mat[i][j]);

            System.out.print("\n");
        }
    }

    // Driver code
    public static void main(String[] args)
    {

        int mat[][] = { { 1, 0, 2, -1 },
                { 3, 0, 0, 5 },
                { 2, 1, 4, -3 },
                { 1, 0, 5, 0 } };

        System.out.print("Determinant "
                + "of the matrix is : "
                + determinantOfMatrix(mat, N));
    }
}
