#coding: utf-8
from gtts import gTTS
import os
import argparse
parser = argparse.ArgumentParser()
parser.add_argument('filename')
args = parser.parse_args()

with open(args.filename,"r") as file:
  filedata = file.read()

tts = gTTS(text=filedata, lang='fr')
tts.save(args.filename.split(".")[0]+".mp3")