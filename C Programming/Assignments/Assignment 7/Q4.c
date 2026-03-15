//4. Write a program to check whether a given character is a vowel or consonant.
#include<stdio.h>

void checkVowel(char *ch);   // function declaration

void main()
{
    char a='b';
    printf("%c\n",a);
    checkVowel(&a);  
}

void checkVowel(char *ch)
{
    if (*ch == 'a' || *ch == 'e' || *ch == 'i' || *ch == 'o' || *ch == 'u' ||
        *ch == 'A' || *ch == 'E' || *ch == 'I' || *ch == 'O' || *ch == 'U')
    {
    printf("the char is Vowel");
    }
    else
    printf("the char is Consonant");
    
}