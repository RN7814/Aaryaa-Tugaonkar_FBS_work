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
	int capacity=1;    
    player *set=(player*)malloc(sizeof(player)*capacity);
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
        printf("\n0. To Exit\n");
        
        printf("Enter a Input :- ");
        scanf("%d",&in);

        if(in==0)
            {
                printf("\nExiting....");
            }
else if(in==1)
{
	
    if(count >= capacity)
	{
    capacity = capacity + 1;
    set = (player*)realloc(set, sizeof(player) * capacity);

    if(set == NULL)
    {
        printf("Memory allocation failed!\n");
        exit(1);
    }

    printf("Capacity increased to %d\n", capacity);
	}

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

void searchplayer (player set[],int count)
{
	int pp;
	printf("Enter no of Jersey of Player :");
	scanf("%d",&pp);
	
	for(int i=0; i<count; i++)
	{
	if(set[i].Jno == pp)
			{
	
			printf("Player found!!\n");
			printf("Player jersey NO :- %d",set[i].Jno);
			printf("Player name :- %s",set[i].Pname);
			printf("Player Runs:- %d ,mathces Played",set[i].runs);
			printf("Player Wickets :- %d",set[i].wickets);
			printf("Player Matches Played :- %d",set[i].mplayed);
			return;
			}
			
	}
	printf("Player Not Found\n");
}

void updateplayer (player set[],int count)
{
	int pp;
	printf("\nEnter the player jersey no :");
	scanf("%d",&pp);
	int found =0;
	
	for(int i=0; i<count; i++)
		{
		if(pp==set[i].Jno)
			{
				found=1;
			printf("\n1. Jersey NO :-");
			scanf("%d",&set[i].Jno);
			printf("\n2. Player Name:-");
			scanf("%s",set[i].Pname);
			printf("\n3. Wickets :-");
			scanf("%d",&set[i].wickets);
			printf("\n4. Runs :-");
			scanf("%d",&set[i].runs);
			printf("\n5. Matches Played");
			scanf("%d",&set[i].mplayed);
			
			printf("Player Data Updated\n");
				
			return;
			
			}
			
		}
	if(found==0)
	printf("Player Not Found\n");	
}

void sortplayer (player set[],int count)
{
	printf("\n How do you want to Sort :- \n\t1.Max Runs\n\t2.Min Runs\n\t3.Max Wickets\n\t4.Min Wickets");
	int in;
	scanf("%d",&in);
	int index=0;
	
	switch (in)
	{
		case 1:
			
			printf("Based on Max Runs");
			
			for(int i=0;i<count;i++)
			{
				if(set[i].runs>set[index].runs)
				{
				
				index=i;
				}
			}
			printf("Top Player (Max Runs): %s | \t | %d\n", set[index].Pname, set[index].runs);

		break;
			
		case 2:
			printf("Based on the Minimum Runs");
			
			for(int i=0;i<count;i++)
			{
				if(set[i].runs < set[index].runs)
				{
					index=i;

				}
			}            
			printf("Top Player (Min Runs): %s | \t | %d\n", set[index].Pname, set[index].runs);
			break;
			
		case 3:
			printf("Based on the Max Wickets");
			
			for(int i=0;i<count;i++)
			{
				if(set[i].wickets > set[index].wickets)          //Max Wickets
				{
					index=i;

				}
			}
			printf("Top Player (Max Wickets): %s | \t | %d\n", set[index].Pname, set[index].wickets);

		break;
			
			
			
		case 4:
			printf("Based on the Min Wickets");
			
			for (int i=0 ; i<count ; i++)
			{
				if(set[i].wickets < set[index].wickets)
				index=i;
			}
			printf("Top Player (Min Wickets): %s | \t | %d\n", set[index].Pname, set[index].wickets);
		break;
		
		
		default:
			printf("Wrong Option !!!!!");
	}	
}

void display(player set[],int count)
{
	printf("||| Player Roaster |||");
	for(int i=0 ; i<count ; i++)
	{
		printf("\n\nPlayer Name :- %s",set[i].Pname);
		printf("\nPlayer Jersey :- %d",set[i].Jno);
		printf("\nPlayer Runs :- %d",set[i].runs);
		printf("\nPlayer Wickets :- %d",set[i].wickets);
		printf("\nPlayer Matches :- %d",set[i].mplayed);
	}
	printf("\n||| Complete |||");
}




