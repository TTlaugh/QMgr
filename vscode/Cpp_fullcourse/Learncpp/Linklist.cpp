#include <iostream>
using namespace std;
struct node
{
    int data;
    node *link;
};
typedef struct node *n;
n creat(int x)
{
    n tmp = new node;
    tmp->data = x;
    tmp->link = NULL;
    return tmp;
}
bool empty(n a)
{
    return a == NULL;
}

int size(n a)
{
    int count = 0;
    while (a != NULL)
    {
        count++;
        a = a->link;
    }
    return count;
}
void inserfirst(n &a, int x)
{
    n tmp = creat(x);
    if (a == NULL)
        a = tmp;
    else
    {
        tmp->link = a;
        a = tmp;
    }
}
void inserlast(n &a, int x)
{
    n tmp = creat(x);
    if (a == NULL)
        a = tmp;
    else
    {
        n p = a;
        while (p != NULL)
        {
            p = p->link;
        }
        p->link = tmp;
    }
}
void insermiddle(n &a, int x, int pos)
{
    n tmp = creat(x);
    int si = size(a);
    if (pos < 0 || pos > si + 1)
        cout << " vi tri chen kh hop le " << endl;
    else if (si == 1)
        inserfirst(a, x);
    else if (si == pos + 1)
        inserlast(a, x);
    n p = a;
    for (int i = 1; i < pos - 1; i++)
    {
        p = p->link;
        // 1 2 3 4 5 6 -4
        /*
        1 : p=2
        2 : p=3
        */
    }
    tmp->link = p->link;
    p->link = tmp;
}

void deletefirst(n &a)
{
    if (a == NULL)
        return;
    else
    {
        a = a->link;
    }
}
void deletelast(n &a)
{
    if (a == NULL)
        return;
    n truoc = NULL, sau = a;
    while (sau->link != NULL)
    {
        truoc = sau;
        sau = sau->link;
    }
    truoc->link = NULL;
}
void deletemiddle(n &a, int pos)
{
    int si = size(a);
    n truoc = NULL, sau = a;
    if (si <= 0 || si > pos + 1)
        cout << " vi tri xoa kh hop le " << endl;
    else
    {
        // 1 2 3 4 5
        // 1: trc =1 ,sau =2
        // 2 :trc =2 ,sau =3
        // 3 : trc =3 ,sau =4
        for (int i = 1; i < pos; i++)
        {
            truoc = sau;
            sau = sau->link;
        }
    }
    if (truoc == NULL)
        a = a->link;
    else
        truoc->link = sau->link;
}
void sapxep(n &a)
{
    for (n p = a; p->link != NULL; p = p->link)
    {
        n min = p;
        for (n q = p->link; q != NULL; q = q->link)
        {
            if (min > q)
                min = q;
        }
        int tmp = min->data;
        min->data = p->data;
        p->data = tmp;
    }
}
int max(n a)
{
    int nodemax = a->data;
    while (a != NULL)
    {
        if (nodemax < a->data)
        {
            nodemax = a->data;
        }
        a = a->link;
    }
    return nodemax;
}
void innode(n a)
{
    while (a != NULL)
    {
        cout << a << " ";
        a = a->link;
    }
    cout << endl;
}
int main()
{
    n head = NULL;
    while (1)
    {
        // cin.ignore();
        cout << "__________MENU___________\n";
        cout << " 1. Chen phan tu vao dau danh sach \n";
        cout << " 2. Chen phan tu vao cuoi danh sach \n";
        cout << " 3. Chen phan tu vao giua danh sach \n";
        cout << " 4. Xoa phan tu o dau \n";
        cout << " 5. Xoa phan tu o cuoi \n";
        cout << " 6. Xoa phan tu o giua \n";
        cout << " 7. Sap xep danh sach \n";
        cout << " 8. Tim GTLN trong danh sach \n";
        cout << " 9. Duyet sanh sach \n";
        cout << " 10. Thoat khoi chuong trinh \n";
        int opt;
        int x;
        int pos;
        cout << " Nhap lua chon: ";
        cin >> opt;
        if (opt == 1)
        {
            cout << " Nhap gia tri can chen : ";
            cin >> x;
            inserfirst(head, x);
        }
        else if (opt == 2)
        {
            cout << " Nhap gia tri can chen : ";
            cin >> x;
            inserlast(head, x);
        }
        else if (opt == 3)
        {
            cout << " Nhap gia tri can chen : ";
            cin >> x;
            cout << " Nhap vi tri can chen : ";
            cin >> pos;
            insermiddle(head, x, pos);
        }
        else if (opt == 4)
        {
            deletefirst(head);
        }
        else if (opt == 5)
        {
            deletelast(head);
        }
        else if (opt == 6)
        {
            cout << " Nhap vi tri can xoa : ";
            cin >> pos;
            deletemiddle(head, pos);
        }
        else if (opt == 7)
        {
            sapxep(head);
        }
        else if (opt == 8)
        {
            cout << max(head);
        }
        else if (opt == 9)
        {
            cout << " cac gia tri danh sach lien ket la :";
            innode(head);
        }
        else if (opt == 10)
            break;
    }

    return 0;
}