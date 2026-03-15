//5. Write a program to check whether a person is eligible to vote (age ≥ 18).
#include<stdio.h>

void checkVote(int *age);   

void main()
{
    int age;

    printf("Enter age: ");
    scanf("%d",&age);
    checkVote(&age);   
}
void checkVote(int *age)
{
    if(*age >= 18)
        printf("Person is eligible to vote");
    else
        printf("Person is NOT eligible to vote");
}