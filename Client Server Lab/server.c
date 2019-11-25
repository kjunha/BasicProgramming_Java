// Server side C/C++ program to demonstrate Socket programming 
#include <unistd.h> 
#include <stdio.h> 
#include <sys/socket.h> 
#include <stdlib.h> 
#include <netinet/in.h> 
#include <string.h> 
#include <pthread.h>
#include <semaphore.h>
#define PORT 8080

void *transferFile(void *args);

int main(int argc, char const *argv[]) 
{ 
	int server_fd, new_socket, valread; 
	struct sockaddr_in address; 
	int opt = 1; 
	int addrlen = sizeof(address); 
	pthread_t request_th;
	
	// Creating socket file descriptor 
	if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0) 
	{ 
		perror("socket failed"); 
		exit(EXIT_FAILURE); 
	} 
	
	// Forcefully attaching socket to the port 8080 
	if (setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, 
												&opt, sizeof(opt))) 
	{ 
		perror("setsockopt"); 
		exit(EXIT_FAILURE); 
	} 
	address.sin_family = AF_INET; 
	address.sin_addr.s_addr = INADDR_ANY; 
	address.sin_port = htons( PORT ); 
	
	// Forcefully attaching socket to the port 8080 
	if (bind(server_fd, (struct sockaddr *)&address, 
								sizeof(address))<0) 
	{ 
		perror("bind failed"); 
		exit(EXIT_FAILURE); 
	} 
	if (listen(server_fd, 3) < 0) 
	{ 
		perror("listen"); 
		exit(EXIT_FAILURE); 
	}
	while(1)
	{
    	if ((new_socket = accept(server_fd, (struct sockaddr *)&address, 
    					(socklen_t*)&addrlen))<0) 
    	{ 
    		perror("accept"); 
    		exit(EXIT_FAILURE); 
    	} 
    	if(pthread_create(&request_th, NULL, &transferFile, (void*)&new_socket))
    	{
    		printf("Create Failed");
    	}
	}
	return 0; 
}

void *transferFile(void *args)
{
	int socket = *((int*) args);
	FILE *fileptr;
	char *buffer;
	long filelen;
	char *directory = (char*)malloc(sizeof(char)*1024);
	read(socket, directory, 1024);
	printf("Request Received: %s\n", directory);
	fileptr = fopen(directory, "rb");
	fseek(fileptr, 0, SEEK_END);
	filelen = ftell(fileptr);
	rewind(fileptr);
	buffer = (char *)malloc((filelen+1)*sizeof(char));
	fread(buffer, filelen, 1, fileptr); 
	fclose(fileptr);
	send(socket, &filelen, sizeof(long), 0);
	for(long i = 0; i < filelen; i++)
	{
		send(socket, &buffer[i], sizeof(char), 0);
	}
	printf("size: %ld\n", filelen);
	printf("message: %s\n", buffer);
}
