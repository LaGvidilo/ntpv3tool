#coding: utf-8
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
                    type=str)
parser.add_argument("PATHTWO", help="chemin images",
                    type=str)
args = parser.parse_args()
l = args.PATH
l2 = args.PATHTWO
try:
    img = Image.open(l2)
    img = img.resize((args.X*2,args.Y*2), Image.ANTIALIAS)
    img.save(l2)
except:
    try:
        img = Image.open(l2)
        img = img.resize((args.X*2,args.Y*2), Image.ANTIALIAS)
        img.convert("RGB")
        img.save(l2)
    except Exception as e:
        #os.remove(l)
        print("ERROR: "+str(l2))
        print(e)

