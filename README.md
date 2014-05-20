# meisterschueler-java

This software enables you to analyze your piano performance and help you with your daily exercises.

## The target
* You switch on your piano
* You connect your android with the piano... maybe with help of IOIO, Arduino or via Bluetooth
* The Android plays a sound that signalises you, it is ready and listens to your piano performance
* You open your scorebook and play
* The most important: the software is "smart" enough to recognize the song, the key, the tempo, the hand, etc.! YOU play as YOU like! No interaction necessary!
* When you released the last key of the song, the android gives you a detailled feedback (e.g. the Hanon and Brahms etudes have a fixed fingering. With these fingering information you can see, your ring finger is quite weak and the thumb holds the key too long)
* After your excercise session you can look into the internet to see and compare your progression 

## Current situation
* Wrote many tests
* Connection works with MIDI-to-USB-Cable and Desktop PC
* You can play every song in each key, the software recognizes the transposition
* Every performance is send to a "Google App Engine" internet site for comparison an deeper analysis for musical repartoire see Repertoire 

## What this Software doesnt do
* It shows you scrolling or falling colored things you have to catch
* If you play a wrong note it stops and wait until you press the right key
* Teaches you a) how to read notes b) the difference between major and minor
* It forces you to repeat a song until you managed it to pressed all keys in the right moment
* You play a stupid c major scale and the software accompany you with a huge orchestra and make standing ovations if you manage it to play the scale 

## Q&A

Q: Why do I need a MIDI Keyboard?

A: Well, I know that a real piano is much more fun to play. But the reason is simple: the accuracy of the MIDI-Signal is much more accurate than a FFT can be. 
