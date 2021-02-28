#coding: utf-8
import autoload as al 
from PIL import Image
import os
import argparse
import glob
parser = argparse.ArgumentParser()
parser.add_argument("X", help="taille X",
                    type=int)
parser.add_argument("Y", help="taille Y",
                    type=int)
parser.add_argument("PATH", help="chemin images",
                    type=int)

for l in glob.glob(parser.PATH+"/*.png"):
	try:
		img = Image.open(l)
		img = img.resize((parser.X,parser.Y), Image.ANTIALIAS)
		img.save(l) 
	except:
		try:
			img = Image.open(l)
			img = img.resize((parser.X,parser.Y), Image.ANTIALIAS)
			img.convert("RGB")
			img.save(l)
		except:
			#os.remove(l)
			print("ERROR: "+str(l))

for l in glob.glob(parser.PATH+"/*.jpg"):
	try:
		img = Image.open(l)
		img = img.resize((parser.X,parser.Y), Image.ANTIALIAS)
		img.save(l) 
	except:
		try:
			img = Image.open(l)
			img = img.resize((parser.X,parser.Y), Image.ANTIALIAS)
			img.convert("RGB")
			img.save(l)
		except:
			#os.remove(l)
			print("ERROR: "+str(l))
