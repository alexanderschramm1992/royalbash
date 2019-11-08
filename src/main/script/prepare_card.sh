originImageFilenameWithoutEnding=$(echo "$1" | cut -f 1 -d '.')
originFrameFilenameWithoutEnding=$(echo "$2" | cut -f 1 -d '.')

sh prepare_cardframe.sh "$originFrameFilenameWithoutEnding.png"
finalFrameFile="$originFrameFilenameWithoutEnding-final.png"

sh prepare_cardimage.sh "$originImageFilenameWithoutEnding.png"
finalImageFile="$originImageFilenameWithoutEnding-final.png"

magick composite -compose Dst_Over -gravity center $finalImageFile $finalFrameFile "$originImageFilenameWithoutEnding-card.png"

rm $finalImageFile
rm $finalFrameFile
