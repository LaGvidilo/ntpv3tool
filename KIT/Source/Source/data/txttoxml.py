#coding: utf-8


import argparse

parser = argparse.ArgumentParser(description='Process some files.')
parser.add_argument('filename', metavar='f', type=str, nargs='+',
                    help='files names')

args = parser.parse_args()

g = open("super.xml","w+b")
for ii in args.filename:
	f = open(ii,"r+b")

	data = f.read()
	g.write('<string name="'+ii.split(".")[0]+'">')
	g.write(data.replace("\n","\\n"))
	g.write('</string>\n')
	f.close()
g.close()
