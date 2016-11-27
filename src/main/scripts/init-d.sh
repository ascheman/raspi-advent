#!/bin/sh

### BEGIN INIT INFO
# Provides:          raspi-advent
# Required-Start:
# Required-Stop:
# Default-Start:     2 3 4 5
# Default-Stop:      0 1 6
# Should-Start:
# Should-Stop:
# Short-Description: start RaspberryPi Advent daemons
### END INIT INFO


# Defaults
RASPI_ADVENT=/home/ascheman/wrk/raspi-advent/src/main/scripts/raspi-advent

# Reads config file (will override defaults above)
#[ -r /etc/default/raspi-advent ] && . /etc/default/raspi-advent

PIDDIR=/var/run/raspi-advent
PIDFILE=${PIDDIR}/raspi-advent.pid

export PIDFILE

# clear conflicting settings from the environment
unset TMPDIR

# See if the daemons are there
if ! test -x ${RASPI_ADVENT}; then
    echo "'${RASPI_ADVENT}' for Advent does not exist!" >&2
    exit 1
fi

. /lib/lsb/init-functions

case "$1" in
	start)
		log_daemon_msg "Starting raspi-advent daemons"
		# Make sure we have our PIDDIR, even if it's on a tmpfs
		install -o root -g root -m 755 -d $PIDDIR

    	if ! start-stop-daemon --start --quiet --oknodo --pidfile ${PIDFILE} --exec ${RASPI_ADVENT} -- -D
		then
			log_end_msg 1
			exit 1
		fi


		log_end_msg 0
		;;
	stop)
		log_daemon_msg "Stopping raspi-advent daemons"

		start-stop-daemon --stop --quiet --pidfile ${PIDFILE}
		# Wait a little and remove stale PID file
		sleep 1
		if [ -f ${PIDFILE} ] && ! ps h `cat ${PIDFILE}` > /dev/null
		then
			# Stale PID file (nmbd was succesfully stopped),
			# remove it (should be removed by nmbd itself IMHO.)
			rm -f ${PIDFILE}
		fi


		log_end_msg 0

		;;
	restart|force-reload)
		$0 stop
		sleep 1
		$0 start
		;;
    status)
		status="0"
		status_of_proc -p ${PIDFILE} ${RASPI_ADVENT} raspi-advent || status=$?
		exit $status
		;;
	*)
		echo "Usage: /etc/init.d/raspi-advent {start|stop|reload|restart|force-reload|status}"
		exit 1
		;;
esac

exit 0
