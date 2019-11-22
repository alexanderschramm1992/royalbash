originImageFilenameWithoutEnding=$(echo "$1" | cut -f 1 -d '.')
originFrameFilenameWithoutEnding=$(echo "$2" | cut -f 1 -d '.')
originSlotFilenameWithoutEnding=$(echo "$3" | cut -f 1 -d '.')

sh prepare_cardframe.sh "$originFrameFilenameWithoutEnding.png"
finalFrameFile="$originFrameFilenameWithoutEnding-final.png"

sh prepare_cardimage.sh "$originImageFilenameWithoutEnding.png"
finalImageFile="$originImageFilenameWithoutEnding-final.png"

magick composite -compose Dst_Over -gravity center $finalImageFile $finalFrameFile "$originImageFilenameWithoutEnding-framed-image.png"

framedImage="$originImageFilenameWithoutEnding-framed-image.png"

magick composite -compose Over -gravity NorthEast -geometry +50+20 "$originSlotFilenameWithoutEnding.png" $framedImage "$originImageFilenameWithoutEnding-card.png"

rm $finalImageFile
rm $finalFrameFile
rm $framedImage
