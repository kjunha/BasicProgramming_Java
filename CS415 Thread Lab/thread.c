#include<stdio.h>
#include<stdlib.h>
#include<pthread.h>
#include<semaphore.h>

void *thread_function(void *arg);
void *append(int* buf, int index, int a);
int *take(int* buf, int index);

int global_a;
int global_b;
int buf_size = 3;
sem_t sum_lock, con_lock, buf_lock; //Create Semaphore (producer limit, consumer limit, buffer size)
int *productBuffer;

char* doPartA(long id){
	sem_wait(&sum_lock);
	if(id % 2 == 0 ) {
		sem_post(&sum_lock);
		return "I am a producer";
	} else {
		sem_post(&sum_lock);
		return "I am a consumer";
	}
}

int doPartB(long id){
	sem_wait(&sum_lock);
	if(id % 2 == 0 ) {
		global_a += 1;
		usleep(rand()%13);
		global_b += 2;
		usleep(rand()%22);
	} else {
		global_b += 3;
		usleep(rand()%11);
		global_a += 1;
		usleep(rand()%17);
	}
	sem_post(&sum_lock);
	return 0;
}
int doProducerPartC(long id) {
	for(int i = 0; i < 2; i++) {
		//Produce a random number
		int p = rand() % 100;
		//Enter Buffer Critical Section
		sem_wait(&buf_lock);
		//A Producer is in the main thread
		sem_wait(&sum_lock);
		//append to buffer
		int index;
		sem_getvalue(&buf_lock, &index);
		productBuffer[index] = p;
		printf("TID %ld: Buffer index %d <== %d is produced.\n", id, index, p);
		//random sleep after produce
		usleep(rand()%100);
		//Buffer[index] is now ready to use
		sem_post(&sum_lock);
		//Let consumer the main process
		sem_post(&con_lock);
	}
}

int doConsumerPartC(long id) {
	for(int i = 0; i < 2; i++) {
		//
		sem_wait(&con_lock);
		sem_wait(&sum_lock);
		//take from buffer
		int index;
		sem_getvalue(&buf_lock, &index);
		printf("TID %ld: Buffer index %d ==> %d is consumed.\n", id, index, productBuffer[index]);
		productBuffer[index] = 0;
		//random sleep after comsume
		usleep(rand()%100);
		//Consumer Exit
		sem_post(&sum_lock);
		//Buffer[index] is now empty
		sem_post(&buf_lock);
	}
}

int main(int argc, char **argv) {
	int num_threads;
	pthread_t *threads; //Threads pointer
	long *tid; //Thread ids
	productBuffer = (int*)calloc(buf_size, sizeof(int));
	
	//Usage error check
	if (argc<2) {
		printf("Usage: %s [NUMBER OF THREADS]\n", argv[0]);
		return 0;
	}
	
	num_threads = atoi(argv[1]);
	printf("Launching %i threads\n", num_threads);
	//generate threads memory location (Heap)
	threads = (pthread_t*) calloc(num_threads,sizeof(pthread_t));
	tid = (long*) calloc(num_threads,sizeof(long));
	 
	//init semaphore with semaphore (sum_lock, which is sem_t)
	//Second 0 means this thread only shared through this process, Linux doesn't support otherwise.
	if (sem_init(&sum_lock,0,1) < 0) {
		printf("Error in sem_init, sum_lock\n");
		perror("sem_init");
		return 0;
	}
	//init comsumer semaphore to 0, when things are produced, producer will post it.
	if (sem_init(&con_lock,0,0) < 0) {
		printf("Error in sem_init, con_lock\n");
		perror("sem_init");
		return 0;
	}
	//init product buffer semaphore for bounded buffer critical section.
	if (sem_init(&buf_lock,0,buf_size) < 0) {
		printf("Error in sem_init\n");
		perror("sem_init");
		return 0;
	}
	
	long i;
	int pcr;
	for (i=0;i<num_threads;i++) {
		tid[i]=i;
		//Do thread function
		if ( (pcr=pthread_create(&threads[i],NULL,&thread_function, (void*)i))) {
			printf("Create Failed: %ld",i);
		}
	}
	//print answer for Part A
	printf("###### Part A and C ######\n");
	// Checks if all thread is done their job.
	for (i=0;i<num_threads;i++) {
		pthread_join(threads[i],NULL);
	}
	
	sem_destroy(&sum_lock);
	sem_destroy(&con_lock);
	sem_destroy(&buf_lock);
	
	//Print answer for part B and C
	printf("###### Part B ######\n");
	printf("A: %d\nB: %d\n", global_a, global_b);
	
	
	free(threads);
	free(tid);
}

void *thread_function(void *arg) {
	long my_id = (long) arg;
	//Thread id: printf("ID: %ld\n",my_id);
	int i,tmp;
	
	// for(i=0;i<100; i++) {
	// 	tmp = sum;
	// 	usleep(10000);
	// 	tmp = tmp+1;
	// 	sum = tmp;
	// }
	printf("TID %ld: %s\n", my_id, doPartA(my_id));
	for(i = 0; i < 5; i++) {
		doPartB(my_id);
	}
	if(my_id % 2 == 0) {
		doProducerPartC(my_id);
	} else {
		doConsumerPartC(my_id);
	}
}





