#include <strings.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <stdarg.h>
#ifdef __linux__
#include <malloc.h>
#endif
#include <sys/types.h>
#include <unistd.h>


#define errExit(msg)    do { perror(msg); exit(EXIT_FAILURE); \
                        } while (0)



int main (int argc, char * * argv)
{

	printf ("###### Debut de mon application.\n");


	printf ("###### Essai de lecture de /dev/random.\n");
	unsigned char buffer[10];
	FILE *fptr_r;
	fptr_r = fopen("/dev/random","rb");  // r for read, b for binary
	fread(buffer,sizeof(buffer),1,fptr_r); // read 10 bytes to our buffer

	printf ("###### Affichage des octets lus.\n");
	printf ("buffer: ");
	for(int i = 0; i<10; i++)
    		printf("[%d]=%u ", i,buffer[i]); // prints a series of bytes
	printf("\n");


	printf ("###### Essai d'ecriture dans /dev/null.\n");
	FILE *fptr_w;
	fptr_w = fopen("/dev/null","wb");  // w for write, b for binary
	fwrite(buffer,sizeof(buffer),1,fptr_w); // write 10 bytes from our buffer


	int mypid = getpid();
	FILE *fp;
	char filename[1024];
	char bufLine[1024];
	sprintf (filename,"/proc/%d/cmdline",mypid);
	printf ("###### Essai d'ecriture haut niveau dans %s.\n",filename);
	fp = fopen(filename, "r");
	if(fp == NULL) {
        	printf("Error opening file\n");
        	exit(1);
    	}
	while( fscanf(fp, "%s", bufLine) != EOF ) {
		printf ("buffer vide=%s\n",bufLine);
	}
	sleep (2);

	int nbBlocks = 512;
	//int nbBlocks = 1536;
	int blockSize= 1024 * 1024;
	printf ("###### malloc de %d blocs de %d octets.\n",nbBlocks,blockSize);
	unsigned char * * arrayOfAlloc;
	if ( ( arrayOfAlloc = (unsigned char * *) malloc (nbBlocks*sizeof(unsigned char*)) ) == NULL)
                   errExit("malloc-arrayOfRef");
	for (int j=0; j<nbBlocks; j++) {
		if ( ( arrayOfAlloc[j] = (unsigned char *) calloc (1,blockSize*sizeof(unsigned char)) ) == NULL)
			errExit("malloc-onecell");
		memset (arrayOfAlloc[j], (j+1)%256 , blockSize*sizeof(unsigned char));
		#ifdef __GLIBC__
			printf("taille du bloc %d: %d\n", j+1, malloc_usable_size(arrayOfAlloc[j]));
		#endif
		printf ("calloc info   du bloc %3d: ",j+1);
		#ifdef __GLIBC__
			malloc_info(0, stdout);
		#endif
		printf ("\n");
	}
	
	printf ("###### wait 20s.\n");
	sleep (20);
	exit (0);
}

