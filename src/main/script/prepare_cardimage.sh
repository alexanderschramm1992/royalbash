originFilenameWithoutEnding=$(echo "$1" | cut -f 1 -d '.')

originFilename="$originFilenameWithoutEnding.png"

magick convert $originFilename -gravity center -crop 2200x2900 $originFilename
firstFilename="$originFilenameWithoutEnding.png"

magick convert $firstFilename -gravity center -crop 2000x2800 $firstFilename
secondFilename="$originFilenameWithoutEnding-0.png"
rm "$originFilenameWithoutEnding-1.png"
rm "$originFilenameWithoutEnding-2.png"
rm "$originFilenameWithoutEnding-3.png"

magick convert $secondFilename -resize 1950x2700\! "$originFilenameWithoutEnding-final.png"
rm "$originFilenameWithoutEnding-0.png"
