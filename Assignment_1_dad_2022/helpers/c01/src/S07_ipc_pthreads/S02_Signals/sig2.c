#include <unistd.h>
#include <signal.h>
#include <errno.h>
#include <sys/types.h>

void sighandler(int sig)
{
	pid_t pid;
	if ((pid = fork()) < 0) {
		perror("err la fork");
		exit(1);
	}

	if(!pid) { 
		execl("/bin/less", "less", "test.txt", NULL);
		perror("exec()");
		exit(1);
	} else {
		if(wait(NULL) < 0)
			perror("wait()");
	}
}

int main()
{
	if(signal(SIGUSR2, sighandler) == SIG_ERR) {
		perror("err cu semnalizarea");
		return 1;
	}

	pause();
	return 0;
}
