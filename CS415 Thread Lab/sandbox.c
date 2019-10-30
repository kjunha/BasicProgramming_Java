#include<stdio.h>
#include<stdlib.h>
#include<pthread.h>
#include<semaphore.h>

sem_t sum_lock;

int main() {
	if (sem_init(&sum_lock,0,2) < 0) {
    	printf("Error in sem_init, sum_lock\n");
    	perror("sem_init");
	}
	int a;
	sem_getvalue(&sum_lock, &a);
	printf("semaphore: %d\n", a);
	return 0;
}
