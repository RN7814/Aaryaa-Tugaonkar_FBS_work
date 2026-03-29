//3. Develop a program to manage a music library. Define a structure to 
//store song details (song ID, title, artist, and duration). Create a menu
//driven program to add new songs and display the song library. Use 
//functions to handle these operations.

#include<stdio.h>

typedef struct song
{
	int sid;
	char stitle[50];
	char sartist[50];
	double duration;
}song;

void addSong( song s1[], int *count);
void displaySongs(song s1[],int count);





void main()
{
	song s1[10];
	int count=0,choise=0;
	while(choise!=3)
	{
	
	printf("\n1. TO ADD SONG \n");
	printf("2. DISPLAY Libary\n");
	printf("3. Exit");
	printf("\nEnter NO :");
	scanf("%d",&choise);

	
	if(choise==1){
	
	addSong(s1,&count);
	
	}
	else if(choise ==2){
	displaySongs(s1,count);
	
	}else if(choise ==3){
	printf("Exiting program..");
	
	}
	else
	printf("Invalid Option");
	
}
}

void displaySongs(song s1[],int count)
{
	if(count==0)
	{
		printf("\nNo songs\n");
	}
	
	printf("Song list :- \n");
	for(int i=0; i< count; i++)
	{
		printf("ID : %d\n",s1[i].sid);
		printf("Title : %s\n",s1[i].stitle);
		printf("Artist : %s\n",s1[i].sartist);
		printf("Duration : %f\n",s1[i].duration);
	}
}

void addSong( song s1[], int *count)
{
	printf("\nEnter a Song id :");
	scanf("%d", &s1[*count].sid);
	
	printf("\nEnter Song Title :");
	scanf("%s",&s1[*count].stitle);
	
	printf("\nEnter Song Artist :");
	scanf("%s",s1[*count].sartist);
	getch();
	printf("\nEnter Song Duration :");
	scanf("%f",&s1[*count].duration);
	
	(*count)++;
	printf("\n\n\nSong added Sucessfully !!!!");
}