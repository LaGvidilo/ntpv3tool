#coding: utf-8
from gtts import gTTS
import os
import argparse
parser = argparse.ArgumentParser()
parser.add_argument('filename')
parser.add_argument('filepth')
args = parser.parse_args()

with open(args.filepth+"/txt.tmp","r") as file:
  filedata = file.read()

tts = gTTS(text=filedata.decode('utf-8'), lang='fr')
tts.save(args.filename)
