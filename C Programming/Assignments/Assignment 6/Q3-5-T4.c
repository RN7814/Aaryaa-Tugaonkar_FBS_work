#include<stdio.h>
#include<math.h>

int armstrong(int no);   // Type 4

void main(){
int no;
printf("Enter a no : "); //153
scanf("%d",&no);

int tell=armstrong(no);

if(tell==1)
printf("Armstrong Number");
else
printf("Not Armstrong");
}

int armstrong(int no){
int tno=no,rem,count=0,sum=0,temp=no;

while(temp!=0){
count++;
temp=temp/10;
}

while(no!=0){
rem=no%10;
sum=sum+pow(rem,count);
no=no/10;
}

if(sum==tno)
return 1;
else
return 0;
}