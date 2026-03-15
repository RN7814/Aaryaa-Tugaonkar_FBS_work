//6. Write a program to check whether a given character is uppercase or lowercase.
#include<stdio.h>

void checkCase(char *ch);   // function declaration

void main()
{
    char ch;

    printf("Enter a character: ");
    scanf("%c",&ch);

    checkCase(&ch);   // passing address
}

// function definition
void checkCase(char *ch)
{
    if(*ch >= 'A' && *ch <= 'Z')
        printf("Character is Uppercase");
    else if(*ch >= 'a' && *ch <= 'z')
        printf("Character is Lowercase");
    else
        printf("Not an alphabet");
}