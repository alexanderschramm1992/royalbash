originImageFilenameWithoutEnding=$(echo "$1" | cut -f 1 -d '.')
originLabelFilename="cardlabel.png"
originFrameFilename="cardframe.png"
originSlotFilename="cardslot.png"
originCreatureSlotFilename="creatureslot.png"

sh prepare_cardimage.sh "$originImageFilenameWithoutEnding.png"
finalImageFile="$originImageFilenameWithoutEnding-final-0.png"

magick composite -compose Dst_Over -gravity center $finalImageFile $originFrameFilename "$originImageFilenameWithoutEnding-card.png"

magick composite -compose Over -gravity NorthEast -geometry +50+30 $originSlotFilename "$originImageFilenameWithoutEnding-card.png" "$originImageFilenameWithoutEnding-card.png"

magick composite -compose Over -gravity NorthWest -geometry +50+30 $originSlotFilename "$originImageFilenameWithoutEnding-card.png" "$originImageFilenameWithoutEnding-card.png"

magick composite -compose Over -gravity South -geometry +0+110 $originLabelFilename "$originImageFilenameWithoutEnding-card.png" "$originImageFilenameWithoutEnding-card.png"

rm $finalImageFile
