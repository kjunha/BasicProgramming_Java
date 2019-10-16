#define _CRT_SECURE_NO_WARNINGS
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include<dirent.h>
#include<errno.h>

//Function getting subdirecories from current dir.
char** getSubdir(char *address) {
  DIR *dir = NULL;
  struct dirent *entry = NULL;
  if((dir = opendir(address)) == NULL) {
    printf("Current Dir Error");
    return NULL;
  }
  char** subdir = (char**)calloc(sizeof(char*), 50);
  int diridx = 0;
  while((entry = readdir(dir)) != NULL) {
    subdir[diridx] = (char*)calloc(sizeof(char), sizeof(entry->d_name));
    strcpy(subdir[diridx], entry->d_name);
    diridx++;
  }
  free(dir);
  free(entry);
  return subdir;
}

//Shall Machine Process
//1. Read command line from stdin
//2. parse it and read first character
//3. toggle the switch for each command (1)
//4. do the job and turn off the switch (0)
//5. ask for next line

int main(int argc, char const *argv[]) {
  //Main loop switch
  int sw = 1;

  //Directory Tree
  //1. First item (index 0) is name of the directory.
  //2. Second item is double pointer (list) of subdirectories.
  char strBuffer[200] = {0,};
  char *current = NULL;
  current = getcwd(strBuffer, 200);
  int location = -1;
  while (sw == 1) {
    //get user input
    printf("Shell: ~%s/$ ", current);
    char *input = malloc(sizeof(char) * 100);
    input = fgets(input, 100, stdin);
    input[strlen(input)-1] = '\0';
    char *tokens = strtok(input, " ");
    char **args = (char**)calloc(100, sizeof(char*));
    int tokcnt = 0;

    //input is null, skip!
    if(tokens == NULL) {
      //Do Nothing
    } else {
      //save tokens as areguement
      while (tokens != NULL) {
        args[tokcnt] = (char*)calloc(strlen(tokens), sizeof(char));
        strcpy(args[tokcnt], tokens);
        tokcnt++;
        tokens = strtok(NULL, " ");
      }
      //command logic part
      //echo: print all areguement after echo
      if(strcmp(args[0], "echo") == 0){
        int word = 1;
        char *echoed = malloc(sizeof(char) * 100);
        strcpy(echoed, "echo: ");
        while(args[word] != NULL) {
          strcat(echoed, args[word]);
          strcat(echoed, " ");
          word++;
        }
        printf("%s\n", echoed);
        free(echoed);
      } else if(strcmp(args[0], "exit") == 0) {
          printf("....Session Logout\n");
          sw = 0;
      //cd: change working directory, if error, print error message, if .., move one up
      } else if(strcmp(args[0], "cd") == 0) {
        char **subdir = getSubdir(current);
        int i = 0;
        while(subdir[i] != NULL) {
          if(strcmp(args[1], "..") == 0) {
            chdir("..");
            current = getcwd(strBuffer, 200);
            break;
          } else if(strcmp(args[1], subdir[i]) == 0){
            printf("target: %s\n", subdir[i]);
            strcat(current,"/");
            strcat(current, subdir[i]);
            if(chdir(current) == -1) {
              printf("Can't change directory.\n");
              current = getcwd(strBuffer, 200);
            }
            break;
          }
          i++;
        }
        free(subdir);
        
      //ls: list of directory in current directory
      } else if(strcmp(args[0], "ls") == 0){
        char **subdir = getSubdir(current);
        int i = 0;
        while(subdir[i] != NULL) {
          printf("%s\n", subdir[i]);
          i++;
        }
        free (subdir);
        
      //mkdir: make a new folder
      } else if(strcmp(args[0], "mkdir") == 0){
        strcat(current, "/");
        strcat(current, args[1]);
        if(mkdir(current, 0775) == 0) {
          printf("New folder successfully created! : %s\n", args[1]);
          current = getcwd(strBuffer, 200);
        } else {
          printf("Failed to create new folder.");
          current = getcwd(strBuffer, 200);
        }
      
      //remove a folder when the folder is empty
      } else if(strcmp(args[0], "rmdir") == 0){
        strcat(current, "/");
        strcat(current, args[1]);
        if(rmdir(current) == 0) {
          printf("Following folder has been deleted! : %s\n", args[1]);
          current = getcwd(strBuffer, 200);
        } else {
          printf("Failed to delete a folder.\n");
          current = getcwd(strBuffer, 200);
        }
        
      //./: complie and run cpp program.
      } else if(args[0][0] == '.', args[0][1] == '/'){
        char **subdir = getSubdir(current);
        char *cppname = malloc(sizeof(char) * 100);
        int i = 2;
        while(args[0][i] != '\0') {
          cppname[i-2] = args[0][i];
          i++;
        }
        cppname[i-1] = '\0';
        i = 0;
        while(subdir[i] != NULL) {
          if(strcmp(subdir[i], cppname) == 0) {
            char *command = malloc(sizeof(char) * 100);
            strcpy(command, "g++ ");
            strcat(command, cppname);
            system(command);
            strcpy(command, "./a.out ");
            i = 1;
            while(args[i] != NULL) {
              strcat(command, args[i]);
              strcat(command, " ");
              i++;
            }
            system(command);
            free(command);
            break;
          }
          i++;
        }
        
        free(cppname);
        free(subdir);
      //Not a registered commeand
      }else {
        printf("Cannot Resolve symbol: %s\n", args[0]);
      }
    }

    //free all pointers used for every looping
    for(int i = 0; i < 100; i++) {
      free(args[i]);
    }
    free(input);
    free(args);
    free(tokens);
  }
  return 0;
}