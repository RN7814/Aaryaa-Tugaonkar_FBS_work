#include<stdio.h>
void sing();
void main(){
    sing();

}
void sing(){
    char a='a';
    printf("Enter a Character: ");
    scanf(" %c", &a); 


	printf("%c\n",a);
if (a == 'a' || a == 'e' || a == 'i' || a == 'o' || a == 'u' ||a == 'A' || a == 'E' || a == 'I' || a == 'O' || a == 'U')
	{
		printf("the char is Vowel");
		
	}else{
		printf("the char is Consonant");
	}

}