#!/usr/bin/bash
if ! [  -f ~/.my.cnf ] ; then
	echo "Le fichier .my.cnf n'existe pas"
	echo "Fin prématurée du script"
	exit 1
fi
if [ $USER = "guest" ]; then
	PORT=3306
else # pour un compte LDAP
	PORT=13306
fi
echo "Installation du port $PORT"
ed ~/.my.cnf <<EOF
/^port
s/^port.*/port    = $PORT/
/^port
s/^port.*/port    = $PORT/
w
q
EOF

echo "Création du dossier ~/www"
if ! [ -d ~/www ] ; then 
	mkdir ~/www 
else
	echo "Le dossier ~/www existe déjà"
fi

# On table sur r-x puur other pour www et la descendance
# La conséquence est l'accès à l'arborescence à partir de toutes les 
# machines du réseau via apache
# 0n peut bloquer avec chmod o-rx www ou sur tout élément de la descendance
if [ $USER = "guest" ]; then
	setfacl -m u:apache:x $HOME
else # pour un partage NFS
	nfs4_setfacl -a A::48:x $HOME
	#echo "ici ldap"
fi
