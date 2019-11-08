originFilenameWithoutEnding=$(echo "$1" | cut -f 1 -d '.')

firstFilename="$originFilenameWithoutEnding-0.png"
magick convert "$originFilenameWithoutEnding.png" -gravity center -crop 1500x2100 $firstFilename

magick convert $firstFilename -resize 2000x2800\! "$originFilenameWithoutEnding-final.png"
rm $firstFilename
