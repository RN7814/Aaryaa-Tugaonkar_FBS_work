//Player Attributes :-Jersey Number, Player Name, 
//Runs, Wickets, and Matches played


//Display Sorted Players: The system should provide a feature to display 
//the sorted player information based on minimum/maximum runs and 
//minimum/maximum wickets, allowing users to see the leading 
//performers in the team.





#include<stdio.h>
#include<string.h>

typedef struct player {

    int Jno;
    int count;
    char Pname[100];
    int runs;
    int wickets;
    int mplayed;
}player;



void main()
{
    
    player *set=(player*)malloc(sizeof(player)*11);
    int in;
    set->count=0;

    while(in!=0)
    {
        printf("\n1. Add Player");
        printf("\n2. Remove Player");
        printf("\n3. Search Player");
        printf("\n4. Update Player");
        printf("\n5. Sort Player");
        printf("\n6. Display Player");

        scanf("%d\n",&in);

        if(in==1)
        {
            addplayer(set);
            set->count++;
        }

        if(in==2)
        {
            removeplayer(set)
            set->count--;
        }

        if(in==3)
        {
            searchplayer(set);

        }

        if(in==4)
        {
            updateplayer(set);
        }



    }
    
}
void addplayer(player *set)
{
   
    printf("Enter the Name of Player :");
    scanf("%s\n",set->Pname);

    printf("Enter the Jercey NO :");
    scanf("%d\n",set->Jno);

    printf("Enter Player Runs :");
    scanf("%d\n",set->runs);

    printf("Enter Player Wickets :");
    scanf("%d\n",set->wickets);

    printf("Enter Player Matches Played :");
    scanf("%d\n",set->mplayed);

    

}