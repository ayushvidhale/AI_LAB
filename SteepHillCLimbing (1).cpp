#include <bits/stdc++.h>
using namespace std;

static const auto init = []
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::cout.tie(nullptr);
    return false;
}();

class Node
{
public:
    int hval;
    vector<vector<int>> board;
    void Hfun(Node &Goal)
    {
        int count = 0;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (board[i][j] != Goal.board[i][j])
                {
                    count++;
                }
            }
        }
        hval = count;
    }
    void PrintBoard()
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                cout << board[i][j] << " ";
            }
            cout << "\n";
        }
        cout << endl;
    }
};

class Position
{
public:
    int x;
    int y;
    void zeroposition(Node &temp)
    {
        int i, j;
        for (i = 0; i < 3; i++)
        {
            for (j = 0; j < 3; j++)
            {
                if (temp.board[i][j] == 0)
                {
                    x = i;
                    y = j;
                    break;
                }
            }
        }
    }
};

int main()
{
    //Goal State
    Node Goal;
    Goal.board = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    Goal.hval = 0;

    //Intial State
    Node Intial;
    Intial.board = {{4, 1, 3}, {7, 2, 6}, {0, 5, 8}};
    Intial.Hfun(Goal);

    int minimum = 10;
    Position curr; //empty tile position
    Node TempBoard;

    while (Intial.hval < minimum && Intial.hval != 0)
    {
        minimum = Intial.hval;
        TempBoard = Intial;
        curr.zeroposition(TempBoard);

        if (curr.x + 1 < 3)
        {
            swap(TempBoard.board[curr.x + 1][curr.y],TempBoard.board[curr.x][curr.y]);
            TempBoard.Hfun(Goal);
            if (TempBoard.hval < Intial.hval)
            {
                Intial = TempBoard;
            }
            swap(TempBoard.board[curr.x + 1][curr.y],TempBoard.board[curr.x][curr.y]);
        }
        if (curr.y + 1 < 3)
        {
            swap(TempBoard.board[curr.x][curr.y + 1],TempBoard.board[curr.x][curr.y]);
            TempBoard.Hfun(Goal);
            if (TempBoard.hval < Intial.hval)
            {
                Intial = TempBoard;
            }
            swap(TempBoard.board[curr.x][curr.y + 1],TempBoard.board[curr.x][curr.y]);
        }
        if (curr.x - 1 >= 0)
        {
            swap(TempBoard.board[curr.x - 1][curr.y],TempBoard.board[curr.x][curr.y]);
            TempBoard.Hfun(Goal);
            if (TempBoard.hval < Intial.hval)
            {
                Intial = TempBoard;
            }
            swap(TempBoard.board[curr.x - 1][curr.y],TempBoard.board[curr.x][curr.y]);
        }
        if (curr.y - 1 >= 0)
        {
            swap(TempBoard.board[curr.x][curr.y - 1],TempBoard.board[curr.x][curr.y]);
            TempBoard.Hfun(Goal);
            if (TempBoard.hval < Intial.hval)
            {
                Intial = TempBoard;
            }
             swap(TempBoard.board[curr.x][curr.y - 1],TempBoard.board[curr.x][curr.y]);
        }
        Intial.PrintBoard();
        cout << "Heuristic value = " <<Intial.hval <<"\n"<< endl;
        cout << "***********************************************"<<endl;
    }

    return 0;
}
