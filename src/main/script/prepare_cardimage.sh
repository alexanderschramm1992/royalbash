originFilenameWithoutEnding=$(echo "$1" | cut -f 1 -d '.')

originFilename="$originFilenameWithoutEnding.png"

magick convert $originFilename -gravity center -crop 2200x2900 $originFilename
firstFilename="$originFilenameWithoutEnding-0.png"
rm "$originFilenameWithoutEnding-1.png"
rm "$originFilenameWithoutEnding-2.png"
rm "$originFilenameWithoutEnding-3.png"

magick convert $firstFilename -resize 1900x2500\! "$originFilenameWithoutEnding-0.png"
secondFilename="$originFilenameWithoutEnding-0.png"

magick convert $secondFilename -gravity center -crop 1860x2280 "$originFilenameWithoutEnding-final.png"
rm "$originFilenameWithoutEnding-0.png"
rm "$originFilenameWithoutEnding-final-1.png"
rm "$originFilenameWithoutEnding-final-2.png"
rm "$originFilenameWithoutEnding-final-3.png"
