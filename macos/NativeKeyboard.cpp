#include <stdio.h>
#include <stdlib.h>
#include <termios.h>
#include <unistd.h>
#include <ctype.h>
#include <poll.h>
#include "NativeKeyboard.h"

struct termios orig_termios;

void disableRawMode() {
  tcsetattr(STDIN_FILENO, TCSAFLUSH, &orig_termios);
}


void enableRawMode2() {
  tcgetattr(STDIN_FILENO, &orig_termios);
  atexit(disableRawMode);
  struct termios raw = orig_termios;
  raw.c_lflag &= ~(ECHO | ICANON | IGNBRK|BRKINT);
  tcsetattr(STDIN_FILENO, TCSAFLUSH, &raw);
}

#define bufferSize 300

char buffer[bufferSize];
int cnt = -1;
int index=-1;

struct pollfd poll_s ={STDIN_FILENO,POLLIN,POLLIN};

JNIEXPORT jint JNICALL Java_us_bringardner_io_filesource_shell_NativeKeyboard_ready  (JNIEnv *, jobject){
	//enableRawMode2();
	 int             retval;
           fd_set          rfds;
           struct timeval  tv;

           /* Watch stdin (fd 0) to see when it has input. */

           FD_ZERO(&rfds);
           FD_SET(0, &rfds);

           /* Wait up to five seconds. */

           tv.tv_sec = 5;
           tv.tv_usec = 0;

           retval = select(1, &rfds, NULL, NULL, &tv);
           /* Don't rely on the value of tv now! */

           if (retval == -1)
               perror("select()");
           else if (retval)
               printf("Data is available now.\n");
               /* FD_ISSET(0, &rfds) will be true. */
           else
               printf("No data within five seconds.\n");
		return retval;
		
	/*
	printf("Before ready %d,%d\n",poll_s.fd,poll_s.events);
	int ret = poll(&poll_s, 1, 1);
	if( ret==1 && poll_s.revents == POLLIN) {
		printf("Before read %d,%d\n",poll_s.fd,poll_s.revents);
		cnt = read(STDIN_FILENO, &buffer, 3);
		if( cnt >0) {
			printf("After read %d\n",buffer[0]);
			cnt--;
			return buffer[0];
		};
	}
	
	printf("After ready=%d, %d\n",ret,poll_s.revents);
	return poll_s.revents;
	*/
}

int isReady() {
	 int             retval;
     fd_set          rfds;
     //struct timeval  tv;
	struct timespec tv;
       /* Watch stdin (fd 0) to see when it has input. */

       FD_ZERO(&rfds);
       FD_SET(0, &rfds);

       /* Wait up to five seconds. */

       tv.tv_sec = 1;
       tv.tv_nsec = 0;


       retval = pselect(1, &rfds, NULL, NULL, &tv,NULL);
       
       /* Don't rely on the value of tv now! 

       if (retval == -1)
           perror("select()");
       else if (retval)
           printf("Data is available now.\n");
       else
           printf("No data within five seconds.\n");
		*/
	return retval;
}

// Signal handler function
void signalHandler(int sig) {
    //cout << "Interrupt handle " << sig << endl;
      printf("Interrupt handle %d\n", sig);
      //write(2,"Interrupt handle \n",19);
    // Optionally exit the program after handling
    //exit(sig);
}

JNIEXPORT jint JNICALL Java_us_bringardner_io_filesource_shell_NativeKeyboard_getChar  (JNIEnv *, jobject){

  if( index < cnt ) {
	return buffer[index++];
  }
  
  /*
  for(int s =1; s <=64; s++) {
	signal(s, signalHandler);
  }
  */
  
  //signal(SIGINT, signalHandler);
  //signal(SIGTSTP, signalHandler);
  
  enableRawMode2();
  //cfmakeraw();

	int r = isReady();
  //printf("ready =%d\n",r);
  if( r==0) {
	return -1;
  }
  
  //printf("Before while\n");
  cnt = -1;
  while(cnt <= 0) {
	//printf("Before read\n");
	cnt = read(STDIN_FILENO, &buffer, bufferSize);
	//printf("After read cnt=%d\n",cnt);
  }
  index = cnt;
 // printf(" cnt = %d ch[0]=%d\n", cnt,buffer[0]);
  if( cnt == 3 ) {
	if( buffer[0] == 27 && buffer[1] == '[') {
		if(buffer[2] == 'A') {
			// up
			cnt=0;
			return 500;
		}
		if(buffer[2] == 'B') {
			// dn
			cnt=0;
			return 501;
		}
		if(buffer[2] == 'C') {
			// rt
			cnt=0;
			return 502;
		}
		if(buffer[2] == 'D') {
			//lf
			cnt=0;
			return 503;
		}
		if(buffer[2] == 'H') {
			//up
			cnt=0;
			return 504;
		}
		if(buffer[2] == '5') {
			//dn
			cnt=0;
			return 505;
		}
		if(buffer[2] == '6') {
			//lf
			cnt=0;
			return 506;
		}
		if(buffer[2] == '3') {
			//lf
			cnt=0;
			return 507;
		}
		if(buffer[2] == 'F') {
			//lf
			cnt=0;
			return 508;
		}
		printf("unknown escape code = %d\n", buffer[2]);
	}
  }
  index = 0;
  jint ret = buffer[index++];
  return ret;
  
  
}