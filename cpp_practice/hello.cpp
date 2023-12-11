// hello.cpp


#include <iostream>
using namespace std;

void calculateBMI(double height, double weight, double& BMI);

int main(int argc, char *argv[])
{
    int age;
    cout << "What is your age? ";
    cin >> age;

    double height;
    cout << "What is your height? ";
    cin >> height;

    double weight;
    cout << "What is your weight? ";
    cin >> weight;

    double BMI = 0;
    calculateBMI(height,weight,BMI);
    cout << "You're " << age << " years old and " << "your BMI is " << BMI << endl;

    if (BMI > 22)
    {
        cout << "You're a fatass" << endl;
    }
}

void calculateBMI(double height, double weight, double &BMI)
{
    BMI =  weight / (height/100) / (height/100);
}