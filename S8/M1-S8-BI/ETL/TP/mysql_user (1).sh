#!/bin/bash

#set -x
#set -v

echo setting config variables ...
CFGFILE="$HOME/.my.cnf"
TEMPLATE="./my.cnf.miage.template"
#PORT="13306"
PORT=$(( 10000 + ( $RANDOM % 1000 ) ))
echo setting file variables ...
DBDIR="$HOME/mariadb"
#BASEDIR="/usr"
PIDFILE="$DBDIR/mariadb.pid"
LOGFILE="$DBDIR/mariadb.log"
SOCKFILE="$DBDIR/mariadb.sock"
echo setting script variables ...
STARTSCRIPT="$DBDIR/mariadb_start.sh"
STOPSCRIPT="$DBDIR/mariadb_stop.sh"
echo setting password/db variables ...
ROOTPWD="rootpwd"
USERDB="miage"
USERDBNAME="${USERDB}usr"
USERDBPWD="${USERDB}pwd"


MDBPID=`cat $PIDFILE 2>/dev/null`
REGEXPNB='^[0-9]+$'
if [[ "${MDBPID}" =~ $REGEXPNB ]] ; 
then
    if [ "$MDBPID" -gt 0 ]
    then
	echo pid file contain not null number ... trying to SIGKILL process "'"${MDBPID}"'".
	kill -SIGKILL ${MDBPID}
    fi
fi


echo clean old files and restart from scratch ...
rm -rf "$DBDIR" "$CFGFILE"
mkdir -p "$DBDIR"
chmod u=rwX,go-rwx "$DBDIR"


echo create ~/.my.cnf from template
#sed "s|~~~~BASEDIR~~~~|$BASEDIR|g;"|  \
cat "$TEMPLATE" | \
    sed "s/~~~~PORT~~~~/$PORT/g;" | \
    sed "s|~~~~SOCKET~~~~|$SOCKFILE|g;" | \
    sed "s|~~~~DATADIR~~~~|$DBDIR|g;" | \
    sed "s|~~~~LOGERROR~~~~|$LOGFILE|g;" | \
    sed "s|~~~~PIDFILE~~~~|$PIDFILE|g;" \
	> "$CFGFILE"


echo create mysql base data files ...
#mysql_install_db \
#    --auth-root-authentication-method=normal \
#    --datadir="$DBDIR" \
#    --basedir="$BASEDIR"
mysql_install_db 


echo launching database instance for first config ...
#mysqld_safe \
#    --log-error="$LOGFILE" \
#    --datadir="$DBDIR" \
#    --pid-file="$PIDFILE" &
mysqld_safe &
MDBPID="$!"


echo wait for 5 seconds ...
sleep 5


echo set root password ...
#'/usr/bin/mysqladmin'
mysqladmin -u root password "$ROOTPWD"
mysqladmin -u root --password="$ROOTPWD" -h localhost.localdomain password "$ROOTPWD"


echo create user database and purge annoying databases ...
mysql --user=root --password="$ROOTPWD" <<EOF
CREATE DATABASE $USERDB;
CREATE USER '$USERDBNAME'@'localhost' IDENTIFIED BY '$USERDBPWD';
GRANT ALL PRIVILEGES ON $USERDB.* TO '$USERDBNAME'@'localhost';
CREATE USER '$USERDBNAME'@'%' IDENTIFIED BY '$USERDBPWD';
GRANT ALL PRIVILEGES ON $USERDB.* TO '$USERDBNAME'@'%';
FLUSH PRIVILEGES;
DROP DATABASE test;
EOF


echo shutdown database ...
mysqladmin -u root --password="$ROOTPWD" shutdown
wait "$MDBPID"

echo creating start/stop scripts ...
cat >"$STARTSCRIPT" <<EOF
#!/bin/bash
echo STARTING MARIADB user database ....
#mysqld_safe \\
#    --log-error="$LOGFILE" \\
#    --datadir="$DBDIR" \\
#    --pid-file="$PIDFILE" &
mysqld_safe &
MDBPID=\$!
echo Mysql launched with pid "\$MDBPID".
sleep 3
ps -p \$MDBPID 1>/dev/null 2>&1 || echo WARNING: Mariadb seems already DEAD !!!
echo "In case of warning(s)/error(s), please see '$LOGFILE'."
EOF
chmod u+rwx,go-rwx "$STARTSCRIPT"
cat >"$STOPSCRIPT" <<EOF
#!/bin/bash
echo STOPPING MARIADB user database ...
MDBPID=\`cat $PIDFILE 2>/dev/null\`
mysqladmin -u root --password="$ROOTPWD" shutdown
REGEXPNB='^[0-9]+$'
if [[ "\$MDBPID" =~ \$REGEXPNB ]] ; 
then
    if [ "\$MDBPID" -gt 0 ]
    then
	echo waiting again 5 seconds.
	sleep 5
	echo testing for mariadb process \$MDBPID after shutdown.
	ps -p \$MDBPID 1>/dev/null 2>&1 && (
	   echo WARNING: Mariadb STILL seems ALIVE !!!
	   echo waiting again 5 seconds the SIGKILL process.
	   kill -SIGKILL \$MDBPID
	   )
     fi
fi
echo "In case of warning(s)/error(s), please see '$LOGFILE'."
EOF
chmod u+rwx,go-rwx "$STOPSCRIPT"
chmod -R go-rwx "$DBDIR"

echo mariadb created. Infos:
echo ---- datadir: $DBDIR
echo ---- cfg template used: $TEMPLATE
echo ---- cfgfile generated: $CFGFILE
echo ---- mariadb network: will listen on "0.0.0.0:$PORT"
echo ---- mariadb socket: $SOCKFILE
echo ---- MASTER / ROOT user: root
echo ---- MASTER / ROOT password: $ROOTPWD
echo ---- user database name: $USERDB
echo ---- username for database: $USERDBNAME
echo ---- password for database: $USERDBPWD
echo ---- script to start: $STARTSCRIPT
echo ---- script to stop: $STOPSCRIPT
echo
