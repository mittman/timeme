#!/bin/bash
#######################
### gitcommits-pdf ####
#######################
### Team 0x00000001 ###
#######################

shared="/usr/share/gitbook";
path="/dev/shm/commits";

if [ -z  "$1" ];
then echo "USAGE: $(basename $0) [template] [title] {[NONE]|[PATH]}";
fi

pathDir=$(dirname "$path");
if [ ! -d "$pathDir" ];
then mkdir -p "$pathDir";
fi

if [ -d "$3" ];
then cd "$3";
fi

git --no-pager log --pretty="format:[START commit][author=%an][time=%at][message=%s][hash=%H]" --shortstat > "${path}.txt";

oldPWD="$PWD";

if [ -d "$shared" ];
then cd "$shared";
else cd "$pathDir";
fi

if [ -d "$1" ];
then template="$1";
else 
  curl "http://timeme.eclipselabs.org.codespot.com/git/scripts/github.tar.gz" | tar xz;
  template="github";
fi

if [ ! -z "$2" ];
then title="$2";
fi

if [ -z "$template" ] || [ -z "$title" ];
then echo "Error"; exit 1;
fi

python2 "gitbook.py" "$template" "$title" "${path}.txt" "${path}.html";
wkhtmltopdf -s Letter -b "${path}.html" "${path}.pdf";
cd "$oldPWD";
evince "${path}.pdf";

### END ###
