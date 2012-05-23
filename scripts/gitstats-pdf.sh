#!/bin/bash
#######################
### gitstats-pdf ######
#######################
version="0.2" #########
### Team 0x00000001 ###
#######################

##############
## Config
inputDir="$HOME/project";
tmpDir="/dev/shm/stats";
outputDir="/dev/shm/";

## Valid colors are
#color="black"
color="blue"
#color="green"
#color="purple"
#color="red"
#color="pink"
#color="orange"
#color="yellow"
#color="white"

##############
## Functions
exit_script()
{
  echo "$1 failed, exiting"; exit 1;
}

check_depends()
{
  for depend in $depends;
  do
    ifcheck=$(which $depend 2>/dev/null);
    if [ -z "$ifcheck" ] && [ "$depend" = "convert" ];
    then echo "Missing dependency: imagemagick not installed"; missing="true";
    else 
      if [ -z "$ifcheck" ]; 
      then echo "Missing dependency: $depend not installed"; missing="true";
      fi;
    fi;
  done;
  if [ "$missing" = "true" ]; 
  then echo "FATAL ERROR: install missing package(s)"; exit 1;
  fi;
}

color_map()
{
  if [ -z "$color" ];
  then exit_script "Color map";
  else
    if [ "$color" = "black" ];
    then red="0"; green="0"; blue="0";
  else
    if [ "$color" = "blue" ];
    then red="0"; green="0"; blue="255";
  else
    if [ "$color" = "green" ];
    then red="0"; green="255"; blue="0";
  else
    if [ "$color" = "purple" ];
    then red="127"; green="0"; blue="255";
  else
    if [ "$color" = "red" ];
    then red="127"; green="0"; blue="255";
  else
    if [ "$color" = "pink" ];
    then red="127"; green="0"; blue="255";
  else
    if [ "$color" = "orange" ];
    then red="127"; green="0"; blue="255";
  else
    if [ "$color" = "yellow" ];
    then red="127"; green="0"; blue="255";
  else
    if [ "$color" = "white" ];
    then red="127"; green="0"; blue="255";
    fi; fi; fi; fi; fi; fi; fi; fi; fi;
  fi;
}


##############
## Check for dependencies
depends="convert evince gitstats sed wkhtmltopdf";
check_depends;
color_map;

## Sanity check
if [ ! -d "$inputDir" ];
then echo "$inputDir does not exist"; exit 1;
fi

if [ ! -d "$tmpDir" ];
then mkdir "$tmpDir"; [ -d "$tmpDir" ] || exit_script "Sanity check";
fi

if [ ! -d "$outputDir" ];
then mkdir "$outputDir"; [ -d "$outputDir" ] || exit_script "Sanity check";
fi
  
##############
## Generate statistics
cd "$inputDir";
echo "#############################";
echo "### Generating statistics ###";
echo "#############################";
gitstats . "$tmpDir" || exit_script "gitstats";
echo -e "[DONE]\n\n";
cd "$tmpDir";

## Recolor tables
echo "#########################";
echo "### Recoloring tables ###";
echo "#########################";
#sed -i "s/rgb([[:digit:]]*, /rgb($red, /g" "$tmpDir/activity.html" || exit_script "sed";
sed -i "s/rgb([[:digit:]]/rgb(/g" "$tmpDir/activity.html" || exit_script "sed";
sed -i "s/, [[:digit:]]*, [[:digit:]]*)/, $green, $blue)/g" "$tmpDir/activity.html" || exit_script "sed";
sed -i "s/background-color: red/background-color: $color/g" "$tmpDir/activity.html" || exit_script "sed";
echo -e "[DONE]\n\n";

## Recolor graphs
echo "#########################";
echo "### Recoloring graphs ###";
echo "#########################";
IFS=$(echo -en "\n\b");
for i in $(ls "$tmpDir"/*.png);
do
  convert -fill $color -opaque red "$i" "${i}_" && mv "${i}_" "$i" || exit_script "imagemagick";
done;
echo -e "[DONE]\n\n";

## Convert to PDF
echo "#########################";
echo "### Converting to PDF ###";
echo "#########################";
p1="$tmpDir/index.html";
p2="$tmpDir/activity.html";
p3="$tmpDir/authors.html";
p4="$tmpDir/files.html";
p5="$tmpDir/lines.html";
p6="$tmpDir/tags.html";

## Sanity check
if [ ! -f "$p1" ] || [ ! -f "$p2" ] || [ ! -f "$p3" ] || [ ! -f "$p4" ] || [ ! -f "$p5" ] || [ ! -f "$p6" ];
then echo "Missing file(s) in $tmpDir"; exit 1;
fi

wkhtmltopdf -s Letter -b "$p1" "$p2" "$p3" "$p4" "$p5" "$p6" "$outputDir/output.pdf" || exit_script "wkhtmltopdf";
echo -e "[DONE]\n\n";

## Open PDF for printing
evince "$outputDir/output.pdf" > /dev/null 2>&1 &

### END ###
