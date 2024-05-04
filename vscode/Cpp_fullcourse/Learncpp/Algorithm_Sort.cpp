#include <iostream>
#include <cstdlib>
#include <ctime>
using namespace std;
void inputarray(int *a, int n);
void showarray(int *a, int n);
void swap(int *&a, int *&b);
void selectionsort(int *a, int n);
void insertionsort(int *a, int n);
void bubblesort(int *a, int n);
void shellsort(int *a, int n);
void quicksort(int *a, int low, int high);
void mergesort(int *a, int left, int right);
// void coutingsort(int data[], int n);
int linearsearch(const int array[], int length, const int &item);
int binarysearch(const int array[], int length, const int &item);
int solan(int *a, int n, int x);
int main()
{
    int *a, n, count = 0, x;
    cout << " nhap so phan tu :";
    cin >> n;
    a = new int[n];
    inputarray(a, n);
    showarray(a, n);
    insertionsort(a, n);
    showarray(a, n);

    cout << endl;

    return 0;
}
void inputarray(int *a, int n)
{
    srand(time(0));
    for (int i = 0; i < n; i++)
    {
        a[i] = rand() % 100;
    }
}
void showarray(int *a, int n)
{
    for (int i = 0; i < n; i++)
        cout << a[i] << "  ";
    cout << endl;
}
void swap(int *&a, int *&b)
{
    int tmp = *a;
    *a = *b;
    *b = tmp;
}
void selectionsort(int *a, int n)
{
    for (int i = 0; i < n - 1; i++)
    {
        int min_index = i;
        for (int j = 1; j < n; j++)
        {
            if (a[min_index] > a[j])
                min_index = j;
        }
        if (a[min_index] != a[i])
        {
            swap(a[i], a[min_index]);
        }
    }
}
void insertionsort(int *a, int n)
{
    for (int i = 1; i < n; i++)
    {
        int value = a[i];
        int j = i - 1;
        while (a[j] > value && j >= 0)
        {
            a[j + 1] = a[j];
            j -= 1;
        }
        a[j + 1] = value;
    }
}
// 9 0 8 6 7 2 3 5 4
void bubblesort(int *a, int n)
{
    for (int i = 0; i < n - 1; i++)
    {
        for (int j = 0; j < n - i - 1; j++)
        {
            if (a[j] > a[j + 1])
                swap(a[j], a[j + 1]);
        }
    }
}
void shellsort(int *a, int n)
{
    int i, j, h = 1, value;
    while (h <= n / 3)
    {
        h = 3 * h + 1;
    }
    while (h > 0)
    {
        for (i = h; i < n; i++)
        {
            value = a[i];
            j = i;
            while (j >= h - 1 && a[j - h] > value)
            {
                a[j] = a[j - h];
                j = j - h;
            }
            a[j] = value;
        }
        h = (h - 1) / 3;
    }
}
int partition(int *a, int left, int right)
{
    int pivot = a[right];
    int i = left;
    int j = right - 1;
    while (true)
    {
        while (i <= j && a[i] < pivot)
            i++;
        while (j >= i && a[j] > pivot)
            j--;
        if (i >= j)
            break;
        swap(a[i], a[j]);
        i++;
        j--;
    }
    swap(a[i], a[right]);
    return i;
}
void quicksort(int *a, int left, int right)
{
    if (left < right)
    {
        int pi = partition(a, left, right);
        quicksort(a, left, pi - 1);
        quicksort(a, pi + 1, right);
    }
}
// tron hai day con lai (left,mid) and (mid+1,right)
void merge(int *a, int left, int mid, int right)
{
    int i, j, k = left;
    int doan1 = mid - left + 1;
    int doan2 = right - mid;
    /*
    0.....7; 8 phan tu
    mid =7-0/2= 3
    doan 1= 3-0 +1=4
    doan 2= 7-3 =4
    */
    int *L = new int[doan1];
    int *R = new int[doan2];
    for (int i = 0; i < doan1; i++)
    {
        L[i] = *(a + left + i);
    }
    for (int j = 0; j < doan2; j++)
    {
        R[j] = *(a + mid + j + 1);
    }
    i = 0, j = 0;
    while (i < doan1 && j < doan2)
    {
        a[k++] = (L[i] <= R[j]) ? L[i++] : R[j++];
    }
    while (i < doan1)
    {
        a[k++] = L[i++];
    }
    while (j < doan2)
    {
        a[k++] = R[j++];
    }
}
void mergesort(int *a, int left, int right)
{
    if (left < right)
    {
        int mid = (left + right) / 2;
        mergesort(a, left, mid);
        mergesort(a, mid + 1, right);
        merge(a, left, mid, right);
    }
}
/*
void coutingsort(int data[], int n)
{
    int long largest= data[0];
    long* tmp;
    tmp = new long[n];
    for (int i = 1; i < n; i++)
    {
        if (largest < data[i]) largest = data[i];
    }
    long* count;
    count = new long[largest + 1];
    for (int i = 0; i <= largest; i++)
        count[i] = 0;
    for (int i = 0; i <= n; i++)
        count[data[i]]++;
    for (int i = n - 1; i >= 0; i--) {
        tmp[count[data[i]] - 1] = data[i];
        count[data[i]]--;
    }
    for (int i = 0; i < n; i++)
        data[i] = tmp[i];
}*/
// thuat toan tim kiem
