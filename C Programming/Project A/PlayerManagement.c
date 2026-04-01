//Player Attributes :-Jersey Number, Player Name, 
//Runs, Wickets, and Matches played


//Display Sorted Players: The system should provide a feature to display 
//the sorted player information based on minimum/maximum runs and 
//minimum/maximum wickets, allowing users to see the leading 
//performers in the team.





#include<stdio.h>
#include<stdlib.h>
#include<string.h>

typedef struct player {

    int Jno;
    
    char Pname[100];
    int runs;
    int wickets;
    int mplayed;
}player;

void addplayer(player *set);
void removeplayer(player set[], int *count);
void searchplayer(player set[], int count);
void updateplayer(player set[], int count);
void sortplayer(player set[], int count);
void display(player set[], int count);


void main()
{
    
    player *set=(player*)malloc(sizeof(player)*11);
    int in=-1;
    int playercount=0;
    int count=0;

    while(in!=0)
    {
        printf("\n1. Add Player");
        printf("\n2. Remove Player");
        printf("\n3. Search Player");
        printf("\n4. Update Player");
        printf("\n5. Sort Player");
        printf("\n6. Display Player");
        printf("\n0. To Exit");
        scanf("%d",&in);

        if(in==0)
            {
                printf("Exiting....");
            }
else if(in==1)
{
    addplayer(&set[count]);
    count++;
}
else if(in==2)
{
    removeplayer(set, &count);
}
else if(in==3)
{
    searchplayer(set, count);
}
else if(in==4)
{
    updateplayer(set, count);
}
else if(in==5)
{
    sortplayer(set, count);
}
else if(in==6)
{
    display(set, count);
}
else
{
    printf("Wrong input");
}
    }
    
}
void addplayer(player *set)
{
   
    printf("Enter the Name of Player :");
    scanf("%s",set->Pname);

    printf("Enter the Jercey NO :");
    scanf("%d",&set->Jno);

    printf("Enter Player Runs :");
    scanf("%d",&set->runs);

    printf("Enter Player Wickets :");
    scanf("%d",&set->wickets);

    printf("Enter Player Matches Played :");
    scanf("%d",&set->mplayed);

    printf("Player Added Successfully !!");

}

void removeplayer(player set[], int *count)
{
    int rp;
    printf("Enter the Jersey NO of Player to remove: ");
    scanf("%d", &rp);

    for(int i = 0; i < *count; i++)
    {
        if(set[i].Jno == rp)
        {
            // shift elements left
            for(int j = i; j < *count - 1; j++)
            {
                set[j] = set[j + 1];
            }

            (*count)--;
            printf("Player removed successfully!\n");
            return;
        }
    }

    printf("Player not found!\n");
}